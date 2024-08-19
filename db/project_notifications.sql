-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `claim_id` int DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `is_read` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `claim_id` (`claim_id`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`claim_id`) REFERENCES `claim` (`claim_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 21:16:42'),(2,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 21:26:39'),(3,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 21:27:45'),(4,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 21:37:29'),(5,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 21:44:28'),(6,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 21:52:57'),(7,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 21:53:38'),(8,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 21:55:14'),(9,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 21:55:23'),(10,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 21:55:38'),(11,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 21:55:50'),(12,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 22:21:42'),(13,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 22:21:59'),(14,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 22:24:34'),(15,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 22:24:55'),(16,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 22:25:20'),(17,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 22:25:34'),(18,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 22:27:13'),(19,1,3,'Your claim (ID: 3) status has been updated to: Approved',1,'2024-08-10 22:29:54'),(20,1,3,'Your claim (ID: 3) status has been updated to: Rejected',1,'2024-08-10 22:30:06'),(21,1,7,'Your claim (ID: 7) status has been updated to: Approved',1,'2024-08-10 22:42:08'),(22,2,4,'Your claim (ID: 4) status has been updated to: Approved',0,'2024-08-11 05:03:04'),(23,5,9,'Your claim (ID: 9) status has been updated to: Rejected',0,'2024-08-12 13:49:21'),(24,1,3,'Your claim (ID: 3) status has been updated to: Approved',0,'2024-08-13 02:51:18'),(25,1,10,'Your claim (ID: 10) status has been updated to: Rejected',0,'2024-08-13 02:55:16'),(26,1,11,'Your claim (ID: 11) status has been updated to: Rejected',0,'2024-08-13 02:55:48'),(27,1,12,'Your claim (ID: 12) status has been updated to: Rejected',0,'2024-08-13 02:58:57'),(28,1,13,'Your claim (ID: 13) status has been updated to: Rejected',0,'2024-08-13 02:59:16'),(29,11,14,'Your claim (ID: 14) status has been updated to: Approved',0,'2024-08-13 03:30:02'),(30,11,16,'Your claim (ID: 16) status has been updated to: Rejected',0,'2024-08-13 03:33:21'),(31,11,17,'Your claim (ID: 17) status has been updated to: Approved',0,'2024-08-13 03:33:41'),(32,5,6,'Your claim (ID: 6) status has been updated to: Approved',0,'2024-08-13 03:36:54'),(33,12,18,'Your claim (ID: 18) status has been updated to: Pending',0,'2024-08-13 13:16:19'),(34,12,18,'Your claim (ID: 18) status has been updated to: Rejected',0,'2024-08-13 13:16:27'),(35,12,19,'Your claim (ID: 19) status has been updated to: Rejected',0,'2024-08-13 13:17:38'),(36,12,20,'Your claim (ID: 20) status has been updated to: Rejected',0,'2024-08-13 13:17:54');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-13  9:24:57
