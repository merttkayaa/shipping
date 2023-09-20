package com.project.Shipping.modules.product.service;

import com.project.Shipping.modules.product.model.dto.ProductDto;
import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.product.model.request.CreateProduct;
import com.project.Shipping.modules.product.model.request.ProductFilter;
import com.project.Shipping.modules.product.model.request.UpdateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDBService {
    ProductDto create(CreateProduct createProduct);

    ProductDto update(UpdateProduct updateProduct);

    ProductDto terminate(String productId);

    Page<ProductDto> get(ProductFilter productFilter, Pageable pageable);

    void checkByProductId(String productId);

    ProductDto getByProductId(String productId);

    void onBoardProduct(String shipName, List<String> productIdList);

    void updateTargetLocation(String productId, LocationType value);

    void updateProductStatus(String productId);
}
