/*
MySQL Data Transfer
Source Host: localhost
Source Database: luckydraw
Target Host: localhost
Target Database: luckydraw
Date: 2013-8-26 0:18:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for prize_info
-- ----------------------------
DROP TABLE IF EXISTS `prize_info`;
CREATE TABLE `prize_info` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(30) default NULL,
  `level` varchar(10) default NULL,
  `num` int(10) default NULL,
  `arise_probability` int(10) default NULL,
  `delay_probability` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_draw
-- ----------------------------
DROP TABLE IF EXISTS `user_draw`;
CREATE TABLE `user_draw` (
  `id` int(10) NOT NULL auto_increment,
  `userno` varchar(10) default NULL,
  `prize_id` int(10) default NULL,
  `draw_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `prize_info` VALUES ('1', '500万大奖', '1', '1', '1', '0.00001');
INSERT INTO `prize_info` VALUES ('2', '100万中奖', '2', '2', '10', '0.0004');
INSERT INTO `prize_info` VALUES ('3', '10元幸运奖', '3', '0', '1000', '0.5');
INSERT INTO `prize_info` VALUES ('4', '谢谢！', '4', '9982', '100000', '0.9');

