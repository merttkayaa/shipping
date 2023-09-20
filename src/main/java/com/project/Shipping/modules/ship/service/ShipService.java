package com.project.Shipping.modules.ship.service;

import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.ship.model.request.CreateShip;
import com.project.Shipping.modules.ship.model.request.InquireFilter;
import com.project.Shipping.modules.ship.model.request.UpdateShip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShipService {
    ShipDto createShip(CreateShip createShip);

    ShipDto updateShip(UpdateShip updateShip);

    ShipDto terminateShip(String shipName);

    Page<ShipDto> inquireShip(InquireFilter inquireShip, Pageable pageable);
}
