package com.project.Shipping.modules.product.model.request;

import com.project.Shipping.modules.product.model.enums.EmergencyType;
import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.product.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProduct {
    @NotNull
    private ProductType type;
    @NotNull
    private Double temp;
    @NotNull
    private Double weight;
    @NotNull
    private Double productArea;
    @NotNull
    private int amount;
    @NotNull
    private LocalDate targetDate;
    @NotNull
    private Boolean timeSensitive;
    @NotNull
    private Boolean tempSensitive;
    @NotNull
    private LocationType targetLocation;
    @NotNull
    private LocationType currentLocation;
    @NotNull
    private EmergencyType emergency;
    @NotNull
    private String productId;
}
