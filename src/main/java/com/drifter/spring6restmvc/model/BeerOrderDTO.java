package com.drifter.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class BeerOrderDTO {
    private UUID id;
    private Integer version;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private String customerRef;
    private BigDecimal paymentAmount;
    private CustomerDTO customer;
    private Set<BeerOrderLineDTO> beerOrderLines;
    private BeerOrderShipmentDTO beerOrderShipment;
}
