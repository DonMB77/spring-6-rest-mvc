package com.drifter.spring6restmvc.repositories;

import com.drifter.spring6restmvc.entities.BeerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderLineRepository extends JpaRepository<BeerOrderLine, UUID> {
}
