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
public class UnMatchedDto {
    private List<String> unMatchedWithShip;
    private String message = "Features of products not matched with the ship";
}
