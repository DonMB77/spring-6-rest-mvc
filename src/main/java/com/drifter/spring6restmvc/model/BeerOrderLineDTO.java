package com.drifter.spring6restmvc.model;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class BeerOrderLineDTO {
    private UUID id;
    private Long version;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private BeerDTO beer;
    @Min(value = 1, message = "Quantity as to be greater than 0")
    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
    private BeerOrderLineStatus beerOrderLineStatus;
}