DROP TABLE IF EXISTS user;

CREATE TABLE test_user (
  id INT(11) unsigned NOT NULL AUTO_INCREMENT,
  first_name varchar(32) NOT NULL,
  last_name varchar(32) NOT NULL,
  phone varchar(24) NOT NULL,
  email varchar(32) UNIQUE NOT NULL,
  password varchar(32) NOT NULL,
  active TINYINT(1),
  PRIMARY KEY (id),
  KEY k_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user VALUES 
(1,'jenny','li','519-781-6118','jenny.lee.zhang@gmail.com','pwd',1),
(2,'annie','smith','519-234-6728','annie.smith@gmail.com','pwd',1),
(3,'luke','bright','519-781-6238','luke.bright@gmail.com','pwd',1);