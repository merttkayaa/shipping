package com.project.Shipping.controller;

import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.ship.model.request.CreateShip;
import com.project.Shipping.modules.ship.model.request.InquireFilter;
import com.project.Shipping.modules.ship.model.request.UpdateShip;
import com.project.Shipping.modules.ship.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ship")
@RequiredArgsConstructor
//@Api(value = "Ship API", description = "Product related operations")
public class ShipController {
    private final ShipService shipService;

    @PostMapping()
    public ShipDto createShip(@Valid @RequestBody CreateShip createShip) {
        return shipService.createShip(createShip);
    }

    @PutMapping()
    public ShipDto updateShip(@Valid @RequestBody UpdateShip updateShip) {
        return shipService.updateShip(updateShip);
    }

    @PutMapping("/{shipName}")
    public ShipDto terminateShip(@PathVariable String shipName) {
        return shipService.terminateShip(shipName);
    }

    @GetMapping()
    public Page<ShipDto> inquireShip(@RequestBody InquireFilter inquireFilter, Pageable pageable) {
        return shipService.inquireShip(inquireFilter, pageable);
    }
}
