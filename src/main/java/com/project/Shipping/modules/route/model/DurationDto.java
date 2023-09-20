package com.project.Shipping.modules.route.model;

import com.project.Shipping.modules.product.model.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DurationDto {
    @NotNull
    private LocationType currentLocation;
    @NotNull
    private LocationType targetLocation;
    @NotNull
    private Double durationTime;
}
