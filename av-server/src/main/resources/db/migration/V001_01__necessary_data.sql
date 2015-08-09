CREATE DATABASE  IF NOT EXISTS `auto` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `auto`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: auto
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Dumping data for table `kraftstoffsorte`
--

LOCK TABLES `kraftstoffsorte` WRITE;
/*!40000 ALTER TABLE `kraftstoffsorte` DISABLE KEYS */;
INSERT INTO `kraftstoffsorte` VALUES (1,'Super'),(2,'Super E10'),(3,'Diesel'),(4,'kWh');
/*!40000 ALTER TABLE `kraftstoffsorte` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `kraftstoff`
--

LOCK TABLES `kraftstoff` WRITE;
/*!40000 ALTER TABLE `kraftstoff` DISABLE KEYS */;
INSERT INTO `kraftstoff` VALUES (1,'Benzin'),(2,'Diesel'),(3,'Elektro');
/*!40000 ALTER TABLE `kraftstoff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tank`
--

LOCK TABLES `tank` WRITE;
/*!40000 ALTER TABLE `tank` DISABLE KEYS */;
INSERT INTO `tank` VALUES (1,'1/4'),(2,'1/2'),(3,'3/4'),(4,'4/4');
/*!40000 ALTER TABLE `tank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `kraftstoff_kraftstoffsorte`
--

LOCK TABLES `kraftstoff_kraftstoffsorte` WRITE;
/*!40000 ALTER TABLE `kraftstoff_kraftstoffsorte` DISABLE KEYS */;
INSERT INTO `kraftstoff_kraftstoffsorte` VALUES (1,1),(1,2),(2,3),(3,4);
/*!40000 ALTER TABLE `kraftstoff_kraftstoffsorte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tanken`
--

LOCK TABLES `tanken` WRITE;
/*!40000 ALTER TABLE `tanken` DISABLE KEYS */;
/*!40000 ALTER TABLE `tanken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role_autoritaet`
--

LOCK TABLES `role_autoritaet` WRITE;
/*!40000 ALTER TABLE `role_autoritaet` DISABLE KEYS */;
INSERT INTO `role_autoritaet` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `role_autoritaet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `version`
--

LOCK TABLES `version` WRITE;
/*!40000 ALTER TABLE `version` DISABLE KEYS */;
INSERT INTO `version` VALUES (1,12,'Desktop');
/*!40000 ALTER TABLE `version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `autoritaet`
--

LOCK TABLES `autoritaet` WRITE;
/*!40000 ALTER TABLE `autoritaet` DISABLE KEYS */;
INSERT INTO `autoritaet` VALUES (2,'ADMIN'),(1,'USER');
/*!40000 ALTER TABLE `autoritaet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `benutzer`
--

LOCK TABLES `benutzer` WRITE;
/*!40000 ALTER TABLE `benutzer` DISABLE KEYS */;

INSERT INTO `benutzer` VALUES 
(1, 'Gechlire', 'lrwuttke@aol.com', 'Wuttke', 'Georg1!', 'Reinhard',1),
(2, 'georg.wuttke', 'georg1wuttke@gmail.com', 'Wuttke', '12345', 'Georg', 2);
/*!40000 ALTER TABLE `benutzer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `benutzer_role`
--

LOCK TABLES `benutzer_role` WRITE;
/*!40000 ALTER TABLE `benutzer_role` DISABLE KEYS */;
INSERT INTO `benutzer_role` VALUES 
(1,2),
(2,1);
/*!40000 ALTER TABLE `benutzer_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Daten für Tabelle `Land`
--

INSERT INTO `Land` (`id`, `name`) VALUES
(1, 'keine Angabe'),
(2, 'Deutschland'),
(3, 'Österreich'),
(4, 'Italien'),
(5, 'Polen'),
(6, 'Schweden');

--
-- Daten für Tabelle `Ort`
--

INSERT INTO `Ort` (`id`, `ort`) VALUES
(1, 'München'),
(2, 'Neuried'),
(3, 'keine Angabe'),
(4, 'Aschheim'),
(5, 'Dietesheim'),
(6, 'Annaberg'),
(7, 'Walchsee'),
(8, 'Oberschleißheim'),
(9, 'Innsbruck'),
(10, 'San Gregorio Magno'),
(11, 'Neapel'),
(12, 'Altenmarkt'),
(13, 'Bayreuth'),
(14, 'Pama'),
(15, 'Casino'),
(16, 'Modena'),
(17, 'Würzburg'),
(18, 'Nürnberg'),
(19, 'Hausham'),
(20, 'Frankfurt am Main'),
(21, 'Koblenz'),
(22, 'Andernach'),
(23, 'Friedrichshafen'),
(24, 'Heiligenberg'),
(25, 'Taufkirchen'),
(26, 'Kaufbeuern');

--
-- Daten für Tabelle `Laenderorte`
--

INSERT INTO `Laenderorte` (`idland`, `idort`) VALUES
(2, 1),
(2, 2),
(2, 4),
(2, 5),
(3, 6),
(3, 7),
(2, 8),
(3, 9),
(4, 10),
(4, 11),
(2, 12),
(2, 13),
(4, 14),
(3, 15),
(4, 16),
(2, 17),
(2, 18),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(2, 23),
(2, 24),
(2, 25);





/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-07 19:41:34
