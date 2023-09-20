package com.project.Shipping.modules.ship.service.impl;

import com.project.Shipping.exception.RecordFound;
import com.project.Shipping.exception.RecordNotFound;
import com.project.Shipping.modules.ship.entity.QShip;
import com.project.Shipping.modules.ship.entity.Ship;
import com.project.Shipping.modules.ship.mapper.ShipMapper;
import com.project.Shipping.modules.ship.model.ProductList;
import com.project.Shipping.modules.ship.model.ShipDto;
import com.project.Shipping.modules.ship.model.enums.ShipStatus;
import com.project.Shipping.modules.ship.model.request.CreateShip;
import com.project.Shipping.modules.ship.model.request.InquireFilter;
import com.project.Shipping.modules.ship.model.request.UpdateShip;
import com.project.Shipping.modules.ship.repository.ShipRepository;
import com.project.Shipping.modules.ship.service.ShipDBService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipDBServiceImpl implements ShipDBService {
    private final ShipRepository repository;
    private final ShipMapper shipMapper;

    @Override
    public ShipDto create(CreateShip createShip) {
        Ship entity = shipMapper.map(createShip);
        Ship savedEntity = repository.save(entity);
        return shipMapper.entityToDto(savedEntity);
    }

    @Override
    public ShipDto update(UpdateShip updateShip) {
        //TODO eğer status terminate ise hata dön
        Optional<Ship> existingEntity = Optional.ofNullable(repository.findByShipName(updateShip.getShipName()).orElseThrow(() ->
                new RecordNotFound("ship not found")));
        Ship savedEntity = repository.save(shipMapper.updateToEntity(existingEntity.get(), updateShip));
        return shipMapper.entityToDto(savedEntity);
    }

    @Override
    public ShipDto terminate(String shipName) {
        //TODO eğer status terminate ise hata dön
        Optional<Ship> optionalEntity = Optional.ofNullable(repository.findByShipName(shipName).orElseThrow(() ->
                new RecordNotFound("ship not found")));
        Ship existingEntity = optionalEntity.get();
        existingEntity.setStatus(ShipStatus.TERMINATED);
        return shipMapper.entityToDto(repository.save(existingEntity));
    }

    @Override
    public Page<ShipDto> get(InquireFilter inquireFilter, Pageable pageable) {
        Assert.notNull(inquireFilter, "query object cannot be empty");
        BooleanBuilder builder = new BooleanBuilder();
        QShip qShip = QShip.ship;

        if (Objects.nonNull(inquireFilter.getShipName())) {
            builder.and(qShip.shipName.eq(inquireFilter.getShipName()));
            Predicate predicate = builder.getValue();
            return repository.findAll(predicate, pageable).map(shipMapper::entityToDto);
        }
        if (Objects.nonNull(inquireFilter.getStatus()))
            builder.and(qShip.status.eq(inquireFilter.getStatus()));
        if (Objects.nonNull(inquireFilter.getCurrentLocation()))
            builder.and(qShip.currentLocation.eq(inquireFilter.getCurrentLocation()));
        if (Objects.nonNull(inquireFilter.getRouteId()))
            builder.and(qShip.routeId.eq(inquireFilter.getRouteId()));
        if (Objects.nonNull(inquireFilter.getMaxWeight()))
            builder.and(qShip.maxWeight.eq(inquireFilter.getMaxWeight()));
        if (Objects.nonNull(inquireFilter.getMaxWeight()))
            builder.and(qShip.areaCapacity.eq(inquireFilter.getAreaCapacity()));
        if (Objects.nonNull(inquireFilter.getProductList()))
            builder.and(qShip.productList.eq(inquireFilter.getProductList()));

        Predicate predicate = builder.getValue();
        return repository.findAll(predicate, pageable).map(shipMapper::entityToDto);
    }

    @Override
    public void checkByShipName(String shipName) {
        Optional<Ship> optionalEntity = repository.findByShipName(shipName);
        if (optionalEntity.isPresent())
            throw new RecordFound("Already exists");
    }

    @Override
    public ShipDto getByShipName(String shipName) {
        Optional<Ship> optionalEntity = Optional.ofNullable(repository.findByShipName(shipName).orElseThrow(() ->
                new RecordNotFound("ship not found")));
        return shipMapper.entityToDto(optionalEntity.get());
    }

    @Override
    public void updateCurrentStorage(ShipDto shipDto, List<String> productIdList) {
        ProductList productList = new ProductList();
        Optional<Ship> optionalEntity = Optional.ofNullable(repository.findByShipName(shipDto.getShipName()).orElseThrow(() ->
                new RecordNotFound("ship not found")));
        Ship existingShip = optionalEntity.get();
        existingShip.setCurrentWeight(shipDto.getCurrentWeight());
        existingShip.setCurrentCapacity(shipDto.getCurrentCapacity());
        productList.setProductList(productIdList);
        repository.save(existingShip);
    }

    @Override
    public void updateProductList(String shipName, String productId, Double area, Double weight) {
        // Can't be null since it's already checked before calling this method
        Ship existingEntity = repository.findByShipName(shipName).get();
        existingEntity.getProductList().getProductList().remove(productId);
        existingEntity.setCurrentCapacity(existingEntity.getCurrentCapacity() - area);
        existingEntity.setCurrentWeight(existingEntity.getCurrentWeight() - weight);
        repository.save(existingEntity);

    }
}
