CREATE TABLE role (
role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
role_name VARCHAR(255) NOT NULL,
description TEXT,
UNIQUE(role_name)
);


CREATE TABLE user_level(
user_level_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_level_name VARCHAR(255) NOT NULL,
description TEXT,
UNIQUE(user_level_name)
);

CREATE TABLE anx_user(
anx_user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
first_name TEXT,
middle_name TEXT,
last_name TEXT,
username VARCHAR(255),
password TEXT,
role_name VARCHAR(255),
user_level_name VARCHAR(255),
house_number VARCHAR(255),
street VARCHAR(255),
city VARCHAR(255),
province VARCHAR(100),
country VARCHAR(100),
postal_code VARCHAR(100),
source_of_fund VARCHAR(100),
birth_date VARCHAR(100),
CONSTRAINT fk_anx_user_role FOREIGN KEY (role_name)
    REFERENCES role(role_name),
CONSTRAINT fk_anx_useruser_leveluser_level_user_level FOREIGN KEY (user_level_name)
    REFERENCES user_level(user_level_name),
UNIQUE(username)
);

INSERT INTO user_level(user_level_name,description)
values ('level1','Registered as Level 1'),
 ('level2','Registered as Level 2'),
 ('level2pending','Pending Level 2 Approval');
 
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