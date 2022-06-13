SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database pccw default character set utf8mb4 collate utf8mb4_general_ci;
use pccw;

CREATE TABLE `pccw_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(36) NOT NULL DEFAULT '',
  `email` varchar(50) NOT NULL DEFAULT '',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;


CREATE TABLE `pccw_user_mail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL DEFAULT '',
  `mail_file` varchar(50) NOT NULL,
  `state` tinyint(8) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idex_email` (`email`) USING BTREE,
  KEY `idx_time_state` (`state`,`create_time`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;