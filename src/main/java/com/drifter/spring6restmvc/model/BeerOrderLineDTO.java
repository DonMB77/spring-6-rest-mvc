package com.drifter.spring6restmvc.model;

import com.drifter.spring6restmvc.entities.Beer;
import com.drifter.spring6restmvc.entities.BeerOrder;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BeerOrderLineDTO {
    private UUID id;
    private Long version;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private BeerDTO beer;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
}