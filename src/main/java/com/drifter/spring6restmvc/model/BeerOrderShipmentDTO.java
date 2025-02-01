package com.drifter.spring6restmvc.model;

import com.drifter.spring6restmvc.entities.BeerOrder;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BeerOrderShipmentDTO {
    private UUID id;
    private Integer version;
    private BeerOrderDTO beerOrder;
    private String trackingNumber;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
}
