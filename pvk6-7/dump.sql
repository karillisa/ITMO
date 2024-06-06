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
-- Table structure for table `criteria`
--

DROP TABLE IF EXISTS `criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criteria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteria`
--

LOCK TABLES `criteria` WRITE;
/*!40000 ALTER TABLE `criteria` DISABLE KEYS */;
INSERT INTO `criteria` VALUES (1,'талантливый'),(4,'быстрый');
/*!40000 ALTER TABLE `criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criteria_test`
--

DROP TABLE IF EXISTS `criteria_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criteria_test` (
  `criteria_id` int NOT NULL,
  `test_id` bigint NOT NULL,
  `val` int DEFAULT NULL,
  UNIQUE KEY `criteria_test_pk` (`criteria_id`,`test_id`),
  KEY `criteria_test_tests_id_fk` (`test_id`),
  CONSTRAINT `criteria_test_criteria_id_fk` FOREIGN KEY (`criteria_id`) REFERENCES `criteria` (`id`),
  CONSTRAINT `criteria_test_tests_id_fk` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteria_test`
--

LOCK TABLES `criteria_test` WRITE;
/*!40000 ALTER TABLE `criteria_test` DISABLE KEYS */;
INSERT INTO `criteria_test` VALUES (1,10,10),(4,10,10);
/*!40000 ALTER TABLE `criteria_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professions`
--

DROP TABLE IF EXISTS `professions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `hidden` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `professions_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professions`
--

LOCK TABLES `professions` WRITE;
/*!40000 ALTER TABLE `professions` DISABLE KEYS */;
INSERT INTO `professions` VALUES (3,'Тестировщик','Тестировщик - это профессионал, занимающийся проверкой и анализом программного обеспечения, выявляющий и исправляющий ошибки, дефекты и недочеты для обеспечения надежности и качества работы приложений и программ.',0),(5,'Веб-разработчик','Веб-разработчик - это эксперт, создающий веб-приложения, используя языки программирования, такие как HTML, CSS, JavaScript, и инструменты для разработки функционала и визуального оформления.',0);
/*!40000 ALTER TABLE `professions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pvk`
--

DROP TABLE IF EXISTS `pvk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pvk` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pvk`
--

LOCK TABLES `pvk` WRITE;
/*!40000 ALTER TABLE `pvk` DISABLE KEYS */;
INSERT INTO `pvk` VALUES (3,'Стремление к профессиональному совершенству'),(4,'Адекватная самооценка'),(5,'Самостоятельность'),(6,'Пунктуальность, педантичность'),(7,'Дисциплинированность'),(8,'Аккуратность в работе'),(9,'Организованность, самодисциплина'),(10,'Старательность, исполнительность'),(11,'Ответственность'),(12,'Трудолюбие'),(13,'Инициативность'),(14,'Самокритичность'),(15,'Оптимизм, доминирование положительных эмоций'),(16,'Самообладание, эмоциональная уравновешенность, выдержка'),(17,'Самоконтроль, способность к самонаблюдению'),(18,'Предусмотрительность'),(19,'Фрустрационная толерантность (отсутствие агрессивности или          депрессивности в ситуациях неудач)'),(20,'Самомобилизующийся тип реакции на препятствия, возникающие на пути к достижению цели.'),(21,'Интернальность (погруженность в себя, самодостаточность, необщительность)'),(22,'Экстернальность (ориентация на взаимодействие с людьми, общительность)'),(23,'Интрапунитивность (ориентация на собственные силы, уверенность в себе, чувство самоэффективности)'),(24,'Экстрапунитивность (ориентация на помощь других людей, надежда на благоприятные обстоятельства, неуверенность в себе)'),(25,'Способность планировать свою деятельность во времени'),(26,'Способность организовывать свою деятельность в условиях большого потока информации и разнообразия поставленных задач'),(27,'Способность брать на себя ответственность за принимаемые решения и действия'),(28,'Способность принимать решение в нестандартных ситуациях'),(29,'Способность рационально действовать в экстремальных ситуациях'),(30,'Способность эффективно действовать (также быстро принимать решения) в условиях дефицита времени'),(31,'Способность переносить неприятные ощущения (дурной запах, шум, грязь, холодная вода, ожог, царапина, удар электрического тока и т.д.) без потрясений'),(32,'Способность аргументировано отстаивать свое мнение'),(33,'Способность к переключениям с одной деятельности на другую'),(34,'Способность преодолевать страх'),(35,'Решительность'),(36,'Сильная воля'),(37,'Смелость'),(38,'Чувство долга'),(39,'Честность'),(40,'Порядочность'),(41,'Товарищество.'),(42,'Зрительная оценка размеров предметов.'),(43,'Зрительное восприятие расстояний между предметами.'),(44,'Глазомер: линейный, угловой, объемный.'),(45,'Глазомер динамический (способность оценивать направление и скорость движения предмета).'),(46,'Способность к различению фигуры (предмета, отметки, сигнала и пр.) на малоконтрастном фоне.'),(47,'Способность различать и опознавать замаскированные объекты.'),(48,'Способность к восприятию пространственного соотношения предметов.'),(49,'Точность и оценка направления на источник звука.'),(50,'Способность узнавать и различать ритмы'),(51,'Речевой слух (восприятие устной речи)'),(52,'Различение отрезков времени'),(53,'Способность к распознаванию небольших отклонений параметров технологических процессов от заданных значений по визуальным признакам'),(54,'Способность к распознаванию небольших отклонений параметров технологических процессов от заданных значений по акустическим признакам'),(55,'Способность к распознаванию небольших отклонений параметров технологических процессов от заданных значений по кинестетическим признакам'),(56,'Способность к зрительным представлениям.'),(57,'Способность к пространственному воображению.'),(58,'Способность к образному представлению предметов, процессов и явлений.'),(59,'Способность наглядно представлять себе новое явление, ранее, не встречающееся в опыте, или старое, но в новых условиях'),(60,'Способность к переводу образа в словесное описание'),(61,'Способность к воссозданию образа по словесному описанию'),(62,'Аналитичность (способность выделять отдельные элементы действительности, способность к классификации)'),(63,'Синтетичность (способность к обобщениям, установлению связей, закономерностей, формирование понятий)'),(64,'Транссонантность (способность к актуализации и вовлечению в процесс мышления информации, хранящейся в памяти, ассоциативность мышления)'),(65,'Логичность'),(66,'Креативность (способность порождать необычные идеи, отклоняться от традиционных схем мышления)'),(67,'Оперативность (скорость мыслительных процессов, интеллектуальная лабильность)'),(68,'Объектные свойства мышления (информационный материал, используемый в процессе мышления)'),(69,'Предметность (объекты реального мира и их признаки)'),(70,'Образность (наглядные образы, схемы, планы и т.д.)'),(71,'Абстрактность (абстрактные образы, понятия)'),(72,'Вербальность (устная и письменная речь)'),(73,'Калькулятивность (цифровой материал)');
/*!40000 ALTER TABLE `pvk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pvk_criteria`
--

DROP TABLE IF EXISTS `pvk_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pvk_criteria` (
  `criteria_id` int NOT NULL,
  `pvk_id` bigint NOT NULL,
  `val` bigint DEFAULT NULL,
  PRIMARY KEY (`pvk_id`,`criteria_id`),
  KEY `pvk_criteria_criteria_id_fk` (`criteria_id`),
  CONSTRAINT `pvk_criteria_criteria_id_fk` FOREIGN KEY (`criteria_id`) REFERENCES `criteria` (`id`),
  CONSTRAINT `pvk_criteria_pvk_id_fk` FOREIGN KEY (`pvk_id`) REFERENCES `pvk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pvk_criteria`
--

LOCK TABLES `pvk_criteria` WRITE;
/*!40000 ALTER TABLE `pvk_criteria` DISABLE KEYS */;
INSERT INTO `pvk_criteria` VALUES (4,3,20),(4,4,60);
/*!40000 ALTER TABLE `pvk_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_categories`
--

DROP TABLE IF EXISTS `test_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_categories`
--

LOCK TABLES `test_categories` WRITE;
/*!40000 ALTER TABLE `test_categories` DISABLE KEYS */;
INSERT INTO `test_categories` VALUES (1,'Внимание'),(2,'Память'),(3,'Мышление'),(4,'Другие тесты');
/*!40000 ALTER TABLE `test_categories` ENABLE KEYS */;
UNLOCK TABLES;

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
  `category_id` int DEFAULT NULL,
  `measure` varchar(10) DEFAULT NULL,
  `time` int DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests`
--

LOCK TABLES `tests` WRITE;
/*!40000 ALTER TABLE `tests` DISABLE KEYS */;
INSERT INTO `tests` VALUES (1,'Тест на cвет','old_tests/light.php',4,'ms',30),(2,'Тест на сложение в уме (звук)','old_tests/count_sound.php',4,'ms',30),(3,'Тест скорости реакции на разные цвета','old_tests/colors.php',4,'ms',30),(4,'Тест на звук','old_tests/sound.php',4,'ms',30),(5,'Тест на сложение в уме (визуально)','old_tests/count_visual.php',4,'ms',30),(6,'Простая РДО','old_tests/simple_rdo.php',4,'ms',30),(7,'Сложная РДО','old_tests/hard_rdo.php',4,'ms',30),(8,'Аналоговое слежение','old_tests/analog_tracking.php',4,'ms',30),(9,'Аналоговое преследование','old_tests/analog_pursuit.php',4,'ms',30),(10,'Красно-черные таблицы Горбова-Шульте','red_black_tables.php',1,'ms',22),(11,'Кольца Ландольта','rings.php',1,'ms',22),(12,'Тест кратковременной памяти на слова','words.php',2,'pc',22),(13,'Тест кратковременной памяти на образы','images.php',2,'pc',22),(14,'Арифметические операции','arithmetic.php',3,'pc',22),(15,'Кубики Косса','cubes.php',3,'pc',22),(16,'Установление закономерностей','patterns.php',3,'%',2);
/*!40000 ALTER TABLE `tests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profession_pvk`
--

DROP TABLE IF EXISTS `user_profession_pvk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profession_pvk` (
  `user_id` bigint DEFAULT NULL,
  `profession_id` bigint DEFAULT NULL,
  `pvk_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `val` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_profession_pvk_pk` (`pvk_id`,`user_id`,`profession_id`),
  KEY `user_profession_pvk_profession__fk` (`profession_id`),
  KEY `user_profession_pvk_user___fk` (`user_id`),
  CONSTRAINT `user_profession_pvk_profession__fk` FOREIGN KEY (`profession_id`) REFERENCES `professions` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `user_profession_pvk_pvk___fk` FOREIGN KEY (`pvk_id`) REFERENCES `pvk` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_profession_pvk_user___fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profession_pvk`
--

LOCK TABLES `user_profession_pvk` WRITE;
/*!40000 ALTER TABLE `user_profession_pvk` DISABLE KEYS */;
INSERT INTO `user_profession_pvk` VALUES (1,5,3,81,40),(1,5,4,82,60),(1,3,4,84,13),(1,3,3,85,26);
/*!40000 ALTER TABLE `user_profession_pvk` ENABLE KEYS */;
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
  `complexity` int DEFAULT NULL,
  `start_pulse` int DEFAULT NULL,
  `end_pulse` int DEFAULT NULL,
  `during_pulse` text,
  UNIQUE KEY `id` (`id`),
  KEY `user_test_results_tests_id_fk` (`test_id`),
  CONSTRAINT `user_test_results_tests_id_fk` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_test_results`
--

LOCK TABLES `user_test_results` WRITE;
/*!40000 ALTER TABLE `user_test_results` DISABLE KEYS */;
INSERT INTO `user_test_results` VALUES (149,16,1,100,NULL,1,NULL,NULL,NULL),(150,16,2,200,NULL,1,NULL,NULL,NULL),(151,1,12,100,'2024/05/28 10:40:31',1,NULL,NULL,NULL),(152,1,12,50,'2024/05/28 10:40:45',2,NULL,NULL,NULL),(153,1,12,100,'2024/05/28 10:40:53',3,NULL,NULL,NULL),(154,1,13,2,'2024/05/28 10:41:14',1,NULL,NULL,NULL),(155,16,1,120,NULL,2,NULL,NULL,NULL),(156,16,5,50,NULL,1,NULL,NULL,NULL),(157,16,1,352,'2024/05/28 15:45:11',0,NULL,NULL,NULL),(158,16,1,737,'2024/05/28 15:49:49',0,NULL,NULL,NULL),(159,16,5,1642,'2024/05/28 15:50:26',0,NULL,NULL,NULL),(160,16,12,100,NULL,1,NULL,NULL,NULL),(161,16,12,50,NULL,2,NULL,NULL,NULL),(162,16,12,100,NULL,3,NULL,NULL,NULL),(163,16,1,323,'2024/05/29 12:14:09',0,NULL,NULL,NULL),(164,16,5,1379,'2024/05/29 12:14:42',0,NULL,NULL,NULL),(165,16,4,394,'2024/05/29 12:18:43',0,NULL,NULL,NULL),(166,16,1,479,'2024/05/29 12:19:16',0,NULL,NULL,NULL),(167,1,10,0,'2024/06/05 21:47:56',1,NULL,NULL,NULL),(168,1,10,0,'2024/06/05 22:37:50',2,NULL,NULL,NULL),(169,1,10,123,'2024/06/05 22:59:21',1,110,100,'[\"111\", \"204\"]'),(170,1,10,123,'2024/06/05 23:01:27',2,110,90,'[\"99\",\"103\",\"107\"]'),(171,1,1,608,'2024/06/05 23:49:25',0,22,222,'[\"222\",\"333\",\"444\",\"333\",\"234\"]');
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
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tests`
--

LOCK TABLES `user_tests` WRITE;
/*!40000 ALTER TABLE `user_tests` DISABLE KEYS */;
INSERT INTO `user_tests` VALUES (106,16,1,1),(107,16,5,2),(108,16,4,0),(110,1,1,0);
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
  `invitation_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
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
INSERT INTO `users` VALUES (1,'Михаил','Мороз','moroz.mkhl@gmail.com','a1c62a848f2e67cb25a71ca72f6585e0',3,NULL,50,0),(16,'Михаил2','Мороз2','aaa@aaa.aa','a1c62a848f2e67cb25a71ca72f6585e0',2,NULL,45,1);
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

-- Dump completed on 2024-06-06 12:19:51
