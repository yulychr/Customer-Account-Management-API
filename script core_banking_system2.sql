CREATE DATABASE  IF NOT EXISTS `core_banking_system2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `core_banking_system2`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: core_banking_system2
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `type_account` enum('ahorros','corriente') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK66gkcp94endmotfwb8r4ocxm9` (`account_number`),
  KEY `FKnnwpo0lfq4xai1rs6887sx02k` (`customer_id`),
  CONSTRAINT `FKnnwpo0lfq4xai1rs6887sx02k` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,5,'317337',521.56,'ahorros'),(2,3,'658445',323.12,'corriente'),(3,1,'146504',893.78,'ahorros'),(4,8,'894558',547.64,'corriente'),(5,10,'244386',125.39,'ahorros'),(6,6,'588396',980.45,'corriente'),(7,4,'881139',634.22,'ahorros'),(8,2,'454483',295.76,'corriente'),(9,7,'625284',822.9,'ahorros'),(10,9,'406719',473.56,'corriente'),(11,5,'903574',500,'ahorros');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dni` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9jf7jfr0lltn86gmjn14l71d8` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'12345678','carlos.martinez@gmail.com','Carlos Armando','Martinez Cotrado'),(2,'87654321','ana.perez@hotmail.com','Ana','Pérez'),(3,'11223344','luis.gonzalez@yahoo.com','Luis','González'),(4,'22334455','maria.rodriguez@outlook.com','María','Rodríguez'),(5,'33445566','jose.hernandez@gmail.com','José','Hernández'),(6,'44556677','patricia.lopez@icloud.com','Patricia','López'),(7,'55667788','juan.garcia@aol.com','Juan','García'),(8,'66778899','elena.sanchez@outlook.com','Elena','Sánchez'),(9,'77889900','pedro.torres@hotmail.com','Pedro','Torres'),(10,'88990011','marta.vazquez@gmail.com','Marta','Vázquez');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-18 11:51:31
