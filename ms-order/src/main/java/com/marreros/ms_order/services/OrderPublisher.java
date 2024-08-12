package com.marreros.ms_order.services;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import com.marreros.ms_order.models.Order;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderPublisher {

    
    private StreamBridge streamBridge;


    public void sendMessage(Order order){
        this.streamBridge.send("orderListener", order.toString());
        this.streamBridge.send("orderListener-in-0", order.toString());
        this.streamBridge.send("orderListener-out-0", order.toString());
    }

}
