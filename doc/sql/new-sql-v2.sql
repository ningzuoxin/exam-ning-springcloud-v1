-- 系统用户表
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `uid` bigint UNSIGNED NOT NULL UNIQUE COMMENT '业务ID',
  `username` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE COMMENT '用户名',
  `password` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `salt` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '盐',
  `nickname` varchar(256) COLLATE utf8mb4_unicode_ci NULL COMMENT '昵称',
  `gender` tinyint UNSIGNED DEFAULT 0 COMMENT '性别，0：未知；1：男；2：女；',
  `phone_number` varchar(128) COLLATE utf8mb4_unicode_ci NULL COMMENT '手机号码',
  `id_number` varchar(32) COLLATE utf8mb4_unicode_ci NULL COMMENT '身份证号',
  `email` varchar(128) COLLATE utf8mb4_unicode_ci NULL COMMENT '电子邮箱',
  `avatar` varchar(512) COLLATE utf8mb4_unicode_ci NULL COMMENT '头像',
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
  `role_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限字符串',
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
  `menu_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `parent_uid` bigint UNSIGNED DEFAULT 0 COMMENT '父菜单ID',
  `sort_num` int DEFAULT 0 COMMENT '排序',
  `path` varchar(256) COLLATE utf8mb4_unicode_ci NULL COMMENT '路由地址',
  `component` varchar(256) COLLATE utf8mb4_unicode_ci NULL COMMENT '组件路径',
  `is_frame` tinyint UNSIGNED DEFAULT 0 COMMENT '是否为外链，0：否；1：是；',
  `menu_type`tinyint UNSIGNED DEFAULT 0 COMMENT '菜单类型，1：目录；2：菜单；3：按钮；',
  `status` tinyint UNSIGNED DEFAULT 0 COMMENT '菜单状态，0：正常；1：停用；',
  `perms` varchar(128) COLLATE utf8mb4_unicode_ci NULL COMMENT '权限标识',
  `icon` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '菜单图标',
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

