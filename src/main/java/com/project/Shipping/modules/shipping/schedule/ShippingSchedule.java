package com.project.Shipping.modules.shipping.schedule;

import com.project.Shipping.modules.product.model.dto.ProductDto;
import com.project.Shipping.modules.product.service.ProductDBService;
import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.ship.service.ShipDBService;
import com.project.Shipping.modules.shipping.entity.Shipping;
import com.project.Shipping.modules.shipping.service.ShippingDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ShippingSchedule {

    private final ShippingDBService shippingDBService;
    private final ProductDBService productDBService;
    private final ShipDBService shipDBService;

    @Scheduled(cron = "0 0 1 * * ?")
    private void checkCancelledProducts() {
        List<Shipping> cancelledList = shippingDBService.getCancelledShipping();
        if (Objects.nonNull(cancelledList)) {
            cancelledList.forEach(item -> {
                item.getCancelledProductIdList().getCancelledProductList().forEach(productId -> {
                    ProductDto product = productDBService.getByProductId(productId);
                    ShipDto ship = shipDBService.getByShipName(product.getShipName());
                    if (product.getTargetLocation().equals(ship.getCurrentLocation())) {
                        shippingDBService.offBoard(ship.getShipName(), productId);
                        shipDBService.updateProductList(ship.getShipName(), productId, product.getProductArea() * product.getAmount(), product.getWeight() * product.getAmount());
                        productDBService.updateProductStatus(productId);
                    }
                });
            });
        }
    }

    @Scheduled(cron = "0 0 17 * * ?")
    private void checkOffBoardProducts() {
        List<Shipping> onBoardedList = shippingDBService.getOnBoardedShipping();
        if (Objects.nonNull(onBoardedList)) {
            onBoardedList.forEach(item -> {
                item.getProductIdList().getProductList().forEach(productId -> {
                    ProductDto product = productDBService.getByProductId(productId);
                    ShipDto ship = shipDBService.getByShipName(product.getShipName());
                    if (product.getTargetLocation().equals(ship.getCurrentLocation())) {
                        shippingDBService.offBoard(ship.getShipName(), productId);
                        shipDBService.updateProductList(ship.getShipName(), productId, product.getProductArea() * product.getAmount(), product.getWeight() * product.getAmount());
                        productDBService.updateProductStatus(productId);
                    }
                });
            });
        }
    }


}
