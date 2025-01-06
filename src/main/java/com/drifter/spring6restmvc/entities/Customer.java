package com.drifter.spring6restmvc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Integer id;
    @Version
    private Integer version;
    private String costumerName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
