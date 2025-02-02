package com.drifter.spring6restmvc.listeners;

import com.drifter.spring6restmvc.events.BeerCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class BeerCreatedListener {

    @Async
    @EventListener
    public void listen(BeerCreatedEvent beerCreatedEvent) {
        System.out.println("BeerCreatedListener - I heard a beer was created!");
        System.out.println(beerCreatedEvent.getBeer().getId());

        System.out.println("Current Thread Name: " + Thread.currentThread().getName());
        System.out.println("Current Thread ID: " + Thread.currentThread().threadId());
    }
}
