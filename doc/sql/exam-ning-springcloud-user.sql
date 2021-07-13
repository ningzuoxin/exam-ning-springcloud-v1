/*
Navicat MySQL Data Transfer

Source Server         : LOCAL
Source Server Version : 80020
Source Host           : 127.0.0.1:3306
Source Database       : exam-ning-springcloud-user

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2021-07-13 14:24:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('2', '系统管理', '0', '1', '/system', '', '1', 'M', '0', '0', '', 'el-icon-setting', '', '2021-06-17 16:50:55', '', '2021-06-17 16:50:55', '');
INSERT INTO `menu` VALUES ('3', '试卷管理', '0', '2', '/testPaper', '', '1', 'M', '0', '0', '', 'el-icon-notebook-1', '', '2021-06-17 17:18:48', '', '2021-07-08 14:21:45', '');
INSERT INTO `menu` VALUES ('4', '考试中心', '0', '3', '/exam', '', '1', 'M', '0', '0', '', 'el-icon-aim', '', '2021-06-17 17:19:44', '', '2021-07-08 14:22:44', '');
INSERT INTO `menu` VALUES ('5', '用户管理', '2', '1', 'listUser', 'system/listUser', '1', 'C', '0', '0', 'system:user:page', 'el-icon-user-solid', '', '2021-06-17 17:22:46', '', '2021-06-17 17:22:46', '');
INSERT INTO `menu` VALUES ('6', '角色管理', '2', '2', 'listRole', 'system/listRole', '1', 'C', '0', '0', 'system:role:page', 'el-icon-s-help', '', '2021-06-18 09:26:53', '', '2021-06-18 09:26:53', '');
INSERT INTO `menu` VALUES ('7', '菜单管理', '2', '3', 'listMenu', 'system/listMenu', '1', 'C', '0', '0', 'system:menu:page', 'el-icon-s-platform', '', '2021-06-18 09:32:09', '', '2021-06-18 09:32:09', '');
INSERT INTO `menu` VALUES ('10', '添加用户', '2', '4', 'addUser', 'system/addUser', '1', 'F', '1', '0', 'system:user:add', 'el-icon-user-solid', '', '2021-06-18 15:05:50', '', '2021-06-18 15:05:50', '');
INSERT INTO `menu` VALUES ('11', '编辑用户', '2', '5', 'updateUser', 'system/updateUser', '1', 'F', '1', '0', 'system:user:edit', 'el-icon-user-solid', '', '2021-06-18 15:07:12', '', '2021-06-18 15:07:12', '');
INSERT INTO `menu` VALUES ('12', '添加角色', '2', '6', 'addRole', 'system/addRole', '1', 'F', '1', '0', 'system:role:add', 'el-icon-user-solid', '', '2021-06-18 15:08:51', '', '2021-06-18 15:08:51', '');
INSERT INTO `menu` VALUES ('13', '添加菜单', '2', '7', 'addMenu', 'system/addMenu', '1', 'F', '1', '0', 'system:menu:add', 'el-icon-user-solid', '', '2021-06-18 15:09:36', '', '2021-06-18 15:09:36', '');
INSERT INTO `menu` VALUES ('15', '添加试卷', '3', '2', 'addTestPaper', 'testPaper/addTestPaper', '1', 'C', '0', '0', 'exam:testPaper:add', 'el-icon-document-add', '', '2021-06-22 09:28:43', '', '2021-06-22 09:28:43', '');
INSERT INTO `menu` VALUES ('16', '预览试卷', '3', '3', 'previewTestPaper', 'testPaper/previewTestPaper', '1', 'F', '1', '0', 'exam:testPaper:preview', '#', '', '2021-06-22 09:30:12', '', '2021-06-22 09:30:12', '');
INSERT INTO `menu` VALUES ('17', '试题列表', '3', '4', 'listQuestion', 'testPaper/listQuestion', '1', 'C', '0', '0', 'exam:question:page', 'el-icon-c-scale-to-original', '', '2021-06-22 09:32:12', '', '2021-07-08 14:24:41', '');
INSERT INTO `menu` VALUES ('18', '添加试题', '3', '5', 'addQuestion', 'testPaper/addQuestion', '1', 'F', '1', '0', 'exam:question:add', '#', '', '2021-06-22 09:33:27', '', '2021-06-22 09:33:27', '');
INSERT INTO `menu` VALUES ('19', '考试列表', '4', '1', 'listExam', 'exam/listExam', '1', 'C', '0', '0', 'exam:testPaper:listExam', 'el-icon-tickets', '', '2021-06-22 09:35:52', '', '2021-06-22 09:35:52', '');
INSERT INTO `menu` VALUES ('20', '批阅试卷', '4', '2', 'listMark', 'exam/listMark', '1', 'C', '0', '0', 'exam:result:marks', 'el-icon-edit-outline', '', '2021-06-22 09:37:11', '', '2021-07-08 14:25:10', '');
INSERT INTO `menu` VALUES ('21', '查看试卷', '4', '3', 'showTestPaperResultDetail', 'exam/showTestPaperResultDetail', '1', 'F', '1', '0', 'exam:result:detail', '#', '', '2021-06-22 09:38:27', '', '2021-06-22 09:38:27', '');
INSERT INTO `menu` VALUES ('22', '阅卷', '4', '4', 'markExam', 'exam/markExam', '1', 'F', '1', '0', 'exam:result:doMark', '#', '', '2021-06-22 09:39:30', '', '2021-06-22 09:39:30', '');
INSERT INTO `menu` VALUES ('23', '开始考试', '0', '4', '/startExam', 'exam/startExam', '1', 'F', '1', '0', 'exam:testPaper:page', '#', '', '2021-06-22 09:40:47', '', '2021-06-22 09:40:47', '');
INSERT INTO `menu` VALUES ('24', '修改菜单', '2', '8', 'updateMenu', 'system/updateMenu', '1', 'F', '1', '0', 'system:menu:update', '#', '', '2021-06-22 11:01:01', '', '2021-06-22 11:01:01', '');
INSERT INTO `menu` VALUES ('25', '修改角色', '2', '9', 'updateRole', 'system/updateRole', '1', 'F', '1', '0', 'system:role:update', '#', '', '2021-06-22 13:51:18', '', '2021-06-22 13:51:18', '');
INSERT INTO `menu` VALUES ('26', '删除菜单', '2', '10', 'deleteMenu', 'system/deleteMenu', '1', 'F', '1', '0', 'system:menu:delete', '#', '', '2021-06-22 13:53:03', '', '2021-06-22 13:53:03', '');
INSERT INTO `menu` VALUES ('27', '删除角色', '2', '11', 'deleteRole', 'system/deleteRole', '1', 'F', '1', '0', 'system:role:delete', '#', '', '2021-06-22 13:53:48', '', '2021-06-22 13:53:48', '');
INSERT INTO `menu` VALUES ('29', '删除用户', '2', '12', 'deleteUser', 'system/deleteUser', '1', 'F', '1', '0', 'system:user:delete', '#', '', '2021-07-08 15:53:04', '', '2021-07-08 15:53:04', '');
INSERT INTO `menu` VALUES ('30', '试卷列表', '3', '1', 'listTestPaper', 'testPaper/listTestPaper', '1', 'C', '0', '0', 'exam:testPaper:page', 'el-icon-tickets', '', '2021-07-13 09:50:24', '', '2021-07-13 09:50:24', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('4', '管理员', 'admin', '1', '1', '0', '0', '', '2021-06-18 15:17:59', '', '2021-06-22 15:40:23', '');
INSERT INTO `role` VALUES ('5', '系统管理员', 'system_admin', '2', '1', '0', '0', '', '2021-06-22 13:54:18', '', '2021-06-22 13:54:18', '');
INSERT INTO `role` VALUES ('6', '学生', 'student', '3', '1', '0', '0', '', '2021-06-22 16:38:20', '4212', '2021-06-22 16:38:20', '');
INSERT INTO `role` VALUES ('7', 'test', 'test', '4', '1', '0', '0', '', '2021-07-08 15:19:03', '', '2021-07-08 15:19:03', '');
INSERT INTO `role` VALUES ('8', '老师', 'teacher', '5', '1', '0', '0', '4212', '2021-07-13 13:50:16', '4215', '2021-07-13 13:50:16', '');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('4', '2');
INSERT INTO `role_menu` VALUES ('4', '5');
INSERT INTO `role_menu` VALUES ('4', '6');
INSERT INTO `role_menu` VALUES ('4', '7');
INSERT INTO `role_menu` VALUES ('4', '10');
INSERT INTO `role_menu` VALUES ('4', '11');
INSERT INTO `role_menu` VALUES ('4', '12');
INSERT INTO `role_menu` VALUES ('4', '13');
INSERT INTO `role_menu` VALUES ('4', '24');
INSERT INTO `role_menu` VALUES ('4', '25');
INSERT INTO `role_menu` VALUES ('4', '26');
INSERT INTO `role_menu` VALUES ('4', '27');
INSERT INTO `role_menu` VALUES ('4', '29');
INSERT INTO `role_menu` VALUES ('5', '2');
INSERT INTO `role_menu` VALUES ('5', '3');
INSERT INTO `role_menu` VALUES ('5', '4');
INSERT INTO `role_menu` VALUES ('5', '5');
INSERT INTO `role_menu` VALUES ('5', '6');
INSERT INTO `role_menu` VALUES ('5', '7');
INSERT INTO `role_menu` VALUES ('5', '10');
INSERT INTO `role_menu` VALUES ('5', '11');
INSERT INTO `role_menu` VALUES ('5', '12');
INSERT INTO `role_menu` VALUES ('5', '13');
INSERT INTO `role_menu` VALUES ('5', '15');
INSERT INTO `role_menu` VALUES ('5', '16');
INSERT INTO `role_menu` VALUES ('5', '17');
INSERT INTO `role_menu` VALUES ('5', '18');
INSERT INTO `role_menu` VALUES ('5', '19');
INSERT INTO `role_menu` VALUES ('5', '20');
INSERT INTO `role_menu` VALUES ('5', '21');
INSERT INTO `role_menu` VALUES ('5', '22');
INSERT INTO `role_menu` VALUES ('5', '23');
INSERT INTO `role_menu` VALUES ('5', '24');
INSERT INTO `role_menu` VALUES ('5', '25');
INSERT INTO `role_menu` VALUES ('5', '26');
INSERT INTO `role_menu` VALUES ('5', '27');
INSERT INTO `role_menu` VALUES ('5', '29');
INSERT INTO `role_menu` VALUES ('5', '30');
INSERT INTO `role_menu` VALUES ('6', '4');
INSERT INTO `role_menu` VALUES ('6', '16');
INSERT INTO `role_menu` VALUES ('6', '19');
INSERT INTO `role_menu` VALUES ('6', '21');
INSERT INTO `role_menu` VALUES ('6', '23');
INSERT INTO `role_menu` VALUES ('7', '2');
INSERT INTO `role_menu` VALUES ('7', '5');
INSERT INTO `role_menu` VALUES ('7', '6');
INSERT INTO `role_menu` VALUES ('7', '7');
INSERT INTO `role_menu` VALUES ('7', '10');
INSERT INTO `role_menu` VALUES ('7', '12');
INSERT INTO `role_menu` VALUES ('7', '13');
INSERT INTO `role_menu` VALUES ('7', '24');
INSERT INTO `role_menu` VALUES ('7', '25');
INSERT INTO `role_menu` VALUES ('7', '26');
INSERT INTO `role_menu` VALUES ('7', '27');
INSERT INTO `role_menu` VALUES ('8', '3');
INSERT INTO `role_menu` VALUES ('8', '4');
INSERT INTO `role_menu` VALUES ('8', '15');
INSERT INTO `role_menu` VALUES ('8', '16');
INSERT INTO `role_menu` VALUES ('8', '17');
INSERT INTO `role_menu` VALUES ('8', '18');
INSERT INTO `role_menu` VALUES ('8', '20');
INSERT INTO `role_menu` VALUES ('8', '21');
INSERT INTO `role_menu` VALUES ('8', '22');
INSERT INTO `role_menu` VALUES ('8', '30');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机',
  `salt` varchar(32) NOT NULL DEFAULT '' COMMENT '密码盐',
  `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '昵称',
  `idcard` varchar(255) NOT NULL DEFAULT '' COMMENT '身份证号',
  `gender` int NOT NULL DEFAULT '0' COMMENT '性别 0 未知 1 男 2 女',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
  `create_time` int unsigned NOT NULL DEFAULT '0' COMMENT '注册时间',
  `update_time` int unsigned NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4218 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4189', 'admin', '123456', '', '', '', '', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', '0', '1618993373');
INSERT INTO `user` VALUES ('4192', 'ning#4192', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618557714', '1618557714');
INSERT INTO `user` VALUES ('4193', 'ning#4193', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558032', '1618558032');
INSERT INTO `user` VALUES ('4194', 'ning#4194', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558050', '1619510071');
INSERT INTO `user` VALUES ('4195', 'ning#4195', '123456', '123456@qq.com', '13101310131', '123456', 'ningzuoxin123456', '9999999999999', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558057', '1619510044');
INSERT INTO `user` VALUES ('4196', 'ning#4196', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618557714', '1618557714');
INSERT INTO `user` VALUES ('4197', 'ning#4197', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558032', '1618558032');
INSERT INTO `user` VALUES ('4198', 'ning#4198', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', '1618558050', '1618993387');
INSERT INTO `user` VALUES ('4199', 'ning#4199', '123456', '123456@qq.com', '13101310131', '123456', 'ning111', '', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558057', '1619510051');
INSERT INTO `user` VALUES ('4200', 'ning#4200', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618557714', '1618557714');
INSERT INTO `user` VALUES ('4201', 'ning#4201', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558032', '1618558032');
INSERT INTO `user` VALUES ('4202', 'ning#4202', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558050', '1618558050');
INSERT INTO `user` VALUES ('4203', 'ning#4203', '123456', '123456@qq.com', '13101310131', '123456', 'ning111', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558057', '1618559970');
INSERT INTO `user` VALUES ('4204', 'ning#4204', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618557714', '1618557714');
INSERT INTO `user` VALUES ('4205', 'ning#4205', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558032', '1619510081');
INSERT INTO `user` VALUES ('4206', 'ning#4206', '123456', '123456@qq.com', '13101310131', '123456', 'ning1', '', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558050', '1618558050');
INSERT INTO `user` VALUES ('4207', 'ning#4207', '123456', '123456@qq.com', '13101310131', '123456', 'ning111', '999999999999999999', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618558057', '1619510014');
INSERT INTO `user` VALUES ('4209', 'ning001', '123456', '123456@qq.com', '13301330133', '123456', '张三丰', '1231231321', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618901020', '1618901020');
INSERT INTO `user` VALUES ('4210', 'ning#0001', '123', '123@qq.com', '13333333333', '123456', '111', '111', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618902787', '1618902787');
INSERT INTO `user` VALUES ('4211', 'zuoxinning', '123456', '123456@163.com', '13401230123', '123456', 'zuoxinning', '19901105', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618902928', '1625730624');
INSERT INTO `user` VALUES ('4212', 'yanrong', '123456', '12345678@qq.com', '13301330133', '123456', '蓉蓉', '987654321', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1618991394', '1624350458');
INSERT INTO `user` VALUES ('4213', 'ning', '123456', '12345678@qq.com', '13301330133', '123456', 'ning', '1234567890', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1624349221', '1624351110');
INSERT INTO `user` VALUES ('4214', 'system', 'system', '123456789@qq.com', '13001300130', '123456', '系统管理员', '', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1626155494', '1626155494');
INSERT INTO `user` VALUES ('4215', 'admin', 'admin', '123456789@qq.com', '13101310131', '123456', '管理员', '', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1626155536', '1626155536');
INSERT INTO `user` VALUES ('4216', 'student1', '123456', '123456@qq.com', '13201320132', '123456', '学生1', '', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1626155577', '1626155577');
INSERT INTO `user` VALUES ('4217', 'teacher1', '123456', '123456@qq.com', '13301330133', '123456', '老师1', '', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', '1626155622', '1626155622');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4211', '6');
INSERT INTO `user_role` VALUES ('4212', '5');
INSERT INTO `user_role` VALUES ('4213', '6');
INSERT INTO `user_role` VALUES ('4214', '5');
INSERT INTO `user_role` VALUES ('4215', '4');
INSERT INTO `user_role` VALUES ('4216', '6');
INSERT INTO `user_role` VALUES ('4217', '8');
