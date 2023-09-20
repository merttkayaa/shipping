package com.project.Shipping.modules.product.mapper;

import com.project.Shipping.modules.product.entity.Product;
import com.project.Shipping.modules.product.model.dto.ProductDto;
import com.project.Shipping.modules.product.model.request.CreateProduct;
import com.project.Shipping.modules.product.model.request.UpdateProduct;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

//@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto map(Product product);

    Product requestToEntity(CreateProduct createProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateToEntity(@MappingTarget Product product, UpdateProduct updateProduct);

}
