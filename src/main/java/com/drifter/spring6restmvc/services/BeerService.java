package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.BeerDTO;
import com.drifter.spring6restmvc.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory);

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    Optional<BeerDTO> updateBeerById(UUID beerUuid, BeerDTO beerDTO);

    Boolean deleteById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerUuid, BeerDTO beerDTO);
}
