package com.drifter.spring6restmvc.repositories;

import com.drifter.spring6restmvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Beer, UUID> {
}
