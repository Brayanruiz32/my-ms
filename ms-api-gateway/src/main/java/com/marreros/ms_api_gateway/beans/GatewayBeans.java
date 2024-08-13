package com.marreros.ms_api_gateway.beans;

import java.util.Set;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marreros.ms_api_gateway.filters.AuthFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class GatewayBeans {

    private final AuthFilter authFilter;

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
                filter.filter(this.authFilter);
                return filter;
            })
            .uri("http://localhost:8081")
            // .uri("lb://ms-product")
        )
        .route(route -> route
            .path("/ms-order/order/**")
            .filters(filter -> filter.filter(this.authFilter))
            .uri("http://localhost:8085")
            // .uri("lb://ms-order")
        )
        .route(route -> route
            .path("/ms-product-fallback/product/**")
            .uri("lb://ms-product-fallback")
        )
        .route(route -> route
            .path("/auth-server/auth/**")
            .uri("http://localhost:3030")
            // .uri("lb://ms-product-fallback")
        )
        .build();
    }
}
