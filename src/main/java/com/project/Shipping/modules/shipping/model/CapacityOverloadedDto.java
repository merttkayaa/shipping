package com.project.Shipping.modules.shipping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapacityOverloadedDto {
    private List<String> capacityOverloadedList;
    private String message = "Products could not be onboarded due to capacity";
}
