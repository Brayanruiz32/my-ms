package com.marreros.ms_order.beans;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadBalancerConfiguration {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context){
        log.info("Configuring Load balancer");
        return ServiceInstanceListSupplier.builder()
        .withBlockingDiscoveryClient()
        .build(context);
    }

}
