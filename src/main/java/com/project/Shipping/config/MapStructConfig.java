package com.project.Shipping.config;

import com.project.Shipping.modules.product.mapper.ProductMapper;
import com.project.Shipping.modules.route.mapper.RouteMapper;
import com.project.Shipping.modules.ship.mapper.ShipMapper;
import com.project.Shipping.modules.shipping.mapper.ShippingMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {
    @Bean
    public ProductMapper productMapper() {
        return Mappers.getMapper(ProductMapper.class);
    }

    @Bean
    public ShipMapper shipMapper() {
        return Mappers.getMapper(ShipMapper.class);
    }

    @Bean
    public RouteMapper routeMapper() {
        return Mappers.getMapper(RouteMapper.class);
    }

    @Bean
    public ShippingMapper shippingMapper() {
        return Mappers.getMapper(ShippingMapper.class);
    }
}
