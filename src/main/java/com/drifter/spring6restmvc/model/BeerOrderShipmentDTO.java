package com.drifter.spring6restmvc.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class BeerOrderShipmentDTO {
    private UUID id;
    private Integer version;
    private BeerOrderDTO beerOrder;
    @NotBlank
    private String trackingNumber;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
}
