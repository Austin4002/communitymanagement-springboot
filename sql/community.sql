/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 24/04/2021 17:58:18
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', 'RB软工卓越171');
INSERT INTO `clazz` VALUES ('1382694657418448897', 'RB软工互联网174');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club
-- ----------------------------
INSERT INTO `club` VALUES ('aeb04ad3b59a4c07aedf77a9180b2e10', '', '太极社', '这是太极社', 0.0, NULL);
INSERT INTO `club` VALUES ('c3229722da8c40aa923b02352b915722', '201719164427', '123', '123', 1.5, '暂无');
INSERT INTO `club` VALUES ('f5c1197012ed4248bcd1781f3c8a426b', '201719164413', '街舞社', '这是一个五星级社团', 5.0, '暂无');

-- ----------------------------
-- Table structure for club_event
-- ----------------------------
DROP TABLE IF EXISTS `club_event`;
CREATE TABLE `club_event`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `club_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `event_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club_event
-- ----------------------------
INSERT INTO `club_event` VALUES ('53431ba7e8974c82b03f422d95a2924f', 'f5c1197012ed4248bcd1781f3c8a426b', '25a91bcf0f14422f9bbdd79af298d269');
INSERT INTO `club_event` VALUES ('5dc8980538f24e83974490e518444a00', 'c3229722da8c40aa923b02352b915722', '25a91bcf0f14422f9bbdd79af298d269');
INSERT INTO `club_event` VALUES ('80148ca7587d403392b2a41a9cba5476', 'aeb04ad3b59a4c07aedf77a9180b2e10', '25a91bcf0f14422f9bbdd79af298d269');
INSERT INTO `club_event` VALUES ('8ffcad8f3bfc414693400104458427ac', 'aeb04ad3b59a4c07aedf77a9180b2e10', '86de5bb1198b4bcb980366839bc89d24');
INSERT INTO `club_event` VALUES ('abea23030b7b4ed3ac5df483def90442', 'f5c1197012ed4248bcd1781f3c8a426b', '86de5bb1198b4bcb980366839bc89d24');

-- ----------------------------
-- Table structure for club_user
-- ----------------------------
DROP TABLE IF EXISTS `club_user`;
CREATE TABLE `club_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团成员表id',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `club_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club_user
-- ----------------------------
INSERT INTO `club_user` VALUES ('1', '201719164413', 'f5c1197012ed4248bcd1781f3c8a426b');
INSERT INTO `club_user` VALUES ('2', '201719164427', 'f5c1197012ed4248bcd1781f3c8a426b');

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
  `status` int(0) NULL DEFAULT NULL COMMENT '管理员审核状态（0拒绝 1通过 2待审核）',
  `sign_in_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动签到表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES ('25a91bcf0f14422f9bbdd79af298d269', '第一次社联例会', '304', '院级', '展示', '2021年04月23日11点00分', '2021年04月23日23点00分', 0, NULL);
INSERT INTO `event` VALUES ('86de5bb1198b4bcb980366839bc89d24', '测试', '304', '院级', '展示', '2021年04月23日20点00分', '2021年04月23日23点00分', 2, NULL);

-- ----------------------------
-- Table structure for finance
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `club_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团id',
  `purpose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用途',
  `money` double(20, 0) NULL DEFAULT NULL COMMENT '金额',
  `period` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '免申请周期',
  `is_app` int(0) NULL DEFAULT NULL COMMENT '是否申请',
  `status` int(0) NULL DEFAULT NULL COMMENT '申请状态（0拒绝 1通过 2待审核）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('201719164413', '123', '胡天阳', '女', '13276909904', '1');
INSERT INTO `user` VALUES ('201719164427', '123', '郜洋洋', '女', '15565779560', '1');
INSERT INTO `user` VALUES ('201719244129', '123', '牛庚新', '男', '123123132', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('0662d18a23ec4bda8ab3b8387f8fb95e', '201719164427', '2');
INSERT INTO `user_role` VALUES ('19b87a5e6e334c01a482316f66044566', '201719164427', '1');
INSERT INTO `user_role` VALUES ('3', '201719164413', '2');
INSERT INTO `user_role` VALUES ('4', '201719244129', '3');

SET FOREIGN_KEY_CHECKS = 1;
