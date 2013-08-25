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
INSERT INTO `prize_info` VALUES ('1', '500万大奖', '1', '1', '1', '0.001');
INSERT INTO `prize_info` VALUES ('2', '100万中奖', '2', '2', '10', '0.004');
INSERT INTO `prize_info` VALUES ('3', '10元幸运奖', '3', '0', '10000', '9e-007');
INSERT INTO `prize_info` VALUES ('4', '谢谢！', '4', '9982', '1000', '0.9');
INSERT INTO `user_draw` VALUES ('1', '46163', '3', '2013-08-25 00:00:00');
INSERT INTO `user_draw` VALUES ('2', '46163', '3', '2013-08-25 11:28:30');
INSERT INTO `user_draw` VALUES ('3', '46163', '3', '2013-08-25 11:29:29');
INSERT INTO `user_draw` VALUES ('4', '0132123', '3', '2013-08-25 11:31:55');
INSERT INTO `user_draw` VALUES ('5', '0132123', '3', '2013-08-25 11:33:46');
INSERT INTO `user_draw` VALUES ('6', '0132123', '3', '2013-08-25 11:34:28');
INSERT INTO `user_draw` VALUES ('7', '0132123', '4', '2013-08-25 12:52:42');
INSERT INTO `user_draw` VALUES ('8', '0132123', '4', '2013-08-25 12:53:05');
INSERT INTO `user_draw` VALUES ('9', '0132123', '4', '2013-08-25 12:53:24');
INSERT INTO `user_draw` VALUES ('10', '0132123', '3', '2013-08-25 12:55:14');
INSERT INTO `user_draw` VALUES ('11', '0132123', '3', '2013-08-25 12:55:29');
INSERT INTO `user_draw` VALUES ('12', '0132123', '3', '2013-08-25 14:12:32');
INSERT INTO `user_draw` VALUES ('13', '0132123', '3', '2013-08-25 14:14:18');
INSERT INTO `user_draw` VALUES ('14', '0132123', '4', '2013-08-25 14:15:44');
INSERT INTO `user_draw` VALUES ('15', '0132123', '3', '2013-08-25 18:46:26');
INSERT INTO `user_draw` VALUES ('16', '0132123', '4', '2013-08-25 19:25:08');
INSERT INTO `user_draw` VALUES ('17', '0132123', '3', '2013-08-25 19:29:08');
INSERT INTO `user_draw` VALUES ('18', '0132123', '4', '2013-08-25 19:41:33');
INSERT INTO `user_draw` VALUES ('19', '0132123', '4', '2013-08-25 19:42:19');
INSERT INTO `user_draw` VALUES ('20', '0132123', '4', '2013-08-25 23:22:00');
INSERT INTO `user_draw` VALUES ('21', '0132123', '4', '2013-08-25 23:26:53');
INSERT INTO `user_draw` VALUES ('22', '0132123', '4', '2013-08-25 23:28:15');
INSERT INTO `user_draw` VALUES ('23', '0132123', '4', '2013-08-25 23:32:29');
INSERT INTO `user_draw` VALUES ('24', '0132123', '4', '2013-08-25 23:36:44');
