create table log(
 log_id            int unsigned         NOT NULL PRIMARY KEY AUTO_INCREMENT,
 log_date          date                NOT NULL,
 o_id              int unsigned          NOT NULL,
 operating_user    int unsigned          NOT NULL,
 fields            varchar(10)          DEFAULT ' ',
 operating         varchar(10)         DEFAULT ' ',
 old               varchar(100)           DEFAULT ' ', 
 new               varchar(100)           DEFAULT ' '
)
