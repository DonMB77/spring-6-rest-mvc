package com.drifter.spring6restmvc.listeners;

import com.drifter.spring6restmvc.events.BeerCreatedEvent;
import com.drifter.spring6restmvc.mappers.BeerMapper;
import com.drifter.spring6restmvc.repositories.BeerAuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerCreatedListener {

    private final BeerMapper beerMapper;
    private final BeerAuditRepository beerAuditRepository;

    @Async
    @EventListener
    public void listen(BeerCreatedEvent beerCreatedEvent) {

        val beerAudit = beerMapper.beerToBeerAudit(beerCreatedEvent.getBeer());
        beerAudit.setAuditEventType("BEER_CREATED");

        if (beerCreatedEvent.getAuthentication() != null && beerCreatedEvent.getAuthentication().getName() != null) {
            beerAudit.setPrincipalName(beerCreatedEvent.getAuthentication().getName());
        }

        val savedBeerAudit = beerAuditRepository.save(beerAudit);
        log.debug("Beer Audit Saved: {}", savedBeerAudit.getId());
    }
}
