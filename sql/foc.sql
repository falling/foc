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

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hq
-- ----------------------------
DROP TABLE IF EXISTS `hq`;
ALTER TABLE `hq` (
  `hq_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ch_name` varchar(20) NOT NULL DEFAULT ' ',
  `used_name` varchar(20) DEFAULT ' ',
  `py_name` varchar(50) NOT NULL DEFAULT ' ',
  `sex` varchar(5) NOT NULL DEFAULT ' ',
  `ethnicity` varchar(10) NOT NULL DEFAULT ' ',
  `passport_no` varchar(10) NOT NULL DEFAULT ' ',
  `date_expriy` datetime NOT NULL,
  `date_birth` datetime NOT NULL,
  `id_num` varchar(20) NOT NULL DEFAULT ' ',
  `o_tel` varchar(20) DEFAULT ' ',
  `cn_tel` varchar(20) DEFAULT ' ',
  `cn_te2` varchar(20) DEFAULT ' ',
  `wechat` varchar(20) DEFAULT ' ',
  `mail` varchar(50) DEFAULT ' ',
  `qq_num` varchar(20) DEFAULT ' ',
  `native_place` varchar(50) DEFAULT ' ',
  `nationality` varchar(50) DEFAULT ' ',
  `residence` varchar(50) DEFAULT ' ',
  `cn_residence` varchar(50) DEFAULT ' ',
  `present_industry` varchar(50) DEFAULT ' ',
  `com_name` varchar(50) DEFAULT ' ',
  `position` varchar(50) DEFAULT ' ',
  `education` varchar(20) DEFAULT ' ',
  `health` varchar(20) DEFAULT ' ',
  `registrant` INT(20),
  `photo` varchar(100) DEFAULT ' ',
  `reg_date` datetime ,
  `remarks` varchar(100) DEFAULT ' ',
  `del` varchar(5) DEFAULT ' ',
  PRIMARY KEY (`hq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `log_date` datetime NOT NULL,
  `identity` varchar(20),
  `o_id` int(10) unsigned,
  `operating_user` int(10) unsigned NOT NULL,
  `fields` varchar(10) NOT NULL DEFAULT ' ',
  `operating` varchar(10) NOT NULL DEFAULT ' ',
  `old_value` varchar(100) DEFAULT ' ',
  `new_value` varchar(100) DEFAULT ' ',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `login_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login_date` datetime NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `ip_id` varchar(15) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lx
-- ----------------------------
DROP TABLE IF EXISTS `lx`;
CREATE TABLE `lx` (
  `lx_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ch_name` varchar(20) NOT NULL DEFAULT ' ',
  `used_name` varchar(20) DEFAULT ' ',
  `py_name` varchar(50) NOT NULL DEFAULT ' ',
  `sex` varchar(5) NOT NULL DEFAULT ' ',
  `ethnicity` varchar(10) DEFAULT ' ',
  `passport_no` varchar(10) DEFAULT ' ',
  `date_expriy` datetime NOT NULL,
  `date_birth` datetime NOT NULL,
  `id_num` varchar(20) DEFAULT ' ',
  `o_tel` varchar(20) DEFAULT ' ',
  `cn_tel` varchar(20) DEFAULT ' ',
  `cn_te2` varchar(20) DEFAULT ' ',
  `wechat` varchar(20) DEFAULT ' ',
  `mail` varchar(50) DEFAULT ' ',
  `qq_num` varchar(20) DEFAULT ' ',
  `native_place` varchar(50) DEFAULT ' ',
  `nationality` varchar(50) DEFAULT ' ',
  `residence` varchar(50) DEFAULT ' ',
  `cn_residence` varchar(50) DEFAULT ' ',
  `present_industry` varchar(50) DEFAULT ' ',
  `com_name` varchar(50) DEFAULT ' ',
  `position` varchar(50) DEFAULT ' ',
  `education` varchar(20) DEFAULT ' ',
  `health` varchar(20) DEFAULT ' ',
  `registrant` INT(20) ,
  `photo` varchar(100) DEFAULT ' ',
  `reg_date` datetime NOT NULL,
  `remarks` varchar(100) DEFAULT ' ',
  `en_cname` varchar(100) DEFAULT ' ',
  `ch_cname` varchar(100) DEFAULT ' ',
  `degree` varchar(50) DEFAULT ' ',
  `gra_date` datetime ,
  `del` varchar(5) DEFAULT ' ',
  PRIMARY KEY (`lx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qj
-- ----------------------------
-- auto-generated definition
DROP TABLE IF EXISTS `qj`;
CREATE TABLE qj
(
  qj_id       INT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  ch_name     VARCHAR(20) DEFAULT ' ' NOT NULL,
  sex         VARCHAR(5) DEFAULT ' '  NOT NULL,
  ethnicity   VARCHAR(10) DEFAULT ' ' NOT NULL,
  passport_no VARCHAR(20) DEFAULT ' ' NOT NULL,
  tel1        VARCHAR(20) DEFAULT ' ' NOT NULL,
  tel2        VARCHAR(20) DEFAULT ' ' NOT NULL,
  del         VARCHAR(5) DEFAULT ' '  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) NOT NULL DEFAULT ' ',
  `pwd` varchar(20) NOT NULL DEFAULT ' ',
  `name` varchar(20) NOT NULL DEFAULT ' ',
  `power` varchar(20) NOT NULL DEFAULT ' ',
  `reg_date` datetime NOT NULL,
  `remarks` varchar(100) NOT NULL DEFAULT ' ',
  `del` varchar(5) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ENGINE = InnoDB DEFAULT CHARSET=utf8;

