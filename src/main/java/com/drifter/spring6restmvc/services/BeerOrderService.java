package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.entities.BeerOrder;
import guru.springframework.spring6restmvcapi.model.BeerOrderCreateDTO;
import guru.springframework.spring6restmvcapi.model.BeerOrderDTO;
import guru.springframework.spring6restmvcapi.model.BeerOrderUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BeerOrderService {

    Page<BeerOrderDTO> listBeerOrders(Integer pageNumber, Integer pageSize);

    Optional<BeerOrderDTO> getBeerOrderById(UUID id);

    BeerOrder saveNewBeerOrder(BeerOrderCreateDTO beer);

    BeerOrderDTO updateOrder(UUID beerOrderId, BeerOrderUpdateDTO beerOrderUpdateDTO);

    void deleteOrderById(UUID beerOrderId);

    //Optional<BeerOrderDTO> patchBeerById(UUID beerOrderId, BeerOrderDTO beer);
}
