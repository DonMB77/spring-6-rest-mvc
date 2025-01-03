package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.model.Beer;
import com.drifter.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @RequestMapping(value = "{beerId}", method = RequestMethod.PATCH)
    public ResponseEntity updatePatchById(@PathVariable("beerId") UUID beerUuid, @RequestBody Beer beer) {

        beerService.patchBeerById(beerUuid, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId, Beer beer) {

        beerService.deleteById(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.PUT)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerUuid, @RequestBody Beer beer) {

        beerService.updateBeerById(beerUuid, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody Beer beer) {

        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> listBears() {
        return beerService.listBeers();
    }

    @RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get beer by Id - in controller.");

        return beerService.getBeerById(beerId);
    }
}
