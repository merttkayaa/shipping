package com.project.Shipping.modules.shipping.mapper;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.shipping.entity.Shipping;
import com.project.Shipping.modules.shipping.model.CancelShippingDto;
import com.project.Shipping.modules.shipping.model.DestinationDurationDTO;
import com.project.Shipping.modules.shipping.model.OnBoardDto;
import com.project.Shipping.modules.shipping.model.entityDto.SaveOnBoard;
import com.project.Shipping.modules.shipping.model.entityDto.ShippingCancel;
import com.project.Shipping.modules.shipping.model.enums.ShippingType;
import com.project.Shipping.modules.shipping.model.request.OnBoardCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ShippingMapper {
    ShippingMapper INSTANCE = Mappers.getMapper(ShippingMapper.class);

    @Mapping(target = "onBoardedDto.onBoardedList", source = "onBoardedList")
    @Mapping(target = "unMatchedDto.unMatchedWithShip", source = "unMatchedWithShipList")
    @Mapping(target = "capacityOverloadedDto.capacityOverloadedList", source = "capacityOverloadedList")
    @Mapping(target = "shipDto", source = "shipDto")
    OnBoardDto prepareDto(List<String> unMatchedWithShipList, List<String> capacityOverloadedList, List<String> onBoardedList, ShipDto shipDto);

    @Mapping(target = "productIdList", source = "onBoardCreate.productIdList")
    @Mapping(target = "shipName", source = "onBoardCreate.shipName")
    @Mapping(target = "unMatchedProductIdList", source = "unMatchedProductIdList")
    @Mapping(target = "capacityOverloadedList", source = "capacityOverloadedList")
    SaveOnBoard createToEntity(OnBoardCreate onBoardCreate, List<String> unMatchedProductIdList, List<String> capacityOverloadedList);

    @Mapping(target = "productIdList.productList", source = "productIdList")
    @Mapping(target = "unMatchedProductIdList.unMatchedProductList", source = "unMatchedProductIdList")
    @Mapping(target = "capacityOverloadedList.capacityList", source = "capacityOverloadedList")
    Shipping onBoardToEntity(SaveOnBoard saveOnBoard);

    @Mapping(target = "currentLocation", source = "currentLocation")
    @Mapping(target = "totalDuration", source = "totalDuration")
    @Mapping(target = "durationMap", source = "durationMap")
    DestinationDurationDTO ToDestinationDurationDto(LocationType currentLocation, Map<String, Double> durationMap, Double totalDuration);

    @Mapping(target = "currentLocation", source = "currentLocation")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "duration", source = "durationTime")
    @Mapping(target = "nextLocation", source = "targetLocation")
    CancelShippingDto toCancelShipping(String message, Double durationTime, LocationType targetLocation, LocationType currentLocation);

    @Mapping(target = "shipName", source = "shipName")
    @Mapping(target = "cancelledProductList", source = "cancelledProductList")
    ShippingCancel toShippingCancel(String shipName, List<String> cancelledProductList);

    @Mapping(target = "shipName", source = "shippingCancel.shipName")
    @Mapping(target = "cancelledProductIdList.cancelledProductList", source = "shippingCancel.cancelledProductList")
    @Mapping(target = "updatedAt", source = "currentTime")
    @Mapping(target = "insertedAt", source = "currentTime")
    @Mapping(target = "type", source = "type")
    Shipping cancelToEntity(ShippingCancel shippingCancel, LocalDate currentTime, ShippingType type);

    @Mapping(target = "shipName", source = "shipName")
    @Mapping(target = "offBoardProductIdList", source = "productId")
    @Mapping(target = "updatedAt", source = "currentTime")
    @Mapping(target = "insertedAt", source = "currentTime")
    @Mapping(target = "type", source = "type")
    Shipping offBoardToEntity(String shipName, String productId, LocalDate currentTime, ShippingType type);
}
