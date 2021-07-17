-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: gestion_laboratorio
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos` (
  `id` int NOT NULL,
  `estado` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES (1,'Ocupado'),(2,'Disponible'),(3,'Disponible'),(4,'Ocupado'),(5,'Disponible'),(6,'Disponible'),(7,'Disponible'),(8,'Disponible'),(9,'Disponible'),(10,'Disponible'),(11,'Disponible'),(12,'Disponible');
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipos_problemas`
--

DROP TABLE IF EXISTS `equipos_problemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos_problemas` (
  `id` int NOT NULL,
  `ID_problema` int NOT NULL,
  `ID_equipo` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_equipo` (`ID_equipo`),
  KEY `ID_problema` (`ID_problema`),
  CONSTRAINT `equipos_problemas_ibfk_1` FOREIGN KEY (`ID_equipo`) REFERENCES `equipos` (`id`),
  CONSTRAINT `equipos_problemas_ibfk_2` FOREIGN KEY (`ID_problema`) REFERENCES `problemas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos_problemas`
--

LOCK TABLES `equipos_problemas` WRITE;
/*!40000 ALTER TABLE `equipos_problemas` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipos_problemas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiantes`
--

DROP TABLE IF EXISTS `estudiantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiantes` (
  `codigo` int NOT NULL,
  `nombre_comple` varchar(150) NOT NULL,
  `carrera` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiantes`
--

LOCK TABLES `estudiantes` WRITE;
/*!40000 ALTER TABLE `estudiantes` DISABLE KEYS */;
INSERT INTO `estudiantes` VALUES (1,'Dela','Ing.Sistemas','dmcilwraith0@economist.com'),(4,'Elba Maria Herrera Matson','Ing.Quimica','jaronstam8@taobao.com'),(221820014,'Carlos Samuel Cuadrado Torres','Ing.Sistemas','ccuadradot@unicartagena.edu.co'),(221820047,'Andres David Hurtado Luna','Ing.Sistemas','gwesgate1@sitemeter.com');
/*!40000 ALTER TABLE `estudiantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `id` int NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime NOT NULL,
  `codigo_estudiante` int DEFAULT NULL,
  `ID_equipo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ID_equipo` (`ID_equipo`),
  KEY `codigo_estudiante` (`codigo_estudiante`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`ID_equipo`) REFERENCES `equipos` (`id`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`codigo_estudiante`) REFERENCES `estudiantes` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (1,'2021-01-27 08:29:22','2021-01-27 08:29:22',221820014,1),(4,'2021-01-27 08:29:22','2021-01-27 08:29:22',1,4);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problemas`
--

DROP TABLE IF EXISTS `problemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problemas` (
  `id` int NOT NULL,
  `componente_dañado` varchar(50) DEFAULT NULL,
  `detalles` text,
  `causas` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problemas`
--

LOCK TABLES `problemas` WRITE;
/*!40000 ALTER TABLE `problemas` DISABLE KEYS */;
/*!40000 ALTER TABLE `problemas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-17 16:20:26
