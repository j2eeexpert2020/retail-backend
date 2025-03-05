package com.retail.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendOrderEvent(String orderEvent) {
        kafkaTemplate.send("order-events", orderEvent);
    }
}
