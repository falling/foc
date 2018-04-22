/*
Navicat MySQL Data Transfer

Source Server         : foc
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : foc

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-04-22 15:22:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hq
-- ----------------------------
DROP TABLE IF EXISTS `hq`;
CREATE TABLE `hq` (
  `hq_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ch_name` varchar(255) NOT NULL DEFAULT ' ',
  `tel` varchar(50) NOT NULL,
  `used_name` varchar(255) DEFAULT ' ',
  `py_name` varchar(255) DEFAULT ' ',
  `sex` varchar(5) DEFAULT ' ',
  `ethnicity` varchar(255) DEFAULT ' ',
  `passport_no` varchar(255) DEFAULT ' ',
  `date_expriy` datetime DEFAULT NULL,
  `date_birth` datetime DEFAULT NULL,
  `id_num` varchar(255) DEFAULT ' ',
  `cn_tel` varchar(255) DEFAULT ' ',
  `wechat` varchar(255) DEFAULT ' ',
  `mail` varchar(255) DEFAULT ' ',
  `qq_num` varchar(255) DEFAULT ' ',
  `native_place` varchar(255) DEFAULT ' ',
  `nationality` varchar(255) DEFAULT ' ',
  `living_country` varchar(50) DEFAULT NULL,
  `residence` varchar(255) DEFAULT ' ',
  `cn_residence` varchar(255) DEFAULT ' ',
  `present_industry` varchar(255) DEFAULT ' ',
  `com_name` varchar(255) DEFAULT ' ',
  `position` varchar(255) DEFAULT ' ',
  `social_services` varchar(255) DEFAULT NULL,
  `registrant` int(20) DEFAULT NULL,
  `photo` varchar(255) DEFAULT ' ',
  `remarks` varchar(255) DEFAULT ' ',
  `reg_date` datetime DEFAULT NULL,
  `del` varchar(5) DEFAULT ' ',
  PRIMARY KEY (`hq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `log_date` datetime NOT NULL,
  `identity` varchar(20) DEFAULT NULL,
  `o_id` int(10) unsigned DEFAULT NULL,
  `operating_user` int(10) unsigned NOT NULL,
  `operating` varchar(10) NOT NULL DEFAULT ' ',
  `old_value` text,
  `new_value` text,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lx
-- ----------------------------
DROP TABLE IF EXISTS `lx`;
CREATE TABLE `lx` (
  `lx_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ch_name` varchar(255) NOT NULL DEFAULT ' ',
  `tel` varchar(50) NOT NULL,
  `used_name` varchar(255) DEFAULT ' ',
  `py_name` varchar(255) DEFAULT ' ',
  `sex` varchar(5) DEFAULT ' ',
  `ethnicity` varchar(255) DEFAULT ' ',
  `passport_no` varchar(255) DEFAULT ' ',
  `date_expriy` datetime DEFAULT NULL,
  `date_birth` datetime DEFAULT NULL,
  `id_num` varchar(255) DEFAULT ' ',
  `cn_tel` varchar(255) DEFAULT ' ',
  `wechat` varchar(255) DEFAULT ' ',
  `mail` varchar(255) DEFAULT ' ',
  `qq_num` varchar(255) DEFAULT ' ',
  `native_place` varchar(255) DEFAULT ' ',
  `nationality` varchar(255) DEFAULT ' ',
  `living_country` varchar(50) DEFAULT NULL,
  `residence` varchar(255) DEFAULT ' ',
  `cn_residence` varchar(255) DEFAULT ' ',
  `present_industry` varchar(255) DEFAULT ' ',
  `com_name` varchar(255) DEFAULT ' ',
  `position` varchar(255) DEFAULT ' ',
  `registrant` int(20) DEFAULT NULL,
  `photo` varchar(255) DEFAULT ' ',
  `reg_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT ' ',
  `en_cname` varchar(255) DEFAULT ' ',
  `ch_cname` varchar(255) DEFAULT ' ',
  `kin_name` varchar(255) DEFAULT NULL,
  `kin_tel` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT ' ',
  `gra_date` datetime DEFAULT NULL,
  `social_services` varchar(255) DEFAULT NULL,
  `del` varchar(5) DEFAULT ' ',
  PRIMARY KEY (`lx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qj
-- ----------------------------
DROP TABLE IF EXISTS `qj`;
CREATE TABLE `qj` (
  `qj_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL DEFAULT ' ',
  `ch_name` varchar(255) NOT NULL DEFAULT ' ',
  `tel` varchar(255) NOT NULL DEFAULT ' ',
  `sex` varchar(5) DEFAULT ' ',
  `ethnicity` varchar(255) DEFAULT ' ',
  `passport_no` varchar(255) DEFAULT ' ',
  `id_num` varchar(255) DEFAULT ' ',
  `address` varchar(255) DEFAULT ' ',
  `kin_name` varchar(255) DEFAULT ' ',
  `kin_relation` varchar(255) DEFAULT ' ',
  `kin_country` varchar(255) DEFAULT ' ',
  `kin_passport_no` varchar(255) DEFAULT ' ',
  `remark` varchar(255) DEFAULT ' ',
  `del` varchar(5) DEFAULT ' ',
  PRIMARY KEY (`qj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for relationship
-- ----------------------------
DROP TABLE IF EXISTS `relationship`;
CREATE TABLE `relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `o_id` int(11) DEFAULT NULL,
  `qj_id` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `relation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL DEFAULT ' ',
  `pwd` varchar(255) NOT NULL DEFAULT ' ',
  `name` varchar(255) NOT NULL DEFAULT ' ',
  `power` varchar(255) NOT NULL DEFAULT ' ',
  `reg_date` datetime NOT NULL,
  `remarks` varchar(255) NOT NULL DEFAULT ' ',
  `del` varchar(5) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
