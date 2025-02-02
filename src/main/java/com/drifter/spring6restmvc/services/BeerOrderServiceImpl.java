package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.BeerOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BeerOrderServiceImpl implements BeerOrderService {

    private Map<UUID, BeerOrderDTO> beerOrderMap;

    public BeerOrderServiceImpl() {
        this.beerOrderMap = new HashMap<>();
    }

    @Override
    public Page<BeerOrderDTO> listBeers(String customerRef, Integer pageNumber, Integer pageSize) {
        return new PageImpl<>(new ArrayList<>(beerOrderMap.values()));
    }

    @Override
    public Optional<BeerOrderDTO> getBeerById(UUID id) {
        return Optional.of(beerOrderMap.get(id));
    }
}
