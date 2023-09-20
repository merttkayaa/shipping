package com.project.Shipping.modules.shipping.model.request;

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
public class OnBoardCreate {
    @NotNull
    private List<String> productIdList;
    @NotNull
    private String shipName;

}
