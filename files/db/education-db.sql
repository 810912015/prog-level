 CREATE DATABASE `education_system`;
 use education_system;

CREATE TABLE `Msg` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `msg_type` TINYINT DEFAULT NULL,
    `msg_title` VARCHAR(100) DEFAULT '',
    `is_time_send` TINYINT DEFAULT NULL,
    `send_time` TIMESTAMP DEFAULT NULL,
    `is_send` TINYINT DEFAULT NULL,
    `is_top` TINYINT DEFAULT NULL,
    `back_remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `MsgUser` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `msg_id` BIGINT DEFAULT NULL,
    `user_no` BIGINT DEFAULT NULL,
    `mobile` CHAR DEFAULT NULL,
    `msg_type` TINYINT DEFAULT NULL,
    `msg_title` VARCHAR(100) DEFAULT '',
    `is_read` TINYINT DEFAULT NULL,
    `is_top` TINYINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `NavBar` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `nav_title` VARCHAR(100) DEFAULT '',
    `nav_url` VARCHAR(100) DEFAULT '',
    `target` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `WebsiteNav` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `parent_id` BIGINT DEFAULT NULL,
    `nav_name` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `WebsiteLink` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `link_name` VARCHAR(100) DEFAULT '',
    `link_url` VARCHAR(100) DEFAULT '',
    `link_target` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `MsgTemplate` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `title` VARCHAR(100) DEFAULT '',
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `SysUser` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `user_no` BIGINT DEFAULT NULL,
    `mobile` CHAR DEFAULT NULL,
    `real_name` VARCHAR(100) DEFAULT '',
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `SysLog` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `user_no` BIGINT DEFAULT NULL,
    `real_name` VARCHAR(100) DEFAULT '',
    `ip` VARCHAR(100) DEFAULT '',
    `operation` VARCHAR(100) DEFAULT '',
    `method` VARCHAR(100) DEFAULT '',
    `path` VARCHAR(100) DEFAULT '',
    `content` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `Website` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `logo_ico` VARCHAR(100) DEFAULT '',
    `logo_img` VARCHAR(100) DEFAULT '',
    `website_title` VARCHAR(100) DEFAULT '',
    `website_keyword` VARCHAR(100) DEFAULT '',
    `website_desc` VARCHAR(100) DEFAULT '',
    `copyright` VARCHAR(100) DEFAULT '',
    `icp` VARCHAR(100) DEFAULT '',
    `prn` VARCHAR(100) DEFAULT '',
    `weixin` VARCHAR(100) DEFAULT '',
    `weixin_xcx` VARCHAR(100) DEFAULT '',
    `weibo` VARCHAR(100) DEFAULT '',
    `is_enable_statistics` TINYINT DEFAULT NULL,
    `statistics_code` VARCHAR(100) DEFAULT '',
    `is_show_service` TINYINT DEFAULT NULL,
    `service1` VARCHAR(100) DEFAULT '',
    `service2` VARCHAR(100) DEFAULT '',
    `user_agreement` VARCHAR(100) DEFAULT '',
    `recruit_title` VARCHAR(100) DEFAULT '',
    `recruit_info` VARCHAR(100) DEFAULT '',
    `entry_agreement` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `SysRole` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `role_name` VARCHAR(100) DEFAULT '',
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `Sys` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `video_type` TINYINT DEFAULT NULL,
    `polyv_useid` VARCHAR(100) DEFAULT '',
    `polyv_writetoken` VARCHAR(100) DEFAULT '',
    `polyv_readtoken` VARCHAR(100) DEFAULT '',
    `polyv_secretkey` VARCHAR(100) DEFAULT '',
    `file_type` TINYINT DEFAULT NULL,
    `aliyun_access_key_id` VARCHAR(100) DEFAULT '',
    `aliyun_access_key_secret` VARCHAR(100) DEFAULT '',
    `aliyun_oss_url` VARCHAR(100) DEFAULT '',
    `aliyun_oss_bucket` VARCHAR(100) DEFAULT '',
    `pay_type` TINYINT DEFAULT NULL,
    `pay_url` VARCHAR(100) DEFAULT '',
    `pay_key` VARCHAR(100) DEFAULT '',
    `pay_secret` VARCHAR(100) DEFAULT '',
    `notify_url` VARCHAR(100) DEFAULT '',
    `sms_code` VARCHAR(100) DEFAULT '',
    `sign_name` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `WebsiteNavArticle` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `nav_id` BIGINT DEFAULT NULL,
    `art_title` VARCHAR(100) DEFAULT '',
    `art_pic` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `SysRoleUser` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `role_id` BIGINT DEFAULT NULL,
    `user_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `SysMenu` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `parent_id` BIGINT DEFAULT NULL,
    `menu_name` VARCHAR(100) DEFAULT '',
    `menu_url` VARCHAR(100) DEFAULT '',
    `api_url` VARCHAR(100) DEFAULT '',
    `menu_icon` VARCHAR(100) DEFAULT '',
    `remark` VARCHAR(100) DEFAULT '',
    `menu_type` TINYINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `SysMenuRole` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `menu_id` BIGINT DEFAULT NULL,
    `role_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
 CREATE DATABASE `education_course`;
 use education_course;

CREATE TABLE `Dic` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `field_name` VARCHAR(100) DEFAULT '',
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseChapterAudit` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `audit_status` INTEGER DEFAULT NULL,
    `audit_opinion` VARCHAR(100) DEFAULT '',
    `course_id` BIGINT DEFAULT NULL,
    `chapter_name` VARCHAR(100) DEFAULT '',
    `chapter_desc` VARCHAR(100) DEFAULT '',
    `is_free` TINYINT DEFAULT NULL,
    `chapter_original` DECIMAL DEFAULT NULL,
    `chapter_discount` DECIMAL DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseChapterPeriod` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `course_id` BIGINT DEFAULT NULL,
    `chapter_id` BIGINT DEFAULT NULL,
    `period_name` VARCHAR(100) DEFAULT '',
    `period_desc` VARCHAR(100) DEFAULT '',
    `is_free` TINYINT DEFAULT NULL,
    `period_original` DECIMAL DEFAULT NULL,
    `period_discount` DECIMAL DEFAULT NULL,
    `count_buy` INTEGER DEFAULT NULL,
    `count_study` INTEGER DEFAULT NULL,
    `is_doc` TINYINT DEFAULT NULL,
    `doc_name` VARCHAR(100) DEFAULT '',
    `doc_url` VARCHAR(100) DEFAULT '',
    `is_video` VARCHAR(100) DEFAULT '',
    `video_no` BIGINT DEFAULT NULL,
    `video_name` VARCHAR(100) DEFAULT '',
    `video_length` VARCHAR(100) DEFAULT '',
    `video_vid` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `DicList` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `field_id` BIGINT DEFAULT NULL,
    `field_key` VARCHAR(100) DEFAULT '',
    `field_value` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `Course` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `lecturer_user_no` BIGINT DEFAULT NULL,
    `category_id1` BIGINT DEFAULT NULL,
    `category_id2` BIGINT DEFAULT NULL,
    `category_id3` BIGINT DEFAULT NULL,
    `course_name` VARCHAR(100) DEFAULT '',
    `course_logo` VARCHAR(100) DEFAULT '',
    `introduce_id` BIGINT DEFAULT NULL,
    `is_free` TINYINT DEFAULT NULL,
    `course_original` DECIMAL DEFAULT NULL,
    `course_discount` DECIMAL DEFAULT NULL,
    `is_putaway` TINYINT DEFAULT NULL,
    `course_sort` INTEGER DEFAULT NULL,
    `count_buy` INTEGER DEFAULT NULL,
    `count_study` INTEGER DEFAULT NULL,
    `period_total` INTEGER DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseChapter` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `course_id` BIGINT DEFAULT NULL,
    `chapter_name` VARCHAR(100) DEFAULT '',
    `chapter_desc` VARCHAR(100) DEFAULT '',
    `is_free` TINYINT DEFAULT NULL,
    `chapter_original` DECIMAL DEFAULT NULL,
    `chapter_discount` DECIMAL DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseIntroduce` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseCategory` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `parent_id` BIGINT DEFAULT NULL,
    `category_type` TINYINT DEFAULT NULL,
    `category_name` VARCHAR(100) DEFAULT '',
    `floor` TINYINT DEFAULT NULL,
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseUserStudy` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `course_id` BIGINT DEFAULT NULL,
    `user_no` BIGINT DEFAULT NULL,
    `period_total` INTEGER DEFAULT NULL,
    `period_study` INTEGER DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `OrderPay` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `order_no` BIGINT DEFAULT NULL,
    `serial_number` BIGINT DEFAULT NULL,
    `order_status` TINYINT DEFAULT NULL,
    `pay_type` TINYINT DEFAULT NULL,
    `pay_time` TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `Adv` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `adv_title` VARCHAR(100) DEFAULT '',
    `adv_img` VARCHAR(100) DEFAULT '',
    `adv_url` VARCHAR(100) DEFAULT '',
    `adv_target` VARCHAR(100) DEFAULT '',
    `adv_location` TINYINT DEFAULT NULL,
    `begin_time` TIMESTAMP DEFAULT NULL,
    `end_time` TIMESTAMP DEFAULT NULL,
    `plat_show` TINYINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseAudit` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `audit_status` INTEGER DEFAULT NULL,
    `audit_opinion` VARCHAR(100) DEFAULT '',
    `lecturer_user_no` BIGINT DEFAULT NULL,
    `category_id1` BIGINT DEFAULT NULL,
    `category_id2` BIGINT DEFAULT NULL,
    `category_id3` BIGINT DEFAULT NULL,
    `course_name` VARCHAR(100) DEFAULT '',
    `course_logo` VARCHAR(100) DEFAULT '',
    `introduce_id` BIGINT DEFAULT NULL,
    `is_free` TINYINT DEFAULT NULL,
    `course_original` DECIMAL DEFAULT NULL,
    `course_discount` DECIMAL DEFAULT NULL,
    `is_putaway` TINYINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `Zone` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `zone_name` VARCHAR(100) DEFAULT '',
    `zone_desc` VARCHAR(100) DEFAULT '',
    `zone_location` TINYINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseRecommend` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `category_id` BIGINT DEFAULT NULL,
    `course_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseUserStudyLog` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `course_id` BIGINT DEFAULT NULL,
    `course_name` VARCHAR(100) DEFAULT '',
    `chapter_id` BIGINT DEFAULT NULL,
    `chapter_name` VARCHAR(100) DEFAULT '',
    `period_id` BIGINT DEFAULT NULL,
    `period_name` VARCHAR(100) DEFAULT '',
    `user_no` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `OrderInfo` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `lecturer_user_no` BIGINT DEFAULT NULL,
    `lecturer_name` VARCHAR(100) DEFAULT '',
    `user_no` BIGINT DEFAULT NULL,
    `mobile` VARCHAR(100) DEFAULT '',
    `register_time` TIMESTAMP DEFAULT NULL,
    `order_no` BIGINT DEFAULT NULL,
    `course_id` BIGINT DEFAULT NULL,
    `course_name` VARCHAR(100) DEFAULT '',
    `price_payable` DECIMAL DEFAULT NULL,
    `price_discount` DECIMAL DEFAULT NULL,
    `price_paid` DECIMAL DEFAULT NULL,
    `platform_profit` DECIMAL DEFAULT NULL,
    `lecturer_profit` DECIMAL DEFAULT NULL,
    `trade_type` TINYINT DEFAULT NULL,
    `pay_type` TINYINT DEFAULT NULL,
    `channel_type` TINYINT DEFAULT NULL,
    `order_status` TINYINT DEFAULT NULL,
    `is_show_lecturer` TINYINT DEFAULT NULL,
    `is_show_user` TINYINT DEFAULT NULL,
    `remark_cus` VARCHAR(100) DEFAULT '',
    `remark` VARCHAR(100) DEFAULT '',
    `pay_time` TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `ZoneCourse` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `zone_id` BIGINT DEFAULT NULL,
    `zone_location` TINYINT DEFAULT NULL,
    `course_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseVideo` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `course_id` BIGINT DEFAULT NULL,
    `chapter_id` BIGINT DEFAULT NULL,
    `period_id` BIGINT DEFAULT NULL,
    `video_name` VARCHAR(100) DEFAULT '',
    `video_no` BIGINT DEFAULT NULL,
    `video_status` TINYINT DEFAULT NULL,
    `video_length` VARCHAR(100) DEFAULT '',
    `video_vid` VARCHAR(100) DEFAULT '',
    `video_oas_id` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `FileStorage` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `file_no` BIGINT DEFAULT NULL,
    `file_name` VARCHAR(100) DEFAULT '',
    `file_url` VARCHAR(100) DEFAULT '',
    `file_type` TINYINT DEFAULT NULL,
    `file_size` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseChapterPeriodAudit` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `audit_status` INTEGER DEFAULT NULL,
    `audit_opinion` VARCHAR(100) DEFAULT '',
    `course_id` BIGINT DEFAULT NULL,
    `chapter_id` BIGINT DEFAULT NULL,
    `period_name` VARCHAR(100) DEFAULT '',
    `period_desc` VARCHAR(100) DEFAULT '',
    `is_free` TINYINT DEFAULT NULL,
    `period_original` DECIMAL DEFAULT NULL,
    `period_discount` DECIMAL DEFAULT NULL,
    `count_buy` INTEGER DEFAULT NULL,
    `count_study` INTEGER DEFAULT NULL,
    `is_doc` TINYINT DEFAULT NULL,
    `doc_name` VARCHAR(100) DEFAULT '',
    `doc_url` VARCHAR(100) DEFAULT '',
    `is_video` VARCHAR(100) DEFAULT '',
    `video_no` BIGINT DEFAULT NULL,
    `video_name` VARCHAR(100) DEFAULT '',
    `video_length` VARCHAR(100) DEFAULT '',
    `video_vid` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `CourseIntroduceAudit` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
 CREATE DATABASE `education_user`;
 use education_user;

CREATE TABLE `Lecturer` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `lecturer_user_no` BIGINT DEFAULT NULL,
    `lecturer_name` VARCHAR(100) DEFAULT '',
    `lecturer_mobile` CHAR DEFAULT NULL,
    `lecturer_email` VARCHAR(100) DEFAULT '',
    `position` VARCHAR(100) DEFAULT '',
    `head_img_url` VARCHAR(100) DEFAULT '',
    `introduce` VARCHAR(100) DEFAULT '',
    `lecturer_proportion` DECIMAL DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `LecturerProfit` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `lecturer_user_no` BIGINT DEFAULT NULL,
    `bank_card_no` VARCHAR(100) DEFAULT '',
    `bank_name` VARCHAR(100) DEFAULT '',
    `bank_branch_name` VARCHAR(100) DEFAULT '',
    `bank_user_name` VARCHAR(100) DEFAULT '',
    `bank_id_card_no` VARCHAR(100) DEFAULT '',
    `lecturer_profit` DECIMAL DEFAULT NULL,
    `platform_profit` DECIMAL DEFAULT NULL,
    `profit_status` TINYINT DEFAULT NULL,
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `Region` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `parent_id` BIGINT DEFAULT NULL,
    `level` INTEGER DEFAULT NULL,
    `center_lng` DECIMAL DEFAULT NULL,
    `center_lat` DECIMAL DEFAULT NULL,
    `province_id` INTEGER DEFAULT NULL,
    `province_code` VARCHAR(100) DEFAULT '',
    `province_name` VARCHAR(100) DEFAULT '',
    `city_id` INTEGER DEFAULT NULL,
    `city_code` VARCHAR(100) DEFAULT '',
    `city_name` VARCHAR(100) DEFAULT '',
    `region_name` VARCHAR(100) DEFAULT '',
    `district_name` VARCHAR(100) DEFAULT '',
    `merger_name` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `User` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `user_no` BIGINT DEFAULT NULL,
    `mobile` VARCHAR(100) DEFAULT '',
    `mobile_salt` VARCHAR(100) DEFAULT '',
    `mobile_psw` VARCHAR(100) DEFAULT '',
    `user_source` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `UserExt` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `user_no` BIGINT DEFAULT NULL,
    `user_type` TINYINT DEFAULT NULL,
    `mobile` VARCHAR(100) DEFAULT '',
    `sex` TINYINT DEFAULT NULL,
    `age` INTEGER DEFAULT NULL,
    `nickname` VARCHAR(100) DEFAULT '',
    `head_img_url` VARCHAR(100) DEFAULT '',
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `SendSmsLog` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `template` VARCHAR(100) DEFAULT '',
    `mobile` CHAR DEFAULT NULL,
    `sms_code` VARCHAR(100) DEFAULT '',
    `is_success` TINYINT DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `LecturerAudit` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `audit_status` TINYINT DEFAULT NULL,
    `audit_opinion` VARCHAR(100) DEFAULT '',
    `lecturer_user_no` BIGINT DEFAULT NULL,
    `lecturer_name` VARCHAR(100) DEFAULT '',
    `lecturer_mobile` CHAR DEFAULT NULL,
    `lecturer_email` VARCHAR(100) DEFAULT '',
    `position` VARCHAR(100) DEFAULT '',
    `head_img_url` VARCHAR(100) DEFAULT '',
    `introduce` VARCHAR(100) DEFAULT '',
    `lecturer_proportion` DECIMAL DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `UserLogLogin` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `user_no` BIGINT DEFAULT NULL,
    `client_id` CHAR DEFAULT NULL,
    `login_status` TINYINT DEFAULT NULL,
    `login_ip` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `Platform` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `client_id` CHAR DEFAULT NULL,
    `client_secret` CHAR DEFAULT NULL,
    `client_name` VARCHAR(100) DEFAULT '',
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `LecturerExt` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `gmt_modified` TIMESTAMP DEFAULT NULL,
    `status_id` TINYINT DEFAULT NULL,
    `sort` INTEGER DEFAULT NULL,
    `lecturer_user_no` BIGINT DEFAULT NULL,
    `total_income` DECIMAL DEFAULT NULL,
    `history_money` DECIMAL DEFAULT NULL,
    `enable_balances` DECIMAL DEFAULT NULL,
    `freeze_balances` DECIMAL DEFAULT NULL,
    `sign` CHAR DEFAULT NULL,
    `bank_card_no` VARCHAR(100) DEFAULT '',
    `bank_name` VARCHAR(100) DEFAULT '',
    `bank_branch_name` VARCHAR(100) DEFAULT '',
    `bank_user_name` VARCHAR(100) DEFAULT '',
    `bank_id_card_no` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
CREATE TABLE `UserLogModified` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `gmt_create` TIMESTAMP DEFAULT NULL,
    `user_no` BIGINT DEFAULT NULL,
    `mobile_old` CHAR DEFAULT NULL,
    `mobile_new` CHAR DEFAULT NULL,
    `remark` VARCHAR(100) DEFAULT '',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;
