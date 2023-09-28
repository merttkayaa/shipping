package com.project.Shipping.modules.route.service;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.route.model.DurationDto;
import com.project.Shipping.modules.route.model.RouteDto;
import com.project.Shipping.modules.route.model.request.CreateRoute;
import com.project.Shipping.modules.route.model.request.RouteFilter;
import com.project.Shipping.modules.route.model.request.UpdateRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RouteDBService {
    RouteDto create(CreateRoute createRoute);

    RouteDto update(UpdateRoute updateRoute, String routeName);

    RouteDto terminate(String routeName);

    Page<RouteDto> get(RouteFilter routeFilter, Pageable pageable);

    void checkByRouteName(String routeName);

    RouteDto getById(Long id);

    DurationDto createDuration(DurationDto durationDto);

    DurationDto getDuration(LocationType currentLocation, LocationType targetLocation);

    List<DurationDto> getAllDurations();
}
