package com.marreros.ms_order.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.marreros.ms_order.dto.OrderDTO;
import com.marreros.ms_order.models.Order;
import com.marreros.ms_order.models.Product;
import com.marreros.ms_order.repositories.OrderRepository;
import com.marreros.ms_order.repositories.ProductFeignClient;
import com.marreros.ms_order.repositories.ProductWebClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private ProductFeignClient productClient;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductWebClient productWebClient;

    @Autowired
    private Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private OrderPublisher orderPublisher;



    public List<Order> findAll() {     
        return orderRepository.findAll();
    }

    public Order findById(Long id) {     
        return orderRepository.findById(id).orElseThrow();
    }

    public Order create(OrderDTO orderDTO){
        var circuitBreaker = circuitBreakerFactory.create("product-circuitbreaker");
        return circuitBreaker.run(
            () -> createMain(orderDTO),
            throwable -> createFallback(orderDTO, throwable)
        );
    }



    public Order createMain(OrderDTO orderDTO){
        log.info("ingresando a consultar por el producto");
        Product product = productClient.findById(orderDTO.getIdProducto()).orElseThrow();

        var newOrder = Order.builder()
        .productoId(product.getId())
        .nombre(product.getNombre())
        .cantidad(orderDTO.getCantidad())
        .build();
        log.info("enviando el mensaje mediante kafka desde order");
        this.orderPublisher.sendMessage(newOrder);

        return orderRepository.save(newOrder);
    }

    public Order createFallback(OrderDTO orderDTO, Throwable error){
        log.warn(error.getMessage());
        log.info("ingresando a consultar en el fallback por el producto");
        
        Product product = productWebClient.getProductById(orderDTO.getIdProducto());

        var newOrder = Order.builder()
        .productoId(product.getId())
        .nombre(product.getNombre())
        .cantidad(orderDTO.getCantidad())
        .build();

        return newOrder;
    }

    public Order update(Long id, OrderDTO orderDTO) {     
        Product product = productClient.findById(orderDTO.getIdProducto()).orElseThrow();
        Order order = orderRepository.findById(id).orElseThrow();

        order.setCantidad(orderDTO.getCantidad());
        order.setProductoId(product.getId());
        return orderRepository.save(order);
    }

    public void delete(Long id) {     
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
    }
}
