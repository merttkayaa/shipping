package com.project.Shipping.modules.shipping.model.entityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShippingCancel {
    private String shipName;
    private List<String> cancelledProductList;
}
