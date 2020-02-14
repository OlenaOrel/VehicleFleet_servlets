drop schema if exists vehicle_fleet;
create schema vehicle_fleet character set utf8 collate utf8_general_ci;
use vehicle_fleet;

create table user
(
    id                Int auto_increment,
    first_name        varchar(20)        not null,
    last_name         varchar(20)        not null,
    origin_first_name varchar(20)        not null,
    origin_last_name  varchar(20)        not null,
    email             varchar(65) unique not null,
    password          varchar(255)       not null,
    `role`            varchar(25),
    primary key (id)
);

create table bus
(
    id            int auto_increment,
    mark          varchar(50)        not null,
    license_plate varchar(20) unique not null,
    primary key (id)
);

create table route
(
    id                     int auto_increment,
    number                 int unique  not null,
    departure_from_city    varchar(50) not null,
    departure_from_city_uk varchar(50) not null,
    arrival_to_city        varchar(50) not null,
    arrival_to_city_uk     varchar(50) not null,
    primary key (id)
);

create table bus_driver
(
    id        int auto_increment,
    driver_id int,
    bus_id    int,
    primary key (id),
    foreign key (driver_id) references user (id),
    foreign key (bus_id) references bus (id),
    unique key `bus_driver` (driver_id, bus_id)
);

create table appointment
(
    id        int auto_increment,
    route_id  int,
    bus_id    int,
    driver_id int,
    `date`    date,
    status    varchar(50) not null,
    primary key (id),
    foreign key (route_id) references route (id),
    foreign key (bus_id) references bus (id),
    foreign key (driver_id) references user (id),
    unique key `appointment1` (driver_id, date)
);