create database db_bms;
use db_bms;
 
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
 
-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bookAuthor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bookPrice` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bookTypeId` int(11) NULL DEFAULT NULL COMMENT '图书类型的外键',
  `BorrowingNumbers` int(255) NULL DEFAULT NULL COMMENT '书本库存',
  `bookDesc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书描述',
   PRIMARY KEY (`book_id`) USING BTREE,
  INDEX `bookTypeId`(`bookTypeId`) USING BTREE,
  CONSTRAINT `bookTypeId` FOREIGN KEY (`bookTypeId`) REFERENCES `booktype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 222225 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
 
-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (2001, '计算机组成原理', '君啊', '2001', 1, 2004, '欢迎');
INSERT INTO `book` VALUES (2002, '测试啊书本名埃', '测者', '2001', 1, 1976, '测述');
 
-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书本的主键',
  `bookTypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书本的类型名字',
  `bookTypeDesc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
 
-- ----------------------------
-- Records of t_booktype
-- ----------------------------
INSERT INTO `booktype` VALUES (1, '计算机类', '计算机相关图书');
INSERT INTO `booktype` VALUES (2, '建筑类', '建筑类相关图书');
INSERT INTO `booktype` VALUES (4, '经济学类', '经济学类经济学类');
 
-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `bookBorrowTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bookReturnTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bookType_id` int(11) NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1 为在借阅状态  0为归还状态',
  INDEX `bookid`(`book_id`) USING BTREE,
  INDEX `typeID`(`bookType_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
 
-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (15, 2001, '20200200005618', '20200215005821', 1, '0');
INSERT INTO `borrow` VALUES (15, 2002, '20200200005618', '20200215005821', 1, '0');
INSERT INTO `borrow` VALUES (15, 2002, '20200200005618', '20200215005821', 1, '0');
INSERT INTO `borrow` VALUES (15, 2001, '20200200005618', '20200215005821', 1, '1');
 
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '判断是否是管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
 
-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2020, '君', '1234', '1');
INSERT INTO `user` VALUES (2021, '君', '1234', '0');
 
SET FOREIGN_KEY_CHECKS = 1;