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
email_address VARCHAR(255),
phone_number VARCHAR(255),
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
phone_code_id INT,
CONSTRAINT fk_anx_user_role FOREIGN KEY (role_name)
    REFERENCES role(role_name),
CONSTRAINT fk_anx_useruser_leveluser_level_user_level FOREIGN KEY (user_level_name)
    REFERENCES user_level(user_level_name),
UNIQUE(email_address),
UNIQUE(phone_number)
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


CREATE TABLE user_level_details(
	user_level_details_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	anx_user_id INT,
	level_limit boolean default false
);

 INSERT INTO role(role_name,description)
values ('user','role for user'),
('admin','role for admin');

CREATE TABLE phone_code(
	phone_code_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	phone_code_name VARCHAR(50),
	country VARCHAR(50)
);
CREATE TABLE user_image(
	user_image_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	anx_user_id INT,
	link VARCHAR(255),
	user_level_details_id INT
);
INSERT INTO phone_code(phone_code_name,country)
VALUES
('+63','Philippines'),
('+66','Thailand'),
('+62','Indonesia'),
('+84','Vietnam'),
('+60','Malaysia'),
('+93','Afghanistan'),
('+355','Albania'),
('+213','Algeria'),
('+1 684','AmericanSamoa'),
('+376','Andorra'),
('+244','Angola'),
('+1 264','Anguilla'),
('+1268','Antigua and Barbuda'),
('+54','Argentina'),
('+374','Armenia'),
('+297','Aruba'),
('+61','Australia'),
('+43','Austria'),
('+994','Azerbaijan'),
('+1 242','Bahamas'),
('+973','Bahrain'),
('+880','Bangladesh'),
('+1 246','Barbados'),
('+375','Belarus'),
('+32','Belgium'),
('+501','Belize'),
('+229','Benin'),
('+1 441','Bermuda'),
('+975','Bhutan'),
('+591','Bolivia, Plurinational State of'),
('+387','Bosnia and Herzegovina'),
('+267','Botswana'),
('+55','Brazil'),
('+246','British Indian Ocean Territory'),
('+673','Brunei Darussalam'),
('+359','Bulgaria'),
('+226','Burkina Faso'),
('+257','Burundi'),
('+855','Cambodia'),
('+237','Cameroon'),
('+1','Canada'),
('+238','Cape Verde'),
('+ 345','Cayman Islands'),
('+236','Central African Republic'),
('+235','Chad'),
('+56','Chile'),
('+86','China'),
('+61','Christmas Island'),
('+61','Cocos (Keeling) Islands'),
('+57','Colombia'),
('+269','Comoros'),
('+242','Congo'),
('+243','Congo, The Democratic Republic of the'),
('+682','Cook Islands'),
('+506','Costa Rica'),
('+225','Cote d''Ivoire'),
('+385','Croatia'),
('+53','Cuba'),
('+537','Cyprus'),
('+420','Czech Republic'),
('+45','Denmark'),
('+253','Djibouti'),
('+1 767','Dominica'),
('+1 849','Dominican Republic'),
('+593','Ecuador'),
('+20','Egypt'),
('+503','El Salvador'),
('+240','Equatorial Guinea'),
('+291','Eritrea'),
('+372','Estonia'),
('+251','Ethiopia'),
('+500','Falkland Islands (Malvinas)'),
('+298','Faroe Islands'),
('+679','Fiji'),
('+358','Finland'),
('+33','France'),
('+594','French Guiana'),
('+689','French Polynesia'),
('+241','Gabon'),
('+220','Gambia'),
('+995','Georgia'),
('+49','Germany'),
('+233','Ghana'),
('+350','Gibraltar'),
('+30','Greece'),
('+299','Greenland'),
('+1 473','Grenada'),
('+590','Guadeloupe'),
('+1 671','Guam'),
('+502','Guatemala'),
('+44','Guernsey'),
('+224','Guinea'),
('+245','Guinea-Bissau'),
('+595','Guyana'),
('+509','Haiti'),
('+379','Holy See (Vatican City State)'),
('+504','Honduras'),
('+852','Hong Kong'),
('+36','Hungary'),
('+354','Iceland'),
('+91','India');