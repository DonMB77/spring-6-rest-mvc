package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.BeerOrderDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public class BeerOrderServiceImpl implements BeerOrderService {
    @Override
    public Page<BeerOrderDTO> listBeers(String customerRef) {
        return null;
    }

    @Override
    public Optional<BeerOrderDTO> getBeerById(UUID id) {
        return Optional.empty();
    }
}
