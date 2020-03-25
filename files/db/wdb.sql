CREATE DATABASE `wpd` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `app_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appId` varchar(100) DEFAULT NULL,
  `clazz` varchar(100) DEFAULT NULL,
  `method` varchar(200) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `msg` text,
  `logger` varchar(100) DEFAULT NULL,
  `Remarks` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_question_dclt` (`Dclt`)
) ENGINE=InnoDB AUTO_INCREMENT=12406 DEFAULT CHARSET=utf8;

CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL DEFAULT '',
  `Title` varchar(100) NOT NULL DEFAULT '',
  `Tag` varchar(100) NOT NULL DEFAULT '',
  `Category` varchar(100) NOT NULL DEFAULT '',
  `Duration` int(11) NOT NULL,
  `Start` datetime DEFAULT NULL,
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `UserId` bigint(20) DEFAULT '0',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `clazz` char(1) DEFAULT '',
  `scope` char(1) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_dclt` (`Dclt`),
  KEY `idx_uid` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

CREATE TABLE `exam_invite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) DEFAULT '0',
  `vUserId` bigint(20) DEFAULT '0',
  `eid` int(11) DEFAULT '0',
  `uemail` varchar(100) NOT NULL DEFAULT '',
  `iid` varchar(100) NOT NULL DEFAULT '',
  `done` char(1) NOT NULL DEFAULT '',
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `firmId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_dclt` (`Dclt`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

CREATE TABLE `exam_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL DEFAULT '0',
  `qid` int(11) NOT NULL DEFAULT '0',
  `sequence` int(11) NOT NULL DEFAULT '0',
  `weight` int(11) NOT NULL DEFAULT '0',
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_question_dclt` (`Dclt`),
  KEY `idx_eid_qid` (`eid`,`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

CREATE TABLE `exam_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL DEFAULT '0',
  `uid` int(11) NOT NULL DEFAULT '0',
  `success` char(1) NOT NULL DEFAULT '' COMMENT ',T,F',
  `score` int(11) NOT NULL DEFAULT '0',
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_question_dclt` (`Dclt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `firm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `logoUrl` varchar(200) DEFAULT NULL,
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_firm_dclt` (`Dclt`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `invitee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firmId` int(11) DEFAULT '0',
  `name` varchar(32) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `cat1` varchar(200) DEFAULT NULL,
  `cat2` varchar(200) DEFAULT NULL,
  `cat3` varchar(200) DEFAULT NULL,
  `cat4` varchar(200) DEFAULT NULL,
  `cat5` varchar(200) DEFAULT NULL,
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_invitee_dclt` (`Dclt`),
  KEY `idx_invitee_firmId` (`firmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) NOT NULL DEFAULT '0',
  `eid` int(11) NOT NULL DEFAULT '0',
  `vid` int(11) NOT NULL DEFAULT '0',
  `uid` int(11) NOT NULL DEFAULT '0',
  `groupId` varchar(100) NOT NULL DEFAULT '',
  `source` text,
  `input` varchar(1000) NOT NULL DEFAULT '',
  `output` varchar(1000) NOT NULL DEFAULT '',
  `success` char(1) NOT NULL DEFAULT '' COMMENT ',T,F',
  `msg` varchar(1000) NOT NULL DEFAULT '',
  `weight` int(11) NOT NULL DEFAULT '0',
  `sequence` int(11) NOT NULL DEFAULT '0',
  `stage` char(1) NOT NULL DEFAULT '',
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lang` varchar(10) DEFAULT '',
  `score` int(11) DEFAULT '0' COMMENT '人工评分分数',
  `scoreReason` varchar(200) DEFAULT '' COMMENT '人工评分依据',
  `eiid` varchar(100) DEFAULT '' COMMENT '邀请码',
  PRIMARY KEY (`id`),
  KEY `idx_question_dclt` (`Dclt`),
  KEY `idx_eiid` (`eiid`)
) ENGINE=InnoDB AUTO_INCREMENT=449 DEFAULT CHARSET=utf8;

CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL DEFAULT '',
  `title` varchar(8000) DEFAULT NULL,
  `Source` text,
  `Category` varchar(100) NOT NULL DEFAULT '',
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lang` varchar(10) DEFAULT '',
  `level` int(11) DEFAULT '0' COMMENT ',1-5',
  `tag1` varchar(100) DEFAULT '' COMMENT ',',
  `tag2` varchar(100) DEFAULT '',
  `otherTag` varchar(500) DEFAULT '' COMMENT '其',
  `userId` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_question_dclt` (`Dclt`)
) ENGINE=InnoDB AUTO_INCREMENT=1245 DEFAULT CHARSET=utf8;

CREATE TABLE `question_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qid` bigint(20) DEFAULT '0',
  `lang` varchar(20) DEFAULT NULL,
  `source` text NOT NULL,
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_qid_lang_dclt` (`qid`,`lang`,`Dclt`)
) ENGINE=InnoDB AUTO_INCREMENT=15394 DEFAULT CHARSET=utf8;

CREATE TABLE `u_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

CREATE TABLE `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `u_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) DEFAULT NULL,
  `qid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `pswd` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `status` bigint(1) DEFAULT '1',
  `petname` varchar(20) DEFAULT '',
  `gender` char(1) DEFAULT '',
  `birthday` datetime DEFAULT NULL,
  `photoUrl` varchar(200) DEFAULT '',
  `intro` varchar(200) DEFAULT '',
  `firmId` int(11) DEFAULT '0',
  `roles` varchar(100) DEFAULT '' COMMENT '逗号隔开的角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

CREATE TABLE `u_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `validation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) NOT NULL DEFAULT '0',
  `sequnence` int(11) NOT NULL DEFAULT '0',
  `weight` int(11) NOT NULL DEFAULT '0',
  `input` varchar(1000) NOT NULL DEFAULT '',
  `output` varchar(1000) NOT NULL DEFAULT '',
  `Remarks` varchar(100) NOT NULL DEFAULT '',
  `Dclt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_question_dclt` (`Dclt`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

