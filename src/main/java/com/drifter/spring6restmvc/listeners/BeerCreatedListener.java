package com.drifter.spring6restmvc.listeners;

import com.drifter.spring6restmvc.events.BeerCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BeerCreatedListener {

    @EventListener
    public void listen(BeerCreatedEvent beerCreatedEvent) {
        System.out.println("BeerCreatedListener - I heard a beer was created!");
        System.out.println(beerCreatedEvent.getBeer().getId());
    }
}
