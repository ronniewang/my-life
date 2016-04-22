/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : my-life

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-04-22 18:07:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_event_type
-- ----------------------------
DROP TABLE IF EXISTS `t_event_type`;
CREATE TABLE `t_event_type` (
  `type_id` int(11) NOT NULL,
  `type_description` varchar(255) NOT NULL,
  `parent_type_id` int(11) DEFAULT NULL,
  `has_children` bit(1) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_event_type
-- ----------------------------
INSERT INTO `t_event_type` VALUES ('1', '工作', '0', '');
INSERT INTO `t_event_type` VALUES ('2', '娱乐', '0', '');
INSERT INTO `t_event_type` VALUES ('3', '交通', '0', '');
INSERT INTO `t_event_type` VALUES ('4', '睡觉', '0', '');
INSERT INTO `t_event_type` VALUES ('31', '步行', '3', '\0');
INSERT INTO `t_event_type` VALUES ('32', '公交', '3', '\0');
INSERT INTO `t_event_type` VALUES ('33', '出租车', '3', '\0');
INSERT INTO `t_event_type` VALUES ('34', '地铁', '3', '\0');
INSERT INTO `t_event_type` VALUES ('35', '飞机', '3', '\0');
INSERT INTO `t_event_type` VALUES ('36', '船', '3', '\0');
INSERT INTO `t_event_type` VALUES ('37', '私家车', '3', '\0');
INSERT INTO `t_event_type` VALUES ('38', '其他', '3', '\0');

-- ----------------------------
-- Table structure for t_my_life_event
-- ----------------------------
DROP TABLE IF EXISTS `t_my_life_event`;
CREATE TABLE `t_my_life_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_start_time` datetime NOT NULL,
  `event_end_time` datetime NOT NULL,
  `event_description` longtext NOT NULL,
  `event_resources` longtext,
  `event_type` int(11) NOT NULL,
  `created_by` bigint(20) NOT NULL,
  `last_modified_by` bigint(20) NOT NULL,
  `created_time` datetime NOT NULL,
  `last_modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_my_life_event
-- ----------------------------
INSERT INTO `t_my_life_event` VALUES ('1', '2016-04-22 08:40:11', '2016-04-22 10:01:11', '搭建my-life项目，并测试可用', null, '0', '1', '1', '2016-04-22 10:01:11', '2016-04-22 10:01:11');
INSERT INTO `t_my_life_event` VALUES ('7', '2016-04-22 07:15:10', '2016-04-01 08:10:10', '坐地铁，看《把时间当做朋友》，受到启发，准备做一个生活轨迹记录系统', null, '0', '1', '1', '2016-04-22 10:26:19', '2016-04-22 10:26:19');
INSERT INTO `t_my_life_event` VALUES ('9', '2016-04-01 08:10:10', '2016-04-22 12:00:42', '查找time-line控件并调试', 'http://www.helloweba.com/demo/v_timeline/', '0', '1', '1', '2016-04-22 13:39:40', '2016-04-22 13:39:43');
INSERT INTO `t_my_life_event` VALUES ('10', '2016-04-22 12:00:42', '2016-04-22 13:30:03', '吃东方宫炒面片，午休 ', null, '0', '1', '1', '2016-04-22 13:40:39', '2016-04-22 13:40:42');
INSERT INTO `t_my_life_event` VALUES ('11', '2016-04-22 13:30:03', '2016-04-22 14:34:03', '为spring-boot的tomcat配置https支持', 'https://drissamri.be/blog/java/enable-https-in-spring-boot/', '0', '1', '1', '2016-04-22 14:35:08', '2016-04-22 14:35:10');
INSERT INTO `t_my_life_event` VALUES ('12', '2016-04-22 14:34:03', '2016-04-22 17:00:01', '调试unique_id的bug，由于把原始引用id给改了，所以比赛时间就错了，unique_id生成并无问题', null, '0', '1', '1', '2016-04-22 17:00:39', '2016-04-22 17:00:42');
INSERT INTO `t_my_life_event` VALUES ('13', '2016-04-22 17:00:01', '2016-04-22 18:06:13', '模仿github代码比例条，生成时间线', 'https://github.com/ronniewang/my-life', '0', '1', '1', '2016-04-22 18:06:53', '2016-04-22 18:06:55');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `is_email_valid` bit(1) DEFAULT NULL,
  `is_mobile_phone_valid` bit(1) DEFAULT NULL,
  `created_time` datetime NOT NULL,
  `last_modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'ro_wsy@163.com', '18910139781', '000000', 'ronniewang', '', '', '2016-04-22 10:15:42', '2016-04-22 10:15:45');
