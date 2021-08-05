CREATE TABLE `agents`
(
   `id`         BIGINT(20)      AUTO_INCREMENT PRIMARY KEY ,
   `active`     BIT(1)          DEFAULT NULL,
   `mnb_number` VARCHAR(255)    DEFAULT NULL,
   `name`       VARCHAR(255)    DEFAULT NULL
);