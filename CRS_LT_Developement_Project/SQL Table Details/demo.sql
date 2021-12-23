/*
MySQL Data Transfer
Source Host: localhost
Source Database: demo
Target Host: localhost
Target Database: demo
Date: 23-12-2021 11:08:42
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

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
  CONSTRAINT `course_professor_student_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

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
  CONSTRAINT `professor_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

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
INSERT INTO `course` VALUES ('25', 'c++', '200');
INSERT INTO `course` VALUES ('26', 'python', '500');
INSERT INTO `course` VALUES ('27', 'react', '5000');
INSERT INTO `course_professor` VALUES ('25', '31');
INSERT INTO `course_professor` VALUES ('26', '32');
INSERT INTO `course_professor_student` VALUES ('25', '31', '30');
INSERT INTO `course_professor_student` VALUES ('26', '32', '30');
INSERT INTO `payment` VALUES ('3', '30', '24', 'paid', '100');
INSERT INTO `professor` VALUES ('31', 'Rohan', 'prof1', 'cse', '25');
INSERT INTO `professor` VALUES ('32', 'Abdul', 'prof1', 'cse', '26');
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'professor');
INSERT INTO `role` VALUES ('3', 'student');
INSERT INTO `student` VALUES ('30', 'stud1', 'stud30', 'cse');
INSERT INTO `student_grade` VALUES ('25', '31', '30', '90', 'A');
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('30', '2');
INSERT INTO `user_role` VALUES ('31', '2');
INSERT INTO `user_role` VALUES ('32', '2');
INSERT INTO `user_role` VALUES ('30', '3');
