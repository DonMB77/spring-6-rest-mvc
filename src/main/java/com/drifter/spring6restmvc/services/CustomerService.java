package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer savedCustomer);

    void updateById(Integer customerId, Customer customer);
}
