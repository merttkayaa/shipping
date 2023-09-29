package com.project.Shipping.modules.shipping.consumer;

import com.project.Shipping.modules.product.service.ProductDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnBoardConsumer {
    private final ProductDBService productDBService;


    public void checkOnBoard(List<String> productIdList) {
        productIdList.forEach(productDBService::getByProductId);
    }
}
