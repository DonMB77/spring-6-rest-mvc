package com.drifter.spring6restmvc.model;

import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public class BeerOrderUpdateDTO {

    private String customerRef;

    @NotNull
    private UUID customerId;

    private Set<BeerOrderUpdateDTO> beerOrderLines;

    private BeerOrderShipmentUpdateDTO beerOrderShipment;
}
