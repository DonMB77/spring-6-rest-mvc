package com.drifter.spring6restmvc.repositories;

import com.drifter.spring6restmvc.entities.Beer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@Testcontainers
@SpringBootTest
@ActiveProfiles("localmysql")
public class MySqlIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:9");

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeers() {
        List<Beer> beers = beerRepository.findAll();

        assertThat(beers.size()).isGreaterThan(0);
    }
}
