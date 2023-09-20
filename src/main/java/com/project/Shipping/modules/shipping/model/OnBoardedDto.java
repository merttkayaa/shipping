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
public class OnBoardedDto {
    private List<String> onBoardedList;
    private String message = "Products succesfully onBoarded";
}
