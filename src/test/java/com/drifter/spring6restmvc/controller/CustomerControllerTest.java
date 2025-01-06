package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.model.Customer;
import com.drifter.spring6restmvc.services.CustomerService;
import com.drifter.spring6restmvc.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Captor
    ArgumentCaptor<Integer> integerArgumentCaptor;

    @Captor
    ArgumentCaptor<Customer> customerArgumentCaptor;

    @BeforeEach
    void setUp() {
        customerServiceImpl  = new CustomerServiceImpl();
    }

    @Test
    void testPatchById() throws Exception{
        Customer customer = customerServiceImpl.getAllCustomers().getFirst();

        Map<String, Object> customerMap = new HashMap<>();
        customerMap.put("costumerName", "newName");

        mockMvc.perform(patch(CustomerController.CUSTOMER_PATH_ID, customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(status().isNoContent());

        verify(customerService).patchById(integerArgumentCaptor.capture(), customerArgumentCaptor.capture());
        assertThat(customer.getId()).isEqualTo(integerArgumentCaptor.getValue());
        assertThat(customerMap.get("costumerName")).isEqualTo(customerArgumentCaptor.getValue().getCostumerName());
    }

    @Test
    void testDeleteById() throws Exception {
        Customer customer = customerServiceImpl.getAllCustomers().getFirst();

        mockMvc.perform(delete(CustomerController.CUSTOMER_PATH_ID, customer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(customerService).deleteById(integerArgumentCaptor.capture());

        assertThat(customer.getId()).isEqualTo(integerArgumentCaptor.getValue());
    }

    @Test
    void testUpdateById () throws Exception {
        Customer customerToBeUpdated = customerServiceImpl.getAllCustomers().getFirst();

        mockMvc.perform(put(CustomerController.CUSTOMER_PATH_ID, customerToBeUpdated.getId())
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

        mockMvc.perform(post(CustomerController.CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void getAllCustomers() throws Exception{
        given(customerService.getAllCustomers()).willReturn(customerServiceImpl.getAllCustomers());

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getCustomerByIdNotFound() throws Exception {
        Random r1 = new Random();

        given(customerService.getCustomerById(any(Integer.class))).willThrow(NotFoundException.class);

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID, r1.nextInt(1,100+1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCustomerById() throws Exception{
        Customer exampleCustomer = customerServiceImpl.getAllCustomers().getFirst();

        given(customerService.getCustomerById(exampleCustomer.getId())).willReturn(Optional.of(exampleCustomer));

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID, exampleCustomer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(exampleCustomer.getId())))
                .andExpect(jsonPath("$.costumerName", is(exampleCustomer.getCostumerName())));
    }
}