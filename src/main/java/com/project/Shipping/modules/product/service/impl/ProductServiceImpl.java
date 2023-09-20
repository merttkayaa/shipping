package com.project.Shipping.modules.product.service.impl;

import com.project.Shipping.modules.product.model.dto.ProductDto;
import com.project.Shipping.modules.product.model.request.CreateProduct;
import com.project.Shipping.modules.product.model.request.ProductFilter;
import com.project.Shipping.modules.product.model.request.UpdateProduct;
import com.project.Shipping.modules.product.service.ProductDBService;
import com.project.Shipping.modules.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDBService productDBService;

    @Override
    public ProductDto createProduct(CreateProduct createProduct) {
        productDBService.checkByProductId(createProduct.getProductId());
        return productDBService.create(createProduct);
    }

    @Override
    public ProductDto updateProduct(UpdateProduct updateProduct) {
        return productDBService.update(updateProduct);
    }

    @Override
    public ProductDto terminateProduct(String productId) {
        return productDBService.terminate(productId);
    }

    @Override
    public Page<ProductDto> inquireProduct(ProductFilter productFilter, Pageable pageable) {
        return productDBService.get(productFilter, pageable);
    }
}
