package com.marreros.ms_order.repositories;

import java.util.Optional;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.marreros.ms_order.beans.LoadBalancerConfiguration;
import com.marreros.ms_order.models.Product;

@FeignClient(name = "ms-product")
@LoadBalancerClient(name = "ms-product", configuration = LoadBalancerConfiguration.class)
public interface ProductFeignClient {

    @GetMapping("/ms-product/product/{id}")
    Optional<Product> findById(@PathVariable Long id);

}
