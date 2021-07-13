/*
Navicat MySQL Data Transfer

Source Server         : LOCAL
Source Server Version : 80020
Source Host           : 127.0.0.1:3306
Source Database       : exam-ning-springcloud-exam

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2021-07-13 14:24:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_question`;
CREATE TABLE `exam_question` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(64) NOT NULL DEFAULT '' COMMENT '题目类型',
  `stem` text COMMENT '题干',
  `score` float(10,1) unsigned NOT NULL DEFAULT '0.0' COMMENT '分数',
  `answer` text COMMENT '参考答案',
  `analysis` text COMMENT '解析',
  `metas` text COMMENT '题目元信息',
  `category_id` int NOT NULL DEFAULT '0' COMMENT '分类ID',
  `used_num` int DEFAULT '0' COMMENT '被试卷使用数',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
  `is_show` int NOT NULL DEFAULT '0' COMMENT '是否显示 0 显示 1 不显示',
  `create_user_id` int NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `create_time` int NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_user_id` int NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `update_time` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  `copy_id` int NOT NULL DEFAULT '0' COMMENT '复制题目ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='考题表';

-- ----------------------------
-- Records of exam_question
-- ----------------------------
INSERT INTO `exam_question` VALUES ('1', 'choice', '1+2=？', '0.0', '1', null, '{\"choices\":[\"1\",\"2\",\"3\",\"4\"]}', '0', '1', '0', '0', '0', '1622611148', '0', '1622611148', '0');
INSERT INTO `exam_question` VALUES ('2', 'choice', '中国有多少个民族？', '0.0', '2', null, '{\"choices\":[\"54\",\"55\",\"56\",\"57\"]}', '0', '1', '0', '0', '0', '1622611180', '0', '1622611180', '0');
INSERT INTO `exam_question` VALUES ('3', 'choice', '中国的首都是哪个城市？', '0.0', '0', null, '{\"choices\":[\"北京\",\"上海\",\"广州\",\"深圳\"]}', '0', '1', '0', '0', '0', '1622611209', '0', '1622611209', '0');
INSERT INTO `exam_question` VALUES ('4', 'choice_multi', '以下属于中国的城市有？', '0.0', '[0,1]', null, '{\"choices\":[\"武汉\",\"长沙\",\"华盛顿\",\"纽约\"]}', '0', '2', '0', '0', '0', '1622611258', '0', '1622611258', '0');
INSERT INTO `exam_question` VALUES ('5', 'choice_multi', '以下在中国境内的河流有？', '0.0', '[0,3]', null, '{\"choices\":[\"长江\",\"尼罗河\",\"亚马逊河\",\"黄河\"]}', '0', '1', '0', '0', '0', '1622611331', '0', '1622611331', '0');
INSERT INTO `exam_question` VALUES ('6', 'true_false', '中国是社会主义国家？', '0.0', '0', null, null, '0', '1', '0', '0', '0', '1622611357', '0', '1622611357', '0');
INSERT INTO `exam_question` VALUES ('7', 'true_false', '中国有55个民族', '0.0', '1', null, null, '0', '1', '0', '0', '0', '1622611375', '0', '1622611375', '0');
INSERT INTO `exam_question` VALUES ('8', 'fill_blank', '中国一共有{{}}个民族，其中{{}}个少数民族。', '0.0', '[\"56\",\"55\"]', null, '[]', '0', '3', '0', '0', '0', '1622611428', '0', '1622611428', '0');
INSERT INTO `exam_question` VALUES ('9', 'question', '请简述遵义会议的历史意义？', '0.0', '1、遵义会议改变了中国共产党的历史进程。\n2、遵义会议改变了中国共产党的历史进程。\n3、遵义会议改变了中国共产党的历史进程。\n4、遵义会议改变了中国共产党的历史进程。\n', null, null, '0', '2', '0', '0', '0', '1623033413', '0', '1623033413', '0');
INSERT INTO `exam_question` VALUES ('10', 'choice', '中华人民共和国成立于哪一年？', '0.0', '1', null, '{\"choices\":[\"1948\",\"1949\",\"1950\",\"1951\"]}', '0', '3', '0', '0', '0', '1623043717', '0', '1623043717', '0');
INSERT INTO `exam_question` VALUES ('11', 'choice', '2021年中国有多少人口？', '0.0', '3', null, '{\"choices\":[\"11亿\",\"12亿\",\"13亿\",\"14亿\"]}', '0', '3', '0', '0', '0', '1623043808', '0', '1623043808', '0');
INSERT INTO `exam_question` VALUES ('12', 'choice_multi', '以下属于中国的直辖市的有？', '0.0', '[0,3]', null, '{\"choices\":[\"北京\",\"广州\",\"深圳\",\"天津\"]}', '0', '3', '0', '0', '0', '1623043932', '0', '1623043932', '0');
INSERT INTO `exam_question` VALUES ('13', 'choice_multi', '以下属于中国的舰队的有？', '0.0', '[0,2,3]', null, '{\"choices\":[\"北海\",\"西海\",\"南海\",\"东海\"]}', '0', '3', '0', '0', '0', '1623043976', '0', '1623043976', '0');
INSERT INTO `exam_question` VALUES ('14', 'true_false', '珠穆朗玛峰是世界上最高的山峰？', '0.0', '0', null, null, '0', '3', '0', '0', '0', '1623044112', '0', '1623044112', '0');
INSERT INTO `exam_question` VALUES ('15', 'true_false', '钢铁侠Ⅰ上映于2008年', '0.0', '0', null, null, '0', '3', '0', '0', '0', '1623044159', '0', '1623044159', '0');
INSERT INTO `exam_question` VALUES ('16', 'fill_blank', '复仇者联盟共有{{}}部，第三部的名称是{{}}。', '0.0', '[\"4\",\"无限战争\"]', null, '[]', '0', '3', '0', '0', '0', '1623044234', '0', '1623044234', '0');
INSERT INTO `exam_question` VALUES ('17', 'choice', '位于中国内陆的直辖市是哪个城市？', '0.0', '0', null, '{\"choices\":[\"重庆\",\"天津\",\"上海\",\"北京\"]}', '0', '1', '0', '0', '0', '1623289270', '0', '1623289270', '0');
INSERT INTO `exam_question` VALUES ('18', 'choice', '以下不属于985院校的是？', '0.0', '2', null, '{\"choices\":[\"湖南大学\",\"中南大学\",\"湖南师范大学\",\"国防科技大学\"]}', '0', '1', '0', '0', '0', '1623289390', '0', '1623289390', '0');
INSERT INTO `exam_question` VALUES ('19', 'choice', '2020年在中国爆发了新冠疫情，最终决定封闭了哪座城市？', '0.0', '1', null, '{\"choices\":[\"长沙\",\"武汉\",\"上海\",\"广州\"]}', '0', '1', '0', '0', '0', '1623289480', '0', '1623289480', '0');
INSERT INTO `exam_question` VALUES ('20', 'choice_multi', '三大球体育项目有？', '0.0', '[0,1,2]', null, '{\"choices\":[\"篮球\",\"足球\",\"排球\",\"橄榄球\"]}', '0', '1', '0', '0', '0', '1623289529', '0', '1623289529', '0');
INSERT INTO `exam_question` VALUES ('21', 'choice_multi', '三大前端框架有？', '0.0', '[0,1,2]', null, '{\"choices\":[\"vue\",\"react\",\"angular\",\"jQuery\"]}', '0', '1', '0', '0', '0', '1623289626', '0', '1623289626', '0');
INSERT INTO `exam_question` VALUES ('22', 'question', '请简述2020年武汉封城的意义？', '0.0', '意义很重大\n意义很重大\n意义很重大\n意义很重大\n', null, null, '0', '1', '0', '0', '0', '1623289673', '0', '1623289673', '0');

-- ----------------------------
-- Table structure for exam_test_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_test_paper`;
CREATE TABLE `exam_test_paper` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` enum('training','formal','mock') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'training' COMMENT '试卷类型（training、mock）',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '试卷名',
  `limited_time` int NOT NULL DEFAULT '0' COMMENT '限时（秒）',
  `times` int NOT NULL DEFAULT '0' COMMENT '允许考试次数 0 不限制次数',
  `total_score` float(10,1) unsigned NOT NULL DEFAULT '0.0' COMMENT '总分',
  `passed_score` float(10,1) unsigned NOT NULL DEFAULT '0.0' COMMENT '及格分',
  `item_count` int NOT NULL DEFAULT '0' COMMENT '题目数量',
  `copy_id` int NOT NULL DEFAULT '0' COMMENT '复制试卷ID',
  `is_used` int NOT NULL DEFAULT '0' COMMENT '是否使用 0 未使用 1 使用',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
  `create_user_id` int NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `create_time` int NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_user_id` int NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `update_time` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='试卷表';

-- ----------------------------
-- Records of exam_test_paper
-- ----------------------------
INSERT INTO `exam_test_paper` VALUES ('107', 'formal', '2020期中考试A卷', '3000', '0', '100.0', '60.0', '8', '0', '1', '0', '0', '1622611487', '0', '1622686713');
INSERT INTO `exam_test_paper` VALUES ('108', 'mock', '2021模拟测验B卷', '3600', '2', '100.0', '60.0', '8', '0', '1', '0', '0', '1622684843', '0', '1622686614');
INSERT INTO `exam_test_paper` VALUES ('109', 'training', '2021日常练习测验卷I卷', '3600', '0', '100.0', '60.0', '8', '0', '1', '1', '0', '1622788700', '0', '1622789631');
INSERT INTO `exam_test_paper` VALUES ('110', 'training', '2021日常练习测验卷2', '3600', '0', '100.0', '60.0', '8', '0', '0', '1', '0', '1622788888', '0', '1622789606');
INSERT INTO `exam_test_paper` VALUES ('111', 'mock', '2021基本常识测验Ⅰ卷', '3600', '2', '100.0', '60.0', '8', '0', '1', '0', '0', '1623044348', '0', '1623044354');
INSERT INTO `exam_test_paper` VALUES ('112', 'formal', '2021高考全国卷Ⅰ卷', '3600', '0', '100.0', '60.0', '8', '0', '1', '0', '0', '1623141056', '0', '1623141061');
INSERT INTO `exam_test_paper` VALUES ('113', 'training', '2021年日常测验卷Ⅰ卷', '3600', '0', '150.0', '90.0', '16', '0', '1', '0', '0', '1623289821', '0', '1623289844');

-- ----------------------------
-- Table structure for exam_test_paper_item
-- ----------------------------
DROP TABLE IF EXISTS `exam_test_paper_item`;
CREATE TABLE `exam_test_paper_item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `test_paper_id` int NOT NULL DEFAULT '0' COMMENT '试卷ID',
  `seq` int NOT NULL DEFAULT '0' COMMENT '题目顺序',
  `question_id` int NOT NULL DEFAULT '0' COMMENT '题目ID',
  `question_type` varchar(64) NOT NULL DEFAULT '' COMMENT '题目类别',
  `score` float(10,1) unsigned NOT NULL DEFAULT '0.0' COMMENT '得分',
  `miss_score` float(10,1) unsigned NOT NULL DEFAULT '0.0' COMMENT '漏选得分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1894 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='试卷项目表';

-- ----------------------------
-- Records of exam_test_paper_item
-- ----------------------------
INSERT INTO `exam_test_paper_item` VALUES ('1830', '107', '0', '8', 'fill_blank', '30.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1831', '107', '0', '7', 'true_false', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1832', '107', '0', '6', 'true_false', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1833', '107', '0', '5', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1834', '107', '0', '4', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1835', '107', '0', '3', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1836', '107', '0', '2', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1837', '107', '0', '1', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1838', '108', '0', '8', 'fill_blank', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1839', '108', '0', '7', 'true_false', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1840', '108', '0', '6', 'true_false', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1841', '108', '0', '5', 'choice_multi', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1842', '108', '0', '4', 'choice_multi', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1843', '108', '0', '3', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1844', '108', '0', '2', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1845', '108', '0', '1', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1846', '109', '0', '8', 'fill_blank', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1847', '109', '0', '7', 'true_false', '5.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1848', '109', '0', '6', 'true_false', '5.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1849', '109', '0', '5', 'choice_multi', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1850', '109', '0', '4', 'choice_multi', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1851', '109', '0', '3', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1852', '109', '0', '2', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1853', '109', '0', '1', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1854', '110', '0', '8', 'fill_blank', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1855', '110', '0', '7', 'true_false', '5.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1856', '110', '0', '6', 'true_false', '5.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1857', '110', '0', '5', 'choice_multi', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1858', '110', '0', '4', 'choice_multi', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1859', '110', '0', '3', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1860', '110', '0', '2', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1861', '110', '0', '1', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1862', '111', '0', '11', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1863', '111', '0', '10', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1864', '111', '0', '13', 'choice_multi', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1865', '111', '0', '12', 'choice_multi', '20.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1866', '111', '0', '15', 'true_false', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1867', '111', '0', '14', 'true_false', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1868', '111', '0', '16', 'fill_blank', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1869', '111', '0', '8', 'fill_blank', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1870', '112', '0', '11', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1871', '112', '0', '10', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1872', '112', '0', '13', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1873', '112', '0', '12', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1874', '112', '0', '15', 'true_false', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1875', '112', '0', '14', 'true_false', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1876', '112', '0', '16', 'fill_blank', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1877', '112', '0', '9', 'question', '30.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1878', '113', '0', '19', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1879', '113', '0', '18', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1880', '113', '0', '17', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1881', '113', '0', '11', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1882', '113', '0', '10', 'choice', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1883', '113', '0', '21', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1884', '113', '0', '20', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1885', '113', '0', '13', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1886', '113', '0', '12', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1887', '113', '0', '4', 'choice_multi', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1888', '113', '0', '15', 'true_false', '5.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1889', '113', '0', '14', 'true_false', '5.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1890', '113', '0', '16', 'fill_blank', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1891', '113', '0', '8', 'fill_blank', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1892', '113', '0', '22', 'question', '10.0', '0.0');
INSERT INTO `exam_test_paper_item` VALUES ('1893', '113', '0', '9', 'question', '10.0', '0.0');

-- ----------------------------
-- Table structure for exam_test_paper_item_result
-- ----------------------------
DROP TABLE IF EXISTS `exam_test_paper_item_result`;
CREATE TABLE `exam_test_paper_item_result` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `testpaper_result_id` int NOT NULL DEFAULT '0' COMMENT '试卷结果ID',
  `testpaper_id` int NOT NULL DEFAULT '0' COMMENT '试卷ID',
  `testpaper_item_id` int NOT NULL DEFAULT '0' COMMENT '试卷项目ID',
  `question_id` int NOT NULL DEFAULT '0' COMMENT '题目ID',
  `user_id` int NOT NULL DEFAULT '0' COMMENT '答题人ID',
  `answer` text COMMENT '答题结果',
  `status` enum('noAnswer','wrong','partRight','right','none') NOT NULL DEFAULT 'none' COMMENT '结果状态（none、right、partRight、wrong、noAnswer）',
  `score` float(10,1) NOT NULL DEFAULT '0.0' COMMENT '得分',
  `teacher_say` text COMMENT '老师评价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=845 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='试卷项目结果表';

-- ----------------------------
-- Records of exam_test_paper_item_result
-- ----------------------------
INSERT INTO `exam_test_paper_item_result` VALUES ('797', '53', '113', '1882', '10', '4213', '1', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('798', '53', '113', '1881', '11', '4213', '3', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('799', '53', '113', '1880', '17', '4213', '0', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('800', '53', '113', '1879', '18', '4213', '2', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('801', '53', '113', '1878', '19', '4213', '1', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('802', '53', '113', '1887', '4', '4213', '[0,1]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('803', '53', '113', '1886', '12', '4213', '[0,2,3]', 'wrong', '0.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('804', '53', '113', '1885', '13', '4213', '[0,2,3]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('805', '53', '113', '1884', '20', '4213', '[0,1,2]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('806', '53', '113', '1883', '21', '4213', '[0,1,2]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('807', '53', '113', '1889', '14', '4213', '0', 'right', '5.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('808', '53', '113', '1888', '15', '4213', '0', 'right', '5.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('809', '53', '113', '1891', '8', '4213', '[\"56\",\"55\"]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('810', '53', '113', '1890', '16', '4213', '[\"4\",\"无限战争\"]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('811', '53', '113', '1893', '9', '4213', '11111111111111111111111111', 'partRight', '8.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('812', '53', '113', '1892', '22', '4213', '22222222222222222222222222', 'partRight', '8.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('813', '54', '112', '1871', '10', '4213', '1', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('814', '54', '112', '1870', '11', '4213', '3', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('815', '54', '112', '1873', '12', '4213', '[0,2,3]', 'wrong', '0.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('816', '54', '112', '1872', '13', '4213', '[0,2,3]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('817', '54', '112', '1875', '14', '4213', '0', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('818', '54', '112', '1874', '15', '4213', '0', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('819', '54', '112', '1876', '16', '4213', '[\"4\",\"无限战争\"]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('820', '54', '112', '1877', '9', '4213', '1111111111111111111', 'partRight', '28.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('821', '55', '111', '1863', '10', '4213', '1', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('822', '55', '111', '1862', '11', '4213', '3', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('823', '55', '111', '1865', '12', '4213', '[0,2,3]', 'wrong', '0.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('824', '55', '111', '1864', '13', '4213', '[0,2,3]', 'right', '20.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('825', '55', '111', '1867', '14', '4213', '0', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('826', '55', '111', '1866', '15', '4213', '0', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('827', '55', '111', '1869', '8', '4213', '[\"56\",\"55\"]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('828', '55', '111', '1868', '16', '4213', '[\"4\",\"无限战争\"]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('829', '56', '113', '1882', '10', '4216', '1', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('830', '56', '113', '1881', '11', '4216', '3', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('831', '56', '113', '1880', '17', '4216', '0', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('832', '56', '113', '1879', '18', '4216', '1', 'wrong', '0.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('833', '56', '113', '1878', '19', '4216', '1', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('834', '56', '113', '1887', '4', '4216', '[0,1]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('835', '56', '113', '1886', '12', '4216', '[0,2,3]', 'wrong', '0.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('836', '56', '113', '1885', '13', '4216', '[0,2,3]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('837', '56', '113', '1884', '20', '4216', '[0,1,2]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('838', '56', '113', '1883', '21', '4216', '[0,1,2]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('839', '56', '113', '1889', '14', '4216', '0', 'right', '5.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('840', '56', '113', '1888', '15', '4216', '0', 'right', '5.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('841', '56', '113', '1891', '8', '4216', '[\"56\",\"55\"]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('842', '56', '113', '1890', '16', '4216', '[\"4\",\"无限战争\"]', 'right', '10.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('843', '56', '113', '1893', '9', '4216', '', 'partRight', '8.0', null);
INSERT INTO `exam_test_paper_item_result` VALUES ('844', '56', '113', '1892', '22', '4216', '111111222222222222222222222', 'partRight', '8.0', null);

-- ----------------------------
-- Table structure for exam_test_paper_result
-- ----------------------------
DROP TABLE IF EXISTS `exam_test_paper_result`;
CREATE TABLE `exam_test_paper_result` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `testpaper_id` int NOT NULL DEFAULT '0' COMMENT '试卷ID',
  `testpaper_name` varchar(255) NOT NULL DEFAULT '' COMMENT '试卷名',
  `user_id` int NOT NULL DEFAULT '0' COMMENT '答卷人ID',
  `score` float(10,1) unsigned NOT NULL DEFAULT '0.0' COMMENT '总分',
  `objective_score` float(10,1) unsigned NOT NULL DEFAULT '0.0' COMMENT '主观题得分',
  `subjective_score` float(10,1) unsigned NOT NULL DEFAULT '0.0' COMMENT '客观题得分',
  `teacher_say` text COMMENT '老师评价',
  `right_item_count` int NOT NULL DEFAULT '0' COMMENT '正确题目数',
  `used_time` int NOT NULL DEFAULT '0' COMMENT '答卷耗时（秒）',
  `status` enum('finished','reviewing','paused','doing') NOT NULL DEFAULT 'doing' COMMENT '试卷批阅状态（doing、paused、reviewing、finished）',
  `check_user_id` int NOT NULL DEFAULT '0' COMMENT '批卷老师ID',
  `checked_time` int NOT NULL DEFAULT '0' COMMENT '批卷时间',
  `update_time` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='试卷结果表';

-- ----------------------------
-- Records of exam_test_paper_result
-- ----------------------------
INSERT INTO `exam_test_paper_result` VALUES ('53', '113', '2021年日常测验卷Ⅰ卷', '4213', '136.0', '16.0', '120.0', '', '13', '78', 'finished', '4212', '1626146797', '1626146797');
INSERT INTO `exam_test_paper_result` VALUES ('54', '112', '2021高考全国卷Ⅰ卷', '4213', '88.0', '28.0', '60.0', '', '6', '39', 'finished', '4217', '1626156686', '1626156686');
INSERT INTO `exam_test_paper_result` VALUES ('55', '111', '2021基本常识测验Ⅰ卷', '4213', '80.0', '0.0', '80.0', '', '7', '40', 'finished', '0', '0', '1626153519');
INSERT INTO `exam_test_paper_result` VALUES ('56', '113', '2021年日常测验卷Ⅰ卷', '4216', '126.0', '16.0', '110.0', '', '12', '54', 'finished', '4217', '1626156657', '1626156657');
