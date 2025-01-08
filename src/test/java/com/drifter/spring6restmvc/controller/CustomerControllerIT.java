package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.entities.Beer;
import com.drifter.spring6restmvc.entities.Customer;
import com.drifter.spring6restmvc.model.BeerDTO;
import com.drifter.spring6restmvc.model.CustomerDTO;
import com.drifter.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.PATH;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testGetAllCustomers () {
        List<CustomerDTO> customerDTOSList = new ArrayList<>();
        customerDTOSList = customerController.getAllCustomers();

        assertThat(customerDTOSList.size()).isEqualTo(3);
    }

    @Test
    void failedTestGetSingleCustomerById () {
        assertThrows(NotFoundException.class, () -> {
            customerController.getSingleCustomerById(UUID.randomUUID());
        });
    }

    @Test
    void testGetSingleCustomerById () {
        Customer customer = customerRepository.findAll().getFirst();

        CustomerDTO customerDTO = customerController.getSingleCustomerById(customer.getId());

        assertThat(customerDTO).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void emptyTestGetAllCustomers () {
        customerRepository.deleteAll();
        assertThat(customerRepository.count()).isEqualTo(0);
    }
}