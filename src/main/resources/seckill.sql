drop database IF EXISTS secondskill;
create database IF NOT EXISTS secondskill charset utf8;
use secondskill;
create table user_info
(
id int AUTO_INCREMENT,
name varchar(64) NOT NULL,
gender tinyint DEFAULT 1 comment '1 boy, 2 girl',
age int,
telephone varchar(12),
register_mode varchar(10) comment 'byphone, bywechar, byalipay',
third_party_id varchar(64),
PRIMARY KEY (id)
);

create table user_password
(
id INT AUTO_INCREMENT,
encrypt_password varchar(128),
user_id int,
PRIMARY KEY (id)
);

ALTER TABLE user_info ADD UNIQUE (telephone);


INSERT INTO user_info(name, gender, age, telephone, register_mode, third_party_id) values ("Tom", 1, 23, 13541993278, 'byphone', 12345);

INSERT INTO user_password(encrypt_password, user_id) VALUES ("iamapassowd",1);

CREATE table item
(
id int AUTO_INCREMENT,
title varchar(64) NOT NULL,
price double default 0,
description varchar(500),
sales int default 0,
image_url varchar(128),
PRIMARY KEY (id)
);

INSERT INTO item(title, price, description, sales, image_url) VALUES ("",,,,'images/pic1223181629.png');
INSERT INTO item(title, price, description, sales, image_url) VALUES ("",,,,'images/pic1223181552.png');
INSERT INTO item(title, price, description, sales, image_url) VALUES ("",,,,'images/pic1223181614.png');

CREATE table item_stock
(
id INT AUTO_INCREMENT,
stock INT default 0,
item_id INT default 0,
PRIMARY KEY (id)
);

CREATE table order_info
(
id varchar(32),
user_id int,
item_id int,
item_price decimal(6,2) default 0,
amount int default 0,
price decimal(6,2),
PRIMARY KEY (id)
);


CREATE table sequence_info
(
name varchar(32) not null,
current_value int default 0,
step int default 0,
PRIMARY KEY (name)
);

insert into sequence_info values ("order_info", 0, 1);


CREATE table promo 
(
id INT AUTO_INCREMENT,
name varchar(128),
start_time datetime default '2000-00-00 00:00:00',
end_time datetime default '2000-00-00 00:00:00',
item_id int,
promo_price decimal(6,2),
PRIMARY KEY (id)
);

ALTER TABLE promo ADD UNIQUE (item_id);

insert into promo(name, start_time,end_time,item_id, promo_price) values ("手机抢购",now(), DATE_ADD(now(),interval 1 hour),1, 999.9);
insert into promo(name, start_time,end_time,item_id, promo_price) values ("手机抢购",DATE_ADD(now(),interval 1 hour), DATE_ADD(now(),interval 2 hour),2, 1.99);
insert into promo(name, start_time,end_time,item_id, promo_price) values ("手机抢购",DATE_ADD(now(),interval 1 hour), DATE_ADD(now(),interval 2 hour),3, 4.99);


-- update item set image_url='images/pic1223181629.png' where id=1;
-- update item set image_url='images/pic1223181552.png' where id=2;
-- update item set image_url='images/pic1223181614.png' where id=3;
-- update item_stock set stock=10 where id=1;