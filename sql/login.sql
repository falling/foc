create table login(
 login_id            int unsigned         NOT NULL PRIMARY KEY AUTO_INCREMENT,
 login_date          date                NOT NULL,
 user_id              int unsigned          NOT NULL,
 ip_id           varchar(15)          DEFAULT ' '
 
)