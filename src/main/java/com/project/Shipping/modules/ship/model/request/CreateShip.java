package com.project.Shipping.modules.ship.model.request;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.ship.model.enums.ShipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateShip {
    @NotNull
    private LocationType currentLocation;
    @NotNull
    private ShipType type;
    @NotNull
    private Double areaCapacity;
    @NotNull
    private Double maxWeight;
    @NotNull
    private String shipName;
}
