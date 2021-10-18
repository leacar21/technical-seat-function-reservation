-- CREATE SCHEMA IF NOT EXISTS `reservations` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- USE `reservations`;

USE `heroku_ece0f54a09b5458`;

CREATE TABLE IF NOT EXISTS `portal`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `version` BIGINT NOT NULL,
    `code` BINARY(16) NOT NULL,
    `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY UK_portal_code (`code`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `reservation`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `version` BIGINT NOT NULL,
    `code` BINARY(16) NOT NULL,
    `ticket_number` VARCHAR(128) NOT NULL,
    `customer_dni` VARCHAR(32) NOT NULL,
    `customer_name` VARCHAR(32) NOT NULL,
    `portal_id` BIGINT NOT NULL,
    CONSTRAINT `FK_reservation_portal` FOREIGN KEY (`portal_id`) REFERENCES `portal` (`id`),
    PRIMARY KEY (`id`),
    UNIQUE KEY UK_reservation_code (`code`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `reservation_item`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `version` BIGINT NOT NULL,
    `code` BINARY(16) NOT NULL,
    `price_paid` DECIMAL(19,4) NOT NULL,
    `section_seat_code` BINARY(16) NOT NULL,
    `reservation_id` BIGINT NOT NULL,
    CONSTRAINT `FK_reservation_item_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`),
    PRIMARY KEY (`id`),
    UNIQUE KEY UK_reservation_item_code (`code`),
    UNIQUE KEY UK_reservation_item_section_seat_code (`section_seat_code`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
