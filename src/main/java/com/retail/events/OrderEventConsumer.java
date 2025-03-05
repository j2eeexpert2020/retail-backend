package com.retail.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    
    @KafkaListener(topics = "order-events", groupId = "order-group")
    public void consumeOrderEvent(String orderEvent) {
        System.out.println("Received Order Event: " + orderEvent);
        // Process order event logic here
    }
}
