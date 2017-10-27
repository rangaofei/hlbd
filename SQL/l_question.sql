/*
Navicat MySQL Data Transfer

Source Server         : localhost_3308
Source Server Version : 50719
Source Host           : localhost:3308
Source Database       : whiteboard

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-24 13:33:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l_question
-- ----------------------------
DROP TABLE IF EXISTS `l_question`;
CREATE TABLE `l_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `studentAnswer` text,
  `book_node_id` bigint(20) DEFAULT NULL,
  `difficult` int(11) NOT NULL,
  `knowledge_point_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `question_analyze` text,
  `question_type_id` bigint(20) DEFAULT NULL,
  `stem` text,
  `xk_book_node_id` bigint(20) DEFAULT NULL,
  `xk_knowledge_point_id` bigint(20) DEFAULT NULL,
  `xk_question_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=927 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
