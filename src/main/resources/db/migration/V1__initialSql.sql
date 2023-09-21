CREATE SCHEMA product;
CREATE SCHEMA route;
CREATE SCHEMA ship;
CREATE SCHEMA shipping;

create table product.product
(
    id               bigint generated always as identity (minvalue 1 maxvalue 999999)
        primary key,
    type             varchar(10)      not null,
    weight           double precision not null,
    temp             double precision,
    amount           integer          not null,
    target_date      date,
    time_sensitive   boolean          not null,
    temp_sensitive   boolean          not null,
    target_location  varchar(100)     not null,
    current_location varchar(100)     not null,
    emergency        varchar(10)      not null,
    status           varchar(20),
    product_id       varchar(20),
    is_on_ship       boolean,
    product_area     double precision,
    ship_name        varchar(100)
);

create sequence product_id_seq
    START 1
    INCREMENT 1
    minvalue 1
    maxvalue 999999
    CACHE 1;

create table route.duration
(
    id               bigint generated always as identity (minvalue 1 maxvalue 999999)
        primary key,
    target_location  varchar(100) not null,
    current_location varchar(100) not null,
    duration_time    double precision
);

create sequence duration_id_seq
    START 1
    INCREMENT 1
    minvalue 1
    maxvalue 999999
    CACHE 1;

create table route.route
(
    id            bigint generated always as identity (minvalue 1 maxvalue 999999)
        primary key,
    route_map     jsonb        not null,
    status        varchar(20)  not null,
    route_name    varchar(100) not null,
    location_size smallint     not null
);

create sequence route_id_seq
    START 1
    INCREMENT 1
    minvalue 1
    maxvalue 999999
    CACHE 1;


create table ship.ship
(
    id               bigint generated always as identity (minvalue 1 maxvalue 999999)
        primary key,
    type             varchar(20)      not null,
    current_location varchar(100)     not null,
    status           varchar(20)      not null,
    route_id         bigint,
    area_capacity    double precision not null,
    max_weight       double precision not null,
    ship_name        varchar(100)     not null,
    product_list     jsonb,
    current_capacity double precision,
    current_weight   double precision
);

create sequence ship_id_seq
    START 1
    INCREMENT 1
    minvalue 1
    maxvalue 999999
    CACHE 1;

create table shipping.shipping
(
    id                         bigint generated always as identity (minvalue 1 maxvalue 999999)
        primary key,
    type                       varchar(10)  not null,
    ship_name                  varchar(100) not null,
    inserted_at                date,
    updated_at                 date,
    un_matched_product_id_list jsonb,
    capacity_overloaded_list   jsonb,
    product_id_list            jsonb,
    cancelled_product_id_list  jsonb,
    off_board_product_id_list  varchar(100)
);

create sequence shipping_id_seq
    START 1
    INCREMENT 1
    minvalue 1
    maxvalue 999999
    CACHE 1;