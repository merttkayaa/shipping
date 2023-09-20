package com.project.Shipping.modules.ship.service;

import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.ship.model.request.CreateShip;
import com.project.Shipping.modules.ship.model.request.InquireFilter;
import com.project.Shipping.modules.ship.model.request.UpdateShip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShipDBService {
    ShipDto create(CreateShip createShip);

    ShipDto update(UpdateShip updateShip);

    ShipDto terminate(String shipName);

    Page<ShipDto> get(InquireFilter inquireShip, Pageable pageable);

    void checkByShipName(String shipName);

    ShipDto getByShipName(String shipName);

    void updateCurrentStorage(ShipDto shipDto, List<String> productIdList);

    void updateProductList(String shipName, String productId, Double area, Double weight);
}
