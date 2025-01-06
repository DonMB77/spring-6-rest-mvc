package com.drifter.spring6restmvc.repositories;

import com.drifter.spring6restmvc.entities.Customer;
import com.drifter.spring6restmvc.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void createNewCustomer() {
        Customer newCustomer = customerRepository.save(Customer.builder()
                .costumerName("Example Name new Customer")
                .build());

        assertThat(newCustomer).isNotNull();
        assertThat(newCustomer.getId()).isNotNull();
    }
}