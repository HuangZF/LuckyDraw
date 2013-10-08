/*
MySQL Data Transfer
Source Host: 192.168.0.92
Source Database: luckydraw
Target Host: 192.168.0.92
Target Database: luckydraw
Date: 2013-9-2 15:28:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for prize_info
-- ----------------------------
DROP TABLE IF EXISTS `prize_info`;
CREATE TABLE `prize_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `sum` int(10) DEFAULT NULL,
  `remain_num` int(10) DEFAULT NULL,
  `arise_probability` int(10) DEFAULT NULL,
  `delay_probability` double DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `active_times` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_draw_details
-- ----------------------------
DROP TABLE IF EXISTS `user_draw_details`;
CREATE TABLE `user_draw_details` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userno` varchar(30) DEFAULT NULL,
  `prize_id` int(10) DEFAULT NULL,
  `pay_object` varchar(60) DEFAULT NULL,
  `gain_object` varchar(60) DEFAULT NULL,
  `draw_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `prize_info` VALUES ('1', '500万大奖', '1', '1', '10', '1', '0.0001', '2013-08-27 15:51:49', '2013-08-29 15:51:59', '0');
INSERT INTO `prize_info` VALUES ('2', '100万中奖', '2', '2', '50', '10', '0.0002', '2013-08-27 15:51:53', '2013-08-29 15:52:04', '0');
INSERT INTO `prize_info` VALUES ('3', '10元幸运奖', '3', '50', '930', '1000', '0.05', '2013-08-27 15:51:55', '2013-08-29 15:52:07', '0');
INSERT INTO `prize_info` VALUES ('4', '谢谢！', '4', '9972', '9784', '5000', '0.7', '2013-08-27 15:51:57', '2013-08-27 15:52:10', '0');
