CREATE TABLE role (
role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
role_name TEXT,
description TEXT
);


CREATE TABLE user_level(
user_level_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_level_name TEXT,
description TEXT
);

CREATE TABLE anx_user(
anx_user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
first_name TEXT,
middle_name TEXT,
last_name TEXT,
username TEXT,
password TEXT,
role_id INT,
user_level_id INT,
house_number VARCHAR(255),
street VARCHAR(255),
city VARCHAR(255),
province VARCHAR(100),
country VARCHAR(100),
postal_code VARCHAR(100),
source_of_fund VARCHAR(100),
birth_date VARCHAR(100),
CONSTRAINT fk_anx_user_role FOREIGN KEY (role_id)
    REFERENCES role(role_id),
CONSTRAINT fk_anx_user_user_level FOREIGN KEY (user_level_id)
    REFERENCES user_level(user_level_id)
);

INSERT INTO user_level(user_level_name,description)
values ('level1','Level 1'),
 ('level2','Level 2'),
 ('level2pending','level 1 pending to level 2');
 
 CREATE TABLE level(
	level_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,	
	description TEXT,
	requirement TEXT,
	cash_in VARCHAR(100),
	cash_out VARCHAR(100),
	enabled boolean default false
);
INSERT INTO level(description,requirement,cash_in,cash_out,enabled)
values ('Level 1','Phone or email verification','2,000 PHP','0 PHP',true),
('Level 2','ID, selfie, phone and email verification','50,000 PHP','50,000 PHP',true),
('Level 3','Address verification','400,000 PHP','400,000 PHP',true),
('Level 4','Custom limits application','Custom (up to 5 million PHP)','Custom(up to 5 million PHP)',true);


CREATE TABLE level_user(
	level_user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	anx_user_id INT,
	level_limit boolean default false
);

 INSERT INTO role(role_name,description)
values ('user','role for user'),
('admin','role for admin');