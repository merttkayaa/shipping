package com.project.Shipping.modules.shipping.entity;

import com.project.Shipping.modules.shipping.model.entityDto.CancelledProductList;
import com.project.Shipping.modules.shipping.model.entityDto.CapacityOverloadedList;
import com.project.Shipping.modules.shipping.model.entityDto.ProductIdList;
import com.project.Shipping.modules.shipping.model.entityDto.UnMatchedProductIdList;
import com.project.Shipping.modules.shipping.model.enums.ShippingType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipping", schema = "shipping")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShippingType type;
    private String shipName;
    @Type(type = "jsonb")
    private ProductIdList productIdList;
    @Type(type = "jsonb")
    private UnMatchedProductIdList unMatchedProductIdList;
    @Type(type = "jsonb")
    private CapacityOverloadedList capacityOverloadedList;
    @Type(type = "jsonb")
    private CancelledProductList cancelledProductIdList;
    private String offBoardProductIdList;
    private LocalDate insertedAt;
    private LocalDate updatedAt;
}
