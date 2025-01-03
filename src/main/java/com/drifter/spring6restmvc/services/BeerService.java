package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerUuid, Beer beer);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerUuid, Beer beer);
}
