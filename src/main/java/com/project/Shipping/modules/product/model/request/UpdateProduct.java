package com.project.Shipping.modules.product.model.request;

import com.project.Shipping.modules.product.model.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProduct {
    private LocalDate targetDate;
    private LocationType targetLocation;
    private Boolean timeSensitive;
    @NotNull
    private String productId;
}
