/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : my-life

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-04-26 09:17:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_daily_summary
-- ----------------------------
DROP TABLE IF EXISTS `t_daily_summary`;
CREATE TABLE `t_daily_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `summary` longtext NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_daily_summary
-- ----------------------------

-- ----------------------------
-- Table structure for t_event_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_event_tag`;
CREATE TABLE `t_event_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `tag` varchar(0) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_event_tag
-- ----------------------------

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
INSERT INTO `t_event_type` VALUES ('5', '学习', '0', '');
INSERT INTO `t_event_type` VALUES ('6', '家务事', '0', '');
INSERT INTO `t_event_type` VALUES ('8', '日常生活', '0', '');
INSERT INTO `t_event_type` VALUES ('9', '运动', '0', '');
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
  `event_duration` int(11) DEFAULT NULL,
  `event_tags` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) NOT NULL,
  `last_modified_by` bigint(20) NOT NULL,
  `created_time` datetime NOT NULL,
  `last_modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_my_life_event
-- ----------------------------
INSERT INTO `t_my_life_event` VALUES ('1', '2016-04-22 08:40:11', '2016-04-22 10:01:11', '搭建my-life项目，并测试可用', null, '1', '4860', null, '1', '1', '2016-04-22 10:01:11', '2016-04-25 14:38:34');
INSERT INTO `t_my_life_event` VALUES ('7', '2016-04-22 07:15:10', '2016-04-22 08:10:10', '坐地铁，看《把时间当做朋友》，受到启发，准备做一个生活轨迹记录系统', null, '3', '3300', null, '1', '1', '2016-04-22 10:26:19', '2016-04-25 14:39:28');
INSERT INTO `t_my_life_event` VALUES ('9', '2016-04-22 08:10:10', '2016-04-22 12:00:42', '查找time-line控件并调试', 'http://www.helloweba.com/demo/v_timeline/', '1', '13832', null, '1', '1', '2016-04-22 13:39:40', '2016-04-25 14:39:28');
INSERT INTO `t_my_life_event` VALUES ('10', '2016-04-22 12:00:42', '2016-04-22 13:30:03', '吃东方宫炒面片，午休 ', null, '8', '5361', null, '1', '1', '2016-04-22 13:40:39', '2016-04-25 14:38:40');
INSERT INTO `t_my_life_event` VALUES ('11', '2016-04-22 13:30:03', '2016-04-22 14:34:03', '为spring-boot的tomcat配置https支持', 'https://drissamri.be/blog/java/enable-https-in-spring-boot/', '1', '3840', null, '1', '1', '2016-04-22 14:35:08', '2016-04-25 14:38:40');
INSERT INTO `t_my_life_event` VALUES ('12', '2016-04-22 14:34:03', '2016-04-22 17:00:01', '调试unique_id的bug，由于把原始引用id给改了，所以比赛时间就错了，unique_id生成并无问题', null, '1', '8758', null, '1', '1', '2016-04-22 17:00:39', '2016-04-25 14:38:40');
INSERT INTO `t_my_life_event` VALUES ('13', '2016-04-22 17:00:01', '2016-04-22 18:06:13', '模仿github代码比例条，生成时间线', 'https://github.com/ronniewang/my-life', '1', '3972', null, '1', '1', '2016-04-22 18:06:53', '2016-04-25 14:38:40');
INSERT INTO `t_my_life_event` VALUES ('14', '2016-04-22 18:06:13', '2016-04-22 18:50:21', '在地铁上，看《把时间当做朋友》，养成做时间规划的习惯', null, '3', '2648', null, '1', '1', '2016-04-24 08:34:04', '2016-04-25 14:39:28');
INSERT INTO `t_my_life_event` VALUES ('15', '2016-04-22 06:50:21', '2016-04-22 19:15:44', '从南锣鼓巷跑到王府世纪，好累，中间停了三次，真的是该锻炼了', null, '9', '44723', null, '1', '1', '2016-04-24 08:35:12', '2016-04-25 14:38:40');
INSERT INTO `t_my_life_event` VALUES ('16', '2016-04-22 19:15:44', '2016-04-22 21:00:40', '和亲爱的去素食吃饭，还有小孩，小范，牛牛', null, '6', '6296', null, '1', '1', '2016-04-24 08:36:11', '2016-04-25 14:38:40');
INSERT INTO `t_my_life_event` VALUES ('17', '2016-04-22 21:00:40', '2016-04-22 21:40:40', '走到南锣鼓巷，坐地铁', null, '8', '2400', null, '1', '1', '2016-04-24 08:37:13', '2016-04-25 14:38:40');
INSERT INTO `t_my_life_event` VALUES ('18', '2016-04-22 21:40:40', '2016-04-22 22:10:56', '坐地铁，看《把时间当做朋友》，立即去做总比不断计划要好', null, '3', '1816', null, '1', '1', '2016-04-24 08:38:14', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('19', '2016-04-22 22:10:56', '2016-04-22 22:50:33', '陪亲爱的看天津的那个系统，槽点无数，嘿嘿', null, '1', '2377', null, '1', '1', '2016-04-24 08:38:50', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('20', '2016-04-22 22:50:33', '2016-04-22 23:50:17', '部落战争，战斗', null, '2', '3584', null, '1', '1', '2016-04-24 08:39:36', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('21', '2016-04-22 23:50:17', '2016-04-23 07:39:58', '睡觉，昨天跑步了，睡的好累', null, '4', '28181', null, '1', '1', '2016-04-24 08:40:13', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('22', '2016-04-23 07:39:58', '2016-04-23 07:50:32', '上厕所', null, '8', '634', null, '1', '1', '2016-04-24 08:41:02', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('23', '2016-04-23 07:50:32', '2016-04-23 09:10:24', '在mac上安装mysql，一直没有搞定', null, '1', '4792', null, '1', '1', '2016-04-24 08:41:51', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('24', '2016-04-23 09:10:24', '2016-04-23 09:42:09', '部落战争，加固村庄', null, '2', '1905', null, '1', '1', '2016-04-24 08:42:21', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('25', '2016-04-23 09:42:09', '2016-04-23 10:00:39', '安装成功了mysql', 'http://stackoverflow.com/questions/4359131/brew-install-mysql-on-mac-os/6378429#6378429', '1', '1110', null, '1', '1', '2016-04-24 08:43:02', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('26', '2016-04-23 10:00:39', '2016-04-23 11:34:06', '书到啦，哈哈哈，当当网', null, '2', '5607', null, '1', '1', '2016-04-24 08:44:28', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('27', '2016-04-23 11:34:06', '2016-04-23 15:31:48', '做麻酱拌面，看《欢乐颂》', null, '8', '14262', null, '1', '1', '2016-04-24 08:45:14', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('28', '2016-04-23 15:31:48', '2016-04-23 16:58:34', '午休', null, '8', '5206', null, '1', '1', '2016-04-24 08:46:04', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('29', '2016-04-23 16:58:34', '2016-04-23 19:31:28', '改天津的系统', null, '1', '9174', null, '1', '1', '2016-04-24 08:47:07', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('30', '2016-04-23 19:31:28', '2016-04-24 00:07:25', '看罗辑思维423读书会', null, '5', '16557', null, '1', '1', '2016-04-24 08:48:52', '2016-04-25 14:39:53');
INSERT INTO `t_my_life_event` VALUES ('31', '2016-04-24 00:07:25', '2016-04-24 07:32:08', '睡觉', null, '4', '26683', null, '1', '1', '2016-04-24 08:49:31', '2016-04-25 14:39:53');
INSERT INTO `t_my_life_event` VALUES ('32', '2016-04-24 07:32:08', '2016-04-24 08:29:47', '晨练，洗漱，煮鸡蛋', null, '8', '3459', null, '1', '1', '2016-04-24 08:50:12', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('33', '2016-04-24 08:29:47', '2016-04-24 08:52:57', '补前两天没输入数据库的数据记录', null, '8', '1390', null, '1', '1', '2016-04-24 08:53:25', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('34', '2016-04-24 08:52:57', '2016-04-24 10:38:00', '继续完成my-life项目', null, '1', '6321', null, '1', '1', '2016-04-25 08:38:51', '2016-04-25 14:38:41');
INSERT INTO `t_my_life_event` VALUES ('39', '2016-04-24 10:38:00', '2016-04-24 11:37:00', '去物美买东西', null, '8', '89940', null, '1', '1', '2016-04-25 17:17:04', '2016-04-25 17:17:04');
INSERT INTO `t_my_life_event` VALUES ('43', '2016-04-24 11:37:00', '2016-04-24 13:16:00', '麻将拌面，看跑男', null, '8', '5940', null, '1', '1', '2016-04-25 21:06:50', '2016-04-25 21:06:50');
INSERT INTO `t_my_life_event` VALUES ('44', '2016-04-24 13:16:00', '2016-04-24 17:55:00', '看欢乐颂，刷鞋，写了点代码，没啥状态', null, '8', '16740', null, '1', '1', '2016-04-25 21:09:40', '2016-04-25 21:09:40');
INSERT INTO `t_my_life_event` VALUES ('45', '2016-04-24 17:55:00', '2016-04-24 20:14:00', '做饭，香椿豆腐，茄丁炒肉，看欢乐颂', null, '8', '8340', null, '1', '1', '2016-04-25 21:10:06', '2016-04-25 21:10:06');
INSERT INTO `t_my_life_event` VALUES ('46', '2016-04-24 20:14:00', '2016-04-24 22:15:00', '部落战争', null, '2', '7260', null, '1', '1', '2016-04-25 21:10:42', '2016-04-25 21:10:42');
INSERT INTO `t_my_life_event` VALUES ('47', '2016-04-24 22:15:00', '2016-04-25 06:33:00', '睡觉', null, '0', '29880', null, '1', '1', '2016-04-25 21:23:09', '2016-04-25 21:23:09');
INSERT INTO `t_my_life_event` VALUES ('48', '2016-04-25 06:33:00', '2016-04-25 07:10:00', '起床，洗漱，热牛奶', null, '8', '2220', null, '1', '1', '2016-04-25 21:28:29', '2016-04-25 21:28:29');
INSERT INTO `t_my_life_event` VALUES ('49', '2016-04-25 07:10:00', '2016-04-25 08:13:00', '做地铁去上班，看《把时间当做朋友》', null, '3', '3780', null, '1', '1', '2016-04-25 21:29:19', '2016-04-25 21:29:19');
INSERT INTO `t_my_life_event` VALUES ('50', '2016-04-25 08:13:00', '2016-04-25 08:37:00', '711买早餐，吃的包子豆浆', null, '8', '1440', null, '1', '1', '2016-04-25 21:30:45', '2016-04-25 21:30:45');
INSERT INTO `t_my_life_event` VALUES ('51', '2016-04-25 08:37:00', '2016-04-25 12:02:00', '上午工作，继续开发my-life系统', null, '1', '12300', null, '1', '1', '2016-04-25 21:31:56', '2016-04-25 21:31:56');
INSERT INTO `t_my_life_event` VALUES ('52', '2016-04-25 12:02:00', '2016-04-25 13:15:00', '吃饭，午休', null, '8', '4380', null, '1', '1', '2016-04-25 21:32:25', '2016-04-25 21:32:25');
INSERT INTO `t_my_life_event` VALUES ('53', '2016-04-25 13:15:00', '2016-04-25 18:03:00', '下午工作，继续开发my-life系统，终于可以从页面增加了，哈哈哈', null, '1', '17280', null, '1', '1', '2016-04-25 21:33:42', '2016-04-25 21:33:42');
INSERT INTO `t_my_life_event` VALUES ('54', '2016-04-25 18:03:00', '2016-04-25 19:10:00', '下班了，做地铁回家，看《把时间当做朋友》，不要逃避，任务由简单的和难的组成，都做完了才叫完成任务，否则只是逃避而已', null, '3', '4020', null, '1', '1', '2016-04-25 21:35:40', '2016-04-25 21:35:40');
INSERT INTO `t_my_life_event` VALUES ('55', '2016-04-25 19:10:00', '2016-04-25 20:28:00', '做晚饭，吃的杏鲍菇和油麦菜，吃饭，看欢乐颂', null, '8', '4680', null, '1', '1', '2016-04-25 21:36:34', '2016-04-25 21:36:34');
INSERT INTO `t_my_life_event` VALUES ('56', '2016-04-25 20:28:00', '2016-04-25 21:01:00', '和亲爱的出去跑步', null, '9', '1980', null, '1', '1', '2016-04-25 21:38:29', '2016-04-25 21:38:29');
INSERT INTO `t_my_life_event` VALUES ('57', '2016-04-25 21:01:00', '2016-04-25 22:41:00', '完善my-life', null, '1', '6000', null, '1', '1', '2016-04-26 07:08:44', '2016-04-26 07:08:44');
INSERT INTO `t_my_life_event` VALUES ('58', '2016-04-25 22:41:00', '2016-04-26 06:35:00', '睡觉', null, '4', '28440', null, '1', '1', '2016-04-26 07:09:00', '2016-04-26 07:09:00');
INSERT INTO `t_my_life_event` VALUES ('59', '2016-04-26 06:35:00', '2016-04-26 07:11:00', '洗漱收拾', null, '8', '2160', null, '1', '1', '2016-04-26 07:09:24', '2016-04-26 07:09:24');
INSERT INTO `t_my_life_event` VALUES ('60', '2016-04-26 07:11:00', '2016-04-26 08:14:00', '地铁上，看《把时间当做朋友》', null, '3', '3780', null, '1', '1', '2016-04-26 08:48:19', '2016-04-26 08:48:19');
INSERT INTO `t_my_life_event` VALUES ('61', '2016-04-26 08:14:00', '2016-04-26 08:45:00', '711买早点，吃早饭，给同事煮鸡蛋', null, '8', '1860', null, '1', '1', '2016-04-26 08:49:10', '2016-04-26 08:49:10');

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
