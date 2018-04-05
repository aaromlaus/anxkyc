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
CONSTRAINT fk_anx_user_role FOREIGN KEY (role_id)
    REFERENCES role(role_id),
CONSTRAINT fk_anx_user_user_level FOREIGN KEY (user_level_id)
    REFERENCES user_level(user_level_id)
);

INSERT INTO user_level(user_level_name,description)
values ('level1','currently register as level 1'),
 ('level2','currently register as level 2'),
 ('level2pending','currently register as level 1 pending to level 2');

 INSERT INTO role(role_name,description)
values ('user','role for user'),
('admin','role for admin');