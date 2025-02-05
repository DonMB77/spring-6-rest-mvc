package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.entities.Customer;
import com.drifter.spring6restmvc.mappers.CustomerMapper;
import com.drifter.spring6restmvc.repositories.BeerOrderRepository;
import com.drifter.spring6restmvc.repositories.CustomerRepository;
import guru.springframework.spring6restmvcapi.model.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Transactional
    @Rollback
    @Test
    void testPatchById() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .costumerName("New Costumer")
                .build();
    }

    @Transactional
    @Rollback
    @Test
    void testPutById () {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCostumerName(customerName);

        ResponseEntity responseEntity = customerController.handlePutRequest(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCostumerName()).isEqualTo(customerName);
    }

    @Transactional
    @Rollback
    @Test
    void testHandlePostRequest () {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .costumerName("New Costumer")
                .build();

        ResponseEntity responseEntity = customerController.handlePostRequest(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(savedUUID).get();
        assertThat(customer).isNotNull();
    }

    @Test
    void deleteByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.handleDeleteRequest(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void deleteByIdFound() {
        Customer customer = customerRepository.findAll().getFirst();

        ResponseEntity responseEntity = customerController.handleDeleteRequest(customer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(customerRepository.findById(customer.getId())).isEmpty();
    }

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
        beerOrderRepository.deleteAll();

        customerRepository.deleteAll();
        assertThat(customerRepository.count()).isEqualTo(0);
    }
}