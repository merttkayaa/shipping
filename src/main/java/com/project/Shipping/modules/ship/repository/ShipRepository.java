package com.project.Shipping.modules.ship.repository;

import com.project.Shipping.modules.ship.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship, Long>, QuerydslPredicateExecutor<Ship> {
    Optional<Ship> findByShipName(String shipName);

}
