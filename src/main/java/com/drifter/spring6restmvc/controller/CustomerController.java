package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.model.CustomerDTO;
import com.drifter.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @RequestMapping(value = CUSTOMER_PATH_ID, method = RequestMethod.PATCH)
    public ResponseEntity handlePatchRequest(@PathVariable("customerId") Integer customerId, @RequestBody CustomerDTO customerDTO) {

        customerService.patchById(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = CUSTOMER_PATH_ID, method = RequestMethod.DELETE)
    public ResponseEntity handleDeleteRequest(@PathVariable("customerId") Integer customerId, CustomerDTO customerDTO) {

        customerService.deleteById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = CUSTOMER_PATH_ID)
    public ResponseEntity handlePutRequest (@PathVariable("customerId") Integer customerId, @RequestBody CustomerDTO customerDTO) {

        customerService.updateById(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = CUSTOMER_PATH, method = RequestMethod.POST)
    public ResponseEntity handlePostRequest (@RequestBody CustomerDTO customerDTO) {

        CustomerDTO savedCustomerDTO = customerService.saveCustomer(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomerDTO.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = CUSTOMER_PATH, method = RequestMethod.GET)
    private List<CustomerDTO> getAllCustomers () {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = CUSTOMER_PATH_ID, method = RequestMethod.GET)
    private CustomerDTO getSingleCustomerById (@PathVariable("customerId") Integer id) {
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }
}
