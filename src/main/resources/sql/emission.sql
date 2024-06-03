CREATE TABLE IF NOT EXISTS `emission` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `country` VARCHAR(255) NOT NULL,
    `year` INT NOT NULL,
    `emissions` DOUBLE NOT NULL,
    `approved_by` INT,
    FOREIGN KEY (`approved_by`) REFERENCES `user`(`id`)
);
