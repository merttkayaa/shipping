package com.project.Shipping.modules.route.repository;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.route.entity.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface DurationRepository extends JpaRepository<Duration, Long>, QuerydslPredicateExecutor<Duration> {
    Optional<Duration> findByCurrentLocationAndTargetLocation(LocationType currentLocation, LocationType targetLocation);
}
