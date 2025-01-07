package com.drifter.spring6restmvc.mappers;

import com.drifter.spring6restmvc.entities.Customer;
import com.drifter.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer custumerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);
}
