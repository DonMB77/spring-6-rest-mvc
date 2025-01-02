package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);
}
