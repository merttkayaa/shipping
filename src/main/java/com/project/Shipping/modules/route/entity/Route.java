package com.project.Shipping.modules.route.entity;

import com.project.Shipping.modules.product.model.enums.LocationType;
import com.project.Shipping.modules.route.model.enums.RouteStatusType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "route", schema = "route")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Type(type = "jsonb")
    private Map<Integer, LocationType> routeMap;
    @Enumerated(EnumType.STRING)
    private RouteStatusType status = RouteStatusType.ACTIVE;
    private String routeName;
    private int locationSize;
}
