package com.project.Shipping.modules.route.repository;

import com.project.Shipping.modules.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long>, QuerydslPredicateExecutor<Route> {
    Optional<Route> findByRouteName(String routeName);
}
