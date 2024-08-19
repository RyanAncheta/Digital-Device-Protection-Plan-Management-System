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
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim` (
  `claim_id` int NOT NULL AUTO_INCREMENT,
  `reg_id` int DEFAULT NULL,
  `date_of_claim` date DEFAULT NULL,
  `description` text,
  `status` varchar(20) DEFAULT 'Pending',
  PRIMARY KEY (`claim_id`),
  KEY `reg_id` (`reg_id`),
  CONSTRAINT `claim_ibfk_1` FOREIGN KEY (`reg_id`) REFERENCES `registered_product` (`reg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim`
--

LOCK TABLES `claim` WRITE;
/*!40000 ALTER TABLE `claim` DISABLE KEYS */;
INSERT INTO `claim` VALUES (3,2,'2024-08-05','asd','Approved'),(4,3,'2024-08-05','asdasd','Approved'),(5,4,'2024-08-05','123','Rejected'),(6,6,'2024-08-05','123456','Approved'),(7,7,'2024-08-05','asdasd','Approved'),(9,15,'2024-08-04','asdasdasd','Rejected'),(10,2,'2024-08-11','12345','Rejected'),(11,2,'2024-08-11','123456','Rejected'),(12,4,'2024-08-04','123456','Rejected'),(13,4,'2024-08-11','123456789','Rejected'),(14,17,'2024-08-04','123','Approved'),(15,18,'2024-08-04','123456','Pending'),(16,17,'2024-08-11','asdas','Rejected'),(17,17,'2024-08-11','123','Approved'),(18,19,'2024-08-12','1231','Rejected'),(19,19,'2024-08-11','12313','Rejected'),(20,19,'2024-08-04','1231321','Rejected');
/*!40000 ALTER TABLE `claim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-13  9:24:56
