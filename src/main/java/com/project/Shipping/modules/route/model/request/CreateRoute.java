package com.project.Shipping.modules.route.model.request;

import com.project.Shipping.modules.product.model.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRoute {
    private Map<Integer, LocationType> routeMap;
    @NotNull
    private String routeName;
}
