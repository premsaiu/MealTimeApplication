CREATE DATABASE  IF NOT EXISTS `mealtime` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mealtime`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mealtime
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `am_items`
--

DROP TABLE IF EXISTS `am_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `am_items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) NOT NULL,
  `item_desc` varchar(255) DEFAULT NULL,
  `item_image` varchar(255) DEFAULT NULL,
  `item_price` double DEFAULT NULL,
  `item_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `email` (`item_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `am_items`
--

LOCK TABLES `am_items` WRITE;
/*!40000 ALTER TABLE `am_items` DISABLE KEYS */;
INSERT INTO `am_items` VALUES (1,'Masala Dosa','Masala Dosa with Chutney','resources/images/ammeal/meal_01.jpg',20,'2016-03-13','2016-03-06','2016-03-06',NULL,NULL,NULL,'YES',1);
/*!40000 ALTER TABLE `am_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `am_sub_items`
--

DROP TABLE IF EXISTS `am_sub_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `am_sub_items` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `item_desc` varchar(255) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `item_type` varchar(255) NOT NULL,
  `cost` double DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `am_sub_items`
--

LOCK TABLES `am_sub_items` WRITE;
/*!40000 ALTER TABLE `am_sub_items` DISABLE KEYS */;
INSERT INTO `am_sub_items` VALUES (1,'Allam Chutney','Allam Chutney','resources/images/ammeal/meal_01.jpg','complementary',10,'2016-02-12','2016-02-12','Prem','Prem',NULL,'YES',1),(2,'Coconut Chutney','Coconut Chutney','resources/images/ammeal/meal_02.jpg','complementary',10,'2016-02-12','2016-02-12','Prem','Prem',NULL,'YES',1),(3,'Idly','Idly','resources/images/ammeal/meal_03.jpg','supplementary',20,'2016-02-12','2016-02-12','Prem','Prem',NULL,'YES',1),(4,'Wada','Wada','resources/images/ammeal/meal_04.jpg','supplementary',15,'2016-02-12','2016-02-12','Prem','Prem',NULL,'YES',1);
/*!40000 ALTER TABLE `am_sub_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `am_updated_items`
--

DROP TABLE IF EXISTS `am_updated_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `am_updated_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `modified_item_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_am_item_id` (`item_id`),
  CONSTRAINT `fk_am_item_id` FOREIGN KEY (`item_id`) REFERENCES `am_items` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `am_updated_items`
--

LOCK TABLES `am_updated_items` WRITE;
/*!40000 ALTER TABLE `am_updated_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `am_updated_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `am_updated_sub_items`
--

DROP TABLE IF EXISTS `am_updated_sub_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `am_updated_sub_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_item_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `modified_item_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_am_sub_item_id` (`sub_item_id`),
  CONSTRAINT `fk_am_sub_item_id` FOREIGN KEY (`sub_item_id`) REFERENCES `am_sub_items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `am_updated_sub_items`
--

LOCK TABLES `am_updated_sub_items` WRITE;
/*!40000 ALTER TABLE `am_updated_sub_items` DISABLE KEYS */;
INSERT INTO `am_updated_sub_items` VALUES (13,4,'MT003','2016-03-13',NULL,NULL,NULL,NULL,'Active','YES',NULL);
/*!40000 ALTER TABLE `am_updated_sub_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meals_master`
--

DROP TABLE IF EXISTS `meals_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meals_master` (
  `meal_id` int(11) NOT NULL,
  `meal_name` varchar(255) NOT NULL,
  `meal_type` varchar(255) NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`meal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meals_master`
--

LOCK TABLES `meals_master` WRITE;
/*!40000 ALTER TABLE `meals_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `meals_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp_table`
--

DROP TABLE IF EXISTS `otp_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otp_table` (
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `otp` varchar(255) NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `otp_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp_table`
--

LOCK TABLES `otp_table` WRITE;
/*!40000 ALTER TABLE `otp_table` DISABLE KEYS */;
INSERT INTO `otp_table` VALUES ('u.premsai@gmail.com','9700640022','986845',NULL,NULL,'2016-02-21 12:53:00'),('narendra.tirupati@yahoo.com','9494663543','767397',NULL,NULL,'2016-03-13 05:16:17'),('bharath.dhanpal@gmail.com','9010889853','542334',NULL,NULL,'2016-02-10 23:55:14'),('undefined','9700640000','165322',NULL,NULL,'2016-02-21 13:06:03'),('prem2.sai2@gmail.com','9700600000','878210',NULL,NULL,'2016-02-21 13:25:02'),('admin@mealtime.com','9700640011','288103',NULL,NULL,'2016-03-04 12:41:18'),('prem3.sai3@gmail.com','9700640023','200522',NULL,NULL,'2016-03-04 13:28:15'),('premcse41@gmail.com','9700640024','405836',NULL,NULL,'2016-03-04 13:29:30'),('prem4.sai4@gmail.com','9700640044','277458',NULL,NULL,'2016-03-13 14:47:40'),('prem5.sai5@gmail.com','9700640055','207782',NULL,NULL,'2016-03-14 18:01:58'),('prem6.sai6@gmail.com','9700640066','267944',NULL,NULL,'2016-03-14 18:43:39'),('prem7.sai7@gmail.com','9700640077','237473',NULL,NULL,'2016-03-16 17:22:55');
/*!40000 ALTER TABLE `otp_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_items`
--

DROP TABLE IF EXISTS `pm_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) NOT NULL,
  `item_desc` varchar(255) DEFAULT NULL,
  `item_image` varchar(255) DEFAULT NULL,
  `item_price` double DEFAULT NULL,
  `item_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `email` (`item_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_items`
--

LOCK TABLES `pm_items` WRITE;
/*!40000 ALTER TABLE `pm_items` DISABLE KEYS */;
INSERT INTO `pm_items` VALUES (1,'Rice','Rice with Curry','resources/images/ammeal/meal_01.jpg',30,'2016-03-13','2016-03-13','2016-03-13',NULL,NULL,NULL,'YES',1),(2,'Rice with Chapathi','Rice & Chapathi with Curry','resources/images/ammeal/meal_02.jpg',40,'2016-03-13','2016-03-13','2016-03-13',NULL,NULL,NULL,'YES',1),(3,'Chapathi','Chapathi with Curry','resources/images/ammeal/meal_03.jpg',30,'2016-03-13','2016-03-13','2016-03-13',NULL,NULL,NULL,'YES',1);
/*!40000 ALTER TABLE `pm_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_sub_items`
--

DROP TABLE IF EXISTS `pm_sub_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_sub_items` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `item_desc` varchar(255) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `item_type` varchar(255) NOT NULL,
  `cost` double DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_sub_items`
--

LOCK TABLES `pm_sub_items` WRITE;
/*!40000 ALTER TABLE `pm_sub_items` DISABLE KEYS */;
INSERT INTO `pm_sub_items` VALUES (1,'Allam Chutney','Allam Chutney','resources/images/ammeal/meal_01.jpg','complementary',10,'2016-02-12','2016-02-12','null','null',NULL,'YES',1),(2,'Coconut Chutney','Coconut Chutney','resources/images/ammeal/meal_02.jpg','complementary',10,'2016-02-12','2016-02-12','null','null',NULL,'YES',1),(3,'Idly','Idly','resources/images/ammeal/meal_03.jpg','supplementary',20,'2016-02-12','2016-02-12','null','null',NULL,'YES',1),(4,'Wada','Wada','resources/images/ammeal/meal_04.jpg','supplementary',15,'2016-02-12','2016-02-12','null','null',NULL,'YES',1);
/*!40000 ALTER TABLE `pm_sub_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_updated_items`
--

DROP TABLE IF EXISTS `pm_updated_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_updated_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `modified_item_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pm_item_id` (`item_id`),
  CONSTRAINT `fk_pm_item_id` FOREIGN KEY (`item_id`) REFERENCES `pm_items` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_updated_items`
--

LOCK TABLES `pm_updated_items` WRITE;
/*!40000 ALTER TABLE `pm_updated_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `pm_updated_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_updated_sub_items`
--

DROP TABLE IF EXISTS `pm_updated_sub_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_updated_sub_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_item_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `modified_item_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pm_sub_item_id` (`sub_item_id`),
  CONSTRAINT `fk_pm_sub_item_id` FOREIGN KEY (`sub_item_id`) REFERENCES `pm_sub_items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_updated_sub_items`
--

LOCK TABLES `pm_updated_sub_items` WRITE;
/*!40000 ALTER TABLE `pm_updated_sub_items` DISABLE KEYS */;
INSERT INTO `pm_updated_sub_items` VALUES (16,2,'MT003','2016-03-13',NULL,NULL,NULL,NULL,'Active','YES',NULL);
/*!40000 ALTER TABLE `pm_updated_sub_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample_meal`
--

DROP TABLE IF EXISTS `sample_meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample_meal` (
  `sample_meal_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(7) NOT NULL,
  `sample_meal_date` date NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`sample_meal_id`),
  UNIQUE KEY `mobile_number` (`mobile_number`),
  KEY `fk_sample_meal_user_id` (`user_id`),
  CONSTRAINT `fk_sample_meal_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample_meal`
--

LOCK TABLES `sample_meal` WRITE;
/*!40000 ALTER TABLE `sample_meal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sample_meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_enquiry`
--

DROP TABLE IF EXISTS `schedule_enquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_enquiry` (
  `enquiry_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(7) NOT NULL,
  `schedule_date_time` datetime NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`enquiry_id`),
  UNIQUE KEY `mobile_number` (`mobile_number`),
  KEY `fk_enquiry_user_id` (`user_id`),
  CONSTRAINT `fk_enquiry_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_enquiry`
--

LOCK TABLES `schedule_enquiry` WRITE;
/*!40000 ALTER TABLE `schedule_enquiry` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_enquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription_master`
--

DROP TABLE IF EXISTS `subscription_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription_master` (
  `subscription_id` int(11) NOT NULL,
  `subscription_name` varchar(255) NOT NULL,
  `subscription_type` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`subscription_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_master`
--

LOCK TABLES `subscription_master` WRITE;
/*!40000 ALTER TABLE `subscription_master` DISABLE KEYS */;
INSERT INTO `subscription_master` VALUES (1,'Meal Time Package1','full','2016-03-05','2016-03-05','Prem','Prem',NULL,'YES',1);
/*!40000 ALTER TABLE `subscription_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master` (
  `user_id` varchar(7) NOT NULL DEFAULT '0',
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `food_style_s1` varchar(250) DEFAULT NULL,
  `food_style_s2` varchar(250) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `mobile_number` (`mobile_number`),
  KEY `fk_role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role_master` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master`
--

LOCK TABLES `user_master` WRITE;
/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` VALUES ('MT002','Prem','Sai','u.premsai@gmail.com','9700640022','Flat No. 1211, Suraram Colony Main Road, Suraram, Jeedimetla, Hyderabad - 500055',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT002.jpg','non-veg','south',NULL),('MT003','Narendra','Tirupati','narendra.tirupati@yahoo.com','9494663543','KNR Homes, Flat No. 207, Bahadurpally, Hyderabad-500043',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\gegdc\\PS336409\\Software\\apache-tomcat-8.0.15\\MealTime_User_Images\\9494663543.jpg','veg','south',NULL),('MT004','Bharath','Dhanpal','bharath.dhanpal@gmail.com','9010889853','Suchitra, Hyderabad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\gegdc\\PS336409\\Software\\apache-tomcat-8.0.15\\MealTime_User_Images\\9010889853.jpg','veg','south',NULL),('MT005','Prem1','Sai1','premcse41@gmail.com','9700640000','Ellamma Street, Santhapet, Chittoor',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT004.jpg','Veg','North',NULL),('MT006','Prem2','Sai2','prem2.sai2@gmail.com','9700600000','Chittoor',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT006.jpg','Veg','North',NULL),('MT008','Prem','Admin','admin@mealtime.com','9700640011','Flat No. 1211, Suraram Colony Main Road, Suraram, Jeedimetla, Hyderabad - 500055',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT008.jpg','veg','north','prem ias'),('MT009','Prem3','Sai3','prem3.sai3@gmail.com','9700640023','Suraram, Hyderabad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT009.jpg','Veg','South',NULL),('MT010','Prem4','Sai4','prem4.sai4@gmail.com','9700640044','Hyderabad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT010.jpg','Veg','South',NULL),('MT011','Prem5','Sai5','prem5.sai5@gmail.com','9700640055','Hyderabad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT011.jpg','Veg','South',NULL),('MT012','Prem6','Sai6','prem6.sai6@gmail.com','9700640066','Hyderabad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT012.jpg','Veg','North',NULL),('MT013','Prem7','Sai7','prem7.sai7@gmail.com','9700640077','Hyderabad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\MT013.jpg','Veg','South',NULL),('MT014',NULL,NULL,NULL,'9700640088',NULL,'2016-03-24',NULL,NULL,NULL,'Visitor','NO',NULL,3,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_master` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER tg_user_master_insert
BEFORE INSERT ON user_master
FOR EACH ROW
BEGIN
  INSERT INTO user_master_seq VALUES (NULL);
  SET NEW.user_id = CONCAT('MT', LPAD(LAST_INSERT_ID(), 3, '0'));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_master_seq`
--

DROP TABLE IF EXISTS `user_master_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master_seq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master_seq`
--

LOCK TABLES `user_master_seq` WRITE;
/*!40000 ALTER TABLE `user_master_seq` DISABLE KEYS */;
INSERT INTO `user_master_seq` VALUES (2),(3),(4),(5),(6),(8),(9),(10),(11),(12),(13),(14);
/*!40000 ALTER TABLE `user_master_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_meals`
--

DROP TABLE IF EXISTS `user_meals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_meals` (
  `user_id` varchar(7) NOT NULL,
  `meal_id` int(11) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_meals`
--

LOCK TABLES `user_meals` WRITE;
/*!40000 ALTER TABLE `user_meals` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_meals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_master`
--

DROP TABLE IF EXISTS `user_role_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_master` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_master`
--

LOCK TABLES `user_role_master` WRITE;
/*!40000 ALTER TABLE `user_role_master` DISABLE KEYS */;
INSERT INTO `user_role_master` VALUES (1,'Admin','2016-02-10','2016-02-10','Prem','Prem',NULL,'YES',1),(2,'User','2016-02-10','2016-02-10','Prem','Prem',NULL,'YES',1),(3,'Visitor','2016-03-24','2016-03-24','Prem','Prem',NULL,'YES',1);
/*!40000 ALTER TABLE `user_role_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subscription`
--

DROP TABLE IF EXISTS `user_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_subscription` (
  `user_id` varchar(7) NOT NULL,
  `subscription_id` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  `user_subscription_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_subscription_id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_subscription_id` (`subscription_id`),
  CONSTRAINT `fk_subscription_id` FOREIGN KEY (`subscription_id`) REFERENCES `subscription_master` (`subscription_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subscription`
--

LOCK TABLES `user_subscription` WRITE;
/*!40000 ALTER TABLE `user_subscription` DISABLE KEYS */;
INSERT INTO `user_subscription` VALUES ('MT010',1,NULL,NULL,'2016-03-13','2016-03-14','MT010','MT008','Success','Yes',NULL,3),('MT011',1,NULL,NULL,'2016-03-14','2016-03-14','MT011','MT008','Success','Yes',NULL,4),('MT012',1,'2016-01-16','2016-01-14','2016-03-15','2016-03-15','MT012','MT008','Success','Yes',NULL,5),('MT013',1,'2016-03-16','2016-04-14','2016-03-16','2016-03-16','MT013','MT008','Success','Yes',NULL,6);
/*!40000 ALTER TABLE `user_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_transaction`
--

DROP TABLE IF EXISTS `user_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_transaction` (
  `transaction_id` int(11) NOT NULL,
  `user_id` varchar(7) DEFAULT NULL,
  `transaction_name` varchar(255) NOT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `transaction_status` varchar(255) DEFAULT NULL,
  `transaction_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_transaction_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_transaction`
--

LOCK TABLES `user_transaction` WRITE;
/*!40000 ALTER TABLE `user_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_wallet`
--

DROP TABLE IF EXISTS `user_wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_wallet` (
  `user_id` varchar(7) NOT NULL,
  `cash` int(10) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wallet`
--

LOCK TABLES `user_wallet` WRITE;
/*!40000 ALTER TABLE `user_wallet` DISABLE KEYS */;
INSERT INTO `user_wallet` VALUES ('MT003',895,NULL,NULL,'success','YES',1),('MT010',1000,'MT008',NULL,'Success','YES',NULL),('MT011',1000,'MT008',NULL,'Success','YES',NULL),('MT012',1000,'MT008',NULL,'Success','YES',NULL),('MT013',1000,'MT008',NULL,'Success','YES',NULL);
/*!40000 ALTER TABLE `user_wallet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-24 20:52:14
