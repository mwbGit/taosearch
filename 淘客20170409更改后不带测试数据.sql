/*
SQLyog v10.2 
MySQL - 5.5.28 : Database - taosearch
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`taosearch` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `taosearch`;

/*Table structure for table `t_authorization` */

DROP TABLE IF EXISTS `t_authorization`;

CREATE TABLE `t_authorization` (
  `authorization_id` int(11) NOT NULL AUTO_INCREMENT,
  `authorization` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`authorization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_authorization` */

/*Table structure for table `t_coupon` */

DROP TABLE IF EXISTS `t_coupon`;

CREATE TABLE `t_coupon` (
  `coupon_id` varchar(32) NOT NULL,
  `coupon_start_time` datetime DEFAULT NULL,
  `coupon_end_time` datetime DEFAULT NULL,
  `coupon_url` varchar(200) DEFAULT NULL,
  `activity_start_time` datetime DEFAULT NULL,
  `coupon_rest_num` int(11) DEFAULT NULL,
  `coupon_get_num` int(11) DEFAULT '0',
  `coupon_use_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_coupon` */

/*Table structure for table `t_item` */

DROP TABLE IF EXISTS `t_item`;

CREATE TABLE `t_item` (
  `item_id` varchar(32) NOT NULL,
  `item_no` varchar(50) DEFAULT NULL,
  `item_url` varchar(255) DEFAULT NULL,
  `item_name` varchar(100) DEFAULT NULL,
  `item_md` varchar(200) DEFAULT NULL,
  `item_title` varchar(100) DEFAULT NULL,
  `item_type` varchar(10) DEFAULT NULL,
  `item_hdlx` varchar(10) DEFAULT NULL COMMENT '001 通用\n            002 定向\n            003 鹊桥',
  `item_yjbl` double DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `item_qhjg` double DEFAULT NULL,
  `state` varchar(4) DEFAULT NULL COMMENT '''001'':''待审核'';''002'':''待二审'';''003'':''推广中'';''004'':''已结束'';''005'':''待付款'';''006'':''付款中'';''007'':''已付款'';''112'':''驳回'';''667'':''拒绝付款'';''999'':''拒绝'';',
  `item_fwdj` double DEFAULT NULL,
  `coupon_id` varchar(32) DEFAULT NULL,
  `item_image` varchar(200) DEFAULT NULL,
  `qq` varchar(30) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `item_image_backup` varchar(200) DEFAULT NULL,
  `item_main_image` varchar(200) DEFAULT NULL,
  `item_website_type` varchar(10) DEFAULT NULL,
  `item_jhlb` varchar(10) DEFAULT NULL,
  `item_jhlj` varchar(255) DEFAULT NULL,
  `item_zfje` double DEFAULT NULL,
  `item_ssje` double DEFAULT NULL,
  `item_zfje_state` varchar(10) DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_item` */

/*Table structure for table `t_item_attachment` */

DROP TABLE IF EXISTS `t_item_attachment`;

CREATE TABLE `t_item_attachment` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` varchar(32) DEFAULT NULL,
  `attachment_value` varchar(200) DEFAULT NULL,
  `attachment_user_name` varchar(30) DEFAULT NULL,
  `attachment_createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `t_item_attachment` */

/*Table structure for table `t_item_audit_log` */

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
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

/*Data for the table `t_item_audit_log` */

/*Table structure for table `t_item_state_dic` */

DROP TABLE IF EXISTS `t_item_state_dic`;

CREATE TABLE `t_item_state_dic` (
  `state` varchar(4) NOT NULL,
  `state_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_item_state_dic` */

insert  into `t_item_state_dic`(`state`,`state_name`) values ('001','待审核'),('002','待二审'),('003','推广中'),('004','已结束'),('005','待付款'),('006','付款中'),('007','已付款'),('112','驳回'),('667','拒绝付款'),('999','拒绝'),('008','审核中'),('009','即将结束');

/*Table structure for table `t_item_type` */

DROP TABLE IF EXISTS `t_item_type`;

CREATE TABLE `t_item_type` (
  `type_id` varchar(10) NOT NULL COMMENT '000 所有分类\r\n            001 女装\r\n            002 男装\r\n            003 内衣\r\n            004 母婴\r\n            005 化妆品\r\n            006 居家\r\n            007 鞋包配饰\r\n            008 美食\r\n            009 文体车品\r\n            010 数码家电',
  `type_name` varchar(20) DEFAULT NULL COMMENT '001 女装\r\n            002 男装\r\n            003 内衣\r\n            004 母婴\r\n            005 化妆品\r\n            006 居家\r\n            007 鞋包配饰\r\n            008 美食\r\n            009 文体车品\r\n            010 数码家电',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_item_type` */

insert  into `t_item_type`(`type_id`,`type_name`) values ('001','女装'),('002','男装'),('003','内衣'),('004','母婴'),('005','化妆品'),('006','居家'),('007','鞋包配饰'),('008','美食'),('009','文体车品'),('010','数码家电');

/*Table structure for table `t_sys_user` */

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
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`user_id`,`username`,`pwd`,`createtime`,`updatetime`,`tel`,`state`,`team_id`,`createuser`,`updateuser`,`admin`) values ('5142cc5cbec649f2b602272e26979a99','admin','63a4d0bc1b9857a93d0499c26c16cf0b','2017-03-27 14:57:25','2017-04-02 23:24:35','18600999998','1','',NULL,'db2f9323138446fd9d7b881481f6bc0e','1');

/*Table structure for table `t_team` */

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

/*Data for the table `t_team` */

/*Table structure for table `t_user_authorization_cfg` */

DROP TABLE IF EXISTS `t_user_authorization_cfg`;

CREATE TABLE `t_user_authorization_cfg` (
  `user_id` varchar(32) NOT NULL,
  `authorization_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`authorization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_authorization_cfg` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

UPDATE `t_coupon` set coupon_get_num = 0 WHERE coupon_get_num is NULL;
