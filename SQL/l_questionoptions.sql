/*
Navicat MySQL Data Transfer

Source Server         : localhost_3308
Source Server Version : 50719
Source Host           : localhost:3308
Source Database       : whiteboard

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-24 13:33:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l_questionoptions
-- ----------------------------
DROP TABLE IF EXISTS `l_questionoptions`;
CREATE TABLE `l_questionoptions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `value` text,
  `xk_questionid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=769 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
