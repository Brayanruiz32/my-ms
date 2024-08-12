package com.marreros.ms_order.services;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderPublisher {
    
    private StreamBridge streamBridge;

    public void sendMessage(String order){
        // this.streamBridge.send("orderListener", order);
        // this.streamBridge.send("orderListener-in-0", order);
        // this.streamBridge.send("orderListener-out-0", order);
        this.streamBridge.send("orderListener", order);
    }

}
