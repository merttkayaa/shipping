package com.project.Shipping.modules.ship.entity;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.ship.model.ProductList;
import com.project.Shipping.modules.ship.model.enums.ShipStatus;
import com.project.Shipping.modules.ship.model.enums.ShipType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ship", schema = "ship")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private LocationType currentLocation;
    @Enumerated(EnumType.STRING)
    private ShipStatus status = ShipStatus.ACTIVE;
    @Enumerated(EnumType.STRING)
    private ShipType type;
    private Long routeId;
    private Double areaCapacity;
    private Double currentCapacity;
    private Double currentWeight;
    private Double maxWeight;
    private String shipName;
    @Type(type = "jsonb")
    private ProductList productList;
}
