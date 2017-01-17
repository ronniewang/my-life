/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : my-life

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-05-04 09:10:28
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_daily_summary
-- ----------------------------
DROP TABLE IF EXISTS `t_daily_summary`;
CREATE TABLE `t_daily_summary` (
  `id`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
  `summary`            LONGTEXT   NOT NULL,
  `created_time`       DATETIME            DEFAULT NULL,
  `name`               CHAR(255)           DEFAULT NULL,
  `read_times`         INT                 DEFAULT 0,
  `last_modified_time` DATETIME            DEFAULT NULL,
  `created_by`         BIGINT(20)          DEFAULT NULL,
  `last_modified_by`   BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_daily_summary
-- ----------------------------

-- ----------------------------
-- Table structure for t_event_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_event_tag`;
CREATE TABLE `t_event_tag` (
  `id`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id`            BIGINT(20) NOT NULL,
  `tag`                VARCHAR(0) NOT NULL,
  `created_time`       DATETIME            DEFAULT NULL,
  `last_modified_time` DATETIME            DEFAULT NULL,
  `created_by`         BIGINT(20)          DEFAULT NULL,
  `last_modified_by`   BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_event_tag
-- ----------------------------

-- ----------------------------
-- Table structure for t_event_type
-- ----------------------------
DROP TABLE IF EXISTS `t_event_type`;
CREATE TABLE `t_event_type` (
  `type_id`          INT(11)      NOT NULL,
  `type_description` VARCHAR(255) NOT NULL,
  `parent_type_id`   INT(11)      DEFAULT NULL,
  `has_children`     BIT(1)       NOT NULL,
  `color`            VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_event_type
-- ----------------------------
INSERT INTO `t_event_type` VALUES ('1', '工作', '0', '', '#EEB422');
INSERT INTO `t_event_type` VALUES ('2', '娱乐', '0', '', '#FF83FA');
INSERT INTO `t_event_type` VALUES ('3', '交通', '0', '', '	#8B5A00');
INSERT INTO `t_event_type` VALUES ('4', '睡觉', '0', '', '#000000');
INSERT INTO `t_event_type` VALUES ('5', '学习', '0', '', '#1C86EE');
INSERT INTO `t_event_type` VALUES ('6', '生活琐事', '0', '', '#AAAAAA');
INSERT INTO `t_event_type` VALUES ('7', '社交', '0', '', '#0000FF');
INSERT INTO `t_event_type` VALUES ('8', '吃饭', '0', '', '#FFFF00');
INSERT INTO `t_event_type` VALUES ('9', '运动', '0', '', '#B3EE3A');

-- ----------------------------
-- Table structure for t_my_life_event
-- ----------------------------
DROP TABLE IF EXISTS `t_my_life_event`;
CREATE TABLE `t_my_life_event` (
  `id`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
  `event_start_time`   DATETIME   NOT NULL,
  `event_end_time`     DATETIME   NOT NULL,
  `event_description`  LONGTEXT   NOT NULL,
  `event_resources`    LONGTEXT,
  `event_type`         INT(11)    NOT NULL,
  `event_duration`     INT(11)             DEFAULT NULL,
  `event_tags`         VARCHAR(255)        DEFAULT NULL,
  `created_by`         BIGINT(20) NOT NULL,
  `last_modified_by`   BIGINT(20) NOT NULL,
  `created_time`       DATETIME   NOT NULL,
  `last_modified_time` DATETIME   NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 62
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id`                    BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `email`                 VARCHAR(255)          DEFAULT NULL,
  `mobile_phone`          VARCHAR(255)          DEFAULT NULL,
  `password`              VARCHAR(255) NOT NULL,
  `username`              VARCHAR(255) NOT NULL,
  `is_email_valid`        BIT(1)                DEFAULT NULL,
  `is_mobile_phone_valid` BIT(1)                DEFAULT NULL,
  `created_time`          DATETIME     NOT NULL,
  `last_modified_time`    DATETIME     NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
  `content`            TEXT       NOT NULL,
  `created_time`       DATETIME   NOT NULL,
  `last_modified_time` DATETIME   NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
