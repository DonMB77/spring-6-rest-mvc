package com.drifter.spring6restmvc.listeners;

import com.drifter.spring6restmvc.config.KafkaConfig;
import guru.springframework.spring6restmvcapi.events.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OrderPlacedKafkaListener {
    AtomicInteger messageCounter = new AtomicInteger(0);

    @KafkaListener(groupId = "KafkaIntegrationTest", topics = KafkaConfig.ORDER_PLACED_TOPIC)
    public void retrieve(OrderPlacedEvent orderPlacedEvent) {
        System.out.println("Received Message: " + orderPlacedEvent);
        messageCounter.incrementAndGet();
    }
}
