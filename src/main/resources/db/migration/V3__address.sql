CREATE TABLE `addresses`
(
    `id`            BIGINT(20)      AUTO_INCREMENT PRIMARY KEY,
    `city`          VARCHAR(255)    DEFAULT NULL,
    `postcode`      VARCHAR(255)    DEFAULT NULL,
    `street`        VARCHAR(255)    DEFAULT NULL,
    `partner_id`    BIGINT(20)      DEFAULT NULL,
    CONSTRAINT `fk_partner_address_id` FOREIGN KEY (`partner_id`) REFERENCES `cfagent`.`partners` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);