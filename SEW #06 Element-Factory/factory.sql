DROP DATABASE IF EXISTS factory;
CREATE DATABASE factory;

GRANT ALL PRIVILEGES ON factory.* TO 'factory'@'%' IDENTIFIED BY 'factory';

USE factory;

CREATE TABLE element (
	id INT AUTO_INCREMENT PRIMARY KEY,
	type VARCHAR(50),
	value VARCHAR(100)
);

INSERT INTO element VALUES (DEFAULT, "adapter", "mysql");
INSERT INTO element VALUES (DEFAULT, "adapter", "postgresql");
INSERT INTO element VALUES (DEFAULT, "adapter", "db2");
INSERT INTO element VALUES (DEFAULT, "classname", "Propel\Runtime\Connection\ConnectionWrapper1");
INSERT INTO element VALUES (DEFAULT, "classname", "Propel\Runtime\Connection\ConnectionWrapper2");
INSERT INTO element VALUES (DEFAULT, "classname", "Propel\Runtime\Connection\ConnectionWrapper3");
INSERT INTO element VALUES (DEFAULT, "user", "Stefan Erceg");
INSERT INTO element VALUES (DEFAULT, "user", "Michael Borko");
INSERT INTO element VALUES (DEFAULT, "user", "Erhard List");
INSERT INTO element VALUES (DEFAULT, "password", "bla1");
INSERT INTO element VALUES (DEFAULT, "password", "bla2");
INSERT INTO element VALUES (DEFAULT, "password", "bla3");

