CREATE TABLE `partners` (
                            `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                            `active` BIT(1) NULL DEFAULT NULL,
                            `company` BIT(1) NULL DEFAULT NULL,
                            `name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                            `agent_id` BIGINT(20) NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `FKprta738ldsyu95p58wwgev0wk` (`agent_id`) USING BTREE,
                            CONSTRAINT `FKprta738ldsyu95p58wwgev0wk` FOREIGN KEY (`agent_id`) REFERENCES `cfagent`.`agents` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);
