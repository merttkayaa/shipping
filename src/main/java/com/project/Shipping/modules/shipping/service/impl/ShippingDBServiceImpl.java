package com.project.Shipping.modules.shipping.service.impl;

import com.project.Shipping.modules.shipping.entity.Shipping;
import com.project.Shipping.modules.shipping.mapper.ShippingMapper;
import com.project.Shipping.modules.shipping.model.entityDto.SaveOnBoard;
import com.project.Shipping.modules.shipping.model.entityDto.ShippingCancel;
import com.project.Shipping.modules.shipping.model.enums.ShippingType;
import com.project.Shipping.modules.shipping.repository.ShippingRepository;
import com.project.Shipping.modules.shipping.service.ShippingDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShippingDBServiceImpl implements ShippingDBService {
    private final ShippingRepository repository;
    private final ShippingMapper mapper;

    @Override
    @Transactional
    public void saveOnBoard(SaveOnBoard saveOnBoard) {
        Shipping entity = mapper.onBoardToEntity(saveOnBoard);
        entity.setUpdatedAt(LocalDate.now());
        entity.setInsertedAt(LocalDate.now());
        entity.setType(ShippingType.ONBOARD);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void cancelShipping(ShippingCancel shippingCancel) {
        Shipping entity = mapper.cancelToEntity(shippingCancel, LocalDate.now(), ShippingType.CANCELLED);
        repository.save(entity);

    }

    @Override
    public List<Shipping> getCancelledShipping() {
        Optional<List<Shipping>> optionalEntity = repository.findAllByType(ShippingType.CANCELLED);
        return optionalEntity.orElse(null);
    }

    @Override
    public void offBoard(String shipName, String productId) {
        Shipping entity = mapper.offBoardToEntity(shipName, productId, LocalDate.now(), ShippingType.DELIVERED);
        repository.save(entity);
    }

    @Override
    public List<Shipping> getOnBoardedShipping() {
        Optional<List<Shipping>> optionalEntity = repository.findAllByType(ShippingType.ONBOARD);
        return optionalEntity.orElse(null);
    }
}
