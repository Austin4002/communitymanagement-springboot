/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 13/05/2021 20:03:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', 'RB软工卓越171');
INSERT INTO `clazz` VALUES ('1382694657418448897', 'RB软工互联网174');
INSERT INTO `clazz` VALUES ('9469d61ac57e4a6b95d575579864c3fc', 'RB软工数171');

-- ----------------------------
-- Table structure for club
-- ----------------------------
DROP TABLE IF EXISTS `club`;
CREATE TABLE `club`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `proprieter_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社长id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团简介',
  `star_level` double(20, 1) NULL DEFAULT NULL COMMENT '星级',
  `notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公告',
  `period` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '免申请时长',
  `finance_start_time` datetime NULL DEFAULT NULL COMMENT '免申请金额开始时间',
  `money` double(20, 2) NULL DEFAULT NULL COMMENT '免申请金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of club
-- ----------------------------
INSERT INTO `club` VALUES ('aeb04ad3b59a4c07aedf77a9180b2e10', '201719244132', '太极社', '这是太极社', 3.3, NULL, '一个月', '2021-05-13 09:23:59', 1212.00);
INSERT INTO `club` VALUES ('c3229722da8c40aa923b02352b915722', '201719164427', '民舞社', '123', 2.5, '暂无', '一周', '2021-05-15 09:34:37', 1212.00);
INSERT INTO `club` VALUES ('f5c1197012ed4248bcd1781f3c8a426b', '201719164413', '街舞社', '这是一个五星级社团', 3.3, '暂无', '一周', '2021-05-15 00:00:00', 100.00);

-- ----------------------------
-- Table structure for club_event
-- ----------------------------
DROP TABLE IF EXISTS `club_event`;
CREATE TABLE `club_event`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `club_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `event_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of club_event
-- ----------------------------
INSERT INTO `club_event` VALUES ('11696628c60d4dbdae3880fd36be66b0', 'c3229722da8c40aa923b02352b915722', 'aea98d6c059a4123bb9f29b6e5f2c96a');
INSERT INTO `club_event` VALUES ('424ec35af404401da704984057b13abd', 'aeb04ad3b59a4c07aedf77a9180b2e10', 'aea98d6c059a4123bb9f29b6e5f2c96a');
INSERT INTO `club_event` VALUES ('53431ba7e8974c82b03f422d95a2924f', 'f5c1197012ed4248bcd1781f3c8a426b', '25a91bcf0f14422f9bbdd79af298d269');
INSERT INTO `club_event` VALUES ('5dc8980538f24e83974490e518444a00', 'c3229722da8c40aa923b02352b915722', '25a91bcf0f14422f9bbdd79af298d269');
INSERT INTO `club_event` VALUES ('5f4025346ce149e59549c11b26376fda', 'aeb04ad3b59a4c07aedf77a9180b2e10', 'ba2d945cc33f45f8a9e0f40dc695d501');
INSERT INTO `club_event` VALUES ('80148ca7587d403392b2a41a9cba5476', 'aeb04ad3b59a4c07aedf77a9180b2e10', '25a91bcf0f14422f9bbdd79af298d269');
INSERT INTO `club_event` VALUES ('8f95849a10034915b24a487a8b9ab480', 'f5c1197012ed4248bcd1781f3c8a426b', 'ba2d945cc33f45f8a9e0f40dc695d501');
INSERT INTO `club_event` VALUES ('8ffcad8f3bfc414693400104458427ac', 'aeb04ad3b59a4c07aedf77a9180b2e10', '86de5bb1198b4bcb980366839bc89d24');
INSERT INTO `club_event` VALUES ('abea23030b7b4ed3ac5df483def90442', 'f5c1197012ed4248bcd1781f3c8a426b', '86de5bb1198b4bcb980366839bc89d24');
INSERT INTO `club_event` VALUES ('e7b88dbbd23f4e5494b0bb1879f4723d', 'c3229722da8c40aa923b02352b915722', 'ba2d945cc33f45f8a9e0f40dc695d501');
INSERT INTO `club_event` VALUES ('ea05bac60b4d4e23808c37fd7cb92a5c', 'f5c1197012ed4248bcd1781f3c8a426b', 'aea98d6c059a4123bb9f29b6e5f2c96a');

-- ----------------------------
-- Table structure for club_user
-- ----------------------------
DROP TABLE IF EXISTS `club_user`;
CREATE TABLE `club_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团成员表id',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `club_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of club_user
-- ----------------------------
INSERT INTO `club_user` VALUES ('1', '201719164413', 'f5c1197012ed4248bcd1781f3c8a426b');
INSERT INTO `club_user` VALUES ('2', '201719164427', 'f5c1197012ed4248bcd1781f3c8a426b');
INSERT INTO `club_user` VALUES ('7ba0143c85bc494e9fc9fc86fe6bcd58', '201719244129', 'f5c1197012ed4248bcd1781f3c8a426b');

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动名称',
  `place` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动地点',
  `type1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动一级类型',
  `type2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动二级类型',
  `start_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动结束时间',
  `status` int NULL DEFAULT NULL COMMENT '管理员审核状态（0拒绝 1通过 2待审核）',
  `sign_in_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动签到表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES ('25a91bcf0f14422f9bbdd79af298d269', '第一次社联例会', '304', '院级', '展示', '2021年04月23日11点00分', '2021年04月23日23点00分', 0, NULL);
INSERT INTO `event` VALUES ('86de5bb1198b4bcb980366839bc89d24', '测试', '304', '院级', '展示', '2021年04月23日20点00分', '2021年04月23日23点00分', 1, NULL);
INSERT INTO `event` VALUES ('aea98d6c059a4123bb9f29b6e5f2c96a', '测试活动流程', '304', '院级', '展示', '2021年05月10日00点00分', '2021年06月15日00点00分', 1, NULL);
INSERT INTO `event` VALUES ('ba2d945cc33f45f8a9e0f40dc695d501', '测试', '1', '国家级', '招新', '2021年05月10日00点00分', '2021年05月11日00点00分', 1, NULL);

-- ----------------------------
-- Table structure for finance
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `club_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团id',
  `purpose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用途',
  `money` double(20, 0) NULL DEFAULT NULL COMMENT '金额',
  `is_app` int NULL DEFAULT NULL COMMENT '是否申请',
  `status` int NULL DEFAULT NULL COMMENT '申请状态（0拒绝 1通过 2待审核）',
  `update_time` datetime NULL DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of finance
-- ----------------------------
INSERT INTO `finance` VALUES ('3a7ec5727cea437abb6c7609c22ba793', 'f5c1197012ed4248bcd1781f3c8a426b', '你猜', 1, 1, 1, '2021-05-13 10:38:01');
INSERT INTO `finance` VALUES ('c1e0ca58128341a3b275580d49ac7996', 'f5c1197012ed4248bcd1781f3c8a426b', '你再猜', 2, 0, 1, '2021-05-13 10:38:49');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端展示需要颜色类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员', 'danger');
INSERT INTO `role` VALUES ('2', 'proprieter', '社长', 'warning');
INSERT INTO `role` VALUES ('3', 'user', '学生', 'success');

-- ----------------------------
-- Table structure for sign_in
-- ----------------------------
DROP TABLE IF EXISTS `sign_in`;
CREATE TABLE `sign_in`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '签到表id',
  `user_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学号',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `event_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sign_in
-- ----------------------------
INSERT INTO `sign_in` VALUES ('45991812a55b4fc7bc871126204ca269', '201719244129', '牛庚新', '86de5bb1198b4bcb980366839bc89d24');
INSERT INTO `sign_in` VALUES ('9fc1c4b3185d4714b9080ef2bc6d4398', '201719244129', '牛庚新', 'aea98d6c059a4123bb9f29b6e5f2c96a');
INSERT INTO `sign_in` VALUES ('fca4ab2f69bd456c8053c9cb462b919d', '201719244129', '牛庚新', 'ba2d945cc33f45f8a9e0f40dc695d501');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('00e8d7d5b81d47f89fbc98a4cde3526b', 'http://127.0.0.1:8081/logList', 'GET', '{\"current\":[\"1\"],\"size\":[\"5\"]}', '127.0.0.1', '2021-05-13 20:01:08');
INSERT INTO `sys_log` VALUES ('2a8a0489f3a64fd5a5557a33c8c18780', 'http://127.0.0.1:8081/logList', 'GET', '{\"current\":[\"1\"],\"size\":[\"10\"]}', '127.0.0.1', '2021-05-13 20:01:36');
INSERT INTO `sys_log` VALUES ('52f6160c422c41cdaae0e23e5c8da0a7', 'http://127.0.0.1:8081/login', 'POST', '{}', '127.0.0.1', '2021-05-13 20:00:12');
INSERT INTO `sys_log` VALUES ('5d6c950cff3248628d75993ac3ca7ac2', 'http://127.0.0.1:8081/menu', 'GET', '{\"role\":[\"admin\"]}', '127.0.0.1', '2021-05-13 20:00:12');
INSERT INTO `sys_log` VALUES ('6ea224259e9b4ac1879b2616f53bffd2', 'http://127.0.0.1:8081/roleList', 'GET', '{\"current\":[\"1\"],\"size\":[\"5\"]}', '127.0.0.1', '2021-05-13 20:00:14');
INSERT INTO `sys_log` VALUES ('71c45b84bb97412ba731d087d8b31e25', 'http://127.0.0.1:8081/logList', 'GET', '{\"current\":[\"3\"],\"size\":[\"5\"]}', '127.0.0.1', '2021-05-13 20:01:15');
INSERT INTO `sys_log` VALUES ('972b2440a19a44e7807de6155a68db12', 'http://127.0.0.1:8081/userList', 'GET', '{\"current\":[\"1\"],\"size\":[\"5\"]}', '127.0.0.1', '2021-05-13 19:58:29');
INSERT INTO `sys_log` VALUES ('9baa1c40409440f996f16f1b249c1886', 'http://127.0.0.1:8081/menu', 'GET', '{\"role\":[\"admin\"]}', '127.0.0.1', '2021-05-13 20:00:48');
INSERT INTO `sys_log` VALUES ('9df5909b037e4ce5b26a7f15843f77a5', 'http://127.0.0.1:8081/userList', 'GET', '{\"current\":[\"1\"],\"size\":[\"5\"]}', '127.0.0.1', '2021-05-13 19:48:48');
INSERT INTO `sys_log` VALUES ('a494385f9e144cc5b32866b6d20009dc', 'http://127.0.0.1:8081/logList', 'GET', '{\"current\":[\"1\"],\"size\":[\"10\"]}', '127.0.0.1', '2021-05-13 20:01:42');
INSERT INTO `sys_log` VALUES ('abab2e5f4ce44830a0fa6983f9de6227', 'http://127.0.0.1:8081/logList', 'GET', '{\"current\":[\"2\"],\"size\":[\"10\"]}', '127.0.0.1', '2021-05-13 20:01:38');
INSERT INTO `sys_log` VALUES ('c2a46565d0494fc38d4a7835255c9a07', 'http://127.0.0.1:8081/menu', 'GET', '{\"role\":[\"admin\"]}', '127.0.0.1', '2021-05-13 19:48:48');
INSERT INTO `sys_log` VALUES ('ca56857dc2364c4aa701c97ec822e93b', 'http://127.0.0.1:8081/logList', 'GET', '{\"current\":[\"1\"],\"size\":[\"5\"]}', '127.0.0.1', '2021-05-13 20:00:48');
INSERT INTO `sys_log` VALUES ('f100c9f3830d4ec7bdd887cde601e12e', 'http://127.0.0.1:8081/logList', 'GET', '{\"current\":[\"2\"],\"size\":[\"5\"]}', '127.0.0.1', '2021-05-13 20:01:14');
INSERT INTO `sys_log` VALUES ('fb541623bee143e39708c8a19560ae2c', 'http://127.0.0.1:8081/menu', 'GET', '{\"role\":[\"admin\"]}', '127.0.0.1', '2021-05-13 19:58:29');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `class_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('201719164413', '123', '胡天阳', '女', '13276909904', '1');
INSERT INTO `user` VALUES ('201719164427', '123', '郜洋洋', '女', '15565779560', '1');
INSERT INTO `user` VALUES ('201719244129', '123', '牛庚新', '男', '123123132', '1');
INSERT INTO `user` VALUES ('201719244132', '123', '葛云翔', '男', '123123132', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('0662d18a23ec4bda8ab3b8387f8fb95e', '201719164427', '2');
INSERT INTO `user_role` VALUES ('19b87a5e6e334c01a482316f66044566', '201719164427', '1');
INSERT INTO `user_role` VALUES ('3', '201719164413', '2');
INSERT INTO `user_role` VALUES ('4', '201719244129', '3');
INSERT INTO `user_role` VALUES ('5', '201719244132', '2');

SET FOREIGN_KEY_CHECKS = 1;
