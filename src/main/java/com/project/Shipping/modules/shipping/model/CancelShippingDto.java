package com.project.Shipping.modules.shipping.model;

import com.project.Shipping.modules.product.model.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CancelShippingDto {
    private LocationType currentLocation;
    private LocationType nextLocation;
    private Double duration;
    private String message;
}
