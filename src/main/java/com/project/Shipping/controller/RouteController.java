package com.project.Shipping.controller;

import com.project.Shipping.modules.route.model.DurationDto;
import com.project.Shipping.modules.route.model.RouteDto;
import com.project.Shipping.modules.route.model.request.CreateRoute;
import com.project.Shipping.modules.route.model.request.RouteFilter;
import com.project.Shipping.modules.route.model.request.UpdateRoute;
import com.project.Shipping.modules.route.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
//@Api(value = "Route API", description = "Product related operations")
public class RouteController {
    private final RouteService routeService;

    @PostMapping()
    public RouteDto createRoute(@Valid @RequestBody CreateRoute createRoute) {
        return routeService.createRoute(createRoute);
    }

    @PutMapping("/{routeName}")
    public RouteDto updateRoute(@RequestBody UpdateRoute updateRoute, @PathVariable String routeName) {
        return routeService.updateRoute(updateRoute, routeName);
    }

    @PutMapping("/terminate/{routeName}")
    public RouteDto terminateRoute(@PathVariable String routeName) {
        return routeService.terminateRoute(routeName);
    }

    @GetMapping()
    public Page<RouteDto> inquireRoute(@RequestBody RouteFilter routeFilter, Pageable pageable) {
        return routeService.inquireRoute(routeFilter, pageable);
    }

    @PostMapping("/create-duration")
    public DurationDto createDuration(@Valid @RequestBody DurationDto durationDto) {
        return routeService.createDuration(durationDto);
    }
}
