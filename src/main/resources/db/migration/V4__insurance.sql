CREATE TABLE `insurances`
(
    `id`         BIGINT(20)   AUTO_INCREMENT PRIMARY KEY,
    `active`     BIT(1)       DEFAULT NULL,
    `company`    VARCHAR(255) DEFAULT NULL,
    `insurance`  VARCHAR(255) DEFAULT NULL,
    `type`       VARCHAR(255) DEFAULT NULL,
    `partner_id` BIGINT(20)   DEFAULT NULL,
    CONSTRAINT `fk_partner_insurance_id` FOREIGN KEY (`partner_id`) REFERENCES `cfagent`.`partners` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);