package com.drifter.spring6restmvc.repositories;

import com.drifter.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvcapi.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    Page<Beer> findAllByBeerNameIsLikeIgnoreCase(String beerName, Pageable pageable);
    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, org.springframework.data.domain.Pageable pageable);
    Page<Beer> findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle, org.springframework.data.domain.Pageable pageable);
}
