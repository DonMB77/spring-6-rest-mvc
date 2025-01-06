package com.drifter.spring6restmvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    private UUID id;
    private Integer version;
    private String costumerName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
