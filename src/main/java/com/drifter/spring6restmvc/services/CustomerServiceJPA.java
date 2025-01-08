package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.mappers.CustomerMapper;
import com.drifter.spring6restmvc.model.CustomerDTO;
import com.drifter.spring6restmvc.repositories.BeerRepository;
import com.drifter.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class CustomerServiceJPA implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {

        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO savedCustomerDTO) {
        return null;
    }

    @Override
    public void updateById(UUID customerId, CustomerDTO customerDTO) {

    }

    @Override
    public void deleteById(UUID customerId) {

    }

    @Override
    public void patchById(UUID customerId, CustomerDTO customerDTO) {

    }
}
