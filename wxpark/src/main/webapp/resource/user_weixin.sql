/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50532
Source Host           : localhost:3306
Source Database       : wxpark

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2016-09-22 16:03:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_weixin
-- ----------------------------
DROP TABLE IF EXISTS `user_weixin`;
CREATE TABLE `user_weixin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) DEFAULT NULL,
  `head_image` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `car_no` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_weixin
-- ----------------------------
INSERT INTO `user_weixin` VALUES ('1', null, null, 'bbb', '云GH6655', null, null, null, null);
INSERT INTO `user_weixin` VALUES ('2', null, null, null, '苏D4192K', null, null, null, null);
INSERT INTO `user_weixin` VALUES ('3', null, null, 'aaaaaaa', '皖A22334', null, null, null, null);
INSERT INTO `user_weixin` VALUES ('6', null, null, 'aaaaaaa', '皖A12345', null, null, null, null);
INSERT INTO `user_weixin` VALUES ('7', null, null, 'aaaaaaa', '苏A4192K', null, null, null, null);
