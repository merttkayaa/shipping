package com.project.Shipping.modules.product.model.request;

import com.project.Shipping.modules.product.model.enums.EmergencyType;
import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.product.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductFilter {
    private String productId;
    private LocationType targetLocation;
    private LocationType currentLocation;
    private EmergencyType emergency;
    private Boolean timeSensitive;
    private Boolean tempSensitive;
    private LocalDate targetDate;
    private ProductType productType;
}
