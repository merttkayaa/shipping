package com.project.Shipping.modules.ship.model.request;

import com.project.Shipping.modules.ship.model.ProductList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateShip {
    @NotNull
    private String shipName;
    private Long routeId;
    private ProductList productList;
}
