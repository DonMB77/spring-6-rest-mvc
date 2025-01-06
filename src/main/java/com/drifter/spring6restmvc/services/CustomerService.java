package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getCustomerById(Integer id);

    CustomerDTO saveCustomer(CustomerDTO savedCustomerDTO);

    void updateById(Integer customerId, CustomerDTO customerDTO);

    void deleteById(Integer customerId);

    void patchById(Integer customerId, CustomerDTO customerDTO);
}
