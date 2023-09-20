package com.project.Shipping.modules.route.service.impl;

import com.project.Shipping.modules.route.model.DurationDto;
import com.project.Shipping.modules.route.model.RouteDto;
import com.project.Shipping.modules.route.model.request.CreateRoute;
import com.project.Shipping.modules.route.model.request.RouteFilter;
import com.project.Shipping.modules.route.model.request.UpdateRoute;
import com.project.Shipping.modules.route.service.RouteDBService;
import com.project.Shipping.modules.route.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteDBService routeDBService;

    @Override
    public RouteDto createRoute(CreateRoute createRoute) {
        routeDBService.checkByRouteName(createRoute.getRouteName());
        return routeDBService.create(createRoute);
    }

    @Override
    public RouteDto updateRoute(UpdateRoute updateRoute, String routeName) {
        return routeDBService.update(updateRoute, routeName);
    }

    @Override
    public RouteDto terminateRoute(String routeName) {
        return routeDBService.terminate(routeName);
    }

    @Override
    public Page<RouteDto> inquireRoute(RouteFilter routeFilter, Pageable pageable) {
        return routeDBService.get(routeFilter, pageable);
    }

    @Override
    public DurationDto createDuration(DurationDto durationDto) {
        return routeDBService.createDuration(durationDto);
    }
}
