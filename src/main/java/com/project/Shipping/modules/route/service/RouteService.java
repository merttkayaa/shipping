package com.project.Shipping.modules.route.service;

import com.project.Shipping.modules.route.model.DurationDto;
import com.project.Shipping.modules.route.model.RouteDto;
import com.project.Shipping.modules.route.model.request.CreateRoute;
import com.project.Shipping.modules.route.model.request.RouteFilter;
import com.project.Shipping.modules.route.model.request.UpdateRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RouteService {
    RouteDto createRoute(CreateRoute createRoute);

    RouteDto updateRoute(UpdateRoute updateRoute, String routeName);

    RouteDto terminateRoute(String routeName);

    Page<RouteDto> inquireRoute(RouteFilter routeFilter, Pageable pageable);

    DurationDto createDuration(DurationDto durationDto);
}
