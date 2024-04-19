-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (x86_64)
--
-- Host: 127.0.0.1    Database: pvk4
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tests`
--

DROP TABLE IF EXISTS `tests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests`
--

LOCK TABLES `tests` WRITE;
/*!40000 ALTER TABLE `tests` DISABLE KEYS */;
INSERT INTO `tests` VALUES (1,'Тест на cвет','light.php'),(2,'Тест на сложение в уме (звук)','count_sound.php'),(3,'Тест скорости реакции на разные цвета','colors.php'),(4,'Тест на звук','sound.php'),(5,'Тест на сложение в уме (визуально)','count_visual.php'),(6,'Простая РДО','simple_rdo.php'),(7,'Сложная РДО','hard_rdo.php'),(8, 'Аналоговое слежение', 'analog_tracking.php'), (9, 'Аналоговое преследование', 'analog_pursuit.php');
/*!40000 ALTER TABLE `tests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_test_results`
--

DROP TABLE IF EXISTS `user_test_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_test_results` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `test_id` bigint DEFAULT NULL,
  `result` int DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_test_results`
--

LOCK TABLES `user_test_results` WRITE;
/*!40000 ALTER TABLE `user_test_results` DISABLE KEYS */;
INSERT INTO `user_test_results` VALUES (42,1,1,1183,'2024/03/21 13:46:32'),(43,1,3,1533,'2024/03/21 13:47:01'),(44,1,4,1253,'2024/03/21 13:47:26'),(45,1,5,1800,'2024/03/21 13:47:56'),(46,1,1,741,'2024/03/21 13:52:34'),(47,1,3,1165,'2024/03/21 13:52:57'),(48,1,4,862,'2024/03/21 13:53:19'),(49,1,5,2348,'2024/03/21 13:53:42'),(50,1,1,339,'2024/03/21 13:55:38'),(51,1,3,642,'2024/03/21 13:56:01'),(52,1,4,356,'2024/03/21 13:56:23'),(53,1,5,809,'2024/03/21 13:56:48'),(54,16,1,519,'2024/03/21 14:12:59'),(55,16,3,1099,'2024/03/21 14:13:20'),(56,16,1,519,'2024/03/21 14:13:45'),(57,16,3,1194,'2024/03/21 14:14:09'),(58,20,1,475,'2024/03/21 14:16:35'),(59,20,4,883,'2024/03/21 14:16:58'),(60,1,2,3948,'2024/03/21 16:30:15'),(61,1,2,2100,'2024/03/21 16:30:40'),(62,1,2,3228,'2024/03/28 15:30:08'),(63,21,1,360,'2024/03/28 15:31:24'),(64,21,3,720,'2024/03/28 15:31:50'),(65,21,4,449,'2024/03/28 15:32:11'),(66,22,2,2847,'2024/03/28 15:49:36'),(67,22,1,404,'2024/03/28 15:50:00'),(68,22,4,328,'2024/03/28 15:50:22'),(69,23,3,1158,'2024/03/28 15:51:10'),(70,23,1,409,'2024/03/28 15:51:32'),(71,23,4,348,'2024/03/28 15:51:57'),(72,1,6,31,'2024/03/29 01:41:00'),(73,1,2,3466,'2024/03/29 01:41:31'),(74,1,6,62,'2024/03/29 01:42:01'),(75,1,2,2958,'2024/03/29 01:42:24'),(76,1,6,-29,'2024/03/29 01:44:14'),(77,1,2,1866,'2024/03/29 01:44:37'),(78,1,7,103,'2024/03/29 12:10:57'),(79,1,7,95,'2024/03/29 12:27:51'),(80,1,2,3125,'2024/03/29 12:28:13');
/*!40000 ALTER TABLE `user_test_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tests`
--

DROP TABLE IF EXISTS `user_tests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `test_id` bigint DEFAULT NULL,
  `order_for_user` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_tests_pk` (`test_id`,`id`),
  UNIQUE KEY `user_tests_user_id_test_id_uindex` (`user_id`,`test_id`),
  CONSTRAINT `user_tests_tests_id_fk` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`),
  CONSTRAINT `user_tests_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tests`
--

LOCK TABLES `user_tests` WRITE;
/*!40000 ALTER TABLE `user_tests` DISABLE KEYS */;
INSERT INTO `user_tests` VALUES (90,16,1,0),(91,16,3,1),(92,20,1,0),(93,20,4,1),(94,1,2,1),(95,21,1,0),(96,21,3,1),(97,21,4,2),(98,22,2,0),(99,22,1,1),(100,22,4,2),(101,23,3,0),(102,23,1,1),(103,23,4,2),(104,1,7,0);
/*!40000 ALTER TABLE `user_tests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `second_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint NOT NULL,
  `invitation_link` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `age` int DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Михаил','Мороз','moroz.mkhl@gmail.com','a1c62a848f2e67cb25a71ca72f6585e0',3,NULL,50,0),(16,'Михаил2','Мороз2','aaa@aaa.aa','a1c62a848f2e67cb25a71ca72f6585e0',1,NULL,45,0),(20,'Vlad','Vlad','2ea238bdec5fbde59117eaa93ab716ff','',1,'2ea238bdec5fbde59117eaa93ab716ff',28,1),(21,'Михаил3','Мороз3','misha2@misha.ru','a1c62a848f2e67cb25a71ca72f6585e0',1,NULL,25,1),(22,'admin','adminov','najifo2300@quipas.com','a1c62a848f2e67cb25a71ca72f6585e0',1,NULL,23,1),(23,'qweqwe','qweqweqwe','misha@moroz.ru','a1c62a848f2e67cb25a71ca72f6585e0',1,NULL,44,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-29 18:29:50
