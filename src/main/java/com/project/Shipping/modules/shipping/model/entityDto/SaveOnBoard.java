package com.project.Shipping.modules.shipping.model.entityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SaveOnBoard {
    @NotNull
    private String shipName;
    @NotNull
    private List<String> productIdList;
    @NotNull
    private List<String> unMatchedProductIdList;
    @NotNull
    private List<String> capacityOverloadedList;
}
