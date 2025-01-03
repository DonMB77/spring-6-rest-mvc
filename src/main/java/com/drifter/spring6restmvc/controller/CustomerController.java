package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.model.Customer;
import com.drifter.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(value = "{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity handleDeleteRequest(@PathVariable("customerId") Integer customerId, Customer customer) {

        customerService.deleteById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{customerId}")
    public ResponseEntity handlePutRequest (@PathVariable("customerId") Integer customerId, @RequestBody Customer customer) {

        customerService.updateById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePostRequest (@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    private List<Customer> getAllCustomers () {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    private Customer getSingleCustomerById (@PathVariable("customerId") Integer id) {
        return customerService.getCustomerById(id);
    }
}
