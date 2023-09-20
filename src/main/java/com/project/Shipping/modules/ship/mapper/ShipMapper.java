package com.project.Shipping.modules.ship.mapper;

import com.project.Shipping.modules.ship.entity.Ship;
import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.ship.model.request.CreateShip;
import com.project.Shipping.modules.ship.model.request.UpdateShip;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShipMapper {
    ShipMapper INSTANCE = Mappers.getMapper(ShipMapper.class);

    Ship map(CreateShip createShip);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ship updateToEntity(@MappingTarget Ship ship, UpdateShip updateShip);

    ShipDto entityToDto(Ship ship);

}
