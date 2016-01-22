-- DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
	firstname VARCHAR(30) NOT NULL,
	lastname VARCHAR(30) NOT NULL,
	email VARCHAR(70) PRIMARY KEY,
	phone VARCHAR(20) NOT NULL,
	city VARCHAR(35) NOT NULL,
	province VARCHAR(35),
	country VARCHAR(4),
	hiredate DATE NOT NULL,
	religion VARCHAR(50),
	language VARCHAR(50),
	FOREIGN KEY (country,religion) REFERENCES religion(country,name),
	FOREIGN KEY (country,language) REFERENCES language(country,name)
);

