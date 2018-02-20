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
CREATE TABLE `hq` (
  `hq_id`            INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ch_name`          VARCHAR(20)      NOT NULL DEFAULT ' ',
  `used_name`        VARCHAR(20)               DEFAULT ' ',
  `py_name`          VARCHAR(50)      NOT NULL DEFAULT ' ',
  `sex`              VARCHAR(5)       NOT NULL DEFAULT ' ',
  `ethnicity`        VARCHAR(10)      NOT NULL DEFAULT ' ',
  `passport_no`      VARCHAR(10)      NOT NULL DEFAULT ' ',
  `date_expriy`      DATETIME         NOT NULL,
  `date_birth`       DATETIME         NOT NULL,
  `id_num`           VARCHAR(20)      NOT NULL DEFAULT ' ',
  `o_tel`            VARCHAR(20)               DEFAULT ' ',
  `cn_tel`           VARCHAR(20)               DEFAULT ' ',
  `cn_te2`           VARCHAR(20)               DEFAULT ' ',
  `wechat`           VARCHAR(20)               DEFAULT ' ',
  `mail`             VARCHAR(50)               DEFAULT ' ',
  `qq_num`           VARCHAR(20)               DEFAULT ' ',
  `native_place`     VARCHAR(50)               DEFAULT ' ',
  `nationality`      VARCHAR(50)               DEFAULT ' ',
  `residence`        VARCHAR(50)               DEFAULT ' ',
  `cn_residence`     VARCHAR(50)               DEFAULT ' ',
  `present_industry` VARCHAR(50)               DEFAULT ' ',
  `com_name`         VARCHAR(50)               DEFAULT ' ',
  `position`         VARCHAR(50)               DEFAULT ' ',
  `education`        VARCHAR(20)               DEFAULT ' ',
  `health`           VARCHAR(20)               DEFAULT ' ',
  `registrant`       INT(20),
  `photo`            VARCHAR(100)              DEFAULT ' ',
  `reg_date`         DATETIME,
  `remarks`          VARCHAR(100)              DEFAULT ' ',
  `del`              VARCHAR(5)                DEFAULT ' ',
  PRIMARY KEY (`hq_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id`         INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `log_date`       DATETIME         NOT NULL,
  `identity`       VARCHAR(255),
  `o_id`           INT(10) UNSIGNED,
  `operating_user` INT(10) UNSIGNED NOT NULL,
  `operating`      VARCHAR(255)     NOT NULL DEFAULT ' ',
  `old_value`      TEXT,
  `new_value`      TEXT,
  PRIMARY KEY (`log_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `login_id`   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login_date` DATETIME         NOT NULL,
  `user_id`    INT(10) UNSIGNED NOT NULL,
  `ip_id`      VARCHAR(255)     NOT NULL DEFAULT ' ',
  PRIMARY KEY (`login_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for lx
-- ----------------------------
DROP TABLE IF EXISTS `lx`;
CREATE TABLE `lx` (
  `lx_id`            INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ch_name`          VARCHAR(255)     NOT NULL DEFAULT ' ',
  `used_name`        VARCHAR(255)              DEFAULT ' ',
  `py_name`          VARCHAR(255)     NOT NULL DEFAULT ' ',
  `sex`              VARCHAR(255)     NOT NULL DEFAULT ' ',
  `ethnicity`        VARCHAR(255)              DEFAULT ' ',
  `passport_no`      VARCHAR(255)              DEFAULT ' ',
  `date_expriy`      DATETIME         NOT NULL,
  `date_birth`       DATETIME         NOT NULL,
  `id_num`           VARCHAR(255)              DEFAULT ' ',
  `o_tel`            VARCHAR(255)              DEFAULT ' ',
  `cn_tel`           VARCHAR(255)              DEFAULT ' ',
  `cn_te2`           VARCHAR(255)              DEFAULT ' ',
  `wechat`           VARCHAR(255)              DEFAULT ' ',
  `mail`             VARCHAR(255)              DEFAULT ' ',
  `qq_num`           VARCHAR(255)              DEFAULT ' ',
  `native_place`     VARCHAR(255)              DEFAULT ' ',
  `nationality`      VARCHAR(255)              DEFAULT ' ',
  `residence`        VARCHAR(255)              DEFAULT ' ',
  `cn_residence`     VARCHAR(255)              DEFAULT ' ',
  `present_industry` VARCHAR(255)              DEFAULT ' ',
  `com_name`         VARCHAR(255)              DEFAULT ' ',
  `position`         VARCHAR(255)              DEFAULT ' ',
  `education`        VARCHAR(255)              DEFAULT ' ',
  `health`           VARCHAR(255)              DEFAULT ' ',
  `registrant`       INT(20),
  `photo`            VARCHAR(255)              DEFAULT ' ',
  `reg_date`         DATETIME         NOT NULL,
  `remarks`          VARCHAR(255)              DEFAULT ' ',
  `en_cname`         VARCHAR(255)              DEFAULT ' ',
  `ch_cname`         VARCHAR(255)              DEFAULT ' ',
  `degree`           VARCHAR(255)              DEFAULT ' ',
  `gra_date`         DATETIME,
  `del`              VARCHAR(5)                DEFAULT ' ',
  PRIMARY KEY (`lx_id`)
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
  sex         VARCHAR(5) DEFAULT ' '   NOT NULL,
  ethnicity   VARCHAR(255) DEFAULT ' ' NOT NULL,
  passport_no VARCHAR(255) DEFAULT ' ' NOT NULL,
  tel1        VARCHAR(255) DEFAULT ' ' NOT NULL,
  tel2        VARCHAR(255) DEFAULT ' ' NOT NULL,
  del         VARCHAR(5) DEFAULT ' '   NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id`   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255)     NOT NULL DEFAULT ' ',
  `pwd`       VARCHAR(255)     NOT NULL DEFAULT ' ',
  `name`      VARCHAR(255)     NOT NULL DEFAULT ' ',
  `power`     VARCHAR(20)      NOT NULL DEFAULT ' ',
  `reg_date`  DATETIME         NOT NULL,
  `remarks`   VARCHAR(255)     NOT NULL DEFAULT ' ',
  `del`       VARCHAR(5)       NOT NULL DEFAULT ' ',
  PRIMARY KEY (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for relationShip
-- ----------------------------
DROP TABLE IF EXISTS `relationShip`;
CREATE TABLE relationship
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  ch_name     VARCHAR(255) NULL,
  passport_no VARCHAR(255) NULL,
  ethnicity   VARCHAR(255) NULL,
  sex         VARCHAR(255) NULL,
  tel1        VARCHAR(255) NULL,
  tel2        VARCHAR(255) NULL,
  del         VARCHAR(255) NULL,
  CONSTRAINT relationship_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

