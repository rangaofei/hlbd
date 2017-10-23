/*
Navicat MySQL Data Transfer

Source Server         : localhost_3308
Source Server Version : 50719
Source Host           : localhost:3308
Source Database       : whiteboard

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-20 09:54:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l_subject
-- ----------------------------
DROP TABLE IF EXISTS `l_subject`;
CREATE TABLE `l_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `stage` int(11) NOT NULL,
  `xk_subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_subject
-- ----------------------------
INSERT INTO `l_subject` VALUES ('28', '语文', '1', '101');
INSERT INTO `l_subject` VALUES ('29', '数学', '1', '102');
INSERT INTO `l_subject` VALUES ('30', '英语', '1', '103');
INSERT INTO `l_subject` VALUES ('31', '语文', '2', '201');
INSERT INTO `l_subject` VALUES ('32', '数学', '2', '202');
INSERT INTO `l_subject` VALUES ('33', '英语', '2', '203');
INSERT INTO `l_subject` VALUES ('34', '物理', '2', '204');
INSERT INTO `l_subject` VALUES ('35', '化学', '2', '205');
INSERT INTO `l_subject` VALUES ('36', '生物', '2', '206');
INSERT INTO `l_subject` VALUES ('37', '政治', '2', '207');
INSERT INTO `l_subject` VALUES ('38', '历史', '2', '208');
INSERT INTO `l_subject` VALUES ('39', '地理', '2', '209');
INSERT INTO `l_subject` VALUES ('40', '科学', '2', '210');
INSERT INTO `l_subject` VALUES ('41', '信息技术', '2', '211');
INSERT INTO `l_subject` VALUES ('42', '语文', '3', '301');
INSERT INTO `l_subject` VALUES ('43', '数学', '3', '302');
INSERT INTO `l_subject` VALUES ('44', '英语', '3', '303');
INSERT INTO `l_subject` VALUES ('45', '物理', '3', '304');
INSERT INTO `l_subject` VALUES ('46', '化学', '3', '305');
INSERT INTO `l_subject` VALUES ('47', '生物', '3', '306');
INSERT INTO `l_subject` VALUES ('48', '政治', '3', '307');
INSERT INTO `l_subject` VALUES ('49', '历史', '3', '308');
INSERT INTO `l_subject` VALUES ('50', '地理', '3', '309');
INSERT INTO `l_subject` VALUES ('51', '信息技术', '3', '311');
SET FOREIGN_KEY_CHECKS=1;
