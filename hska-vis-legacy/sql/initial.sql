insert into `role` (`level1`, `type`) values(0, 'admin');
insert into `role` (`level1`, `type`) values(1, 'user');

insert into `customer` (`name`, `lastname`, `password`, `username`, `role`) values('admin', 'admin', 'admin', 'admin', 1);

Drop DATABASE IF EXISTS `product`;
Drop DATABASE IF EXISTS `category`;

CREATE DATABASE IF NOT EXISTS `product`;
CREATE DATABASE IF NOT EXISTS `category`;

CREATE USER 'user'@'%' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'user'@'%';

