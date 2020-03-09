CREATE TABLE IF NOT EXISTS `receipts` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT
);

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `country_origin` varchar(255) NOT NULL,
  `net_price` decimal(10,2) NOT NULL
);