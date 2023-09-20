package com.project.Shipping.modules.product.model.dto;

import com.project.Shipping.modules.product.model.enums.EmergencyType;
import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.product.model.enums.ProductStatus;
import com.project.Shipping.modules.product.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private ProductType type;
    private Double temp;
    private Double weight;
    private Double productArea;
    private int amount;
    private LocalDate targetDate;
    private Boolean timeSensitive;
    private Boolean tempSensitive;
    private LocationType targetLocation;
    private LocationType currentLocation;
    private EmergencyType emergency;
    private String productId;
    private ProductStatus status;
    private String shipName;

}
