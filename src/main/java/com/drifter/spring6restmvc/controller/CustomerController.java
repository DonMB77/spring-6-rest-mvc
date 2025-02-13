package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.services.CustomerService;
import guru.springframework.spring6restmvcapi.model.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @RequestMapping(value = CUSTOMER_PATH_ID, method = RequestMethod.PATCH)
    public ResponseEntity handlePatchRequest(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO) {

        customerService.patchById(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = CUSTOMER_PATH_ID, method = RequestMethod.DELETE)
    public ResponseEntity handleDeleteRequest(@PathVariable("customerId") UUID customerId) {

        if (! customerService.deleteById(customerId)) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = CUSTOMER_PATH_ID)
    public ResponseEntity handlePutRequest (@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO) {

        customerService.updateById(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = CUSTOMER_PATH, method = RequestMethod.POST)
    public ResponseEntity handlePostRequest (@Validated @RequestBody CustomerDTO customerDTO) {

        CustomerDTO savedCustomerDTO = customerService.saveCustomer(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomerDTO.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = CUSTOMER_PATH, method = RequestMethod.GET)
    public List<CustomerDTO> getAllCustomers () {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = CUSTOMER_PATH_ID, method = RequestMethod.GET)
    public CustomerDTO getSingleCustomerById (@PathVariable("customerId") UUID id) {
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }
}
