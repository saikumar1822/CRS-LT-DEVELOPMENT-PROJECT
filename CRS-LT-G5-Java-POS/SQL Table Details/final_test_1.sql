/*
MySQL Data Transfer
Source Host: localhost
Source Database: final_test_1
Target Host: localhost
Target Database: final_test_1
Date: 22-12-2021 22:54:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(255) NOT NULL,
  `admin_password` varchar(255) NOT NULL,
  PRIMARY KEY  (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL auto_increment,
  `course_name` varchar(255) NOT NULL,
  `course_cost` double NOT NULL,
  PRIMARY KEY  (`course_id`),
  UNIQUE KEY `course_name` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course_professor
-- ----------------------------
DROP TABLE IF EXISTS `course_professor`;
CREATE TABLE `course_professor` (
  `course_id` int(11) NOT NULL,
  `professor_id` int(11) NOT NULL,
  PRIMARY KEY  (`course_id`,`professor_id`),
  KEY `course_id` (`course_id`),
  KEY `professor_id` (`professor_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_professor_ibfk_1` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`professor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course_professor_student
-- ----------------------------
DROP TABLE IF EXISTS `course_professor_student`;
CREATE TABLE `course_professor_student` (
  `course_id` int(11) NOT NULL,
  `professor_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  PRIMARY KEY  (`course_id`,`professor_id`,`student_id`),
  KEY `course_id` (`course_id`),
  KEY `professor_id` (`professor_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `course_professor_student_ibfk_6` FOREIGN KEY (`professor_id`) REFERENCES `course_professor` (`professor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_professor_student_ibfk_4` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_professor_student_ibfk_5` FOREIGN KEY (`course_id`) REFERENCES `course_professor` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL auto_increment,
  `student_id` int(11) default NULL,
  `course_id` int(11) default NULL,
  `payment_status` varchar(255) default NULL,
  `amount` double default NULL,
  PRIMARY KEY  (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for professor
-- ----------------------------
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
  `professor_id` int(11) NOT NULL auto_increment,
  `professor_name` varchar(255) NOT NULL,
  `professor_password` varchar(255) NOT NULL,
  `professor_department` varchar(255) NOT NULL,
  `course_id` int(11) default NULL,
  PRIMARY KEY  (`professor_id`),
  KEY `f1` (`course_id`),
  CONSTRAINT `f1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL auto_increment,
  `student_name` varchar(255) NOT NULL,
  `student_password` varchar(255) NOT NULL,
  `student_department` varchar(255) NOT NULL,
  PRIMARY KEY  (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for student_grade
-- ----------------------------
DROP TABLE IF EXISTS `student_grade`;
CREATE TABLE `student_grade` (
  `course_id` int(11) NOT NULL,
  `professor_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `mark` double NOT NULL,
  `grade` varchar(255) NOT NULL,
  PRIMARY KEY  (`course_id`,`professor_id`,`student_id`),
  KEY `course_id` (`course_id`),
  KEY `professor_id` (`professor_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `student_grade_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_grade_ibfk_2` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`professor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_grade_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin1', 'admin1');
INSERT INTO `course` VALUES ('22', 'c++', '1000');
INSERT INTO `course` VALUES ('23', 'java', '100');
INSERT INTO `course_professor` VALUES ('22', '27');
INSERT INTO `course_professor` VALUES ('22', '29');
INSERT INTO `course_professor` VALUES ('23', '28');
INSERT INTO `course_professor` VALUES ('23', '29');
INSERT INTO `course_professor_student` VALUES ('22', '27', '27');
INSERT INTO `course_professor_student` VALUES ('23', '28', '27');
INSERT INTO `payment` VALUES ('2', '27', '22', 'paid', '1000');
INSERT INTO `professor` VALUES ('27', 'prof1', 'prof27', 'eee', '22');
INSERT INTO `professor` VALUES ('28', 'prof28', 'prof2', 'ece', '23');
INSERT INTO `professor` VALUES ('29', 'a', 'a', 'a', '22');
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'professor');
INSERT INTO `role` VALUES ('3', 'student');
INSERT INTO `student` VALUES ('27', 'student-1', 'stud27', 'eee');
INSERT INTO `student` VALUES ('28', 'stud30', 'stud30', 'eee');
INSERT INTO `student` VALUES ('29', 'stud31', 'stud31', 'iT');
INSERT INTO `student_grade` VALUES ('22', '27', '27', '45', 'Fail');
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('27', '2');
INSERT INTO `user_role` VALUES ('28', '2');
INSERT INTO `user_role` VALUES ('29', '2');
INSERT INTO `user_role` VALUES ('27', '3');
