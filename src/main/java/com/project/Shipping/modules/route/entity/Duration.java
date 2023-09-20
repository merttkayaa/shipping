package com.project.Shipping.modules.route.entity;

import com.project.Shipping.modules.product.model.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "duration", schema = "route")
public class Duration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private LocationType targetLocation;
    @Enumerated(EnumType.STRING)
    private LocationType currentLocation;
    private Double durationTime;

}
