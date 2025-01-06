package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    void updateBeerById(UUID beerUuid, BeerDTO beerDTO);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerUuid, BeerDTO beerDTO);
}
