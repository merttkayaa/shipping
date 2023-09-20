package com.project.Shipping.modules.shipping.service;

import com.project.Shipping.modules.shipping.entity.Shipping;
import com.project.Shipping.modules.shipping.model.entityDto.SaveOnBoard;
import com.project.Shipping.modules.shipping.model.entityDto.ShippingCancel;

import java.util.List;

public interface ShippingDBService {
    void saveOnBoard(SaveOnBoard saveOnBoard);

    void cancelShipping(ShippingCancel shippingCancel);

    List<Shipping> getCancelledShipping();

    void offBoard(String shipName, String productId);

    List<Shipping> getOnBoardedShipping();
}
