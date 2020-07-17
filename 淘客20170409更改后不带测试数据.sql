/*
Navicat MySQL Data Transfer

Source Server         : mwb
Source Server Version : 50637
Source Host           : 47.93.194.91:3306
Source Database       : taosearch

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2020-07-17 14:10:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_authorization
-- ----------------------------
DROP TABLE IF EXISTS `t_authorization`;
CREATE TABLE `t_authorization` (
  `authorization_id` int(11) NOT NULL AUTO_INCREMENT,
  `authorization` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`authorization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_coupon
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon` (
  `coupon_id` varchar(32) NOT NULL,
  `coupon_start_time` datetime DEFAULT NULL,
  `coupon_end_time` datetime DEFAULT NULL,
  `coupon_url` varchar(200) DEFAULT NULL,
  `activity_start_time` datetime DEFAULT NULL,
  `coupon_rest_num` int(11) DEFAULT NULL,
  `coupon_get_num` int(11) unsigned DEFAULT '0',
  `coupon_use_num` int(11) DEFAULT NULL,
  `coupon_type_id` int(11) DEFAULT '1',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_item
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item` (
  `item_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `item_no` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `item_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `item_name` varchar(100) DEFAULT NULL,
  `item_md` varchar(200) DEFAULT NULL,
  `item_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_type` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `item_hdlx` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '001 通用\n            002 定向\n            003 鹊桥',
  `item_yjbl` double DEFAULT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `item_qhjg` double DEFAULT NULL,
  `state` varchar(4) CHARACTER SET utf8 DEFAULT NULL COMMENT '''001'':''待审核'';''002'':''待二审'';''003'':''推广中'';''004'':''已结束'';''005'':''待付款'';''006'':''付款中'';''007'':''已付款'';''112'':''驳回'';''667'':''拒绝付款'';''999'':''拒绝'';',
  `item_fwdj` double DEFAULT NULL,
  `coupon_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `item_image` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `qq` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_generalize_main_image` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `item_main_image` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `item_image_backup` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_website_type` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `item_jhlb` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `item_jhlj` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_zfje` double DEFAULT NULL,
  `item_ssje` double DEFAULT NULL,
  `item_zfje_state` varchar(10) CHARACTER SET utf8 DEFAULT '0',
  `item_merge` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_sflx` int(11) NOT NULL DEFAULT '0',
  `real_image` varchar(255) DEFAULT NULL,
  `real_image_text` varchar(255) DEFAULT NULL,
  `friend_text` varchar(255) DEFAULT NULL,
  `head_id` varchar(32) DEFAULT NULL,
  `shop_id` int(11) unsigned NOT NULL DEFAULT '0',
  `item_tgsc` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`item_id`),
  KEY `state` (`state`) USING BTREE,
  KEY `idx_shop_id` (`shop_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_item_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_item_attachment`;
CREATE TABLE `t_item_attachment` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` varchar(32) DEFAULT NULL,
  `attachment_value` varchar(200) DEFAULT NULL,
  `attachment_user_name` varchar(30) DEFAULT NULL,
  `attachment_createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75693 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_item_audit_log
-- ----------------------------
DROP TABLE IF EXISTS `t_item_audit_log`;
CREATE TABLE `t_item_audit_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` varchar(32) DEFAULT NULL,
  `before_audit_status` varchar(10) DEFAULT NULL,
  `after_audit_status` varchar(10) DEFAULT NULL,
  `auditor` varchar(32) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `audit_remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=266865 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_item_state_dic
-- ----------------------------
DROP TABLE IF EXISTS `t_item_state_dic`;
CREATE TABLE `t_item_state_dic` (
  `state` varchar(4) NOT NULL,
  `state_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_item_type
-- ----------------------------
DROP TABLE IF EXISTS `t_item_type`;
CREATE TABLE `t_item_type` (
  `type_id` varchar(10) NOT NULL COMMENT '000 所有分类\r\n            001 女装\r\n            002 男装\r\n            003 内衣\r\n            004 母婴\r\n            005 化妆品\r\n            006 居家\r\n            007 鞋包配饰\r\n            008 美食\r\n            009 文体车品\r\n            010 数码家电',
  `type_name` varchar(20) DEFAULT NULL COMMENT '001 女装\r\n            002 男装\r\n            003 内衣\r\n            004 母婴\r\n            005 化妆品\r\n            006 居家\r\n            007 鞋包配饰\r\n            008 美食\r\n            009 文体车品\r\n            010 数码家电',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `address` varchar(512) NOT NULL DEFAULT '',
  `link` varchar(1024) NOT NULL DEFAULT '',
  `item_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '去重推广次数',
  `real_item_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '推广次数',
  `price` double NOT NULL DEFAULT '0' COMMENT '均客单价',
  `total_settlement` double NOT NULL,
  `settlement` double NOT NULL DEFAULT '0' COMMENT '均结算价',
  `add_time` datetime NOT NULL,
  `user_id` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='店铺信息';

-- ----------------------------
-- Table structure for t_shop_user
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_user`;
CREATE TABLE `t_shop_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `phone` varchar(64) NOT NULL DEFAULT '',
  `qq` varchar(64) NOT NULL DEFAULT '',
  `qq_name` varchar(64) NOT NULL DEFAULT '',
  `wechat` varchar(64) NOT NULL DEFAULT '',
  `wechat_name` varchar(64) NOT NULL DEFAULT '',
  `shop_id` int(11) unsigned NOT NULL,
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0老板 1运营',
  `add_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='店铺人员信息';

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `user_id` varchar(32) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `pwd` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL COMMENT '1启用0停用',
  `team_id` varchar(32) DEFAULT NULL,
  `createuser` varchar(32) DEFAULT NULL,
  `updateuser` varchar(32) DEFAULT NULL,
  `admin` varchar(1) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_team
-- ----------------------------
DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
  `team_id` varchar(32) NOT NULL,
  `team_name` varchar(30) DEFAULT NULL,
  `team_leader` varchar(32) DEFAULT NULL,
  `team_remark` varchar(200) DEFAULT NULL,
  `team_state` varchar(10) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `createuser` varchar(32) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `updateuser` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_authorization_cfg
-- ----------------------------
DROP TABLE IF EXISTS `t_user_authorization_cfg`;
CREATE TABLE `t_user_authorization_cfg` (
  `user_id` varchar(32) NOT NULL,
  `authorization_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`authorization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
