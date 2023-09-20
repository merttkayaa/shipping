package com.project.Shipping.modules.route.model.request;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.route.model.enums.RouteStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRoute {
    private Map<String, LocationType> routeMap;
    private RouteStatusType status;
}
