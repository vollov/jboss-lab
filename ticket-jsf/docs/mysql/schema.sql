-- CREATE DATABASE ticketsystem;
-- USE ticketsystem;
CREATE USER 'jboss'@'localhost' IDENTIFIED BY 'jboss';
GRANT ALL PRIVILEGES ON ticket.* TO 'jboss'@'localhost' WITH
GRANT OPTION;

CREATE TABLE seat_type (
id int(10) NOT NULL PRIMARY KEY auto_increment,
description varchar(50) NOT NULL,
price int(10) NOT NULL,
quantity int(10) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE seat (
id int(10) NOT NULL PRIMARY KEY auto_increment,
booked Boolean,
seat_id int(10),
FOREIGN KEY (seat_id) REFERENCES seat_type (id) ON DELETE CASCADE)
ENGINE=InnoDB;