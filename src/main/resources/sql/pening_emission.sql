CREATE TABLE IF NOT EXISTS `pending_emission` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `country` VARCHAR(255) NOT NULL,
    `year` INT NOT NULL,
    `emissions` DOUBLE NOT NULL,
    `submitted_by` INT,
    FOREIGN KEY (`submitted_by`) REFERENCES `user`(`id`)
);
