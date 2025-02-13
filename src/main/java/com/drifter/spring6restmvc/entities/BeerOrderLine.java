package com.drifter.spring6restmvc.entities;

import guru.springframework.spring6restmvcapi.model.BeerOrderLineStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by jt on 2019-01-26.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class BeerOrderLine {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    public boolean isNew() {
        return this.id == null;
    }

    @ManyToOne
    private BeerOrder beerOrder;

    @ManyToOne
    private Beer beer;

    @Min(value = 1, message = "Quantity as to be greater than 0")
    private Integer orderQuantity = 1;
    private Integer quantityAllocated = 0;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private BeerOrderLineStatus beerOrderLineStatus = BeerOrderLineStatus.NEW;
}
