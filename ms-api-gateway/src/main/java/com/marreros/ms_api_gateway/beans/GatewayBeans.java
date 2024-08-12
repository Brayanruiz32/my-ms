package com.marreros.ms_api_gateway.beans;

import java.util.Set;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeans {

    @Bean
    public RouteLocator routeLocatorEurekaOnCB(RouteLocatorBuilder builder){
        return builder
        .routes()
        .route(route -> route
            .path("/ms-product/product/**")
            .filters(filter -> {
                filter.circuitBreaker(config -> config
                .setName("gateway-cb")
                .setStatusCodes(Set.of("500", "400"))
                .setFallbackUri("forward:/ms-product-fallback/product/*"));
                return filter;
            })
            .uri("http://localhost:8081")
            // .uri("lb://ms-product")
        )
        .route(route -> route
            .path("/ms-order/order/**")
            .uri("http://localhost:8085")
            // .uri("lb://ms-order")
        )
        .route(route -> route
            .path("/ms-product-fallback/product/**")
            .uri("lb://ms-product-fallback")
        )
        .build();
    }
}
