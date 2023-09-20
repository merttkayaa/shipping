package com.project.Shipping.modules.product.service.impl;

import com.project.Shipping.exception.RecordFound;
import com.project.Shipping.exception.RecordNotFound;
import com.project.Shipping.modules.product.entity.Product;
import com.project.Shipping.modules.product.entity.QProduct;
import com.project.Shipping.modules.product.mapper.ProductMapper;
import com.project.Shipping.modules.product.model.dto.ProductDto;
import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.product.model.enums.ProductStatus;
import com.project.Shipping.modules.product.model.request.CreateProduct;
import com.project.Shipping.modules.product.model.request.ProductFilter;
import com.project.Shipping.modules.product.model.request.UpdateProduct;
import com.project.Shipping.modules.product.repository.ProductRepository;
import com.project.Shipping.modules.product.service.ProductDBService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDBServiceImpl implements ProductDBService {
    private final ProductMapper mapper;
    private final ProductRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ProductDto create(CreateProduct createProduct) {
        Product product = mapper.requestToEntity(createProduct);
        Product savedProduct = repository.save(product);
        return mapper.map(savedProduct);
    }

    @Override
    @Transactional
    public ProductDto update(UpdateProduct updateProduct) {
        Optional<Product> existingProduct = Optional.ofNullable(repository.findByProductId(updateProduct.getProductId()).orElseThrow(() ->
                new RecordNotFound("product not found")));
        Product updatedProduct = mapper.updateToEntity(existingProduct.get(), updateProduct);
        return mapper.map(repository.save(updatedProduct));
    }

    @Override
    @Transactional
    public ProductDto terminate(String productId) {
        Optional<Product> optionalEntity = Optional.ofNullable(repository.findByProductId(productId).orElseThrow(() ->
                new RecordNotFound("product not found")));
        Product existingProduct = optionalEntity.get();
        existingProduct.setStatus(ProductStatus.TERMINATED);
        return mapper.map(repository.save(existingProduct));

    }

    @Override
    public Page<ProductDto> get(ProductFilter productFilter, Pageable pageable) {
        Assert.notNull(productFilter, "query object cannot be empty");
        BooleanBuilder builder = new BooleanBuilder();
        QProduct qProduct = QProduct.product;

        if (Objects.nonNull(productFilter.getProductId()))
            builder.and(qProduct.productId.eq(productFilter.getProductId()));
        if (Objects.nonNull(productFilter.getProductType()))
            builder.and(qProduct.type.eq(productFilter.getProductType()));
        if (Objects.nonNull(productFilter.getEmergency()))
            builder.and(qProduct.emergency.eq(productFilter.getEmergency()));
        if (Objects.nonNull(productFilter.getTargetDate()))
            builder.and(qProduct.targetDate.eq(productFilter.getTargetDate()));
        if (Objects.nonNull(productFilter.getTargetLocation()))
            builder.and(qProduct.targetLocation.eq(productFilter.getTargetLocation()));
        if (Objects.nonNull(productFilter.getCurrentLocation()))
            builder.and(qProduct.currentLocation.eq(productFilter.getCurrentLocation()));
        if (Objects.nonNull(productFilter.getTempSensitive()))
            builder.and(qProduct.tempSensitive.eq(productFilter.getTempSensitive()));
        if (Objects.nonNull(productFilter.getTimeSensitive()))
            builder.and(qProduct.timeSensitive.eq(productFilter.getTimeSensitive()));
        if (Objects.nonNull(productFilter.getTargetDate()))
            builder.and(qProduct.targetDate.eq(productFilter.getTargetDate()));

        Predicate predicate = builder.getValue();
        return repository.findAll(predicate, pageable).map(mapper::map);
    }

    @Override
    public void checkByProductId(String productId) {
        Optional<Product> optionalEntity = repository.findByProductId(productId);
        if (optionalEntity.isPresent())
            throw new RecordFound("Already Exists");
    }

    @Override
    public ProductDto getByProductId(String productId) {
        Optional<Product> optionalEntity = Optional.ofNullable(repository.findByProductId(productId).orElseThrow(() ->
                new RecordNotFound("product not found")));
        return mapper.map(optionalEntity.get());
    }

    @Override
    @Transactional
    public void onBoardProduct(String shipName, List<String> productIdList) {
        List<Product> entityList = new ArrayList<>();
        productIdList.forEach(item -> {
            Optional<Product> optionalEntity = Optional.ofNullable(repository.findByProductId(item).orElseThrow(() ->
                    new RecordNotFound("product not found")));
            Product existingEntity = optionalEntity.get();
            existingEntity.setIsOnShip(true);
            existingEntity.setShipName(shipName);
            entityList.add(existingEntity);
        });
        repository.saveAll(entityList);
    }

    @Override
    @Transactional
    public void updateTargetLocation(String productId, LocationType targetLocation) {
        Optional<Product> optionalEntity = Optional.ofNullable(repository.findByProductId(productId).orElseThrow(() ->
                new RecordNotFound("product not found")));
        Product existingEntity = optionalEntity.get();
        existingEntity.setTargetLocation(targetLocation);
        repository.save(existingEntity);
    }

    @Override
    public void updateProductStatus(String productId) {
        Product existingEntity = repository.findByProductId(productId).get();
        existingEntity.setStatus(ProductStatus.COMPLETED);
        repository.save(existingEntity);
    }
}
