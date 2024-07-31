/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50743
Source Host           : localhost:3306
Source Database       : yoake_shop

Target Server Type    : MYSQL
Target Server Version : 50743
File Encoding         : 65001

Date: 2024-05-06 10:20:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `full_location` varchar(255) DEFAULT NULL COMMENT '省市区',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `receiver` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `province_code` varchar(255) DEFAULT NULL COMMENT '所在省份编码',
  `city_code` varchar(255) DEFAULT NULL COMMENT '市对应编码',
  `county_code` varchar(255) DEFAULT NULL COMMENT '区/县对应编码',
  `address` varchar(255) DEFAULT NULL COMMENT '收货人详细地址',
  `is_default` int(11) DEFAULT '0' COMMENT '是否设置为默认地址（数值类型） 1是 0否 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '福建省 厦门市 湖里区', '5', '张三', '15346842314', '110000', '110100', '110101', '翻斗花园', '0');
INSERT INTO `address` VALUES ('2', '福建省 漳州市 南靖县', '5', '李四', '13569645248', '120000', '120100', '120101', '22', '0');
INSERT INTO `address` VALUES ('3', '福建省 漳州市 南靖县', '6', '乱糟糟', '15880514277', '', '', '', 'xxxx', '0');
INSERT INTO `address` VALUES ('4', '福建省 漳州市 南靖县', '6', '张三', '15881315455', '', '', '', 'xx', '0');
INSERT INTO `address` VALUES ('5', '福建省 漳州市 南靖县', '6', '王五', '14694557845', '', '', '', '新建组', '0');
INSERT INTO `address` VALUES ('6', '北京市 北京市 东城区', '22', '晨曦', '18039030078', '110000', '110100', '110101', '123', '0');
INSERT INTO `address` VALUES ('7', '北京市 北京市 朝阳区', '30', '张三', '15880514277', '110000', '110100', '110105', 'xxx', '0');
INSERT INTO `address` VALUES ('8', '北京市 北京市 东城区', '22', '张三', '13599634851', '110000', '110100', '110101', 'xxx', '1');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `href_url` varchar(255) DEFAULT '' COMMENT '跳转链接商品id',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片url',
  `type` varchar(255) DEFAULT NULL COMMENT '轮播图类型 1为主页轮播图 2为分类页轮播图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('1', '1013001', 'https://pic36.photophoto.cn/20150809/0847085770495268_b.jpg', '1');
INSERT INTO `banner` VALUES ('3', '1005000', 'https://img.alicdn.com/imgextra/i4/759621831/O1CN01IgO2il1POdnBG2oUh_!!759621831.jpg', '1');
INSERT INTO `banner` VALUES ('4', '1005000', 'https://img.anyusj.com/wp-content/uploads/2023/02/Collection-iPhone-14-Pro-Max-Mockups1-300x200.jpg', '1');
INSERT INTO `banner` VALUES ('5', '1005000', 'https://photo.16pic.com/00/78/53/16pic_7853444_b.jpg', '1');
INSERT INTO `banner` VALUES ('6', '1019000', 'https://photo.16pic.com/00/55/45/16pic_5545513_b.jpg', '1');
INSERT INTO `banner` VALUES ('7', '1019000', 'https://img.cnmo.com/1819_600x375/1818132.png', '2');
INSERT INTO `banner` VALUES ('8', '1005000', 'http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-15/4a79180a-1a5a-4042-8a77-4db0b9c800a8.jpg', '2');
INSERT INTO `banner` VALUES ('11', '', 'http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-15/dfc11bb0-4af5-4e9b-9458-99f615cc685a.jpg', '2');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `picture` varchar(255) DEFAULT NULL COMMENT '商品的图片',
  `count` int(11) DEFAULT NULL COMMENT '商品购买的数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '加入时的价格',
  `now_price` decimal(10,2) DEFAULT NULL COMMENT '当前的价格',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `selected` varchar(255) DEFAULT '0' COMMENT '是否选中 true 选中 默认false',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('16', '6', '63', 'OPPO一加12第三代骁龙8旗舰芯5g智能游戏手机哈苏超光影像OnePlus一加 12官方正品', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '4', '4299.00', '4299.00', '300', '1');
INSERT INTO `cart` VALUES ('17', '6', '65', 'OPPO Find N2 5G全网通 折叠屏 骁龙8+ 超轻设计 游戏拍照手机', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/2f90afe2-5b8b-4a8d-b340-d0bda1858a00.jpg', '1', '4549.00', '4549.00', '1501', '1');
INSERT INTO `cart` VALUES ('18', '5', '64', 'OPPO一加 Ace 3第二代骁龙8旗舰芯长续航智能游戏拍照手机1加ace3一加 ace 3一加ace3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3aa63e13-ece9-477b-af70-f001eb96b4e1.jpg', '1', '2599.00', '2599.00', '500', '0');
INSERT INTO `cart` VALUES ('29', '22', '63', 'OPPO一加12第三代骁龙8旗舰芯5g智能游戏手机哈苏超光影像OnePlus一加 12官方正品', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '1', '4299.00', '4299.00', '296', '0');
INSERT INTO `cart` VALUES ('30', '30', '63', 'OPPO一加12第三代骁龙8旗舰芯5g智能游戏手机哈苏超光影像OnePlus一加 12官方正品', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '2', '4299.00', '4299.00', '295', '0');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id 0为根节点不能删除',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `parent_id` int(11) NOT NULL COMMENT '父节点id',
  `list_level` int(11) DEFAULT NULL COMMENT '属于几级列表',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  `desc` varchar(255) DEFAULT NULL COMMENT '三级列表用到的描述',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `price` decimal(10,2) unsigned zerofill DEFAULT NULL COMMENT '三级列表价格',
  `now_price` decimal(10,2) unsigned zerofill DEFAULT NULL COMMENT '打折完后的价格',
  `discount` int(11) DEFAULT NULL COMMENT '商品折扣',
  `removed` int(11) DEFAULT '0' COMMENT '禁用 默认0为未禁用，1为禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '根节点', '-1', '0', null, null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('3', 'OPPO', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/b92563bc-8595-49c4-9174-6675e6bab85b.png', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('7', 'ViVo', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/675571c0-a026-454f-b5cc-8a268bcf903e.png', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('8', '黑鲨', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/b313d4af-c363-4744-be62-8bdd501f2730.jpg', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('9', '华为', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/c9ff7d5e-cbd8-463c-a61f-d4ab876bd3ac.png', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('10', '魅族', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/a7f9f32b-4a39-4ac6-8f37-78c4988844d8.png', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('11', '苹果', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/628a9b3d-32f0-4e7f-8d7d-df6553f82a3a.png', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('12', '荣耀', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/ea4411e0-7a54-4a99-a56f-81d86da28ee7.png', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('13', '索尼', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5beb7a61-723b-406b-b8a7-a9ac1900f3f0.jpg', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('14', '小米', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/f5470a1e-3a81-4173-83c9-6db8c86a2c6c.png', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('15', '真我', '0', '1', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/03e66b0d-9148-452b-85f9-d2dc1bc4d396.png', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('16', '一加专区', '3', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('17', 'FInd N 系列', '3', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('18', 'Find X 系列', '3', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('19', 'Reno 系列', '3', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('20', 'K 系列', '3', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('21', 'A 系列', '3', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('22', '平板电脑', '3', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('23', 'X系列', '7', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('24', 'S系列', '7', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('25', 'Y系列', '7', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('26', 'IQOO专区', '7', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('27', '平板电脑', '7', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('28', '手机', '8', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('29', 'pro系列', '8', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('30', '非凡大师', '9', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('31', '折叠系列', '9', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('32', 'Mate系列', '9', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('33', 'P系列', '9', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('34', 'nova系列', '9', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('35', '畅享系列', '9', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('36', '平板电脑', '9', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('37', '魅族20系列', '10', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('38', '魅族21系列', '10', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('39', '魅族16系列', '10', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('40', '魅族18系列', '10', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('41', 'iPhone手机', '11', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('42', 'iPad系列', '11', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('43', '折叠专区', '12', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('44', 'Magic系列', '12', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('45', 'HONOR系列', '12', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('46', 'X系列', '12', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('47', 'Play系列', '12', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('48', '畅玩系列', '12', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('49', 'Xperia 1 V', '13', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('50', 'Xperia 1 IV', '13', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('51', 'Xperia5 IV', '13', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('52', 'Xperia5 V', '13', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('54', 'Xiaomi 数字系列', '14', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('55', 'Xiaomi Civi系列', '14', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('56', 'Xiaomi MIX系列', '14', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('57', 'Redmi K系列', '14', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('58', 'Redmi Note系列', '14', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('59', 'Redmi 数字系列', '14', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('60', 'GT系列', '15', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('61', 'GT Neo系列', '15', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('62', '数字系列', '15', '2', '', null, null, null, null, null, '0');
INSERT INTO `category` VALUES ('63', 'OPPO一加12第三代骁龙8旗舰芯5g智能游戏手机哈苏超光影像OnePlus一加 12官方正品', '16', '3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '', '295', '00004299.00', '00004299.00', null, '0');
INSERT INTO `category` VALUES ('64', 'OPPO一加 Ace 3第二代骁龙8旗舰芯长续航智能游戏拍照手机1加ace3一加 ace 3一加ace3', '16', '3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3aa63e13-ece9-477b-af70-f001eb96b4e1.jpg', '', '492', '00002599.00', '00002599.00', null, '0');
INSERT INTO `category` VALUES ('65', 'OPPO Find N2 5G全网通 折叠屏 骁龙8+ 超轻设计 游戏拍照手机', '17', '3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/2f90afe2-5b8b-4a8d-b340-d0bda1858a00.jpg', '', '1500', '00004549.00', '00004549.00', null, '0');
INSERT INTO `category` VALUES ('66', 'OPPO Find X7 潮汐架构 × 天玑9300旗舰新品5G智能拍照超级闪充手机oppo官方旗舰店findx7XD4', '18', '3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/fcbbb9a1-eb86-4d8a-91a8-cdf0e2c91e50.jpg', '', '500', '00003999.00', '00003999.00', null, '0');
INSERT INTO `category` VALUES ('67', 'OPPO Reno11Pro oppo reno11 pro 正品手机5g智能全网通新款 0ppo reno10pro+十 官方旗舰店官网正品oppo手机', '19', '3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/b35d071f-62b5-4bc2-b560-c4de6fc4d867.jpg', '', '1541', '00003499.00', '00003499.00', null, '0');
INSERT INTO `category` VALUES ('68', ' OPPO K11 oppok11 5g智能全网通 oppo手机新款上市2023 k11x k10x oppo手机官方正品旗舰店', '20', '3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/e30aff39-84cb-4a2a-b7c1-eebd3114527c.jpg', '', '2997', '00001799.00', '00001799.00', null, '0');
INSERT INTO `category` VALUES ('69', 'OPPO A2 oppoa2 手机 oppo手机官方旗舰店官网正品 5g智能全网通0ppo a1 a36 a2pro a1pro手机', '21', '3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/18e8a602-c323-4f5c-8489-aa61b954eedd.jpg', '', '15456', '00001699.00', '00001699.00', null, '0');
INSERT INTO `category` VALUES ('70', 'oppo平板电脑新款pad原装正品学生家用网课学习游戏办公绘画商务用新品官方旗舰手机', '22', '3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/bc93bb0c-24ec-44bf-8e34-5621e9233782.jpg', '', '300', '00002068.00', '00002068.00', null, '1');
INSERT INTO `category` VALUES ('71', 'xxx', '3', '2', '', null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for goods_pictures
-- ----------------------------
DROP TABLE IF EXISTS `goods_pictures`;
CREATE TABLE `goods_pictures` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '图片唯一id',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `goods_id` int(11) NOT NULL COMMENT '对应商品的id',
  `type` int(11) DEFAULT NULL COMMENT '1 商品主图图片集合 2 商品海报图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods_pictures
-- ----------------------------
INSERT INTO `goods_pictures` VALUES ('106', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '63', '1');
INSERT INTO `goods_pictures` VALUES ('107', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/48fc368d-4fa3-4c71-afb1-3d75defab64b.jpg', '63', '1');
INSERT INTO `goods_pictures` VALUES ('108', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/ef2e28f6-bad1-4362-9732-ae579db162bd.jpg', '63', '1');
INSERT INTO `goods_pictures` VALUES ('109', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/7f55c485-e6df-4258-8d4e-4c093651ca3e.png', '63', '2');
INSERT INTO `goods_pictures` VALUES ('110', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/de88d9c9-6cd3-4cec-b90a-ee37d0accd2f.jpg', '63', '2');
INSERT INTO `goods_pictures` VALUES ('111', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3bf71f81-6550-4b13-bde8-f6d33505bfb3.jpg', '63', '2');
INSERT INTO `goods_pictures` VALUES ('112', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/692943b8-5b18-4d14-8032-884b14e3c2d2.jpg', '63', '2');
INSERT INTO `goods_pictures` VALUES ('113', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3907f99c-2eca-4739-8b26-c63d634f2371.jpg', '63', '2');
INSERT INTO `goods_pictures` VALUES ('114', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3aa63e13-ece9-477b-af70-f001eb96b4e1.jpg', '64', '1');
INSERT INTO `goods_pictures` VALUES ('115', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/59c63c18-e09b-4456-a023-6a7acf1e414f.jpg', '64', '1');
INSERT INTO `goods_pictures` VALUES ('116', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/579e330d-710f-407e-93ac-2ee47d0acf83.jpg', '64', '1');
INSERT INTO `goods_pictures` VALUES ('117', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/7c6d381c-eb63-4d05-a0ad-e2927516dd3e.jpg', '64', '2');
INSERT INTO `goods_pictures` VALUES ('118', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/cb1b6d1d-45b6-4e81-bde4-fc1f1fb89a16.jpg', '64', '2');
INSERT INTO `goods_pictures` VALUES ('119', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/02ccf94a-cca5-40a4-ae36-2cc221073b7f.jpg', '64', '2');
INSERT INTO `goods_pictures` VALUES ('120', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/2f90afe2-5b8b-4a8d-b340-d0bda1858a00.jpg', '65', '1');
INSERT INTO `goods_pictures` VALUES ('121', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/37bc6b69-3a82-4e65-a4ac-d81f76e8328d.jpg', '65', '1');
INSERT INTO `goods_pictures` VALUES ('122', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/63fd501b-dbf2-40f4-8a66-330aab7103c9.jpg', '65', '1');
INSERT INTO `goods_pictures` VALUES ('123', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/04fef209-942a-4b26-80ab-537acebab2fd.jpg', '65', '2');
INSERT INTO `goods_pictures` VALUES ('124', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/cdd8dfab-23f3-4f8b-9892-3c58e641ac65.jpg', '65', '2');
INSERT INTO `goods_pictures` VALUES ('125', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/206e6100-b725-4fc4-aef3-60910134c769.jpg', '65', '2');
INSERT INTO `goods_pictures` VALUES ('126', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/fcbbb9a1-eb86-4d8a-91a8-cdf0e2c91e50.jpg', '66', '1');
INSERT INTO `goods_pictures` VALUES ('127', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/24ada1c1-6614-46b0-879d-170f3138c54e.jpg', '66', '2');
INSERT INTO `goods_pictures` VALUES ('128', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/b35d071f-62b5-4bc2-b560-c4de6fc4d867.jpg', '67', '1');
INSERT INTO `goods_pictures` VALUES ('129', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/00d5e3d2-4b0b-46dd-b1ab-b8dbb477a8f8.jpg', '67', '2');
INSERT INTO `goods_pictures` VALUES ('130', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/e30aff39-84cb-4a2a-b7c1-eebd3114527c.jpg', '68', '1');
INSERT INTO `goods_pictures` VALUES ('131', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/7f608e2d-9f2d-4603-b062-5debf4561dca.jpg', '68', '2');
INSERT INTO `goods_pictures` VALUES ('132', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/18e8a602-c323-4f5c-8489-aa61b954eedd.jpg', '69', '1');
INSERT INTO `goods_pictures` VALUES ('133', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/701456d5-ec37-4249-90b3-fe68a1459405.jpg', '69', '2');
INSERT INTO `goods_pictures` VALUES ('134', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/bc93bb0c-24ec-44bf-8e34-5621e9233782.jpg', '70', '1');
INSERT INTO `goods_pictures` VALUES ('135', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/fb27fd37-2ab6-4f16-a362-c6bf4e669507.jpg', '70', '2');

-- ----------------------------
-- Table structure for goods_properties
-- ----------------------------
DROP TABLE IF EXISTS `goods_properties`;
CREATE TABLE `goods_properties` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品的属性id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品属性标签名',
  `value` varchar(255) DEFAULT NULL COMMENT '商品标签名说明',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods_properties
-- ----------------------------
INSERT INTO `goods_properties` VALUES ('42', '网络类型', '5G全网通', '63');
INSERT INTO `goods_properties` VALUES ('43', '品牌', 'OnePlus/一加', '63');
INSERT INTO `goods_properties` VALUES ('44', '售后服务', '全国联保', '63');
INSERT INTO `goods_properties` VALUES ('45', '屏幕刷新率', '120Hz', '63');
INSERT INTO `goods_properties` VALUES ('46', '网络类型', '5G全网通', '64');
INSERT INTO `goods_properties` VALUES ('47', '品牌', 'OnePlus/一加', '64');
INSERT INTO `goods_properties` VALUES ('48', 'CPU核心数', '八核', '64');
INSERT INTO `goods_properties` VALUES ('49', '电池容量', '5500mAh', '64');
INSERT INTO `goods_properties` VALUES ('50', 'OPPO型号', 'Find N2', '65');
INSERT INTO `goods_properties` VALUES ('51', '电池容量', '4520mAh', '65');
INSERT INTO `goods_properties` VALUES ('52', '解锁方式', '侧边指纹', '65');
INSERT INTO `goods_properties` VALUES ('53', '网络类型', '5G全网通', '66');
INSERT INTO `goods_properties` VALUES ('54', '品牌', 'OPPO', '66');
INSERT INTO `goods_properties` VALUES ('55', '品牌', 'OPPO', '67');
INSERT INTO `goods_properties` VALUES ('56', 'OPPO型号', 'Reno11 Pro', '67');
INSERT INTO `goods_properties` VALUES ('57', '品牌', 'OPPO', '68');
INSERT INTO `goods_properties` VALUES ('58', 'OPPO型号', 'K11', '68');
INSERT INTO `goods_properties` VALUES ('59', '品牌', 'OPPO', '69');
INSERT INTO `goods_properties` VALUES ('60', 'OPPO型号', 'A2', '69');
INSERT INTO `goods_properties` VALUES ('61', '网络类型', 'WIFI', '70');
INSERT INTO `goods_properties` VALUES ('62', '品牌', 'OPPO', '70');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `order_state` int(11) DEFAULT NULL COMMENT '订单状态，1为待付款、2为待发货、3为待收货、4为待评价、5为已完成、6为已取消、7为待退款、8为已退款 ',
  `receiver_contact` varchar(255) DEFAULT NULL COMMENT '收货人',
  `receiver_mobile` varchar(255) DEFAULT NULL COMMENT '收货人手机',
  `receiver_address` varchar(255) DEFAULT NULL COMMENT '收货人的完整地址',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `post_fee` int(11) DEFAULT '5' COMMENT '运费',
  `buyer_message` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `cancel_reason` varchar(255) DEFAULT NULL COMMENT '取消订单理由',
  `pay_money` decimal(10,2) DEFAULT NULL COMMENT '支付总价格',
  `comment` varchar(255) DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('20', '6', '6', '张三', '15881315455', '福建省 漳州市 南靖县 xx', '2024-03-09 16:46:35', '5', '不吃', '管理员取消', null, null);
INSERT INTO `orders` VALUES ('21', '6', '3', '张三', '15881315455', '福建省 漳州市 南靖县 xx', '2024-04-21 08:38:44', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('22', '6', '3', '乱糟糟', '15880514277', '福建省 漳州市 南靖县 xxxx', '2024-02-20 23:09:16', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('23', '6', '4', '乱糟糟', '15880514277', '福建省 漳州市 南靖县 xxxx', '2024-02-20 23:09:28', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('24', '6', '6', '王五', '14694557845', '福建省 漳州市 南靖县 新建组', '2024-03-09 16:46:37', '5', '', '管理员取消', null, null);
INSERT INTO `orders` VALUES ('25', '22', '5', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-04-02 21:22:37', '5', '', null, null, '还不错');
INSERT INTO `orders` VALUES ('26', '22', '5', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-21 09:22:17', '5', '', null, null, '你好');
INSERT INTO `orders` VALUES ('27', '22', '2', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-02-23 11:24:08', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('28', '22', '2', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 09:32:29', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('29', '22', '2', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 10:07:59', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('30', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-01 14:37:30', '5', '', '管理员取消', null, null);
INSERT INTO `orders` VALUES ('31', '22', '7', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-10 10:09:39', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('32', '22', '2', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 10:08:14', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('33', '22', '2', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 10:55:40', '5', '', null, null, null);
INSERT INTO `orders` VALUES ('34', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 11:13:18', '5', '', '不想要了', null, null);
INSERT INTO `orders` VALUES ('35', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 11:22:09', '5', '', '地址信息填写错误', '4304.00', null);
INSERT INTO `orders` VALUES ('36', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 11:23:50', '5', '', '商品无货', '4304.00', null);
INSERT INTO `orders` VALUES ('37', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 11:23:58', '5', '', '商品降价', '2604.00', null);
INSERT INTO `orders` VALUES ('39', '22', '2', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-10 16:48:28', '5', '', null, '2604.00', null);
INSERT INTO `orders` VALUES ('41', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 16:45:30', '5', '', '商品无货', '2604.00', null);
INSERT INTO `orders` VALUES ('42', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 16:45:18', '5', '', '商品信息填错了', '4304.00', null);
INSERT INTO `orders` VALUES ('43', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 16:44:57', '5', '', '商品降价', '4304.00', null);
INSERT INTO `orders` VALUES ('44', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 16:44:31', '5', '', '商品无货', '4004.00', null);
INSERT INTO `orders` VALUES ('45', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 16:44:48', '5', '', '商品信息填错了', '4004.00', null);
INSERT INTO `orders` VALUES ('46', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 16:44:24', '5', '', '其它', '4304.00', null);
INSERT INTO `orders` VALUES ('47', '22', '2', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 11:43:04', '5', '', null, '2604.00', null);
INSERT INTO `orders` VALUES ('48', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-09 16:44:04', '5', '', '不想要了', '4304.00', null);
INSERT INTO `orders` VALUES ('50', '22', '8', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-10 17:27:25', '5', '', null, '7153.00', null);
INSERT INTO `orders` VALUES ('52', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-10 17:12:24', '5', '', '商品降价', '2604.00', null);
INSERT INTO `orders` VALUES ('53', '22', '6', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-10 17:20:49', '5', '', '商品无货', '4304.00', null);
INSERT INTO `orders` VALUES ('56', '22', '5', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-21 09:13:36', '5', '', null, '8303.00', '你好');
INSERT INTO `orders` VALUES ('57', '22', '5', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-21 09:15:15', '5', '', null, '2604.00', '你好');
INSERT INTO `orders` VALUES ('58', '22', '7', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-05-03 23:43:52', '5', '', null, '4304.00', null);
INSERT INTO `orders` VALUES ('59', '22', '5', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-21 09:23:42', '5', '', null, '2604.00', 'cccx');
INSERT INTO `orders` VALUES ('60', '22', '8', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-21 09:34:10', '5', '', null, '4304.00', null);
INSERT INTO `orders` VALUES ('61', '22', '1', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-03-25 10:15:52', '5', '', null, '2604.00', null);
INSERT INTO `orders` VALUES ('62', '22', '1', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-04-03 08:20:49', '5', '', null, '4304.00', null);
INSERT INTO `orders` VALUES ('63', '22', '1', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-04-03 08:48:01', '5', '', null, '4304.00', null);
INSERT INTO `orders` VALUES ('64', '22', '1', '晨曦', '18039030078', '北京市 北京市 东城区 123', '2024-04-03 08:51:08', '5', '', null, '2604.00', null);
INSERT INTO `orders` VALUES ('65', '22', '1', '张三', '13599634851', '北京市 北京市 东城区 xxx', '2024-04-03 09:01:48', '5', '', null, '4304.00', null);
INSERT INTO `orders` VALUES ('66', '22', '6', '张三', '13599634851', '北京市 北京市 东城区 xxx', '2024-04-21 08:34:53', '5', '', '商品信息填错了', '2604.00', null);
INSERT INTO `orders` VALUES ('67', '22', '2', '张三', '13599634851', '北京市 北京市 东城区 xxx', '2024-04-06 21:28:42', '5', '', null, '4304.00', null);
INSERT INTO `orders` VALUES ('68', '22', '3', '张三', '13599634851', '北京市 北京市 东城区 xxx', '2024-04-21 08:39:15', '5', '', null, '2604.00', null);
INSERT INTO `orders` VALUES ('69', '30', '2', '张三', '15880514277', '北京市 北京市 朝阳区 xxx', '2024-05-06 00:57:38', '5', '', null, '4304.00', null);
INSERT INTO `orders` VALUES ('70', '30', '6', '张三', '15880514277', '北京市 北京市 朝阳区 xxx', '2024-05-06 00:57:13', '5', '', '其它', '4304.00', null);

-- ----------------------------
-- Table structure for order_products
-- ----------------------------
DROP TABLE IF EXISTS `order_products`;
CREATE TABLE `order_products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '关联表id',
  `order_id` int(255) NOT NULL COMMENT '订单编号,指向订单表',
  `goods_id` int(11) NOT NULL COMMENT '商品编号,指向商品表',
  `quantity` int(11) DEFAULT NULL COMMENT '购买的数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_products
-- ----------------------------
INSERT INTO `order_products` VALUES ('25', '20', '63', '1');
INSERT INTO `order_products` VALUES ('26', '21', '63', '1');
INSERT INTO `order_products` VALUES ('27', '22', '68', '1');
INSERT INTO `order_products` VALUES ('28', '23', '68', '1');
INSERT INTO `order_products` VALUES ('29', '24', '64', '1');
INSERT INTO `order_products` VALUES ('30', '25', '64', '1');
INSERT INTO `order_products` VALUES ('31', '26', '65', '1');
INSERT INTO `order_products` VALUES ('32', '27', '68', '1');
INSERT INTO `order_products` VALUES ('33', '28', '64', '1');
INSERT INTO `order_products` VALUES ('34', '29', '64', '1');
INSERT INTO `order_products` VALUES ('35', '30', '64', '2');
INSERT INTO `order_products` VALUES ('36', '31', '64', '2');
INSERT INTO `order_products` VALUES ('37', '31', '65', '1');
INSERT INTO `order_products` VALUES ('38', '32', '63', '1');
INSERT INTO `order_products` VALUES ('39', '33', '63', '1');
INSERT INTO `order_products` VALUES ('40', '34', '63', '1');
INSERT INTO `order_products` VALUES ('41', '35', '63', '1');
INSERT INTO `order_products` VALUES ('42', '36', '63', '1');
INSERT INTO `order_products` VALUES ('43', '37', '64', '1');
INSERT INTO `order_products` VALUES ('45', '39', '64', '1');
INSERT INTO `order_products` VALUES ('47', '41', '64', '1');
INSERT INTO `order_products` VALUES ('48', '42', '63', '1');
INSERT INTO `order_products` VALUES ('49', '43', '63', '1');
INSERT INTO `order_products` VALUES ('50', '45', '66', '1');
INSERT INTO `order_products` VALUES ('51', '44', '66', '1');
INSERT INTO `order_products` VALUES ('52', '46', '63', '1');
INSERT INTO `order_products` VALUES ('53', '47', '64', '1');
INSERT INTO `order_products` VALUES ('54', '48', '63', '1');
INSERT INTO `order_products` VALUES ('56', '50', '65', '1');
INSERT INTO `order_products` VALUES ('57', '50', '64', '1');
INSERT INTO `order_products` VALUES ('60', '52', '64', '1');
INSERT INTO `order_products` VALUES ('61', '53', '63', '1');
INSERT INTO `order_products` VALUES ('64', '56', '63', '1');
INSERT INTO `order_products` VALUES ('65', '56', '66', '1');
INSERT INTO `order_products` VALUES ('66', '57', '64', '1');
INSERT INTO `order_products` VALUES ('67', '58', '63', '1');
INSERT INTO `order_products` VALUES ('68', '59', '64', '1');
INSERT INTO `order_products` VALUES ('69', '60', '63', '1');
INSERT INTO `order_products` VALUES ('70', '61', '64', '1');
INSERT INTO `order_products` VALUES ('71', '62', '63', '1');
INSERT INTO `order_products` VALUES ('72', '63', '63', '1');
INSERT INTO `order_products` VALUES ('73', '64', '64', '1');
INSERT INTO `order_products` VALUES ('74', '65', '63', '1');
INSERT INTO `order_products` VALUES ('75', '66', '64', '1');
INSERT INTO `order_products` VALUES ('76', '67', '63', '1');
INSERT INTO `order_products` VALUES ('77', '68', '64', '1');
INSERT INTO `order_products` VALUES ('78', '69', '63', '1');
INSERT INTO `order_products` VALUES ('79', '70', '63', '1');

-- ----------------------------
-- Table structure for recycle
-- ----------------------------
DROP TABLE IF EXISTS `recycle`;
CREATE TABLE `recycle` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '二手手机回收id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `goods_name` varchar(255) DEFAULT '' COMMENT '商品名',
  `goods_picture` varchar(255) DEFAULT '' COMMENT '商品图',
  `type` tinyint(4) DEFAULT NULL COMMENT '机身成色，0 全新或者仅拆封，未激活，1 机身外观完美，无使用痕迹，2 外观有轻微使用痕迹，3 外观有明显划痕/磕碰/掉漆，4 外观有变形/碎裂/缺失/刻字，5 机身弯曲或断裂',
  `evaluate` decimal(10,2) DEFAULT NULL COMMENT '估价',
  `state` int(11) DEFAULT NULL COMMENT '回收状况1代表待处理 2代表待客户确认 3代表回收成功 4 代表已取消',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `receiver_contact` varchar(255) DEFAULT '' COMMENT '联系人',
  `receiver_mobile` varchar(255) DEFAULT '' COMMENT '联系电话',
  `receiver_address` varchar(255) DEFAULT '' COMMENT '联系地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of recycle
-- ----------------------------
INSERT INTO `recycle` VALUES ('1', '5', '63', 'aa', 'bb', '1', '5200.00', '4', '2024-04-07 17:16:53', 'cc', 'dd', 'ee');
INSERT INTO `recycle` VALUES ('2', '5', '63', 'OPPO一加12第三代骁龙8旗舰芯5g智能游戏手机哈苏超光影像OnePlus一加 12官方正品', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '1', '4000.00', '2', '2024-04-07 13:22:10', '张三', '15880514277', '福建省 漳州市 南靖县 xxx');
INSERT INTO `recycle` VALUES ('4', '22', '63', 'OPPO一加12第三代骁龙8旗舰芯5g智能游戏手机哈苏超光影像OnePlus一加 12官方正品', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '3', '2579.00', '1', '2024-04-07 14:28:09', '晨曦', '18039030078', '北京市 北京市 东城区123');
INSERT INTO `recycle` VALUES ('5', '22', '64', 'OPPO一加 Ace 3第二代骁龙8旗舰芯长续航智能游戏拍照手机1加ace3一加 ace 3一加ace3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3aa63e13-ece9-477b-af70-f001eb96b4e1.jpg', '5', '519.00', '1', '2024-04-07 15:22:26', '张三', '13599634851', '北京市 北京市 东城区xxx');
INSERT INTO `recycle` VALUES ('6', '22', '64', 'OPPO一加 Ace 3第二代骁龙8旗舰芯长续航智能游戏拍照手机1加ace3一加 ace 3一加ace3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3aa63e13-ece9-477b-af70-f001eb96b4e1.jpg', '5', '600.00', '4', '2024-04-07 17:43:00', '晨曦', '18039030078', '北京市 北京市 东城区123');
INSERT INTO `recycle` VALUES ('7', '22', '64', 'OPPO一加 Ace 3第二代骁龙8旗舰芯长续航智能游戏拍照手机1加ace3一加 ace 3一加ace3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3aa63e13-ece9-477b-af70-f001eb96b4e1.jpg', '4', '1039.00', '2', '2024-04-07 17:29:06', '张三', '13599634851', '北京市 北京市 东城区xxx');
INSERT INTO `recycle` VALUES ('9', '22', '64', 'OPPO一加 Ace 3第二代骁龙8旗舰芯长续航智能游戏拍照手机1加ace3一加 ace 3一加ace3', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/3aa63e13-ece9-477b-af70-f001eb96b4e1.jpg', '3', '1559.00', '2', '2024-04-07 15:27:40', '张三', '13599634851', '北京市 北京市 东城区xxx');
INSERT INTO `recycle` VALUES ('10', '22', '63', 'OPPO一加12第三代骁龙8旗舰芯5g智能游戏手机哈苏超光影像OnePlus一加 12官方正品', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '3', '2579.00', '3', '2024-04-07 15:29:57', '张三', '13599634851', '北京市 北京市 东城区xxx');
INSERT INTO `recycle` VALUES ('11', '22', '63', 'OPPO一加12第三代骁龙8旗舰芯5g智能游戏手机哈苏超光影像OnePlus一加 12官方正品', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/5602964a-1dd2-4f26-a001-dd3dd01db902.jpg', '2', '3009.00', '1', '2024-04-08 09:36:14', '张三', '13599634851', '北京市 北京市 东城区xxx');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(255) NOT NULL DEFAULT '' COMMENT '用户账号名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` varchar(255) DEFAULT '男' COMMENT '性别',
  `password` varchar(255) NOT NULL DEFAULT '123456' COMMENT '密码 默认123456',
  `profession` varchar(255) DEFAULT NULL COMMENT '职业',
  `full_location` varchar(255) DEFAULT NULL COMMENT '城市全称',
  `avatar` varchar(255) DEFAULT 'http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-06/db628d42-88a7-46e7-abb8-659448c33081.png' COMMENT '头像',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '13599692454', '张三', '2024-02-03', '男', '123456', '前端', '福建省 漳州市 南靖县', 'http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/avatar/2024-02-02/c700e0df-5578-4300-89f6-929a427089fe.png', '2024-02-03 14:45:30');
INSERT INTO `user` VALUES ('3', '135996934', '请你不要在迷恋哥', '2024-02-08', '女', '123456', '后端', '福建省 漳州市 南靖县', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/7be84767-74ad-4176-bf12-2571e8d7eaa9.png', '2024-02-03 16:02:26');
INSERT INTO `user` VALUES ('4', '1352296934', '王五', '2024-03-06', '女', '123456', '前端', '福建省 莆田市 城厢区', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/4aebc9f5-3d94-4b4d-860a-68ba6d366724.png', '2024-02-03 16:13:13');
INSERT INTO `user` VALUES ('5', '13526934', '现学现卖1', '2024-01-26', '女', '123456', '前端', '天津市 天津市 和平区', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/0c186628-dd3b-402a-8d74-06f271178385.jpg', '2024-02-03 18:15:45');
INSERT INTO `user` VALUES ('6', '13546875126', 'Yoake1', '2024-02-19', '男', '123456', '留学生', '北京市 北京市 东城区', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/e7bddd84-b07d-4363-81a7-95db1b039434.png', '2024-02-22 19:24:44');
INSERT INTO `user` VALUES ('22', '18039030078', 'yoake', '2024-02-22', '女', '123456', '前端', '北京市 北京市 东城区', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/14d41fec-2608-488d-8bb7-aeb1784d8a90.jpg', '2024-02-22 20:53:50');
INSERT INTO `user` VALUES ('23', '13456489465', 'nick1', '2019-12-30', '男', '123456', '前端', '福建省 漳州市 平和县', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/7112dbf0-87c7-4c8b-b35b-36ad31ec5aeb.png', '2024-03-01 11:07:27');
INSERT INTO `user` VALUES ('24', '12455645644', 'nick2', '2024-01-01', '女', '123456', '前端', '福建省 漳州市 南靖县', 'http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-06/db628d42-88a7-46e7-abb8-659448c33081.png', '2024-03-01 11:11:42');
INSERT INTO `user` VALUES ('25', '156456845644', 'nick3', '2024-02-04', '女', '123456', '前端', '福建省 福州市 闽侯县', 'https://web-yoake.oss-cn-beijing.aliyuncs.com/af01df2b-3bea-45e3-b732-8fb11adb57a8.png', '2024-03-01 11:12:35');
INSERT INTO `user` VALUES ('26', '13485545564', 'nick3', '2024-01-29', '男', '123456', '前端', null, 'http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-06/db628d42-88a7-46e7-abb8-659448c33081.png', '2024-03-01 11:13:05');
INSERT INTO `user` VALUES ('27', '13456456455', 'nick5', '2023-03-14', '男', '123456', '前端', null, 'http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-06/db628d42-88a7-46e7-abb8-659448c33081.png', '2024-03-01 11:13:22');
INSERT INTO `user` VALUES ('30', '15880514277', 'nick1', null, '男', '123456', null, null, 'https://web-yoake.oss-cn-beijing.aliyuncs.com/47019824-a8f5-4818-8fb2-3ae6e836c8a4.jpg', '2024-04-02 17:45:57');
