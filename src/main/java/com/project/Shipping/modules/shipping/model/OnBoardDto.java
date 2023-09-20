package com.project.Shipping.modules.shipping.model;

import com.project.Shipping.modules.ship.model.ShipDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OnBoardDto {
    private ShipDto shipDto;
    private OnBoardedDto onBoardedDto;
    private UnMatchedDto unMatchedDto;
    private CapacityOverloadedDto capacityOverloadedDto;
}
