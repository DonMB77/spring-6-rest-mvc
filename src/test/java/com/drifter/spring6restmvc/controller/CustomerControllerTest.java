package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.model.Customer;
import com.drifter.spring6restmvc.services.CustomerService;
import com.drifter.spring6restmvc.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    @MockitoBean
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        customerServiceImpl  = new CustomerServiceImpl();
    }

    @Test
    void testUpdateById () throws Exception {
        Customer customerToBeUpdated = customerServiceImpl.getAllCustomers().getFirst();

        mockMvc.perform(put("/api/v1/customer" + customerToBeUpdated.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerToBeUpdated)))
                .andExpect(status().isNoContent());

        verify(customerService).updateById(any(Integer.class), any(Customer.class));
    }

    @Test
    void testSaveCustomer () throws Exception {
        Customer newCustomer = customerServiceImpl.getAllCustomers().getFirst();
        newCustomer.setId(null);

        given(customerService.saveCustomer(any(Customer.class))).willReturn(customerServiceImpl.getAllCustomers().get(1));

        mockMvc.perform(post("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void getAllCustomers() throws Exception{
        given(customerService.getAllCustomers()).willReturn(customerServiceImpl.getAllCustomers());

        mockMvc.perform(get("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getCustomerById() throws Exception{
        Customer exampleCustomer = customerServiceImpl.getAllCustomers().getFirst();

        given(customerService.getCustomerById(exampleCustomer.getId())).willReturn(exampleCustomer);

        mockMvc.perform(get("/api/v1/customer/" + exampleCustomer.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(exampleCustomer.getId())))
                .andExpect(jsonPath("$.costumerName", is(exampleCustomer.getCostumerName())));
    }
}