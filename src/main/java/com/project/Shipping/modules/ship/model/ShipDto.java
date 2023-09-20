package com.project.Shipping.modules.ship.model;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.ship.model.enums.ShipStatus;
import com.project.Shipping.modules.ship.model.enums.ShipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipDto {
    private LocationType currentLocation;
    private ShipStatus status;
    private ShipType type;
    private Long routeId;
    private Double maxCapacity;
    private Double maxWeight;
    private Double currentCapacity;
    private Double currentWeight;
    private String shipName;
    private ProductList productList;
}
