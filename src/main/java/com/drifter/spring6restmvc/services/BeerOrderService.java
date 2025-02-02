package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.entities.BeerOrder;
import com.drifter.spring6restmvc.model.BeerOrderCreateDTO;
import com.drifter.spring6restmvc.model.BeerOrderDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BeerOrderService {

    Page<BeerOrderDTO> listBeerOrders(Integer pageNumber, Integer pageSize);

    Optional<BeerOrderDTO> getBeerOrderById(UUID id);

    BeerOrder saveNewBeerOrder(BeerOrderCreateDTO beer);

    //Optional<BeerOrderDTO> updateBeerById(UUID beerOrderId, BeerOrderDTO beer);

    //Boolean deleteById(UUID beerOrderId);

    //Optional<BeerOrderDTO> patchBeerById(UUID beerOrderId, BeerOrderDTO beer);
}
