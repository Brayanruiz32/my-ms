package com.marreros.order_listener;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class OrderListener {

    @Bean
    public Consumer<String> consumerOrder(){
        return order ->{
            log.info("estoy consumiendo desde el order {}", order);
        };
    }

}
