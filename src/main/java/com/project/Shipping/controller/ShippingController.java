package com.project.Shipping.controller;

import com.project.Shipping.modules.shipping.model.CancelShippingDto;
import com.project.Shipping.modules.shipping.model.DestinationDurationDTO;
import com.project.Shipping.modules.shipping.model.OnBoardDto;
import com.project.Shipping.modules.shipping.model.request.CancelShipping;
import com.project.Shipping.modules.shipping.model.request.OnBoardCreate;
import com.project.Shipping.modules.shipping.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class ShippingController {
    private final ShippingService shippingService;

    @PostMapping("/onboard")
    public OnBoardDto onBoard(@Valid @RequestBody OnBoardCreate onBoardCreate) {
        return shippingService.onBoard(onBoardCreate);
    }

    @GetMapping("/inquire-destination-duration/{productId}")
    public DestinationDurationDTO inquireDestinationDuration(@PathVariable String productId) {
        return shippingService.inquireDestinationDuration(productId);
    }

    @PutMapping()
    public List<CancelShippingDto> cancelShipping(@Valid @RequestBody CancelShipping cancelShipping) {
        return shippingService.cancelShipping(cancelShipping);
    }
}
