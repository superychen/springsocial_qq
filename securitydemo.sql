/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.21 : Database - toupiao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`securitydemo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `securitydemo`;

/*Table structure for table `persistent_logins` */

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `persistent_logins` */

insert  into `persistent_logins`(`username`,`series`,`token`,`last_used`) values 
('cqyc','31TIeMaW4AmdelkLgibsgg==','Ths2sO2s1BxkUufXmwRubA==','2019-06-30 17:22:54'),
('cqyc','FzzJcLWown2OaxDTYN9Pdg==','nOKQoNGEcHDWkG8W1V7kdQ==','2019-06-30 10:07:32');

/*Table structure for table `results` */


DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `login_name` varchar(32) DEFAULT NULL,
  `passwrod` varchar(64) DEFAULT NULL,
  `user_type` char(1) DEFAULT NULL,
  `del_flag` varchar(10) DEFAULT '0' COMMENT '用户是否删除',
  `voting_group` varchar(255) DEFAULT '0' COMMENT '已经投票的组',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`login_name`,`passwrod`,`user_type`,`del_flag`,`voting_group`) values 
(2,'杨晨','cqyc','$2a$10$/pQlCv.orMyX3yYRlLPvxesib4OD6ktGHW2DEBfk3kX3P/tur4qpS','0','0','0');

/*Table structure for table `userconnection` */

DROP TABLE IF EXISTS `userconnection`;

CREATE TABLE `userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL,
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userconnection` */

insert  into `userconnection`(`userId`,`providerId`,`providerUserId`,`rank`,`displayName`,`profileUrl`,`imageUrl`,`accessToken`,`secret`,`refreshToken`,`expireTime`) values 
('阿司匹林','qq','55621336C15AF7167B627E07A347A42E',1,'阿司匹林',NULL,'http://thirdqq.qlogo.cn/g?b=oidb&k=Z6Z4ia0g6ww3zvh1E99OxAQ&s=40','05356FFDA932377A392374D3A79B594A',NULL,'7B474E5B70831F3A70F035872B3157FB',1570082172260);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
