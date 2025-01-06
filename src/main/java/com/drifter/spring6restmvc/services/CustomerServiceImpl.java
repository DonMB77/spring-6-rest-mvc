package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        Random random1 = new Random();
        customerMap = new HashMap<>();

        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .id(random1.nextInt(1,100+1))
                .costumerName("customer1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .id(random1.nextInt(1,100+1))
                .costumerName("customer2")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO3 = CustomerDTO.builder()
                .id(random1.nextInt(1,100+1))
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
    public Optional<CustomerDTO> getCustomerById(Integer id) {
        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO savedCustomerDTO) {
        Random random1 = new Random();

        CustomerDTO newCustomerDTO = CustomerDTO.builder()
                .id(random1.nextInt(1, 100+1))
                .costumerName(savedCustomerDTO.getCostumerName())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(newCustomerDTO.getId(), newCustomerDTO);

        return newCustomerDTO;
    }

    @Override
    public void updateById(Integer customerId, CustomerDTO customerDTO) {
        CustomerDTO fetchedCustomerDTO = customerMap.get(customerId);
        fetchedCustomerDTO.setId(customerId);
        fetchedCustomerDTO.setCostumerName(customerDTO.getCostumerName());

        customerMap.put(customerId, fetchedCustomerDTO);
    }

    @Override
    public void deleteById(Integer customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchById(Integer customerId, CustomerDTO customerDTO) {
        CustomerDTO fetchedCustomerDTO = customerMap.get(customerId);

        if (StringUtils.hasText(customerDTO.getCostumerName())) {
            fetchedCustomerDTO.setCostumerName(customerDTO.getCostumerName());
        }
    }
}
