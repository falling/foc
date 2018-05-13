/*
Navicat MySQL Data Transfer

Source Server         : foc
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : foc

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-01-09 20:07:50
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hq
-- ----------------------------
DROP TABLE IF EXISTS `hq`;
CREATE TABLE hq
(
  hq_id            INT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  ch_name          VARCHAR(255) DEFAULT ' '  NOT NULL,
  used_name        VARCHAR(255) DEFAULT ' '  NULL,
  py_name          VARCHAR(255) DEFAULT ' ' NULL,
  sex              VARCHAR(5) DEFAULT ' '   NULL,
  ethnicity        VARCHAR(255) DEFAULT ' ' NULL,
  passport_no      VARCHAR(255) DEFAULT ' ' NULL,
  date_birth       DATETIME                 NULL,
  id_num           VARCHAR(255) DEFAULT ' ' NULL,
  o_tel            VARCHAR(255) DEFAULT ' ' NULL,
  cn_tel           VARCHAR(255) DEFAULT ' ' NULL,
  wechat           VARCHAR(255) DEFAULT ' '  NULL,
  mail             VARCHAR(255) DEFAULT ' '  NULL,
  qq_num           VARCHAR(255) DEFAULT ' '  NULL,
  native_place     VARCHAR(255) DEFAULT ' '  NULL,
  nationality      VARCHAR(255) DEFAULT ' '  NULL,
  residence        VARCHAR(255) DEFAULT ' '  NULL,
  residence_detail VARCHAR(255) DEFAULT ' '  NULL,
  cn_residence     VARCHAR(255) DEFAULT ' '  NULL,
  present_industry VARCHAR(255) DEFAULT ' '  NULL,
  com_name         VARCHAR(255) DEFAULT ' '  NULL,
  position         VARCHAR(255) DEFAULT ' '  NULL,
  registrant       INT(20)                  NULL,
  photo            VARCHAR(255) DEFAULT ' ' NULL,
  reg_date         DATETIME                 NULL,
  social_services  VARCHAR(255)              NULL,
  remarks          VARCHAR(255) DEFAULT ' ' NULL,
  del              VARCHAR(5) DEFAULT ' '   NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE log
(
  log_id         INT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  log_date       DATETIME                NOT NULL,
  identity       VARCHAR(20)             NULL,
  o_id           INT UNSIGNED            NULL,
  operating_user INT UNSIGNED            NOT NULL,
  operating      VARCHAR(10) DEFAULT ' ' NOT NULL,
  old_value      TEXT                    NULL,
  new_value      TEXT                    NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE login
(
  login_id   INT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  login_date DATETIME                NOT NULL,
  user_id    INT UNSIGNED            NOT NULL,
  ip_id      VARCHAR(15) DEFAULT ' ' NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for lx
-- ----------------------------
DROP TABLE IF EXISTS `lx`;
CREATE TABLE lx
(
  lx_id            INT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  ch_name          VARCHAR(255) DEFAULT ' '  NOT NULL,
  used_name        VARCHAR(255) DEFAULT ' '  NULL,
  py_name          VARCHAR(255) DEFAULT ' '  NULL,
  sex              VARCHAR(5) DEFAULT ' '    NULL,
  ethnicity        VARCHAR(255) DEFAULT ' '  NULL,
  passport_no      VARCHAR(255) DEFAULT ' '  NULL,
  date_birth       DATETIME                  NULL,
  id_num           VARCHAR(255) DEFAULT ' '  NULL,
  o_tel            VARCHAR(255) DEFAULT ' '  NULL,
  cn_tel           VARCHAR(255) DEFAULT ' '  NULL,
  wechat           VARCHAR(255) DEFAULT ' '  NULL,
  mail             VARCHAR(255) DEFAULT ' '  NULL,
  qq_num           VARCHAR(255) DEFAULT ' '  NULL,
  native_place     VARCHAR(255) DEFAULT ' '  NULL,
  nationality      VARCHAR(255) DEFAULT ' '  NULL,
  residence        VARCHAR(255) DEFAULT ' '  NULL,
  residence_detail VARCHAR(255) DEFAULT ' '  NULL,
  cn_residence     VARCHAR(255) DEFAULT ' '  NULL,
  present_industry VARCHAR(255) DEFAULT ' '  NULL,
  com_name         VARCHAR(255) DEFAULT ' '  NULL,
  position         VARCHAR(255) DEFAULT ' '  NULL,
  registrant       INT(20)                  NULL,
  photo            VARCHAR(255) DEFAULT ' ' NULL,
  reg_date         DATETIME                 NOT NULL,
  remarks          VARCHAR(255) DEFAULT ' ' NULL,
  en_cname         VARCHAR(255) DEFAULT ' ' NULL,
  ch_cname         VARCHAR(255) DEFAULT ' ' NULL,
  degree           VARCHAR(255) DEFAULT ' ' NULL,
  social_services  VARCHAR(255) DEFAULT ' ' NULL,
  family_name  VARCHAR(255) DEFAULT ' ' NULL,
  family_tel   VARCHAR(255) DEFAULT ' ' NULL,
  del              VARCHAR(5) DEFAULT ' '   NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for qj
-- ----------------------------
-- auto-generated definition
DROP TABLE IF EXISTS `qj`;
CREATE TABLE qj
(
  qj_id       INT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  ch_name     VARCHAR(255) DEFAULT ' ' NOT NULL,
  sex         VARCHAR(5) DEFAULT ' '  NOT NULL,
  ethnicity   VARCHAR(255) DEFAULT ' ' NOT NULL,
  passport_no VARCHAR(255) DEFAULT ' ' NOT NULL,
  tel1        VARCHAR(255) DEFAULT ' ' NOT NULL,
  tel2        VARCHAR(255) DEFAULT ' ' NOT NULL,
  del         VARCHAR(5) DEFAULT ' '  NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE user
(
  user_id   INT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  user_name VARCHAR(255) DEFAULT ' '  NOT NULL,
  pwd       VARCHAR(255) DEFAULT ' '  NOT NULL,
  name      VARCHAR(255) DEFAULT ' '  NOT NULL,
  power     VARCHAR(255) DEFAULT ' '  NOT NULL,
  reg_date  DATETIME                 NOT NULL,
  remarks   VARCHAR(255) DEFAULT ' ' NOT NULL,
  del       VARCHAR(5) DEFAULT ' '   NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


INSERT INTO  user (user_name,pwd,name,power,reg_date,del) values ('root','123456','root','admin',now(),'0')

