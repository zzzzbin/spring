CREATE TABLE IF NOT EXISTS employee (
id VARCHAR (50) PRIMARY KEY ,
name VARCHAR (50),
age INT
);

/* User master */
CREATE TABLE IF NOT EXISTS m_user(
    user_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR (100),
    user_name VARCHAR (50),
    birthday DATE,
    age INT,
    gender INT,
    department_id INT,
    role VARCHAR (20)
);

/* Department master */
CREATE TABLE IF NOT EXISTS m_department(
    department_id INT PRIMARY KEY,
    department_name VARCHAR (50)
);

/* User master */
CREATE TABLE IF NOT EXISTS t_salary(
    user_id VARCHAR(50),
    year_months VARCHAR (50),
    salary INT,
    PRIMARY  KEY (user_id, year_months)
);

CREATE TABLE IF NOT EXISTS product_category (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS  m_product  (
   id  BIGINT(20) NOT NULL AUTO_INCREMENT,
   sku  VARCHAR(255) DEFAULT NULL,
   name  VARCHAR(255) DEFAULT NULL,
   description  VARCHAR(255) DEFAULT NULL,
   unit_price  DECIMAL(13,2) DEFAULT NULL,
   image_url  VARCHAR(255) DEFAULT NULL,
   active  BIT DEFAULT 1,
   units_in_stock  INT(11) DEFAULT NULL,
    date_created  DATETIME(6) DEFAULT NULL,
   last_updated  DATETIME(6) DEFAULT NULL,
   category_id  BIGINT(20) NOT NULL,
  PRIMARY KEY ( id )
)