package com.project.Shipping.modules.ship.model.request;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.ship.model.ProductList;
import com.project.Shipping.modules.ship.model.enums.ShipStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InquireFilter {
    private String shipName;
    private Long routeId;
    private Double areaCapacity;
    private Double maxWeight;
    private ShipStatus status;
    private ProductList productList;
    private LocationType currentLocation;
}
