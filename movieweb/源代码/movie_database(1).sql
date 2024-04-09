/*
 Navicat Premium Data Transfer

 Source Server         : db
 Source Server Type    : MySQL
 Source Server Version : 80100 (8.1.0)
 Source Host           : localhost:3306
 Source Schema         : movie_database

 Target Server Type    : MySQL
 Target Server Version : 80100 (8.1.0)
 File Encoding         : 65001

 Date: 20/01/2024 20:26:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actor
-- ----------------------------
DROP TABLE IF EXISTS `actor`;
CREATE TABLE `actor`  (
  `movie_id` int NOT NULL,
  `main_actor` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`movie_id`, `main_actor`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of actor
-- ----------------------------
INSERT INTO `actor` VALUES (1, '格桑');
INSERT INTO `actor` VALUES (1, '王诗槐');
INSERT INTO `actor` VALUES (1, '谢兰');
INSERT INTO `actor` VALUES (2, '万思维');
INSERT INTO `actor` VALUES (2, '张娜拉');
INSERT INTO `actor` VALUES (2, '林志颖');
INSERT INTO `actor` VALUES (2, '王子子');
INSERT INTO `actor` VALUES (3, '何晟铭');
INSERT INTO `actor` VALUES (3, '杨幂');
INSERT INTO `actor` VALUES (3, '田亮');
INSERT INTO `actor` VALUES (3, '胡静');
INSERT INTO `actor` VALUES (4, '洪金宝');
INSERT INTO `actor` VALUES (4, '熊黛林');
INSERT INTO `actor` VALUES (4, '甄子丹');
INSERT INTO `actor` VALUES (4, '黄晓明');
INSERT INTO `actor` VALUES (5, '夏梓桐');
INSERT INTO `actor` VALUES (5, '徐娇');
INSERT INTO `actor` VALUES (5, '景甜');
INSERT INTO `actor` VALUES (5, '胡歌');
INSERT INTO `actor` VALUES (6, '刘诗诗');
INSERT INTO `actor` VALUES (6, '大左');
INSERT INTO `actor` VALUES (6, '胡夏');
INSERT INTO `actor` VALUES (6, '谢楠');
INSERT INTO `actor` VALUES (7, '徐峥');
INSERT INTO `actor` VALUES (7, '朱琳');
INSERT INTO `actor` VALUES (7, '林鹏');
INSERT INTO `actor` VALUES (7, '许绍雄');
INSERT INTO `actor` VALUES (8, '于谦');
INSERT INTO `actor` VALUES (8, '李易祥');
INSERT INTO `actor` VALUES (8, '杨青倩');
INSERT INTO `actor` VALUES (8, '王彤');
INSERT INTO `actor` VALUES (9, '夏雨');
INSERT INTO `actor` VALUES (9, '杨立新');
INSERT INTO `actor` VALUES (9, '郭家铭');
INSERT INTO `actor` VALUES (9, '陆毅');
INSERT INTO `actor` VALUES (10, '任达华');
INSERT INTO `actor` VALUES (10, '张家辉');
INSERT INTO `actor` VALUES (10, '文咏珊');
INSERT INTO `actor` VALUES (10, '谢安琪');
INSERT INTO `actor` VALUES (11, '周志强');
INSERT INTO `actor` VALUES (11, '徐涛');
INSERT INTO `actor` VALUES (11, '赵岭');
INSERT INTO `actor` VALUES (11, '阿兰');
INSERT INTO `actor` VALUES (12, '姚晨');
INSERT INTO `actor` VALUES (12, '姜超');
INSERT INTO `actor` VALUES (12, '范明');
INSERT INTO `actor` VALUES (12, '闫妮');
INSERT INTO `actor` VALUES (13, '孙俪');
INSERT INTO `actor` VALUES (13, '杨幂');
INSERT INTO `actor` VALUES (13, '梁超');
INSERT INTO `actor` VALUES (13, '邓超');
INSERT INTO `actor` VALUES (14, '任贤齐');
INSERT INTO `actor` VALUES (14, '熊乃瑾');
INSERT INTO `actor` VALUES (14, '翁虹');
INSERT INTO `actor` VALUES (14, '腾格尔');
INSERT INTO `actor` VALUES (15, '徐峥');
INSERT INTO `actor` VALUES (15, '李小璐');
INSERT INTO `actor` VALUES (15, '李曼');
INSERT INTO `actor` VALUES (15, '王宝强');
INSERT INTO `actor` VALUES (16, '卢惠光');
INSERT INTO `actor` VALUES (16, '吴孟达');
INSERT INTO `actor` VALUES (16, '谢苗');
INSERT INTO `actor` VALUES (16, '谷德昭');
INSERT INTO `actor` VALUES (17, '姜宏波');
INSERT INTO `actor` VALUES (17, '张一山');
INSERT INTO `actor` VALUES (17, '成龙');
INSERT INTO `actor` VALUES (17, '颜丙燕');
INSERT INTO `actor` VALUES (18, '侯勇');
INSERT INTO `actor` VALUES (18, '汤姆·巴特勒');
INSERT INTO `actor` VALUES (18, '穆立新');
INSERT INTO `actor` VALUES (19, '冯绍峰');
INSERT INTO `actor` VALUES (19, '王珞丹');
INSERT INTO `actor` VALUES (19, '钟汉良');
INSERT INTO `actor` VALUES (19, '陈柏霖');
INSERT INTO `actor` VALUES (20, '伍允龙');
INSERT INTO `actor` VALUES (20, '安志杰');
INSERT INTO `actor` VALUES (20, '洪金宝');
INSERT INTO `actor` VALUES (20, '胡然');
INSERT INTO `actor` VALUES (21, '咏梅');
INSERT INTO `actor` VALUES (21, '焦刚');
INSERT INTO `actor` VALUES (21, '秦海璐');
INSERT INTO `actor` VALUES (21, '董子健');
INSERT INTO `actor` VALUES (22, '张子栋');
INSERT INTO `actor` VALUES (22, '王一鸣');
INSERT INTO `actor` VALUES (22, '肖逾榛');
INSERT INTO `actor` VALUES (22, '马上又');
INSERT INTO `actor` VALUES (23, '景甜');
INSERT INTO `actor` VALUES (23, '迷你彬');
INSERT INTO `actor` VALUES (23, '郝好');
INSERT INTO `actor` VALUES (23, '陆毅');
INSERT INTO `actor` VALUES (24, '张雨绮');
INSERT INTO `actor` VALUES (24, '苏瑾');
INSERT INTO `actor` VALUES (24, '连凯');
INSERT INTO `actor` VALUES (24, '陈坤');
INSERT INTO `actor` VALUES (25, 'Angelababy');
INSERT INTO `actor` VALUES (25, '江珊');
INSERT INTO `actor` VALUES (25, '白百何');
INSERT INTO `actor` VALUES (25, '赵又廷');
INSERT INTO `actor` VALUES (26, '景甜');
INSERT INTO `actor` VALUES (26, '林子聪');
INSERT INTO `actor` VALUES (26, '郭品超');
INSERT INTO `actor` VALUES (26, '霍建华');
INSERT INTO `actor` VALUES (27, '井柏然');
INSERT INTO `actor` VALUES (27, '付辛博');
INSERT INTO `actor` VALUES (27, '吴佩慈');
INSERT INTO `actor` VALUES (27, '阚清子');
INSERT INTO `actor` VALUES (28, '孙天宇');
INSERT INTO `actor` VALUES (28, '谢娜');
INSERT INTO `actor` VALUES (28, '郭德纲');
INSERT INTO `actor` VALUES (28, '黄磊');
INSERT INTO `actor` VALUES (29, '埃丝特·威廉斯');
INSERT INTO `actor` VALUES (29, '维克多·迈彻');
INSERT INTO `actor` VALUES (30, '娜塔莎·理查德森');
INSERT INTO `actor` VALUES (30, '拉尔夫·费因斯');
INSERT INTO `actor` VALUES (31, '余文乐');
INSERT INTO `actor` VALUES (31, '卢靖姗');
INSERT INTO `actor` VALUES (31, '蒋璐霞');
INSERT INTO `actor` VALUES (32, '乔杉');
INSERT INTO `actor` VALUES (32, '范丞丞');
INSERT INTO `actor` VALUES (32, '马丽');
INSERT INTO `actor` VALUES (33, '姜大卫');
INSERT INTO `actor` VALUES (33, '狄龙');
INSERT INTO `actor` VALUES (33, '陈观泰');
INSERT INTO `actor` VALUES (34, '詹姆斯·凯恩');
INSERT INTO `actor` VALUES (34, '阿尔·帕西诺');
INSERT INTO `actor` VALUES (34, '马龙·白兰度');
INSERT INTO `actor` VALUES (35, '刘青云');
INSERT INTO `actor` VALUES (35, '方中信');
INSERT INTO `actor` VALUES (35, '李若彤');

-- ----------------------------
-- Table structure for lunbo
-- ----------------------------
DROP TABLE IF EXISTS `lunbo`;
CREATE TABLE `lunbo`  (
  `lunbo_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `picture` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`lunbo_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lunbo
-- ----------------------------
INSERT INTO `lunbo` VALUES (1, 31, '001.jpg');
INSERT INTO `lunbo` VALUES (2, 32, '002.jpg');
INSERT INTO `lunbo` VALUES (3, 33, '003.jpg');
INSERT INTO `lunbo` VALUES (4, 34, '004.jpg');
INSERT INTO `lunbo` VALUES (5, 35, '005.jpg');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pm` int NOT NULL DEFAULT 0,
  `user_type` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('a@163.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 1);

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `movie_id` int NOT NULL,
  `movie_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `genre` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `production_place` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `rating` decimal(3, 1) NULL DEFAULT NULL,
  `week_hits` int NULL DEFAULT NULL,
  `month_hits` int NULL DEFAULT NULL,
  `all_hits` int NULL DEFAULT NULL,
  `picture` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `video_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `video_vip` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`movie_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, '爱情天梯', '爱情', '内地', '2011-11-21', 8.0, 776, 4673, 6770000, '001.jpg', '打红姑懂事那年起，父亲就常跟她说，一个人的命运是上天安排的，任何人无法改变。', '001.mp4', '0');
INSERT INTO `movie` VALUES (2, '一起飞', '爱情', '内地', '2012-08-21', 6.5, 342, 1423, 10000000, '002.jpg', '“苍狼”特警队在原始森林中展开大搜捕；一支神秘队伍在森林中前行。', '001.mp4', '0');
INSERT INTO `movie` VALUES (3, '跑出一片天', '硬汉', '内地', '2012-06-01', 7.3, 2134, 12456, 6170000, '003.jpg', '单身家庭长大的男孩李小天（苗雅宁饰），从小就有跑步天赋。', '001.mp4', '0');
INSERT INTO `movie` VALUES (4, '叶问2:宗师传奇', '硬汉', '港澳', '2010-04-27', 9.0, 5657, 11244, 30000000, '004.jpg', '抗日战争期间，叶问(甄子丹)以双拳捍卫了中国人的尊严，不惜得罪日本鬼子逃离佛山。', '001.mp4', '0');
INSERT INTO `movie` VALUES (5, '龙之谷：破晓奇兵', '奇幻', '内地', '2014-07-31', 5.9, 428, 2115, 1360000, '005.jpg', '在黑暗阴云的笼罩下，强大的魔物企图占领整片大陆，一切迹象都预示着黑暗的根源', '001.mp4', '0');
INSERT INTO `movie` VALUES (6, '伤心童话', '爱情', '内地', '2012-09-14', 6.5, 2221, 12411, 1060000, '006.jpg', '影视公司宣传企划杨佳（刘诗诗饰）得了怪病，命悬一线。', '001.mp4', '0');
INSERT INTO `movie` VALUES (7, '搞定岳父大人', '爱情', '内地', '2012-08-03', 7.3, 766, 3543, 20000000, '007.jpg', '范坚强眼看三十，却还未立业。周末他就要去拜见素未谋面的未来岳父岳母。', '001.mp4', '0');
INSERT INTO `movie` VALUES (8, '就是闹着玩的', '恶搞', '内地', '2012-01-23', 7.4, 2354, 13454, 7780000, '008.jpg', '随着冲锋的号角，村民们忘了是拍电影，跟着八路们冲进了拍摄现场，', '001.mp4', '0');
INSERT INTO `movie` VALUES (9, '甲午大海战', '抗日', '内地', '2012-07-06', 7.2, 157, 446, 10000000, '009.jpg', '1877年，一批少年学子怀着强国之梦登上海轮远赴英国，进入皇家海军学院。', '001.mp4', '0');
INSERT INTO `movie` VALUES (10, '大追捕', '枪战', '港澳', '2012-03-15', 7.8, 231, 425, 30000000, '010.jpg', '二十年前因奸杀少女依芸入狱，重犯王远阳在狱中历经凌辱，变成性格怪异、人见人惧的哑巴杀手。', '001.mp4', '0');
INSERT INTO `movie` VALUES (11, '藏獒多吉', '恶搞', '内地', '2011-07-15', 5.9, 1414, 16331, 1680000, '011.jpg', '少年田劲的父母早年为了将西医带入藏地做出了无数的牺牲，由于无法适应高原的生活，', '001.mp4', '0');
INSERT INTO `movie` VALUES (12, '武林外传', '恶搞', '内地', '2011-01-26', 9.6, 5353, 13531, 5060000, '012.jpg', '贼奸商图谋七侠镇，众伙计力保同福居。郭芙蓉（姚晨 饰）脾气再度发作', '001.mp4', '0');
INSERT INTO `movie` VALUES (13, '分手大师', '恶搞', '内地', '2014-06-26', 5.9, 6786, 13515, 10000000, '013.jpg', '茫茫人海，风雨飘摇，俯瞰城市，每一个大厦每一个窗户里都有无数男女为了感情哭闹，争吵，自杀，上吊！', '001.mp4', '0');
INSERT INTO `movie` VALUES (14, '双城计中计', '杀手', '内地', '2012-02-24', 7.6, 1358, 5366, 10000000, '014.jpg', '在赌场内“散步”的小老千陈少卿欠下黑道老大林啸东一只手，为了保住这只手', '001.mp4', '1');
INSERT INTO `movie` VALUES (15, '人在囧途', '恶搞', '内地', '2010-06-04', 8.1, 2411, 14145, 10000000, '015.jpg', '春节将近，玩具集团老板李成功（徐峥饰）回长沙过年，同时在情人曼妮（李曼饰）的逼迫下', '001.mp4', '1');
INSERT INTO `movie` VALUES (16, '小飞侠', '恶搞', '港澳', '1995-11-21', 5.9, 770, 4610, 3960000, '016.jpg', '号称世界最高功夫的谢峻谢飞父子打败独角猩猩兽，救出了被关在山中山的小公主。', '001.mp4', '0');
INSERT INTO `movie` VALUES (17, '寻找成龙', '恶搞', '内地', '2009-07-03', 7.5, 378, 1422, 7060000, '017.jpg', '中影基地，一把青龙宝剑划破了天空，身着明朝服装的侠士剑锋一过，在他身边的明朝士兵们立即倒下一片。', '001.mp4', '1');
INSERT INTO `movie` VALUES (18, '冲出亚马逊', '硬汉', '内地', '2002-06-01', 7.6, 2131, 6246, 20000000, '018.jpg', '1999年8月，中国特种兵王辉（侯勇 饰）与胡小龙（穆立新 饰）远赴拉美的亚马逊河流域', '001.mp4', '0');
INSERT INTO `movie` VALUES (19, '后会无期', '穿越', '内地', '2014-07-24', 8.5, 5657, 11244, 20000000, '019.jpg', '居住在中国最东边小岛上的三个青年马浩汉（冯绍峰 饰）、江河（陈柏霖 饰）、胡生。', '001.mp4', '1');
INSERT INTO `movie` VALUES (20, '恶战', '枪战', '港澳', '2014-01-10', 7.3, 4281, 21151, 60000000, '020.jpg', '天生神力的青年马永贞（伍允龙 饰）从家乡来到上海谋生，', '001.mp4', '0');
INSERT INTO `movie` VALUES (21, '青春派', '青春', '内地', '2013-09-14', 7.9, 3221, 9411, 20000000, '021.jpg', '不疯狂，怎叫青春？居然（董子健 饰）在高考前拍毕业照时，当着全校师生的面，', '001.mp4', '0');
INSERT INTO `movie` VALUES (22, '愤怒的小孩', '青春', '内地', '2013-08-03', 7.2, 4901, 11543, 50000000, '022.jpg', '孙小傲是个古灵精怪的淘气包。爸爸孙建业和妈妈在俄罗斯工作，小傲一直由耳聋寡言的爷爷照顾。', '001.mp4', '0');
INSERT INTO `movie` VALUES (23, '新妈妈再爱我一次', '亲情', '内地', '2012-01-23', 6.6, 2354, 7454, 9080000, '023.jpg', ' 妻子小霖（景甜 饰）去世后，米一帆（陆毅 饰）就和五岁的儿子小乐（迷你彬 饰）相依为命。', '001.mp4', '0');
INSERT INTO `movie` VALUES (24, '钱学森', '历史', '内地', '2011-07-06', 7.8, 457, 1200, 2770000, '024.jpg', '2009年秋，钱学森已近垂暮，总理亲来探望。钱学森一针见血地指出中国教育制度的弊端，恳请总理能够改变现状。', '001.mp4', '0');
INSERT INTO `movie` VALUES (25, '第一次', '爱情', '内地', '2012-06-08', 5.9, 2310, 4250, 10000000, '025.jpg', '宋诗乔（Angelababy/杨颖 饰）经常独自对着录音机说出自己心中的秘密。因为她患有的先天性疾病。', '001.mp4', '1');
INSERT INTO `movie` VALUES (26, '超时空救兵', '穿越', '内地', '2012-01-24', 6.6, 6414, 16331, 30000000, '026.jpg', '立志成为作家的青年二蛋（霍建华 饰）历时三年，写成唐朝侠客史可进之传奇故事。', '001.mp4', '0');
INSERT INTO `movie` VALUES (27, '美味情歌', '爱情', '内地', '2009-04-26', 6.5, 535, 3987, 990000, '027.jpg', '灵珊和艺男都是从国外留学归来，他们的父亲是会所姜总经理的朋友。', '001.mp4', '0');
INSERT INTO `movie` VALUES (28, '火星没事', '恶搞', '内地', '2009-06-26', 6.3, 6786, 13515, 6710000, '028.jpg', '罗秉文为了完成太太的遗愿，领养了一个男孩，取名没事。', '001.mp4', '1');
INSERT INTO `movie` VALUES (29, '出水芙蓉', '爱情', '欧美', '1952-12-04', 6.7, 138, 566, 1000000, '029.jpg', '女主人翁安妮·凯勒是一名澳洲音乐教师的女儿，从小以游泳作为疗腿疾的方法，竟练就一身游泳绝技。', '001.mp4', '0');
INSERT INTO `movie` VALUES (30, '伯爵夫人', '爱情', '欧美', '2005-10-30', 5.9, 241, 1414, 1000000, '030.jpg', '故事背景为20世纪30年代的上海，讲述了一位梦想破灭的前美国外交官与一位被迫在酒吧中低贱过活的流亡俄罗斯女伯爵之间的故事。 ', '001.mp4', '0');
INSERT INTO `movie` VALUES (31, '中国兵王', '体育', '内地', '2020-01-01', 5.3, 123, 533, 242452, '031.jpg', '中国首部女性特战力量电影《中国兵王之绝密任务》讲述女兵们为了国家与民族的安危，在和平时期刀尖舔血，化身成洋溢着青春热血、背负着国家使命的女兵们，悄然出征，为正义默默献身。', '001.mp4', '1');
INSERT INTO `movie` VALUES (32, '人生路不熟', '青春', '内地', '2023-02-03', 8.9, 2422, 1245, 3252521, '032.jpg', '一家四口意外开启一段有笑有泪的卡车之旅！卡车司机周东海（乔杉 饰）非常不看好准女婿万一帆（范丞丞 饰），为讨未来岳父欢心，万一帆越努力越“翻车”，准岳母霍梅梅（马丽 饰）和女儿周微雨（张婧仪 饰）也为调解这对冤家费尽心思。倒霉不断的万一帆，能否在旅途中通过未来岳父周东海的种种考验', '001.mp4', '0');
INSERT INTO `movie` VALUES (33, '刺马', '杀手', '港澳', '1973-02-24', 8.8, 32, 123, 2514314, '033.jpg', '《刺马》是根据清朝四大奇案改编的真人真事改编，同一故事已多次改编为京剧、地方戏曲和电影。张彻的本片是以京剧作为蓝本却加进不少枝叶，也渗进了他对名利场的嘲讽和对世事的感怀，使得全片结构均衡、思想与感情有较佳结合。本片的人物性格丰富而有层次，主角们都各有人性的优劣点', '001.mp4', '0');
INSERT INTO `movie` VALUES (34, '教父', '杀手', '欧美', '1972-04-05', 9.2, 123, 432, 353151515, '034.jpg', '40年代的美国，“教父”维托·唐·柯里昂（马龙·白兰度 饰）是黑手党柯里昂家族的首领，带领家族从事非法的勾当，但同时他也是许多弱小平民的保护神，深得人们爱戴。因为拒绝了毒枭索洛索的毒品交易要求，柯里昂家族和纽约其他几个黑手党家族的矛盾激化、圣诞前夕，索洛索劫持了“教父”的参谋汤姆，并派人暗杀“教父”；因为内奸的出卖，“教父”的大儿子逊尼被仇家杀害；小儿子麦克（阿尔·帕西诺 饰）也被卷了进来，失去爱妻。', '001.mp4', '0');
INSERT INTO `movie` VALUES (35, '十万火急', '灾难', '港澳', '1997-01-04', 7.3, 23, 145, 435663, '035.jpg', ' 消防队长云（刘青云饰）是个刻己尽责的救火队员，每当意外发生，他都要和队员赶到火场。新调来的上司（方中信饰）决心以严谨纪律重整阵容，他的苛刻要求招致一众消防员的不满。但在生活上，他们各自并不如意。上司重遇前妻，形同陌路。', '001.mp4', '0');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `movie_id` int NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`movie_id`, `type`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '剧情');
INSERT INTO `type` VALUES (1, '爱情');
INSERT INTO `type` VALUES (2, '剧情');
INSERT INTO `type` VALUES (2, '悬疑');
INSERT INTO `type` VALUES (2, '爱情');
INSERT INTO `type` VALUES (3, '儿童');
INSERT INTO `type` VALUES (3, '剧情');
INSERT INTO `type` VALUES (3, '运动');
INSERT INTO `type` VALUES (4, '传记');
INSERT INTO `type` VALUES (4, '剧情');
INSERT INTO `type` VALUES (4, '动作');
INSERT INTO `type` VALUES (5, '冒险');
INSERT INTO `type` VALUES (5, '奇幻');
INSERT INTO `type` VALUES (5, '家庭');
INSERT INTO `type` VALUES (6, '剧情');
INSERT INTO `type` VALUES (6, '爱情');
INSERT INTO `type` VALUES (7, '剧情');
INSERT INTO `type` VALUES (7, '喜剧');
INSERT INTO `type` VALUES (7, '爱情');
INSERT INTO `type` VALUES (8, '剧情');
INSERT INTO `type` VALUES (8, '喜剧');
INSERT INTO `type` VALUES (9, '剧情');
INSERT INTO `type` VALUES (9, '动作');
INSERT INTO `type` VALUES (9, '战争');
INSERT INTO `type` VALUES (10, '动作');
INSERT INTO `type` VALUES (10, '悬疑');
INSERT INTO `type` VALUES (10, '犯罪');
INSERT INTO `type` VALUES (11, '儿童');
INSERT INTO `type` VALUES (11, '剧情');
INSERT INTO `type` VALUES (11, '动画');
INSERT INTO `type` VALUES (12, '剧情');
INSERT INTO `type` VALUES (12, '动作');
INSERT INTO `type` VALUES (12, '喜剧');
INSERT INTO `type` VALUES (13, '剧情');
INSERT INTO `type` VALUES (13, '喜剧');
INSERT INTO `type` VALUES (13, '爱情');
INSERT INTO `type` VALUES (14, '剧情');
INSERT INTO `type` VALUES (14, '喜剧');
INSERT INTO `type` VALUES (14, '悬疑');
INSERT INTO `type` VALUES (15, '剧情');
INSERT INTO `type` VALUES (15, '喜剧');
INSERT INTO `type` VALUES (15, '经典');
INSERT INTO `type` VALUES (16, '剧情');
INSERT INTO `type` VALUES (16, '喜剧');
INSERT INTO `type` VALUES (17, '儿童');
INSERT INTO `type` VALUES (17, '喜剧');
INSERT INTO `type` VALUES (18, '剧情');
INSERT INTO `type` VALUES (18, '动作');
INSERT INTO `type` VALUES (19, '喜剧');
INSERT INTO `type` VALUES (19, '爱情');
INSERT INTO `type` VALUES (20, '动作');
INSERT INTO `type` VALUES (20, '犯罪');
INSERT INTO `type` VALUES (21, '剧情');
INSERT INTO `type` VALUES (21, '爱情');
INSERT INTO `type` VALUES (22, '剧情');
INSERT INTO `type` VALUES (22, '喜剧');
INSERT INTO `type` VALUES (23, '剧情');
INSERT INTO `type` VALUES (23, '爱情');
INSERT INTO `type` VALUES (24, '传记');
INSERT INTO `type` VALUES (24, '剧情');
INSERT INTO `type` VALUES (25, '剧情');
INSERT INTO `type` VALUES (25, '爱情');
INSERT INTO `type` VALUES (26, '动作');
INSERT INTO `type` VALUES (26, '喜剧');
INSERT INTO `type` VALUES (27, '喜剧');
INSERT INTO `type` VALUES (27, '爱情');
INSERT INTO `type` VALUES (28, '喜剧');
INSERT INTO `type` VALUES (28, '科幻');
INSERT INTO `type` VALUES (29, '剧情');
INSERT INTO `type` VALUES (29, '爱情');
INSERT INTO `type` VALUES (30, '剧情');
INSERT INTO `type` VALUES (30, '爱情');
INSERT INTO `type` VALUES (31, '剧情');
INSERT INTO `type` VALUES (32, '剧情');
INSERT INTO `type` VALUES (32, '喜剧');
INSERT INTO `type` VALUES (33, '剧情');
INSERT INTO `type` VALUES (33, '古装');
INSERT INTO `type` VALUES (33, '爱情');
INSERT INTO `type` VALUES (34, '剧情');
INSERT INTO `type` VALUES (34, '惊悚');
INSERT INTO `type` VALUES (34, '经典');
INSERT INTO `type` VALUES (35, '动作');
INSERT INTO `type` VALUES (35, '惊悚');
INSERT INTO `type` VALUES (35, '灾难');

SET FOREIGN_KEY_CHECKS = 1;
