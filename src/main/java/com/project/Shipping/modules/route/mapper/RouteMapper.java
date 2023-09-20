package com.project.Shipping.modules.route.mapper;

import com.project.Shipping.modules.route.entity.Duration;
import com.project.Shipping.modules.route.entity.Route;
import com.project.Shipping.modules.route.model.DurationDto;
import com.project.Shipping.modules.route.model.RouteDto;
import com.project.Shipping.modules.route.model.request.CreateRoute;
import com.project.Shipping.modules.route.model.request.UpdateRoute;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteMapper {
    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    Route createToEntity(CreateRoute createRoute);

    RouteDto entityToDto(Route route);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Route updateToEntity(@MappingTarget Route route, UpdateRoute updateRoute);

    Duration dtoToDurationEntity(DurationDto durationDto);

    DurationDto entityToDurationDto(Duration save);
}
