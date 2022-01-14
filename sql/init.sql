Drop DATABASE `product`;
Drop DATABASE `category`;

CREATE DATABASE IF NOT EXISTS `product`;
CREATE DATABASE IF NOT EXISTS `category`;

CREATE USER 'user'@'%' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'user'@'%';
