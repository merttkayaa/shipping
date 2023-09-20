package com.project.Shipping.modules.shipping.repository;

import com.project.Shipping.modules.shipping.entity.Shipping;
import com.project.Shipping.modules.shipping.model.enums.ShippingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ShippingRepository extends JpaRepository<Shipping, Long>, QuerydslPredicateExecutor<Shipping> {
    Optional<List<Shipping>> findAllByType(ShippingType shippingType);
}
