package com.drifter.spring6restmvc.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BeerOrderLineCreateDTO {
    @NotNull
    private UUID beerId;
    @Min(value = 1, message = "Quantity as to be greater than 0")
    private Integer orderQuantity = 0;
}
