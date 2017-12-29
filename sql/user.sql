create table user (
 user_id               int unsigned         NOT NULL PRIMARY KEY AUTO_INCREMENT,
 user_name            varchar(10)          DEFAULT ' ',
 pwd              varchar(20)          DEFAULT ' ',
 name             varchar(20)          DEFAULT ' ',
 power            varchar(20)          DEFAULT ' ',
 reg_date         date                 NOT NULL,
 remarks             varchar(100)         DEFAULT ' ',
 del                 varchar(5)           DEFAULT ' ' 
) CHARSET = 'utf8';