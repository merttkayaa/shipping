package com.project.Shipping.modules.shipping.service;

import com.project.Shipping.modules.shipping.model.CancelShippingDto;
import com.project.Shipping.modules.shipping.model.DestinationDurationDTO;
import com.project.Shipping.modules.shipping.model.OnBoardDto;
import com.project.Shipping.modules.shipping.model.request.CancelShipping;
import com.project.Shipping.modules.shipping.model.request.OnBoardCreate;

import java.util.List;

public interface ShippingService {
    OnBoardDto onBoard(OnBoardCreate onBoardCreate);

    DestinationDurationDTO inquireDestinationDuration(String productId);

    List<CancelShippingDto> cancelShipping(CancelShipping cancelShipping);
}
