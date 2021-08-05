CREATE TABLE `agents` (
                          `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                          `active` BIT(1) NULL DEFAULT NULL,
                          `mnb_number` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                          `name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                          PRIMARY KEY (`id`) USING BTREE
);