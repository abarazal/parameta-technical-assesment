CREATE DATABASE `parameta` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

CREATE USER 'parameta_dev'@'localhost' IDENTIFIED BY 'Bb123456$';

GRANT ALL PRIVILEGES ON parameta.* TO 'parameta_dev'@'localhost';

FLUSH PRIVILEGES;

CREATE TABLE `employee` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_names` varchar(255) DEFAULT NULL,
  `last_names` varchar(255) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `document_number` int(10) unsigned DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `hire_date` datetime DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

