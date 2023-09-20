package com.project.Shipping.modules.route.model.request;

import com.project.Shipping.modules.route.model.enums.RouteStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteFilter {
    private RouteStatusType status;
    private String routeName;
}
