package com.project.Shipping.modules.ship.service.impl;

import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.ship.model.request.CreateShip;
import com.project.Shipping.modules.ship.model.request.InquireFilter;
import com.project.Shipping.modules.ship.model.request.UpdateShip;
import com.project.Shipping.modules.ship.service.ShipDBService;
import com.project.Shipping.modules.ship.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {
    private final ShipDBService shipDBService;

    @Override
    public ShipDto createShip(CreateShip createShip) {
        shipDBService.checkByShipName(createShip.getShipName());
        return shipDBService.create(createShip);
    }

    @Override
    public ShipDto updateShip(UpdateShip updateShip) {
        return shipDBService.update(updateShip);
    }

    @Override
    public ShipDto terminateShip(String shipName) {
        return shipDBService.terminate(shipName);
    }

    @Override
    public Page<ShipDto> inquireShip(InquireFilter inquireFilter, Pageable pageable) {
        return shipDBService.get(inquireFilter, pageable);
    }


}
