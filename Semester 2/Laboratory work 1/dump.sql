-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (x86_64)
--
-- Host: 127.0.0.1    Database: pvk
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
-- Table structure for table `professions`
--

DROP TABLE IF EXISTS `professions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_general_ci,
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
INSERT INTO `professions` VALUES (1,'Dev Ops','DevOps - это специалист, комбинирующий процессы разработки и эксплуатации системы, который отвечает за автоматизацию и интеграцию процессов разработки, тестирования и развертывания приложений.',0),(3,'Тестировщик','Тестировщик - это профессионал, занимающийся проверкой и анализом программного обеспечения, выявляющий и исправляющий ошибки, дефекты и недочеты для обеспечения надежности и качества работы приложений и программ.',0),(5,'Веб-разработчик','Веб-разработчик - это эксперт, создающий веб-приложения, используя языки программирования, такие как HTML, CSS, JavaScript, и инструменты для разработки функционала и визуального оформления.',0);
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
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profession_pvk`
--

LOCK TABLES `user_profession_pvk` WRITE;
/*!40000 ALTER TABLE `user_profession_pvk` DISABLE KEYS */;
INSERT INTO `user_profession_pvk` VALUES (1,3,18,62,100),(1,3,38,63,80),(1,3,73,64,30),(14,1,4,72,30),(14,1,13,73,40),(14,1,8,74,50),(14,1,12,75,60),(14,1,23,76,20),(1,1,4,77,50),(1,1,8,78,50),(1,1,13,79,90),(1,1,23,80,60);
/*!40000 ALTER TABLE `user_profession_pvk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `second_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Михаил','Мороз','moroz.mkhl@gmail.com','a1c62a848f2e67cb25a71ca72f6585e0',3),(14,'Михаил2','Мороз2','bigmichael1922@gmail.com','a1c62a848f2e67cb25a71ca72f6585e0',3);
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

-- Dump completed on 2024-02-28 21:51:48
