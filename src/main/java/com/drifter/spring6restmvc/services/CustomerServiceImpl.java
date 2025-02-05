package com.drifter.spring6restmvc.services;

import guru.springframework.spring6restmvcapi.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        customerMap = new HashMap<>();

        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .costumerName("customer1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .costumerName("customer2")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .costumerName("customer3")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customerDTO1.getId(), customerDTO1);
        customerMap.put(customerDTO2.getId(), customerDTO2);
        customerMap.put(customerDTO3.getId(), customerDTO3);

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO savedCustomerDTO) {
        CustomerDTO newCustomerDTO = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .costumerName(savedCustomerDTO.getCostumerName())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(newCustomerDTO.getId(), newCustomerDTO);

        return newCustomerDTO;
    }

    @Override
    public Optional<CustomerDTO> updateById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO fetchedCustomerDTO = customerMap.get(customerId);
        fetchedCustomerDTO.setId(customerId);
        fetchedCustomerDTO.setCostumerName(customerDTO.getCostumerName());

        customerMap.put(customerId, fetchedCustomerDTO);

        return Optional.of(customerDTO);
    }

    @Override
    public Boolean deleteById(UUID customerId) {
        customerMap.remove(customerId);

        return true;
    }

    @Override
    public Optional<CustomerDTO> patchById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO fetchedCustomerDTO = customerMap.get(customerId);

        if (StringUtils.hasText(customerDTO.getCostumerName())) {
            fetchedCustomerDTO.setCostumerName(customerDTO.getCostumerName());
        }

        return Optional.of(customerDTO);
    }
}
