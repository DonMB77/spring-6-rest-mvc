package com.drifter.spring6restmvc.listeners;

import guru.springframework.spring6restmvcapi.events.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderPlacedListener {

    @Async
    @EventListener
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        System.out.println("Order Placed Event Received");
    }
}
