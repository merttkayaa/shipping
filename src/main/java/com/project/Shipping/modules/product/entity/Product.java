package com.project.Shipping.modules.product.entity;

import com.project.Shipping.modules.product.model.enums.EmergencyType;
import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.product.model.enums.ProductStatus;
import com.project.Shipping.modules.product.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product", schema = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private Double temp;
    private Double weight;
    private int amount;
    private LocalDate targetDate;
    private Boolean timeSensitive;
    private Boolean tempSensitive;
    @Enumerated(EnumType.STRING)
    private LocationType targetLocation;
    @Enumerated(EnumType.STRING)
    private LocationType currentLocation;
    @Enumerated(EnumType.STRING)
    private EmergencyType emergency;
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;
    private String productId;
    private String shipName;
    private Boolean isOnShip = false;
    private Double productArea;
}
