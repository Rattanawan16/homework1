--drop table user;

create table user (
linear_id varchar(45) NOT NULL,
id_card_no varchar(45) NULL,
login_name varchar(45) NULL,
login_password varchar(100) NULL,
first_name_th varchar(45) NULL,
last_name_th varchar(45) NULL,
first_name_en varchar(45) NULL,
last_name_en varchar(45) NULL,

email varchar(45) NULL,
title varchar(50) NULL,
birth_date timestamp NOT NULL,
gender varchar(10) NULL,
marriage_status varchar(10) NULL,
nationality varchar(10) NULL,
mobile varchar(45) NULL,
telephone varchar(45) NULL,
occupation varchar(50) NULL,
company varchar(50) NULL,
user_type varchar(50) NULL,
user_subtype varchar(50) NULL,
login_latest_date timestamp NULL,
logout_latest_date timestamp NULL,

state varchar(45) NOT NULL,
status varchar(45) NOT NULL,
create_by varchar(45) NOT NULL,
create_date timestamp NOT NULL,
change_by varchar(45) NOT NULL,
change_date timestamp NOT NULL,
CONSTRAINT user_pkey PRIMARY KEY (linear_id),
CONSTRAINT user_un UNIQUE (login_name)
);


--drop table topic;

create table topic (
linear_id varchar(45) NOT NULL,
topic_subject varchar(100) NULL,
topic_detail text NULL,
visitor_amount numeric(10,2) NULL,
last_visitor_date timestamp NULL,
last_visitor_by varchar(45) NULL,

state varchar(45) NOT NULL,
status varchar(45) NOT NULL,
create_by varchar(45) NOT NULL,
create_date timestamp NOT NULL,
change_by varchar(45) NOT NULL,
change_date timestamp NOT NULL,
CONSTRAINT topic_pkey PRIMARY KEY (linear_id)
);