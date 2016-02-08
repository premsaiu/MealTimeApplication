CREATE DATABASE  IF NOT EXISTS `mealtime` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mealtime`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mealtime
-- ------------------------------------------------------
-- Server version	5.6.26

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
/*!40000 ALTER TABLE `am_sub_items` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `otp_table` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `pm_sub_items` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `subscription_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `mobile_number` (`mobile_number`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role_master` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master`
--

LOCK TABLES `user_master` WRITE;
/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` VALUES (4,'Premsai',NULL,'u.premsai@gmail.com','9700640000','Ellamma Street, Santhapet, Chittoor',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(8,'Prem Sai',NULL,'u.premsai@outlook.com','9581533245','Suraram',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\9581533245.jpg',NULL,NULL),(9,'Prem Sai',NULL,'premcse41@gmail.com','9700640022','Ellamma Street, Santhapet, Chittoor',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\9700640022.jpg',NULL,NULL),(10,'Bharath Dhanpal',NULL,'dhanpalbharath456@gmail.com','9010889853','Hyderabad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'D:\\Softwares\\apache-tomcat-8.0.30\\MealTime_User_Images\\9010889853.jpg',NULL,NULL);
/*!40000 ALTER TABLE `user_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_meals`
--

DROP TABLE IF EXISTS `user_meals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_meals` (
  `user_id` int(11) NOT NULL,
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
INSERT INTO `user_role_master` VALUES (1,'admin','2016-01-16','2016-01-16','Prem Sai','Prem Sai',NULL,'YES',1),(2,'user','2016-01-16','2016-01-16','Prem Sai','Prem Sai',NULL,'YES',1);
/*!40000 ALTER TABLE `user_role_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subscription`
--

DROP TABLE IF EXISTS `user_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_subscription` (
  `user_id` int(11) NOT NULL,
  `subscription_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `version` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subscription`
--

LOCK TABLES `user_subscription` WRITE;
/*!40000 ALTER TABLE `user_subscription` DISABLE KEYS */;
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
  `user_id` int(11) DEFAULT NULL,
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
  `user_id` int(11) NOT NULL,
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
/*!40000 ALTER TABLE `user_wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mealtime'
--

--
-- Dumping routines for database 'mealtime'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-08 19:58:09
