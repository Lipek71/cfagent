CREATE TABLE `insurances` (
                              `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                              `active` BIT(1) NULL DEFAULT NULL,
                              `company` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                              `insurance` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                              `type` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                              `partner_id` BIGINT(20) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `FKcqomw06hpo9kjohc8vjs1m5ec` (`partner_id`) USING BTREE,
                              CONSTRAINT `FKcqomw06hpo9kjohc8vjs1m5ec` FOREIGN KEY (`partner_id`) REFERENCES `cfagent`.`partners` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);