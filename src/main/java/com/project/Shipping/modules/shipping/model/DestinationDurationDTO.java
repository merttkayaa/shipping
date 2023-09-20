package com.project.Shipping.modules.shipping.model;

import com.project.Shipping.modules.product.model.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DestinationDurationDTO {
    private LocationType currentLocation;
    private Map<String, Double> durationMap;
    private Double totalDuration;
}
