package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customerMap;

    public CustomerServiceImpl() {
        Random random1 = new Random();
        customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(random1.nextInt(1,100+1))
                .costumerName("customer1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(random1.nextInt(1,100+1))
                .costumerName("customer2")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .id(random1.nextInt(1,100+1))
                .costumerName("customer3")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerMap.get(id);
    }
}
