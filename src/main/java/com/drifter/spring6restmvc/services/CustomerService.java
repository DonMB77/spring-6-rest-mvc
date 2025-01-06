package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getCustomerById(UUID id);

    CustomerDTO saveCustomer(CustomerDTO savedCustomerDTO);

    void updateById(UUID customerId, CustomerDTO customerDTO);

    void deleteById(UUID customerId);

    void patchById(UUID customerId, CustomerDTO customerDTO);
}
