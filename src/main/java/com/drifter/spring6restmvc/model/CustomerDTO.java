package com.drifter.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomerDTO {
    private Integer id;
    private String costumerName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
