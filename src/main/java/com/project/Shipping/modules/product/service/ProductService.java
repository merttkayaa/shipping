package com.project.Shipping.modules.product.service;

import com.project.Shipping.modules.product.model.dto.ProductDto;
import com.project.Shipping.modules.product.model.request.CreateProduct;
import com.project.Shipping.modules.product.model.request.ProductFilter;
import com.project.Shipping.modules.product.model.request.UpdateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDto createProduct(CreateProduct createProduct);

    ProductDto updateProduct(UpdateProduct updateProduct);

    ProductDto terminateProduct(String productId);

    Page<ProductDto> inquireProduct(ProductFilter productFilter, Pageable pageable);
}
