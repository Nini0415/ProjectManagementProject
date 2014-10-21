CREATE DATABASE  IF NOT EXISTS `pmbox` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pmbox`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: pmbox
-- ------------------------------------------------------
-- Server version	5.6.13

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
-- Table structure for table `document_table`
--

DROP TABLE IF EXISTS `document_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_table` (
  `documentID` int(11) NOT NULL AUTO_INCREMENT,
  `projectID` int(11) DEFAULT NULL,
  `authorID` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `content` varchar(900) DEFAULT NULL,
  `createDate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`documentID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_table`
--

LOCK TABLES `document_table` WRITE;
/*!40000 ALTER TABLE `document_table` DISABLE KEYS */;
INSERT INTO `document_table` VALUES (1,6,2,'bb\'s planning','bb\'s plannings content','04/21/2014'),(2,6,2,'bb\'s project closing record','bb\'s project closing record content','04/21/2014'),(5,6,2,'TestDocName','sdfdghj,vmn bvcxx\r\n\'waertdhyukil\r\nsadzfgxdhcfv','04/21/2014 23:26:24 '),(6,7,14,'Sign in','This is my first document in Big Star project!\r\nGood luck for me!','04/22/2014 14:38:19 ');
/*!40000 ALTER TABLE `document_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newuserrequest_table`
--

DROP TABLE IF EXISTS `newuserrequest_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newuserrequest_table` (
  `requestID` int(11) NOT NULL AUTO_INCREMENT,
  `senderID` int(11) DEFAULT NULL,
  `adminID` int(11) DEFAULT NULL,
  `requestUserName` varchar(45) DEFAULT NULL,
  `sendDate` varchar(45) DEFAULT NULL,
  `finishDate` varchar(45) DEFAULT NULL,
  `initialPass` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`requestID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newuserrequest_table`
--

LOCK TABLES `newuserrequest_table` WRITE;
/*!40000 ALTER TABLE `newuserrequest_table` DISABLE KEYS */;
INSERT INTO `newuserrequest_table` VALUES (1,2,1,'Newbie','04/19/2014 15:29:33 ','04/19/2014 21:15:47 ','c03focsm'),(2,2,1,'dan','04/19/2014 20:14:59 ','04/19/2014 21:17:18 ','w2uc5tg2'),(3,2,0,'xyz','04/19/2014 21:01:50 ',NULL,NULL),(4,2,1,'Bear','04/20/2014 22:52:29 ','04/20/2014 22:53:12 ','e7v6z1j9');
/*!40000 ALTER TABLE `newuserrequest_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_table`
--

DROP TABLE IF EXISTS `project_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_table` (
  `projectID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `createDate` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`projectID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_table`
--

LOCK TABLES `project_table` WRITE;
/*!40000 ALTER TABLE `project_table` DISABLE KEYS */;
INSERT INTO `project_table` VALUES (2,'tt','04/16/2014 23:15:06 ','Open'),(6,'silly bear','04/20/2014 22:49:36 ','Open'),(7,'Big star','04/21/2014 20:06:52 ','Open'),(8,'bb','04/21/2014 20:07:03 ','Open'),(16,'final project','04/22/2014 24:15:54 ','Open'),(17,'Algorithm final','04/22/2014 24:16:10 ','Open');
/*!40000 ALTER TABLE `project_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectuser_table`
--

DROP TABLE IF EXISTS `projectuser_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projectuser_table` (
  `puID` int(11) NOT NULL AUTO_INCREMENT,
  `projectID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `inProjectRole` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`puID`),
  KEY `FK_oc5plj9ay5u5d8bnp5gt79t6q` (`userID`),
  KEY `FK_hi2sjewwrr4ry03fjnomoxivu` (`projectID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectuser_table`
--

LOCK TABLES `projectuser_table` WRITE;
/*!40000 ALTER TABLE `projectuser_table` DISABLE KEYS */;
INSERT INTO `projectuser_table` VALUES (1,2,2,'ProjectAdmin'),(2,6,2,'ProjectAdmin'),(3,2,7,'ProjectMember'),(4,7,2,'ProjectAdmin'),(5,8,2,'ProjectAdmin'),(19,16,2,'ProjectAdmin'),(20,17,2,'ProjectAdmin'),(21,7,14,'ProjectMember'),(22,2,6,'ProjectClient');
/*!40000 ALTER TABLE `projectuser_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_table`
--

DROP TABLE IF EXISTS `task_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_table` (
  `taskID` int(11) NOT NULL AUTO_INCREMENT,
  `projectID` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `createDate` varchar(45) DEFAULT NULL,
  `startDate` varchar(45) DEFAULT NULL,
  `dueDate` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`taskID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_table`
--

LOCK TABLES `task_table` WRITE;
/*!40000 ALTER TABLE `task_table` DISABLE KEYS */;
INSERT INTO `task_table` VALUES (3,2,'project2 task1','04/15/2014',NULL,NULL,'Open'),(4,2,'project2 task2','04/15/2014',NULL,NULL,'Open'),(5,2,'project2 task1','04/15/2014',NULL,NULL,'Open'),(6,2,'project2 task2','04/15/2014',NULL,NULL,'Open'),(7,6,'project4 task1','04/15/2014',NULL,NULL,'Open'),(9,6,'star task3','04/18/2014 10:59:36 ',NULL,NULL,'Open'),(14,7,'Create bear','04/20/2014 22:50:39 ',NULL,NULL,'Open'),(15,6,'bbb','04/21/2014 16:29:10 ',NULL,NULL,'Open'),(16,6,'ccc','04/21/2014 16:29:15 ',NULL,NULL,'Open'),(17,7,'weafdsrg','04/21/2014 20:05:47 ',NULL,NULL,'Open');
/*!40000 ALTER TABLE `task_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo_table`
--

DROP TABLE IF EXISTS `todo_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todo_table` (
  `todoID` int(11) NOT NULL AUTO_INCREMENT,
  `taskID` int(11) DEFAULT NULL,
  `createrID` int(11) DEFAULT NULL,
  `createDate` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `assigneeID` int(11) DEFAULT NULL,
  `startDate` varchar(45) DEFAULT NULL,
  `dueDate` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`todoID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo_table`
--

LOCK TABLES `todo_table` WRITE;
/*!40000 ALTER TABLE `todo_table` DISABLE KEYS */;
INSERT INTO `todo_table` VALUES (6,7,2,'04/18/2014 15:16:46 ','planning',4,NULL,NULL,'Open'),(7,3,2,'04/18/2014 15:17:11 ','one stone',4,NULL,NULL,'Open'),(8,7,2,'04/20/2014 18:15:40 ','Review plan',7,'04/21/2014','04/22/2014','Open'),(9,7,2,'04/20/2014 20:53:56 ','fegr',0,NULL,NULL,'Open'),(10,7,2,'04/20/2014 20:54:01 ','345678',0,NULL,NULL,'Open'),(11,4,2,'04/20/2014 22:50:55 ','observe bear',0,NULL,NULL,'Open'),(12,4,2,'04/20/2014 22:51:07 ','design bear',0,NULL,NULL,'Open'),(13,5,2,'04/21/2014 20:04:43 ','tweswpto',7,'04/20/2014','04/25/2014','Open'),(14,5,2,'04/21/2014 20:04:54 ','sdnvcm',0,NULL,NULL,'Open'),(15,6,2,'04/21/2014 20:05:51 ','qWeqrw4',0,NULL,NULL,'Open'),(16,14,2,'04/22/2014 14:34:34 ','observe bear',0,NULL,NULL,'Open');
/*!40000 ALTER TABLE `todo_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todocomment_table`
--

DROP TABLE IF EXISTS `todocomment_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todocomment_table` (
  `commentID` int(11) NOT NULL AUTO_INCREMENT,
  `todoID` int(11) DEFAULT NULL,
  `commenterID` int(11) DEFAULT NULL,
  `commentDate` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`commentID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todocomment_table`
--

LOCK TABLES `todocomment_table` WRITE;
/*!40000 ALTER TABLE `todocomment_table` DISABLE KEYS */;
INSERT INTO `todocomment_table` VALUES (1,1,2,'04/15/2014','first comment for todo1'),(2,1,2,'04/15/2014','second comment for todo1'),(4,6,2,'04/18/2014 23:25:48 ','Let\'s get start'),(5,6,2,'04/18/2014 23:28:00 ','Meeting tomorrow at 402'),(8,11,2,'04/20/2014 22:51:34 ','I really like bear'),(9,13,2,'04/21/2014 20:05:01 ','dzhgf'),(11,11,6,'04/22/2014 20:31:17 ','I like bear too.'),(12,11,6,'04/22/2014 20:31:40 ','They are always so cute.');
/*!40000 ALTER TABLE `todocomment_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_table` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  UNIQUE KEY `UK_g8c2ka1qgvbm7y9xryye81b5o` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table`
--

LOCK TABLES `user_table` WRITE;
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` VALUES (1,'admin','admin','admin'),(2,'bb','bb','user'),(4,'cc','cc','user'),(6,'ee','ee','user'),(7,'nn','nn','user'),(12,'dan','w2uc5tg2','user'),(13,'Bear','e7v6z1j9','user'),(14,'lucky','lucky','user'),(15,'test','test','user');
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userfile_table`
--

DROP TABLE IF EXISTS `userfile_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userfile_table` (
  `fileID` int(11) NOT NULL AUTO_INCREMENT,
  `projectID` int(11) DEFAULT NULL,
  `uploaderID` int(11) DEFAULT NULL,
  `uploadTime` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fileID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userfile_table`
--

LOCK TABLES `userfile_table` WRITE;
/*!40000 ALTER TABLE `userfile_table` DISABLE KEYS */;
INSERT INTO `userfile_table` VALUES (1,2,2,'04/22/2014 19:23:56 ','20140422190735file1.txt'),(2,7,2,'04/22/2014 19:25:52 ','20140422192552file2.txt');
/*!40000 ALTER TABLE `userfile_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-22 23:42:29
