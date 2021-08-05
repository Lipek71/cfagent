CREATE TABLE `partners`
(
     `id`           BIGINT(20)      AUTO_INCREMENT PRIMARY KEY,
     `active`       BIT(1)          DEFAULT NULL,
     `company`      BIT(1)          DEFAULT NULL,
     `name`         VARCHAR(255)    DEFAULT NULL,
     `agent_id`     BIGINT(20)      DEFAULT NULL,
      CONSTRAINT `fk_agent_partner_id` FOREIGN KEY (`agent_id`) REFERENCES `cfagent`.`agents` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);