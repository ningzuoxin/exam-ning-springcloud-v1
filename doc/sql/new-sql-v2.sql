-- 系统用户表
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `username` varchar(256) NOT NULL UNIQUE COMMENT '用户名',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `salt` varchar(128) NOT NULL COMMENT '盐',
  `nickname` varchar(256) NULL COMMENT '昵称',
  `gender` tinyint UNSIGNED DEFAULT 0 COMMENT '性别，0：未知；1：男；2：女；',
  `phone_number` varchar(128) NULL COMMENT '手机号码',
  `id_number` varchar(32) NULL COMMENT '身份证号',
  `email` varchar(128) NULL COMMENT '电子邮箱',
  `avatar` varchar(512) NULL COMMENT '头像',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_nickname` (`nickname`),
  INDEX `idx_phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '系统用户表';

-- 系统角色表
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_key` varchar(128) NOT NULL COMMENT '角色权限字符串',
  `sort_num` int DEFAULT 0 COMMENT '排序',
  `status` tinyint UNSIGNED DEFAULT 0 COMMENT '角色状态，0：正常；1：停用；',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_role_key` (`role_key`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '系统角色表';

-- 系统用户角色表
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `user_uid` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `role_uid` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_user_uid` (`user_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '系统用户角色表';

-- 系统菜单表
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `menu_name` varchar(64) NOT NULL COMMENT '菜单名称',
  `parent_uid` bigint UNSIGNED DEFAULT 0 COMMENT '父菜单ID',
  `sort_num` int DEFAULT 0 COMMENT '排序',
  `path` varchar(256) NULL COMMENT '路由地址',
  `component` varchar(256) NULL COMMENT '组件路径',
  `is_frame` tinyint UNSIGNED DEFAULT 0 COMMENT '是否为外链，0：否；1：是；',
  `menu_type`tinyint UNSIGNED DEFAULT 0 COMMENT '菜单类型，1：目录；2：菜单；3：按钮；',
  `status` tinyint UNSIGNED DEFAULT 0 COMMENT '菜单状态，0：正常；1：停用；',
  `perms` varchar(128) NULL COMMENT '权限标识',
  `icon` varchar(128) DEFAULT '#' COMMENT '菜单图标',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_parent_uid` (`parent_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '系统菜单表';

-- 系统角色菜单表
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `role_uid` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `menu_uid` bigint UNSIGNED NOT NULL COMMENT '菜单ID',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_role_uid` (`role_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '系统角色菜单表';

-- 从原 menu 表将数据迁移至新 sys_menu 表 SQL
insert into sys_menu(uid, menu_name, parent_uid, sort_num, `path`, component, is_frame, menu_type, status, perms, icon)
select m.menu_id, m.menu_name, m.parent_id, m.order_num, m.`path`, m.component, m.is_frame, (case when m.menu_type = 'M' then 1 when m.menu_type = 'C' then 2 when m.menu_type = 'F' then 3 end) as m_type, m.status, m.perms, m.icon
from menu m;

-- 试题表
CREATE TABLE IF NOT EXISTS `exam_question` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `type` varchar(32) NOT NULL COMMENT '题型',
  `content` text NOT NULL COMMENT '内容',
  `correct_answer` varchar(512) NOT NULL COMMENT '正确答案',
  `explanation` varchar(512) NULL COMMENT '解析',
  `options` varchar(512) NULL COMMENT '选项',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='试题表';

-- 试卷表
CREATE TABLE IF NOT EXISTS `exam_paper` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `type` tinyint UNSIGNED NOT NULL COMMENT '试卷类型，1：正式考试；2：模拟考试；3：日常练习；',
  `title` varchar(256) NOT NULL COMMENT '试卷标题',
  `time_limit` int UNSIGNED NOT NULL COMMENT '考试时间限制，单位：秒',
  `frequency_limit` int UNSIGNED NOT NULL COMMENT '考试次数限制，0：不限制；',
  `total_score` int UNSIGNED NOT NULL COMMENT '总分',
  `passed_score` int UNSIGNED NOT NULL COMMENT '及格分',
  `question_count` int UNSIGNED NOT NULL COMMENT '试题数量',
  `copy_paper_uid` bigint UNSIGNED DEFAULT 0 COMMENT '复制试卷ID',
  `status` tinyint UNSIGNED DEFAULT 0 COMMENT '试卷状态，0：正常；1：停用；',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_type_title` (`type`, `title`),
  INDEX `idx_copy_paper_uid` (`copy_paper_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='试卷表';

-- 试卷结果表
CREATE TABLE IF NOT EXISTS `exam_paper_result` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `paper_uid` bigint UNSIGNED NOT NULL COMMENT '试卷ID',
  `paper_title` varchar(256) NOT NULL COMMENT '试卷标题',
  `user_uid` bigint UNSIGNED NOT NULL COMMENT '答题者用户ID',
  `score` decimal(4,1) NOT NULL COMMENT '最终得分',
  `objective_score` decimal(4,1) NOT NULL COMMENT '客观题得分',
  `subjective_score` decimal(4,1) NOT NULL COMMENT '主观题得分',
  `comments` varchar(512) NULL COMMENT '教师点评',
  `right_count` int NOT NULL COMMENT '答题正确数',
  `time_used` int NOT NULL COMMENT '答题耗时，单位：秒',
  `status` tinyint UNSIGNED DEFAULT 0 COMMENT '阅卷状态，0：待阅卷；1：阅卷中；2：暂停；3：阅卷结束；',
  `check_user_uid` bigint UNSIGNED NOT NULL COMMENT '阅卷老师用户ID',
  `check_time` timestamp NULL COMMENT '批卷时间',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_paper_uid` (`paper_uid`),
  INDEX `idx_user_uid` (`user_uid`),
  INDEX `idx_check_user_uid` (`check_user_uid`),
  INDEX `idx_status` (`status`),
  INDEX `idx_score` (`score`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='试卷结果表';

-- 试卷试题表
CREATE TABLE IF NOT EXISTS `exam_paper_question` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `paper_uid` bigint UNSIGNED NOT NULL COMMENT '试卷ID',
  `question_uid` bigint UNSIGNED NOT NULL COMMENT '试题ID',
  `right_score` decimal(4,1) NOT NULL COMMENT '答对得分',
  `miss_score` decimal(4,1) NOT NULL COMMENT '漏选得分',
  `sort_num` int DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_paper_uid` (`paper_uid`),
  INDEX `idx_question_uid` (`question_uid`),
  INDEX `idx_sort_num` (`sort_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='试卷试题表';

CREATE TABLE IF NOT EXISTS `exam_paper_question_result` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `paper_result_uid` bigint UNSIGNED NOT NULL COMMENT '试卷结果ID',
  `paper_uid` bigint UNSIGNED NOT NULL COMMENT '试卷ID',
  `paper_question_uid` bigint UNSIGNED NOT NULL COMMENT '试卷试题ID',
  `question_uid` bigint UNSIGNED NOT NULL COMMENT '试题ID',
  `user_uid` bigint UNSIGNED NOT NULL COMMENT '答题者用户ID',
  `answer` text NOT NULL COMMENT '答题结果',
  `status` tinyint UNSIGNED DEFAULT 0 COMMENT '结果状态，0：未答；1：正确；2：部分正确；3：错误；',
  `score` decimal(4,1) NOT NULL COMMENT '得分',
  `comments` varchar(512) NULL COMMENT '教师点评',
  `is_deleted` tinyint UNSIGNED DEFAULT 0 COMMENT '是否删除，0：未删除；1：已删除；',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_paper_result_uid` (`paper_result_uid`),
  INDEX `idx_paper_uid` (`paper_uid`),
  INDEX `idx_paper_question_uid` (`paper_question_uid`),
  INDEX `idx_question_uid` (`question_uid`),
  INDEX `idx_user_uid` (`user_uid`),
  INDEX `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='试卷试题结果表';

