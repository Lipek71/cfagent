CREATE TABLE `addresses` (
                             `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                             `city` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                             `postcode` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                             `street` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                             `partner_id` BIGINT(20) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE,
                             INDEX `FKrq4wwx4aydfmou5mw9my300me` (`partner_id`) USING BTREE,
                             CONSTRAINT `FKrq4wwx4aydfmou5mw9my300me` FOREIGN KEY (`partner_id`) REFERENCES `cfagent`.`partners` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);