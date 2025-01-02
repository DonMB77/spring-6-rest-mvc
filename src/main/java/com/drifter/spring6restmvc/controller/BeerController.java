package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.model.Beer;
import com.drifter.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class BeerController {
    private final BeerService beerService;

    @RequestMapping("/api/v1/beer")
    public List<Beer> listBears() {
        return beerService.listBeers();
    }

    public Beer getBeerById(UUID id) {

        log.debug("Get beer by Id - in controller.");

        return beerService.getBeerById(id);
    }
}
