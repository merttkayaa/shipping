package com.project.Shipping.controller;

import com.project.Shipping.modules.product.model.dto.ProductDto;
import com.project.Shipping.modules.product.model.request.CreateProduct;
import com.project.Shipping.modules.product.model.request.ProductFilter;
import com.project.Shipping.modules.product.model.request.UpdateProduct;
import com.project.Shipping.modules.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
//@Api(value = "Product API", description = "Product related operations")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ProductDto createProduct(@Valid @RequestBody CreateProduct createProduct) {
        // TODO requestentity kullan
        return productService.createProduct(createProduct);
    }

    @PutMapping()
    public ProductDto updateProduct(@Valid @RequestBody UpdateProduct updateProduct) {
        return productService.updateProduct(updateProduct);
    }

    @PutMapping("/terminate")
    public ProductDto terminateProduct(@PathVariable String productId) {
        return productService.terminateProduct(productId);

    }

    @GetMapping("/get")
    public Page<ProductDto> inquireProduct(@RequestBody ProductFilter productFilter, Pageable pageable) {
        return productService.inquireProduct(productFilter, pageable);
    }
}
