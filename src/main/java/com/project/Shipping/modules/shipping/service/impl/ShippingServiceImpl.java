package com.project.Shipping.modules.shipping.service.impl;

import com.project.Shipping.modules.product.model.dto.ProductDto;
import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.product.service.ProductDBService;
import com.project.Shipping.modules.route.model.RouteDto;
import com.project.Shipping.modules.route.service.RouteDBService;
import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.ship.service.ShipDBService;
import com.project.Shipping.modules.shipping.mapper.ShippingMapper;
import com.project.Shipping.modules.shipping.model.CancelShippingDto;
import com.project.Shipping.modules.shipping.model.DestinationDurationDTO;
import com.project.Shipping.modules.shipping.model.OnBoardDto;
import com.project.Shipping.modules.shipping.model.request.CancelShipping;
import com.project.Shipping.modules.shipping.model.request.OnBoardCreate;
import com.project.Shipping.modules.shipping.service.ShippingDBService;
import com.project.Shipping.modules.shipping.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {
    private final ShippingDBService shippingDBService;
    private final ProductDBService productDBService;
    private final ShipDBService shipDBService;
    private final RouteDBService routeDBService;
    private final ShippingMapper shippingMapper;

    @Override
    public OnBoardDto onBoard(OnBoardCreate onBoardCreate) {
        List<String> unMatchedWithShipList = new ArrayList<>();
        List<String> capacityOverloadedList = new ArrayList<>();
        List<String> onBoardedList = new ArrayList<>();

        ShipDto shipDto = shipDBService.getByShipName(onBoardCreate.getShipName());

        onBoardCreate.getProductIdList().forEach(item -> {
            ProductDto productDto = productDBService.getByProductId(item);
            if (!checkRouteMatch(productDto, shipDto) || !productDto.getType().name().equals(shipDto.getType().name())) {
                unMatchedWithShipList.add(productDto.getProductId());
            } else if (!checkStorageMatch(productDto, shipDto)) {
                capacityOverloadedList.add(productDto.getProductId());
            } else {
                onBoardedList.add(productDto.getProductId());
                shipDto.setCurrentWeight(shipDto.getCurrentWeight() - productDto.getWeight() * productDto.getAmount());
                shipDto.setCurrentCapacity(shipDto.getCurrentCapacity() - productDto.getProductArea() * productDto.getAmount());
            }

        });
        productDBService.onBoardProduct(shipDto.getShipName(), onBoardedList);
        shipDBService.updateCurrentStorage(shipDto, onBoardedList);
        shippingDBService.saveOnBoard(shippingMapper.createToEntity(onBoardCreate, unMatchedWithShipList, capacityOverloadedList));

        return shippingMapper.prepareDto(unMatchedWithShipList, capacityOverloadedList, onBoardedList, shipDto);
    }

    @Override
    public DestinationDurationDTO inquireDestinationDuration(String productId) {
        ProductDto productDto = productDBService.getByProductId(productId);
        LocationType currentLocation = productDto.getCurrentLocation();

        Long routeId = shipDBService.getByShipName(productDto.getShipName()).getRouteId();
        RouteDto routeDto = routeDBService.getById(routeId);

        Map<String, Double> durationMap = new HashMap<>();
        Double totalDuration = 0.0;

        Iterator<Map.Entry<Integer, LocationType>> iterator = routeDto.getRouteMap().entrySet().iterator();
        Map.Entry<Integer, LocationType> currentEntry = null;
        Map.Entry<Integer, LocationType> nextEntry = null;

        while (iterator.hasNext()) {
            if (currentEntry == null) {
                currentEntry = iterator.next();
                if (!currentEntry.getValue().equals(currentLocation)) {
                    continue;
                }
            } else {
                nextEntry = iterator.next();
                String locationName = currentEntry.getValue().name() + ":" + nextEntry.getValue().name();
                Double durationTime = routeDBService.getDuration(currentEntry.getValue(), nextEntry.getValue()).getDurationTime();
                durationMap.put(locationName, durationTime);
                totalDuration = totalDuration + durationTime;
                currentEntry = nextEntry;
            }
        }
        return shippingMapper.ToDestinationDurationDto(currentLocation, durationMap, totalDuration);
    }

    @Override
    public List<CancelShippingDto> cancelShipping(CancelShipping cancelShipping) {
        List<CancelShippingDto> responseList = new ArrayList<>();
        CancelShippingDto response = new CancelShippingDto();

        cancelShipping.getProductIdList().forEach(item -> {
            ProductDto productDto = productDBService.getByProductId(item);
            LocationType currentLocation = productDto.getCurrentLocation();

            Long routeId = shipDBService.getByShipName(productDto.getShipName()).getRouteId();
            RouteDto routeDto = routeDBService.getById(routeId);

            Iterator<Map.Entry<Integer, LocationType>> iterator = routeDto.getRouteMap().entrySet().iterator();
            Map.Entry<Integer, LocationType> currentEntry = null;
            Map.Entry<Integer, LocationType> nextEntry = null;

            while (iterator.hasNext()) {
                if (currentEntry == null) {
                    currentEntry = iterator.next();
                    if (!currentEntry.getValue().equals(currentLocation)) {
                        continue;
                    }
                } else {
                    nextEntry = iterator.next();
                    Double durationTime = routeDBService.getDuration(currentEntry.getValue(), nextEntry.getValue()).getDurationTime();
                    String message = "ProductId :" + item + " will be arrived from :" + currentLocation + "to :" + nextEntry.getValue().name() + " in " + durationTime.toString() + " days";
                    responseList.add(shippingMapper.toCancelShipping(message, durationTime, nextEntry.getValue(), currentLocation));
                    break;
                }
            }
            productDBService.updateTargetLocation(item, nextEntry.getValue());
            shippingDBService.cancelShipping(shippingMapper.toShippingCancel(productDto.getShipName(), cancelShipping.getProductIdList()));

        });
        return responseList;
    }

    private Boolean checkRouteMatch(ProductDto productDto, ShipDto shipDto) {
        RouteDto routeDto = routeDBService.getById(shipDto.getRouteId());
        if (!productDto.getCurrentLocation().equals(shipDto.getCurrentLocation())) {
            return false;
        }
        return routeDto.getRouteMap().containsValue(productDto.getTargetLocation());
    }

    private Boolean checkStorageMatch(ProductDto productDto, ShipDto shipDto) {
        Double totalWeight = productDto.getWeight() * productDto.getAmount();
        Double totalArea = productDto.getProductArea() * productDto.getAmount();

        if (totalWeight.compareTo(shipDto.getCurrentWeight()) > 0) {
            return false;
        }
        return totalArea.compareTo(shipDto.getCurrentCapacity()) <= 0;
    }
}
