package com.drifter.spring6restmvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomerDTO {
    private Integer id;
    private Integer version;
    private String costumerName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
