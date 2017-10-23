/*
Navicat MySQL Data Transfer

Source Server         : localhost_3308
Source Server Version : 50719
Source Host           : localhost:3308
Source Database       : whiteboard

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-20 10:11:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l_version
-- ----------------------------
DROP TABLE IF EXISTS `l_version`;
CREATE TABLE `l_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  `xk_subject_id` bigint(20) DEFAULT NULL,
  `xkversion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_version
-- ----------------------------
INSERT INTO `l_version` VALUES ('1', '北师大版', '28', '101', '213');
INSERT INTO `l_version` VALUES ('2', '沪教版', '28', '101', '214');
INSERT INTO `l_version` VALUES ('3', '冀教版', '28', '101', '215');
INSERT INTO `l_version` VALUES ('4', '人教新课标', '28', '101', '218');
INSERT INTO `l_version` VALUES ('5', '苏教版', '28', '101', '219');
INSERT INTO `l_version` VALUES ('6', '西师大版', '28', '101', '220');
INSERT INTO `l_version` VALUES ('7', '语文A版', '28', '101', '221');
INSERT INTO `l_version` VALUES ('8', '语文S版', '28', '101', '222');
INSERT INTO `l_version` VALUES ('9', '鄂教版', '28', '101', '224');
INSERT INTO `l_version` VALUES ('10', '北师大版', '29', '102', '199');
INSERT INTO `l_version` VALUES ('11', '沪教版', '29', '102', '200');
INSERT INTO `l_version` VALUES ('12', '冀教版', '29', '102', '201');
INSERT INTO `l_version` VALUES ('13', '青岛版（六年制）', '29', '102', '202');
INSERT INTO `l_version` VALUES ('14', '苏教版（旧）', '29', '102', '205');
INSERT INTO `l_version` VALUES ('15', '西师大版', '29', '102', '207');
INSERT INTO `l_version` VALUES ('16', '浙教版', '29', '102', '208');
INSERT INTO `l_version` VALUES ('17', '人教版', '29', '102', '210');
INSERT INTO `l_version` VALUES ('18', '苏教版', '29', '102', '211');
INSERT INTO `l_version` VALUES ('19', '北师大版（一起）', '30', '103', '229');
INSERT INTO `l_version` VALUES ('20', '冀教版', '30', '103', '230');
INSERT INTO `l_version` VALUES ('21', '人教PEP', '30', '103', '233');
INSERT INTO `l_version` VALUES ('22', '人教新版', '30', '103', '234');
INSERT INTO `l_version` VALUES ('23', '外研版（三起）', '30', '103', '237');
INSERT INTO `l_version` VALUES ('24', '外研版（一起）', '30', '103', '238');
INSERT INTO `l_version` VALUES ('25', '北京课改版', '30', '103', '242');
INSERT INTO `l_version` VALUES ('26', '北师大版（三起）', '30', '103', '258');
INSERT INTO `l_version` VALUES ('27', '北京课改版', '31', '201', '5');
INSERT INTO `l_version` VALUES ('28', '北师大版', '31', '201', '6');
INSERT INTO `l_version` VALUES ('29', '长春版', '31', '201', '7');
INSERT INTO `l_version` VALUES ('30', '鄂教版', '31', '201', '8');
INSERT INTO `l_version` VALUES ('31', '沪教版(旧)', '31', '201', '9');
INSERT INTO `l_version` VALUES ('32', '鲁教版', '31', '201', '11');
INSERT INTO `l_version` VALUES ('33', '人教版', '31', '201', '12');
INSERT INTO `l_version` VALUES ('34', '沪教版', '31', '201', '14');
INSERT INTO `l_version` VALUES ('35', '苏教版', '31', '201', '15');
INSERT INTO `l_version` VALUES ('36', '语文版', '31', '201', '16');
INSERT INTO `l_version` VALUES ('37', '浙教版', '31', '201', '17');
INSERT INTO `l_version` VALUES ('38', '人教版', '32', '202', '23');
INSERT INTO `l_version` VALUES ('39', '北师大版', '32', '202', '24');
INSERT INTO `l_version` VALUES ('40', '浙教版', '32', '202', '25');
INSERT INTO `l_version` VALUES ('41', '华师大版', '32', '202', '26');
INSERT INTO `l_version` VALUES ('42', '苏科版', '32', '202', '27');
INSERT INTO `l_version` VALUES ('43', '湘教版', '32', '202', '28');
INSERT INTO `l_version` VALUES ('44', '沪科版', '32', '202', '29');
INSERT INTO `l_version` VALUES ('45', '冀教版', '32', '202', '30');
INSERT INTO `l_version` VALUES ('46', '北京课改版', '32', '202', '31');
INSERT INTO `l_version` VALUES ('47', '青岛版', '32', '202', '32');
INSERT INTO `l_version` VALUES ('48', '鲁教版（五四学制）', '32', '202', '33');
INSERT INTO `l_version` VALUES ('49', '沪教版（五四学制）', '32', '202', '34');
INSERT INTO `l_version` VALUES ('50', '北京课改版', '33', '203', '38');
INSERT INTO `l_version` VALUES ('51', '北师大版(旧)', '33', '203', '39');
INSERT INTO `l_version` VALUES ('52', '牛津上海版', '33', '203', '42');
INSERT INTO `l_version` VALUES ('53', '牛津译林版', '33', '203', '45');
INSERT INTO `l_version` VALUES ('54', '人教新目标版', '33', '203', '46');
INSERT INTO `l_version` VALUES ('55', '仁爱版', '33', '203', '47');
INSERT INTO `l_version` VALUES ('56', '外研版', '33', '203', '48');
INSERT INTO `l_version` VALUES ('57', '北师大版', '33', '203', '263');
INSERT INTO `l_version` VALUES ('58', '牛津上海版(旧)', '33', '203', '264');
INSERT INTO `l_version` VALUES ('59', '牛津译林版(旧)', '33', '203', '265');
INSERT INTO `l_version` VALUES ('60', '人教版', '34', '204', '55');
INSERT INTO `l_version` VALUES ('61', '苏科版', '34', '204', '56');
INSERT INTO `l_version` VALUES ('62', '北师大版', '34', '204', '57');
INSERT INTO `l_version` VALUES ('63', '沪科版', '34', '204', '58');
INSERT INTO `l_version` VALUES ('64', '教科版', '34', '204', '59');
INSERT INTO `l_version` VALUES ('65', '沪粤版', '34', '204', '60');
INSERT INTO `l_version` VALUES ('66', '北京课改版', '34', '204', '61');
INSERT INTO `l_version` VALUES ('67', '鲁教版', '34', '204', '62');
INSERT INTO `l_version` VALUES ('68', '人教版', '35', '205', '67');
INSERT INTO `l_version` VALUES ('69', '北京课改版', '35', '205', '68');
INSERT INTO `l_version` VALUES ('70', '鲁教版', '35', '205', '69');
INSERT INTO `l_version` VALUES ('71', '沪教版', '35', '205', '70');
INSERT INTO `l_version` VALUES ('72', '湘教版', '35', '205', '71');
INSERT INTO `l_version` VALUES ('73', '粤教版', '35', '205', '73');
INSERT INTO `l_version` VALUES ('74', '仁爱湘教版', '35', '205', '74');
INSERT INTO `l_version` VALUES ('75', '人教版', '36', '206', '78');
INSERT INTO `l_version` VALUES ('76', '苏教版', '36', '206', '79');
INSERT INTO `l_version` VALUES ('77', '北师大版', '36', '206', '80');
INSERT INTO `l_version` VALUES ('78', '苏科版', '36', '206', '81');
INSERT INTO `l_version` VALUES ('79', '北京课改版', '36', '206', '82');
INSERT INTO `l_version` VALUES ('80', '河北少儿版', '36', '206', '83');
INSERT INTO `l_version` VALUES ('81', '鲁科版', '36', '206', '84');
INSERT INTO `l_version` VALUES ('82', '济南版', '36', '206', '85');
INSERT INTO `l_version` VALUES ('83', '北师大版', '37', '207', '89');
INSERT INTO `l_version` VALUES ('84', '教科版', '37', '207', '90');
INSERT INTO `l_version` VALUES ('85', '鲁教版', '37', '207', '91');
INSERT INTO `l_version` VALUES ('86', '人教版', '37', '207', '92');
INSERT INTO `l_version` VALUES ('87', '人民版', '37', '207', '93');
INSERT INTO `l_version` VALUES ('88', '陕教版', '37', '207', '94');
INSERT INTO `l_version` VALUES ('89', '苏教版', '37', '207', '95');
INSERT INTO `l_version` VALUES ('90', '湘教版', '37', '207', '96');
INSERT INTO `l_version` VALUES ('91', '粤教版', '37', '207', '97');
INSERT INTO `l_version` VALUES ('92', '北师大版', '38', '208', '100');
INSERT INTO `l_version` VALUES ('93', '川教版', '38', '208', '101');
INSERT INTO `l_version` VALUES ('94', '华师大版', '38', '208', '102');
INSERT INTO `l_version` VALUES ('95', '冀教版', '38', '208', '103');
INSERT INTO `l_version` VALUES ('96', '鲁教版', '38', '208', '105');
INSERT INTO `l_version` VALUES ('97', '人教版', '38', '208', '106');
INSERT INTO `l_version` VALUES ('98', '人教版《历史与社会》', '38', '208', '107');
INSERT INTO `l_version` VALUES ('99', '岳麓版', '38', '208', '108');
INSERT INTO `l_version` VALUES ('100', '中华书局版', '38', '208', '109');
INSERT INTO `l_version` VALUES ('101', '人教新课标版', '39', '209', '112');
INSERT INTO `l_version` VALUES ('102', '商务星球版', '39', '209', '113');
INSERT INTO `l_version` VALUES ('103', '上海教育版', '39', '209', '114');
INSERT INTO `l_version` VALUES ('104', '湘教版', '39', '209', '115');
INSERT INTO `l_version` VALUES ('105', '粤教版', '39', '209', '116');
INSERT INTO `l_version` VALUES ('106', '中图版', '39', '209', '117');
INSERT INTO `l_version` VALUES ('107', '晋教版', '39', '209', '118');
INSERT INTO `l_version` VALUES ('108', '华师大版', '40', '210', '243');
INSERT INTO `l_version` VALUES ('109', '牛津上海版', '40', '210', '244');
INSERT INTO `l_version` VALUES ('110', '鄂教版', '40', '210', '245');
INSERT INTO `l_version` VALUES ('111', '浙教版', '40', '210', '246');
INSERT INTO `l_version` VALUES ('112', '北师大版', '41', '211', '251');
INSERT INTO `l_version` VALUES ('113', '冀教版', '41', '211', '252');
INSERT INTO `l_version` VALUES ('114', '人教版', '41', '211', '253');
INSERT INTO `l_version` VALUES ('115', '人教版（三四年制）', '41', '211', '254');
INSERT INTO `l_version` VALUES ('116', '苏科版', '41', '211', '255');
INSERT INTO `l_version` VALUES ('117', '浙教版', '41', '211', '256');
INSERT INTO `l_version` VALUES ('118', '人教新课标', '42', '301', '125');
INSERT INTO `l_version` VALUES ('119', '粤教版', '42', '301', '126');
INSERT INTO `l_version` VALUES ('120', '鲁教版', '42', '301', '127');
INSERT INTO `l_version` VALUES ('121', '北师大版', '42', '301', '128');
INSERT INTO `l_version` VALUES ('122', '苏教版', '42', '301', '129');
INSERT INTO `l_version` VALUES ('123', '语文版（07版）', '42', '301', '130');
INSERT INTO `l_version` VALUES ('124', '北京版（08版）', '42', '301', '131');
INSERT INTO `l_version` VALUES ('125', '鲁人版（08版）', '42', '301', '132');
INSERT INTO `l_version` VALUES ('126', '新课标人教A版', '43', '302', '139');
INSERT INTO `l_version` VALUES ('127', '新课标人教B版', '43', '302', '140');
INSERT INTO `l_version` VALUES ('128', '北师大版', '43', '302', '141');
INSERT INTO `l_version` VALUES ('129', '苏教版', '43', '302', '142');
INSERT INTO `l_version` VALUES ('130', '湘教版', '43', '302', '143');
INSERT INTO `l_version` VALUES ('131', '人教新课标版', '44', '303', '146');
INSERT INTO `l_version` VALUES ('132', '译林牛津版', '44', '303', '147');
INSERT INTO `l_version` VALUES ('133', '外研版', '44', '303', '148');
INSERT INTO `l_version` VALUES ('134', '北师大版', '44', '303', '149');
INSERT INTO `l_version` VALUES ('135', '冀教版', '44', '303', '150');
INSERT INTO `l_version` VALUES ('136', '重庆大学版', '44', '303', '151');
INSERT INTO `l_version` VALUES ('137', '新课标人教版', '45', '304', '156');
INSERT INTO `l_version` VALUES ('138', '沪科版', '45', '304', '157');
INSERT INTO `l_version` VALUES ('139', '江苏版', '45', '304', '158');
INSERT INTO `l_version` VALUES ('140', '教科版', '45', '304', '159');
INSERT INTO `l_version` VALUES ('141', '鲁科版', '45', '304', '160');
INSERT INTO `l_version` VALUES ('142', '粤教版', '45', '304', '161');
INSERT INTO `l_version` VALUES ('143', '人教版', '46', '305', '166');
INSERT INTO `l_version` VALUES ('144', '鲁科版', '46', '305', '167');
INSERT INTO `l_version` VALUES ('145', '苏教版', '46', '305', '168');
INSERT INTO `l_version` VALUES ('146', '人教版', '47', '306', '173');
INSERT INTO `l_version` VALUES ('147', '苏教版', '47', '306', '174');
INSERT INTO `l_version` VALUES ('148', '浙科版', '47', '306', '175');
INSERT INTO `l_version` VALUES ('149', '中图版', '47', '306', '176');
INSERT INTO `l_version` VALUES ('150', '北师大版', '47', '306', '177');
INSERT INTO `l_version` VALUES ('151', '沪科版', '47', '306', '178');
INSERT INTO `l_version` VALUES ('152', '人教版', '48', '307', '180');
INSERT INTO `l_version` VALUES ('153', '沪教版', '48', '307', '181');
INSERT INTO `l_version` VALUES ('154', '人教新课标版', '49', '308', '183');
INSERT INTO `l_version` VALUES ('155', '标准实验版', '49', '308', '184');
INSERT INTO `l_version` VALUES ('156', '北师大版', '49', '308', '185');
INSERT INTO `l_version` VALUES ('157', '人民版', '49', '308', '186');
INSERT INTO `l_version` VALUES ('158', '大象版', '49', '308', '187');
INSERT INTO `l_version` VALUES ('159', '新岳麓版', '49', '308', '188');
INSERT INTO `l_version` VALUES ('160', '人教版', '50', '309', '194');
INSERT INTO `l_version` VALUES ('161', '鲁教版', '50', '309', '195');
INSERT INTO `l_version` VALUES ('162', '湘教版', '50', '309', '196');
INSERT INTO `l_version` VALUES ('163', '中图版', '50', '309', '197');
INSERT INTO `l_version` VALUES ('164', '教科版', '51', '311', '247');
INSERT INTO `l_version` VALUES ('165', '人教版', '51', '311', '248');
INSERT INTO `l_version` VALUES ('166', '浙教版', '51', '311', '249');
INSERT INTO `l_version` VALUES ('167', '中图版', '51', '311', '250');
SET FOREIGN_KEY_CHECKS=1;
