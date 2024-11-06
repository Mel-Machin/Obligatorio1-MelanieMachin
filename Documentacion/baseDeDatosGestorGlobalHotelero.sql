-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: gestorglobalhotelero
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `cama`
--

DROP TABLE IF EXISTS `cama`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cama` (
  `codCama` int NOT NULL AUTO_INCREMENT,
  `tipoCama` varchar(50) DEFAULT NULL,
  `idHabitacion` int DEFAULT NULL,
  PRIMARY KEY (`codCama`),
  KEY `fk_cama_habitacion` (`idHabitacion`),
  CONSTRAINT `cama_ibfk_1` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`),
  CONSTRAINT `fk_cama_habitacion` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cama`
--

LOCK TABLES `cama` WRITE;
/*!40000 ALTER TABLE `cama` DISABLE KEYS */;
INSERT INTO `cama` VALUES (1,'King',1),(2,'Queen',2),(3,'Individual',3),(4,'Doble',4),(5,'Twin',5),(6,'Individual',NULL),(7,'Doble',NULL),(8,'Queen',NULL),(9,'King',NULL),(10,'Sofa cama',NULL),(11,'King',10),(12,'King',10),(13,'Matrimonial',11),(14,'Redonda',12);
/*!40000 ALTER TABLE `cama` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caracteristica`
--

DROP TABLE IF EXISTS `caracteristica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caracteristica` (
  `codigoCaracteristica` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `idHabitacion` int DEFAULT NULL,
  PRIMARY KEY (`codigoCaracteristica`),
  KEY `fk_caracteristica_habitacion` (`idHabitacion`),
  CONSTRAINT `caracteristica_ibfk_1` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`),
  CONSTRAINT `fk_caracteristica_habitacion` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caracteristica`
--

LOCK TABLES `caracteristica` WRITE;
/*!40000 ALTER TABLE `caracteristica` DISABLE KEYS */;
INSERT INTO `caracteristica` VALUES (1,'WiFi','Servicios',1),(2,'Aire Acondicionado','Servicios',1),(3,'Cama King','Servicios',2),(4,'Desayuno Incluido','Servicios',3),(5,'Estacionamiento','Servicios',1),(6,'Vista al mar','Extra',NULL),(7,'Televisión','Comodidad',NULL),(8,'Wi-Fi','Conectividad',NULL),(9,'Aire acondicionado','Clima',NULL),(10,'Minibar','Comodidad',NULL),(11,'Mini bar','Servicios de cortesía',12);
/*!40000 ALTER TABLE `caracteristica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `codigoPostal` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `codigoPais` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`codigoPostal`),
  KEY `fk_ciudad_pais` (`codigoPais`),
  CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`codigoPais`) REFERENCES `pais` (`codigoISO`),
  CONSTRAINT `fk_ciudad_pais` FOREIGN KEY (`codigoPais`) REFERENCES `pais` (`codigoISO`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES ('1000','Buenos Aires','ARG'),('110111','Bogotá','COL'),('2000','Rio de Janeiro','BRA'),('70000','Colonia del Sacramento','URY'),('8320000','Santiago','CHI');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitacion` (
  `idHabitacion` int NOT NULL AUTO_INCREMENT,
  `nroHabitacion` int DEFAULT NULL,
  `idHotel` int DEFAULT NULL,
  `idTipoHabitacion` int DEFAULT NULL,
  PRIMARY KEY (`idHabitacion`),
  UNIQUE KEY `unique_hotel_habitacion` (`idHotel`,`nroHabitacion`),
  KEY `fk_habitacion_tipohabitacion` (`idTipoHabitacion`),
  CONSTRAINT `fk_habitacion_hotel` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`idHotel`) ON DELETE CASCADE,
  CONSTRAINT `fk_habitacion_tipohabitacion` FOREIGN KEY (`idTipoHabitacion`) REFERENCES `tipohabitacion` (`idTipoHabitacion`) ON DELETE CASCADE,
  CONSTRAINT `habitacion_ibfk_1` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`idHotel`),
  CONSTRAINT `habitacion_ibfk_2` FOREIGN KEY (`idTipoHabitacion`) REFERENCES `tipohabitacion` (`idTipoHabitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (1,101,1,1),(2,102,1,2),(3,201,2,1),(4,202,2,3),(5,301,3,2),(6,401,3,4),(8,203,4,4),(9,103,4,2),(10,504,5,3),(11,506,2,3),(12,421,3,5);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitacionreserva`
--

DROP TABLE IF EXISTS `habitacionreserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitacionreserva` (
  `idHabitacion` int NOT NULL,
  `idReserva` int NOT NULL,
  PRIMARY KEY (`idHabitacion`,`idReserva`),
  KEY `idReserva` (`idReserva`),
  CONSTRAINT `habitacionreserva_ibfk_1` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`),
  CONSTRAINT `habitacionreserva_ibfk_2` FOREIGN KEY (`idReserva`) REFERENCES `reserva` (`idReserva`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacionreserva`
--

LOCK TABLES `habitacionreserva` WRITE;
/*!40000 ALTER TABLE `habitacionreserva` DISABLE KEYS */;
INSERT INTO `habitacionreserva` VALUES (1,1),(2,1),(1,2),(2,10),(1,11),(3,12),(4,13),(11,14),(2,15),(9,16);
/*!40000 ALTER TABLE `habitacionreserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `idHotel` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `codigoCiudad` varchar(10) DEFAULT NULL,
  `cantidadEstrellas` int DEFAULT NULL,
  PRIMARY KEY (`idHotel`),
  KEY `fk_hotel_ciudad` (`codigoCiudad`),
  CONSTRAINT `fk_hotel_ciudad` FOREIGN KEY (`codigoCiudad`) REFERENCES `ciudad` (`codigoPostal`) ON DELETE CASCADE,
  CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`codigoCiudad`) REFERENCES `ciudad` (`codigoPostal`),
  CONSTRAINT `chk_cantidadEstrellas` CHECK ((`cantidadEstrellas` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Hotel Buenos Aires','1000',4),(2,'Hotel Santiago','8320000',5),(3,'Hotel Río','2000',3),(4,'Hotel Bogotá','110111',4),(5,'Hotel Colonia','70000',4);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `huesped`
--

DROP TABLE IF EXISTS `huesped`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `huesped` (
  `idHuesped` int NOT NULL AUTO_INCREMENT,
  `tipoDocumento` int DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `primerApellido` varchar(100) DEFAULT NULL,
  `segundoApellido` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idHuesped`),
  KEY `fk_huesped_tipodocumento` (`tipoDocumento`),
  CONSTRAINT `fk_huesped_tipodocumento` FOREIGN KEY (`tipoDocumento`) REFERENCES `tipodocumento` (`idTipoDocumento`) ON DELETE CASCADE,
  CONSTRAINT `huesped_ibfk_1` FOREIGN KEY (`tipoDocumento`) REFERENCES `tipodocumento` (`idTipoDocumento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `huesped`
--

LOCK TABLES `huesped` WRITE;
/*!40000 ALTER TABLE `huesped` DISABLE KEYS */;
INSERT INTO `huesped` VALUES (1,1,'Juan','Pérez','Gómez','123456789'),(2,3,'María','Rodríguez','López','987654321'),(3,5,'Carlos','García','Martínez','234567890'),(4,4,'Ana','Sánchez','Fernández','345678901'),(5,2,'Pedro','Martínez','González','456789012');
/*!40000 ALTER TABLE `huesped` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `codigoISO` varchar(5) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`codigoISO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES ('ARG','Argentina'),('BRA','Brasil'),('CHI','Chile'),('COL','Colombia'),('URY','Uruguay');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registroocupacion`
--

DROP TABLE IF EXISTS `registroocupacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registroocupacion` (
  `idOcupacion` int NOT NULL AUTO_INCREMENT,
  `medioRegistro` varchar(30) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `idHabitacion` int DEFAULT NULL,
  PRIMARY KEY (`idOcupacion`),
  KEY `fk_registroocupacion_habitacion` (`idHabitacion`),
  CONSTRAINT `fk_registroocupacion_habitacion` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`) ON DELETE CASCADE,
  CONSTRAINT `registroocupacion_ibfk_1` FOREIGN KEY (`idHabitacion`) REFERENCES `habitacion` (`idHabitacion`),
  CONSTRAINT `chk_medioRegistro` CHECK ((`medioRegistro` in (_utf8mb4'sistema',_utf8mb4'fuera del sistema')))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registroocupacion`
--

LOCK TABLES `registroocupacion` WRITE;
/*!40000 ALTER TABLE `registroocupacion` DISABLE KEYS */;
INSERT INTO `registroocupacion` VALUES (1,'Sistema',1,1),(2,'Fuera del Sistema',0,2),(3,'Sistema',1,3),(5,'Fuera del Sistema',0,5);
/*!40000 ALTER TABLE `registroocupacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `idReserva` int NOT NULL AUTO_INCREMENT,
  `idHuesped` int DEFAULT NULL,
  `idHotel` int DEFAULT NULL,
  `idTarifa` int DEFAULT NULL,
  `estadoPago` varchar(50) DEFAULT NULL,
  `fechaReserva` date DEFAULT NULL,
  `fechaCheckIn` date DEFAULT NULL,
  `estadoCheckIn` varchar(50) DEFAULT NULL,
  `fechaCheckOut` date DEFAULT NULL,
  `estadoCheckOut` varchar(50) DEFAULT NULL,
  `cantidadPersonas` int DEFAULT NULL,
  `observacion` text,
  PRIMARY KEY (`idReserva`),
  KEY `fk_reserva_huesped` (`idHuesped`),
  KEY `fk_reserva_hotel` (`idHotel`),
  KEY `fk_reserva_tarifa` (`idTarifa`),
  CONSTRAINT `fk_reserva_hotel` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`idHotel`) ON DELETE CASCADE,
  CONSTRAINT `fk_reserva_huesped` FOREIGN KEY (`idHuesped`) REFERENCES `huesped` (`idHuesped`) ON DELETE CASCADE,
  CONSTRAINT `fk_reserva_tarifa` FOREIGN KEY (`idTarifa`) REFERENCES `tarifa` (`idTarifa`) ON DELETE CASCADE,
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`idHuesped`) REFERENCES `huesped` (`idHuesped`),
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`idHotel`),
  CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`idTarifa`) REFERENCES `tarifa` (`idTarifa`),
  CONSTRAINT `chk_estadoCheckIn` CHECK ((`estadoCheckIn` in (_utf8mb4'pendiente',_utf8mb4'realizado'))),
  CONSTRAINT `chk_estadoCheckOut` CHECK ((`estadoCheckOut` in (_utf8mb4'pendiente',_utf8mb4'realizado'))),
  CONSTRAINT `chk_estadoPago` CHECK ((`estadoPago` in (_utf8mb4'pendiente',_utf8mb4'realizado')))
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,1,1,1,'realizado','2023-10-01','2024-01-05','realizado','2024-01-10','realizado',1,'Ninguna'),(2,2,2,2,'Pendiente','2023-10-02','2024-02-01','pendiente','2024-02-10','pendiente',2,'Requiere habitación con vista'),(3,3,3,3,'realizado','2023-10-03','2024-03-01','realizado','2024-03-05','realizado',1,'Solicita cama adicional'),(4,4,4,4,'realizado','2023-10-04','2024-04-01','realizado','2024-04-05','realizado',4,'Ninguna'),(5,5,5,5,'Pendiente','2023-10-05','2024-05-01','pendiente','2024-05-05','pendiente',3,'Preferencia de cama king'),(7,2,1,NULL,'pendiente','0009-05-16','0016-06-15','pendiente','0021-06-15','pendiente',2,'s'),(8,3,1,NULL,'pendiente','0010-05-17','0016-06-15','pendiente','0022-06-16','pendiente',1,'l'),(9,2,1,NULL,'pendiente','2024-11-04','2024-12-06','pendiente','2024-12-10','pendiente',2,'Ho'),(10,3,1,NULL,'pendiente','2024-11-03','2024-12-06','pendiente','2024-12-10','pendiente',2,'t'),(11,2,1,NULL,'pendiente','2024-12-05','2024-12-06','pendiente','2024-12-10','pendiente',2,'g'),(12,2,2,NULL,'realizado','2024-12-05','2024-12-06','pendiente','2024-12-10','pendiente',2,'a'),(13,2,2,NULL,'pendiente','2024-12-05','2024-12-06','pendiente','2024-12-12','pendiente',2,'d'),(14,2,2,NULL,'pendiente','2024-12-05','2024-12-06','pendiente','2024-12-10','pendiente',3,'d'),(15,2,1,NULL,'pendiente','2024-11-01','2024-12-01','pendiente','2024-12-04','pendiente',2,'f'),(16,4,4,NULL,'pendiente','2024-01-01','2024-01-01','pendiente','2024-01-01','pendiente',2,'j');
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarifa` (
  `idTarifa` int NOT NULL AUTO_INCREMENT,
  `fechaInicio` date NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `idTipoHabitacion` int DEFAULT NULL,
  `vigente` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`idTarifa`),
  KEY `fk_tarifa_tipohabitacion` (`idTipoHabitacion`),
  CONSTRAINT `fk_tarifa_tipohabitacion` FOREIGN KEY (`idTipoHabitacion`) REFERENCES `tipohabitacion` (`idTipoHabitacion`) ON DELETE CASCADE,
  CONSTRAINT `tarifa_ibfk_1` FOREIGN KEY (`idTipoHabitacion`) REFERENCES `tipohabitacion` (`idTipoHabitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES (1,'2024-01-01',100.00,1,1),(2,'2024-01-01',150.00,2,1),(3,'2024-01-01',250.00,3,1),(4,'2024-01-01',300.00,4,1),(5,'2024-01-01',200.00,5,1),(6,'2024-11-04',350.00,3,1);
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodocumento`
--

DROP TABLE IF EXISTS `tipodocumento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipodocumento` (
  `idTipoDocumento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `identificador` varchar(50) DEFAULT NULL,
  `abreviatura` varchar(10) DEFAULT NULL,
  `codigoPaisOrigen` varchar(5) DEFAULT NULL,
  `fechaEmitido` date DEFAULT NULL,
  `fechaVencimiento` date DEFAULT NULL,
  PRIMARY KEY (`idTipoDocumento`),
  KEY `fk_tipodocumento_pais` (`codigoPaisOrigen`),
  CONSTRAINT `fk_tipodocumento_pais` FOREIGN KEY (`codigoPaisOrigen`) REFERENCES `pais` (`codigoISO`) ON DELETE CASCADE,
  CONSTRAINT `tipodocumento_ibfk_1` FOREIGN KEY (`codigoPaisOrigen`) REFERENCES `pais` (`codigoISO`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodocumento`
--

LOCK TABLES `tipodocumento` WRITE;
/*!40000 ALTER TABLE `tipodocumento` DISABLE KEYS */;
INSERT INTO `tipodocumento` VALUES (1,'Documento Nacional de Identidad','12345678','DNI','ARG','2020-01-01','2030-01-01'),(2,'Tarjeta de Identidad','A1234567','TI','COL','2021-03-01','2031-03-01'),(3,'Pasaporte','P87654321','PP','ARG','2022-06-15','2032-06-15'),(4,'Cédula de Identidad','12345678','CI','URY','2022-02-15','2032-02-15'),(5,'Cédula de Identidad','100000001','CI','CHI','2023-02-15','2033-02-15');
/*!40000 ALTER TABLE `tipodocumento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipohabitacion`
--

DROP TABLE IF EXISTS `tipohabitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipohabitacion` (
  `idTipoHabitacion` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`idTipoHabitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipohabitacion`
--

LOCK TABLES `tipohabitacion` WRITE;
/*!40000 ALTER TABLE `tipohabitacion` DISABLE KEYS */;
INSERT INTO `tipohabitacion` VALUES (1,'Habitación Simple','Una habitación para una sola persona.'),(2,'Habitación Doble','Habitación para dos personas.'),(3,'Suite','Habitación de lujo con más comodidades.'),(4,'Habitación Familiar','Habitación para familias.'),(5,'Habitación Ejecutiva','Habitación con servicios ejecutivos.');
/*!40000 ALTER TABLE `tipohabitacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-05 23:54:11
