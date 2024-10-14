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

