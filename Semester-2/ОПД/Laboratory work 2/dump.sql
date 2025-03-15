-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (x86_64)
--
-- Host: 127.0.0.1    Database: pvk2
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests`
--

LOCK TABLES `tests` WRITE;
/*!40000 ALTER TABLE `tests` DISABLE KEYS */;
INSERT INTO `tests` VALUES (1,'Тест на cвет','light.php'),(2,'Тест на сложение в уме (звук)','count_sound.php'),(3,'Тест скорости реакции на разные цвета','colors.php'),(4,'Тест на звук','sound.php'),(5,'Тест на сложение в уме (визуально)','count_visual.php');
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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_test_results`
--

LOCK TABLES `user_test_results` WRITE;
/*!40000 ALTER TABLE `user_test_results` DISABLE KEYS */;
INSERT INTO `user_test_results` VALUES (42,1,1,1183,'2024/03/21 13:46:32'),(43,1,3,1533,'2024/03/21 13:47:01'),(44,1,4,1253,'2024/03/21 13:47:26'),(45,1,5,1800,'2024/03/21 13:47:56'),(46,1,1,741,'2024/03/21 13:52:34'),(47,1,3,1165,'2024/03/21 13:52:57'),(48,1,4,862,'2024/03/21 13:53:19'),(49,1,5,2348,'2024/03/21 13:53:42'),(50,1,1,339,'2024/03/21 13:55:38'),(51,1,3,642,'2024/03/21 13:56:01'),(52,1,4,356,'2024/03/21 13:56:23'),(53,1,5,809,'2024/03/21 13:56:48'),(54,16,1,519,'2024/03/21 14:12:59'),(55,16,3,1099,'2024/03/21 14:13:20'),(56,16,1,519,'2024/03/21 14:13:45'),(57,16,3,1194,'2024/03/21 14:14:09'),(58,20,1,475,'2024/03/21 14:16:35'),(59,20,4,883,'2024/03/21 14:16:58');
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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tests`
--

LOCK TABLES `user_tests` WRITE;
/*!40000 ALTER TABLE `user_tests` DISABLE KEYS */;
INSERT INTO `user_tests` VALUES (84,1,1,0),(85,1,3,1),(86,1,4,2),(87,1,5,3),(90,16,1,0),(91,16,3,1),(92,20,1,0),(93,20,4,1);
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
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Михаил','Мороз','moroz.mkhl@gmail.com','a1c62a848f2e67cb25a71ca72f6585e0',3,NULL),(16,'Михаил','Мороз','aaa@aaa.aa','a1c62a848f2e67cb25a71ca72f6585e0',1,NULL),(20,'Vlad','Vladov','2ea238bdec5fbde59117eaa93ab716ff','',1,'2ea238bdec5fbde59117eaa93ab716ff');
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

-- Dump completed on 2024-03-21 20:17:27
