package com.drifter.spring6restmvc.repositories;

import com.drifter.spring6restmvc.bootstrap.BootstrapData;
import com.drifter.spring6restmvc.entities.Beer;
import com.drifter.spring6restmvc.model.BeerStyle;
import com.drifter.spring6restmvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerListByName() {
        Page<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%", null);

        assertThat(list.getContent().size()).isEqualTo(336);
    }

    @Test
    void testSaveBeerNameTooLong() {
        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("This is a test beer with a very long name. In this case more than the constraint of 50 characters. Oh wow!")
                    .beerStyle(BeerStyle.PILSNER)
                    .upc("23456")
                    .price(BigDecimal.valueOf(14.99))
                    .build());

            beerRepository.flush();
        });
    }

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