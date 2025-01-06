package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Integer id);

    Customer saveCustomer(Customer savedCustomer);

    void updateById(Integer customerId, Customer customer);

    void deleteById(Integer customerId);

    void patchById(Integer customerId, Customer customer);
}
