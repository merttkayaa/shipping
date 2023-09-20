package com.project.Shipping.modules.route.service.impl;

import com.project.Shipping.exception.RecordFound;
import com.project.Shipping.exception.RecordNotFound;
import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.route.entity.Duration;
import com.project.Shipping.modules.route.entity.QRoute;
import com.project.Shipping.modules.route.entity.Route;
import com.project.Shipping.modules.route.mapper.RouteMapper;
import com.project.Shipping.modules.route.model.DurationDto;
import com.project.Shipping.modules.route.model.RouteDto;
import com.project.Shipping.modules.route.model.enums.RouteStatusType;
import com.project.Shipping.modules.route.model.request.CreateRoute;
import com.project.Shipping.modules.route.model.request.RouteFilter;
import com.project.Shipping.modules.route.model.request.UpdateRoute;
import com.project.Shipping.modules.route.repository.DurationRepository;
import com.project.Shipping.modules.route.repository.RouteRepository;
import com.project.Shipping.modules.route.service.RouteDBService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteDBServiceImpl implements RouteDBService {
    private final RouteRepository repository;
    private final DurationRepository durationRepository;
    private final RouteMapper routeMapper;

    @Override
    public RouteDto create(CreateRoute createRoute) {
        Route entity = routeMapper.createToEntity(createRoute);
        entity.setLocationSize(createRoute.getRouteMap().size());
        return routeMapper.entityToDto(repository.save(entity));
    }

    @Override
    public RouteDto update(UpdateRoute updateRoute, String routeName) {
        Optional<Route> existingEntity = Optional.ofNullable(repository.findByRouteName(routeName).orElseThrow(() ->
                new RecordNotFound("route not found")));
        Route updatedEntity = routeMapper.updateToEntity(existingEntity.get(), updateRoute);
        updatedEntity.setLocationSize(updateRoute.getRouteMap().size());
        return routeMapper.entityToDto(repository.save(updatedEntity));
    }

    @Override
    public RouteDto terminate(String routeName) {
        Optional<Route> optionalEntity = Optional.ofNullable(repository.findByRouteName(routeName).orElseThrow(() ->
                new RecordNotFound("route not found")));
        Route existingEntity = optionalEntity.get();
        existingEntity.setStatus(RouteStatusType.TERMINATED);
        return routeMapper.entityToDto(repository.save(existingEntity));
    }

    @Override
    public Page<RouteDto> get(RouteFilter routeFilter, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QRoute qRoute = QRoute.route;
        if (Objects.nonNull(routeFilter.getRouteName()))
            builder.and(qRoute.routeName.eq(routeFilter.getRouteName()));
        if (Objects.nonNull(routeFilter.getStatus()))
            builder.and(qRoute.status.eq(routeFilter.getStatus()));

        Predicate predicate = builder.getValue();
        return repository.findAll(predicate, pageable).map(routeMapper::entityToDto);
    }

    @Override
    public void checkByRouteName(String routeName) {
        Optional<Route> optionalEntity = repository.findByRouteName(routeName);
        if (optionalEntity.isPresent())
            throw new RecordFound("Already exists");
    }

    @Override
    public RouteDto getById(Long id) {
        Optional<Route> optionalEntity = Optional.ofNullable(repository.findById(id).orElseThrow(() ->
                new RecordNotFound("route not found")));
        return routeMapper.entityToDto(optionalEntity.get());
    }

    @Override
    public DurationDto createDuration(DurationDto durationDto) {
        Duration entity = routeMapper.dtoToDurationEntity(durationDto);
        return routeMapper.entityToDurationDto(durationRepository.save(entity));
    }

    @Override
    public DurationDto getDuration(LocationType currentLocation, LocationType targetLocation) {
        Optional<Duration> optionalEntity = Optional.ofNullable(durationRepository.findByCurrentLocationAndTargetLocation(currentLocation, targetLocation).orElseThrow(() ->
                new RecordNotFound("route not found")));
        return routeMapper.entityToDurationDto(optionalEntity.get());
    }
}
