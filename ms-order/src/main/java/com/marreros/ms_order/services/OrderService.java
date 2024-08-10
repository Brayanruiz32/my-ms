package com.marreros.ms_order.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marreros.ms_order.dto.OrderDTO;
import com.marreros.ms_order.models.Order;
import com.marreros.ms_order.models.Product;
import com.marreros.ms_order.repositories.OrderRepository;
import com.marreros.ms_order.repositories.ProductClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderRepository orderRepository;
    
    public List<Order> findAll() {     
        return orderRepository.findAll();
    }

    public Order findById(Long id) {     
        return orderRepository.findById(id).orElseThrow();
    }

    public Order create(OrderDTO orderDTO){
        log.info("ingresando a consultar por el producto");
        Product product = productClient.findById(orderDTO.getIdProducto()).orElseThrow();

        var newOrder = Order.builder()
        .productoId(product.getId())
        .nombre(product.getNombre())
        .cantidad(orderDTO.getCantidad())
        .build();

        return orderRepository.save(newOrder);
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
