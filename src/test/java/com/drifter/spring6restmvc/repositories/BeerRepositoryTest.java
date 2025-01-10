package com.drifter.spring6restmvc.repositories;

import com.drifter.spring6restmvc.entities.Beer;
import com.drifter.spring6restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My new beer")
                        .beerStyle(BeerStyle.PILSNER)
                        .upc("23456")
                        .price(BigDecimal.valueOf(14.99))
                .build());

        // here the persistence kicks in
        beerRepository.flush();
        //asserting that we have a beer present in the context
        assertThat(savedBeer).isNotNull();
        //asserting that the id of that beer is indeed not null
        assertThat(savedBeer.getId()).isNotNull();
    }
}