/*
Navicat MySQL Data Transfer

Source Server         : localhost_3308
Source Server Version : 50719
Source Host           : localhost:3308
Source Database       : whiteboard

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-24 13:33:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l_questiontype
-- ----------------------------
DROP TABLE IF EXISTS `l_questiontype`;
CREATE TABLE `l_questiontype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `xk_questiontype_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  `xk_subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_questiontype
-- ----------------------------
INSERT INTO `l_questiontype` VALUES ('1', '其他', '1', null, null);
INSERT INTO `l_questiontype` VALUES ('2', '选择题', '2', null, null);
INSERT INTO `l_questiontype` VALUES ('3', '现代文阅读', '8', '28', '101');
INSERT INTO `l_questiontype` VALUES ('4', '文言文阅读', '9', '28', '101');
INSERT INTO `l_questiontype` VALUES ('5', '书写', '14', '28', '101');
INSERT INTO `l_questiontype` VALUES ('6', '语言表达', '15', '28', '101');
INSERT INTO `l_questiontype` VALUES ('7', '填空题', '18', '28', '101');
INSERT INTO `l_questiontype` VALUES ('8', '简答题', '21', '28', '101');
INSERT INTO `l_questiontype` VALUES ('9', '信息匹配', '29', '28', '101');
INSERT INTO `l_questiontype` VALUES ('10', '书面表达', '30', '28', '101');
INSERT INTO `l_questiontype` VALUES ('11', '句型转换', '31', '28', '101');
INSERT INTO `l_questiontype` VALUES ('12', '判断题', '39', '28', '101');
INSERT INTO `l_questiontype` VALUES ('13', '连线题', '42', '28', '101');
INSERT INTO `l_questiontype` VALUES ('14', '排序题', '52', '28', '101');
INSERT INTO `l_questiontype` VALUES ('15', '古诗阅读', '61', '28', '101');
INSERT INTO `l_questiontype` VALUES ('16', '修改病句', '62', '28', '101');
INSERT INTO `l_questiontype` VALUES ('17', '计算题', '19', '29', '102');
INSERT INTO `l_questiontype` VALUES ('18', '解答题', '20', '29', '102');
INSERT INTO `l_questiontype` VALUES ('19', '作图题', '38', '29', '102');
INSERT INTO `l_questiontype` VALUES ('20', '完形填空', '24', '30', '103');
INSERT INTO `l_questiontype` VALUES ('21', '阅读理解', '25', '30', '103');
INSERT INTO `l_questiontype` VALUES ('22', '单词拼写', '26', '30', '103');
INSERT INTO `l_questiontype` VALUES ('23', '补充句子', '33', '30', '103');
INSERT INTO `l_questiontype` VALUES ('24', '翻译', '34', '30', '103');
INSERT INTO `l_questiontype` VALUES ('25', '改错', '35', '30', '103');
INSERT INTO `l_questiontype` VALUES ('26', '连词成句', '67', '30', '103');
INSERT INTO `l_questiontype` VALUES ('27', '情景交际', '69', '30', '103');
INSERT INTO `l_questiontype` VALUES ('28', '匹配题', '70', '30', '103');
INSERT INTO `l_questiontype` VALUES ('29', '看图题', '72', '30', '103');
INSERT INTO `l_questiontype` VALUES ('30', '诗歌鉴赏', '10', '31', '201');
INSERT INTO `l_questiontype` VALUES ('31', '语言表达', '11', '31', '201');
INSERT INTO `l_questiontype` VALUES ('32', '名著导读', '12', '31', '201');
INSERT INTO `l_questiontype` VALUES ('33', '默写', '13', '31', '201');
INSERT INTO `l_questiontype` VALUES ('34', '综合性学习', '53', '31', '201');
INSERT INTO `l_questiontype` VALUES ('35', '命题作文', '84', '31', '201');
INSERT INTO `l_questiontype` VALUES ('36', '话题作文', '85', '31', '201');
INSERT INTO `l_questiontype` VALUES ('37', '材料作文', '86', '31', '201');
INSERT INTO `l_questiontype` VALUES ('38', '单项选择', '6', '33', '203');
INSERT INTO `l_questiontype` VALUES ('39', '单词拼写', '32', '33', '203');
INSERT INTO `l_questiontype` VALUES ('40', '单词造句', '36', '33', '203');
INSERT INTO `l_questiontype` VALUES ('41', '选词填空', '37', '33', '203');
INSERT INTO `l_questiontype` VALUES ('42', '语法填空', '55', '33', '203');
INSERT INTO `l_questiontype` VALUES ('43', '语音', '76', '33', '203');
INSERT INTO `l_questiontype` VALUES ('44', '七选五', '78', '33', '203');
INSERT INTO `l_questiontype` VALUES ('45', '短文填空', '79', '33', '203');
INSERT INTO `l_questiontype` VALUES ('46', '口语应用', '80', '33', '203');
INSERT INTO `l_questiontype` VALUES ('47', '任务型阅读', '81', '33', '203');
INSERT INTO `l_questiontype` VALUES ('48', '双选题', '4', '34', '204');
INSERT INTO `l_questiontype` VALUES ('49', '多选题', '5', '34', '204');
INSERT INTO `l_questiontype` VALUES ('50', '实验题', '17', '34', '204');
INSERT INTO `l_questiontype` VALUES ('51', '综合题', '23', '34', '204');
INSERT INTO `l_questiontype` VALUES ('52', '探究题', '40', '35', '205');
INSERT INTO `l_questiontype` VALUES ('53', '推断题', '43', '35', '205');
INSERT INTO `l_questiontype` VALUES ('54', '信息分析题', '44', '35', '205');
INSERT INTO `l_questiontype` VALUES ('55', '选择填充题', '68', '35', '205');
INSERT INTO `l_questiontype` VALUES ('56', '单选题', '3', '37', '207');
INSERT INTO `l_questiontype` VALUES ('57', '不定项选择题', '7', '37', '207');
INSERT INTO `l_questiontype` VALUES ('58', '论述题', '22', '37', '207');
INSERT INTO `l_questiontype` VALUES ('59', '辨析题', '41', '37', '207');
INSERT INTO `l_questiontype` VALUES ('60', '填表题', '45', '38', '208');
INSERT INTO `l_questiontype` VALUES ('61', '列举题', '46', '38', '208');
INSERT INTO `l_questiontype` VALUES ('62', '问答题', '47', '38', '208');
INSERT INTO `l_questiontype` VALUES ('63', '操作题', '59', '41', '211');
INSERT INTO `l_questiontype` VALUES ('64', '小作文', '54', '42', '301');
INSERT INTO `l_questiontype` VALUES ('65', '短文改错', '27', '44', '303');
INSERT INTO `l_questiontype` VALUES ('66', '阅读填空', '28', '44', '303');
INSERT INTO `l_questiontype` VALUES ('67', '单句改错', '65', '44', '303');
INSERT INTO `l_questiontype` VALUES ('68', '七选五', '66', '44', '303');
INSERT INTO `l_questiontype` VALUES ('69', '完成句子', '71', '44', '303');
INSERT INTO `l_questiontype` VALUES ('70', '阅读表达', '82', '44', '303');
INSERT INTO `l_questiontype` VALUES ('71', '对话填空', '83', '44', '303');
SET FOREIGN_KEY_CHECKS=1;
