CREATE TABLE product_phone
(
    id            bigint(20) PRIMARY KEY AUTO_INCREMENT,
    name          varchar(255),
    producer      varchar(255),
    line_product  varchar(255),
    price         bigint(20),
    inserted_time datetime(6),
    updated_time  datetime(6)
)

INSERT INTO `product_phone`(`name`, `producer`, `line_product`, `price`, `inserted_time`, `updated_time`) VALUES
('Iphone 7','Apple','Iphone','500', '2023-06-29 00:00:00.000', '2023-06-29 00:00:00.000'),
('Iphone 8','Apple','Iphone','600', '2023-06-29 00:00:00.000', '2023-06-29 00:00:00.000'),
('Iphone 9','Apple','Iphone','700', '2023-06-29 00:00:00.000', '2023-06-29 00:00:00.000'),
('Iphone 10','Apple','Iphone','8000', '2023-06-29 00:00:00.000', '2023-06-29 00:00:00.000')
