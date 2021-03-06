-- MySQL dump 10.13  Distrib 5.6.16, for Win32 (x86)
--
-- Host: localhost    Database: auto
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `auto`
--

DROP TABLE IF EXISTS `auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `erstZulassung` date DEFAULT NULL,
  `kauf` date DEFAULT NULL,
  `kfz` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  `kmAktuell` int(11) NOT NULL,
  `kmKauf` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto`
--

LOCK TABLES `auto` WRITE;
/*!40000 ALTER TABLE `auto` DISABLE KEYS */;
/*!40000 ALTER TABLE `auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auto_benutzer`
--

DROP TABLE IF EXISTS `auto_benutzer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auto_benutzer` (
  `idBenutzer` int(11) NOT NULL,
  `idAuto` int(11) NOT NULL,
  KEY `FK_1654he0aa2sl04fqxl5j0stj` (`idAuto`),
  KEY `FK_s5axsk1wf0oua1oq6j7i2xfty` (`idBenutzer`),
  CONSTRAINT `FK_s5axsk1wf0oua1oq6j7i2xfty` FOREIGN KEY (`idBenutzer`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_1654he0aa2sl04fqxl5j0stj` FOREIGN KEY (`idAuto`) REFERENCES `auto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto_benutzer`
--

LOCK TABLES `auto_benutzer` WRITE;
/*!40000 ALTER TABLE `auto_benutzer` DISABLE KEYS */;
/*!40000 ALTER TABLE `auto_benutzer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auto_benzinart`
--

DROP TABLE IF EXISTS `auto_benzinart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auto_benzinart` (
  `idAuto` int(11) NOT NULL,
  `idBenzinart` int(11) NOT NULL,
  PRIMARY KEY (`idAuto`,`idBenzinart`),
  KEY `FK_8ho9umuhcax2rtt2slgv223es` (`idBenzinart`),
  CONSTRAINT `FK_ecuidvpx26xkm8aoo5h3j00ju` FOREIGN KEY (`idAuto`) REFERENCES `auto` (`id`),
  CONSTRAINT `FK_8ho9umuhcax2rtt2slgv223es` FOREIGN KEY (`idBenzinart`) REFERENCES `benzinart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto_benzinart`
--

LOCK TABLES `auto_benzinart` WRITE;
/*!40000 ALTER TABLE `auto_benzinart` DISABLE KEYS */;
/*!40000 ALTER TABLE `auto_benzinart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `benutzer`
--

DROP TABLE IF EXISTS `benutzer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `benutzer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  `passwort` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r41gjsqbn8sl31wvqnj6y5678` (`name`,`passwort`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `benutzer`
--

LOCK TABLES `benutzer` WRITE;
/*!40000 ALTER TABLE `benutzer` DISABLE KEYS */;
INSERT INTO `benutzer` VALUES (1,'Georg','123');
/*!40000 ALTER TABLE `benutzer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `benzinart`
--

DROP TABLE IF EXISTS `benzinart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `benzinart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `benzinart`
--

LOCK TABLES `benzinart` WRITE;
/*!40000 ALTER TABLE `benzinart` DISABLE KEYS */;
INSERT INTO `benzinart` VALUES (1,'Super'),(2,'Super E10'),(3,'Diesel');
/*!40000 ALTER TABLE `benzinart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laenderorte`
--

DROP TABLE IF EXISTS `laenderorte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laenderorte` (
  `idLand` int(11) NOT NULL,
  `idOrt` int(11) NOT NULL,
  PRIMARY KEY (`idLand`,`idOrt`),
  UNIQUE KEY `UK_4bp7uqdxsi8ahr02s1nfiff7f` (`idOrt`),
  CONSTRAINT `FK_b54oqs02r08in449ywo2tcwrc` FOREIGN KEY (`idLand`) REFERENCES `land` (`id`),
  CONSTRAINT `FK_4bp7uqdxsi8ahr02s1nfiff7f` FOREIGN KEY (`idOrt`) REFERENCES `ort` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laenderorte`
--

LOCK TABLES `laenderorte` WRITE;
/*!40000 ALTER TABLE `laenderorte` DISABLE KEYS */;
INSERT INTO `laenderorte` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `laenderorte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `land`
--

DROP TABLE IF EXISTS `land`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `land` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `land`
--

LOCK TABLES `land` WRITE;
/*!40000 ALTER TABLE `land` DISABLE KEYS */;
INSERT INTO `land` VALUES (1,'Deutschland'),(2,'Österreich'),(3,'Italien'),(4,'Polen');
/*!40000 ALTER TABLE `land` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ort`
--

DROP TABLE IF EXISTS `ort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ort` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ort` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ort`
--

LOCK TABLES `ort` WRITE;
/*!40000 ALTER TABLE `ort` DISABLE KEYS */;
INSERT INTO `ort` VALUES (1,'München'),(2,'Neuried');
/*!40000 ALTER TABLE `ort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sonstigeausgaben`
--

DROP TABLE IF EXISTS `sonstigeausgaben`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sonstigeausgaben` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `kmStand` int(11) NOT NULL,
  `kommentar` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  `kosten` decimal(19,2) DEFAULT NULL,
  `auto_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_g41s6hfaebmw0itevbb5bgrso` (`auto_id`),
  CONSTRAINT `FK_937bupwxucp5ltjmxplv5mjbo` FOREIGN KEY (`Id`) REFERENCES `auto` (`id`),
  CONSTRAINT `FK_g41s6hfaebmw0itevbb5bgrso` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sonstigeausgaben`
--

LOCK TABLES `sonstigeausgaben` WRITE;
/*!40000 ALTER TABLE `sonstigeausgaben` DISABLE KEYS */;
/*!40000 ALTER TABLE `sonstigeausgaben` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tank`
--

DROP TABLE IF EXISTS `tank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `beschreibung` varchar(255) COLLATE latin1_german1_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tank`
--

LOCK TABLES `tank` WRITE;
/*!40000 ALTER TABLE `tank` DISABLE KEYS */;
INSERT INTO `tank` VALUES (1,'1/4'),(2,'1/2'),(3,'3/4'),(4,'1/1');
/*!40000 ALTER TABLE `tank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tanken`
--

DROP TABLE IF EXISTS `tanken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tanken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum` datetime DEFAULT NULL,
  `kmStand` int(11) NOT NULL,
  `kosten` decimal(19,2) DEFAULT NULL,
  `liter` decimal(19,2) DEFAULT NULL,
  `preisProLiter` decimal(19,2) DEFAULT NULL,
  `auto_id` int(11) DEFAULT NULL,
  `benzinArt_id` int(11) DEFAULT NULL,
  `land_id` int(11) DEFAULT NULL,
  `ort_id` int(11) DEFAULT NULL,
  `tank_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ocqfvjobcxmdf58bcpplg15p2` (`auto_id`),
  KEY `FK_7taqcdgdmi72of12btsjs3moq` (`benzinArt_id`),
  KEY `FK_fxbrofpqod3ticax5yl08gr4p` (`land_id`),
  KEY `FK_g4wuu74alnaqunxnugr9fcgo0` (`ort_id`),
  KEY `FK_bj6fca1xx6tc6c2sqf8ocbc1` (`tank_id`),
  CONSTRAINT `FK_bj6fca1xx6tc6c2sqf8ocbc1` FOREIGN KEY (`tank_id`) REFERENCES `tank` (`id`),
  CONSTRAINT `FK_7taqcdgdmi72of12btsjs3moq` FOREIGN KEY (`benzinArt_id`) REFERENCES `benzinart` (`id`),
  CONSTRAINT `FK_fxbrofpqod3ticax5yl08gr4p` FOREIGN KEY (`land_id`) REFERENCES `land` (`id`),
  CONSTRAINT `FK_g4wuu74alnaqunxnugr9fcgo0` FOREIGN KEY (`ort_id`) REFERENCES `ort` (`id`),
  CONSTRAINT `FK_ocqfvjobcxmdf58bcpplg15p2` FOREIGN KEY (`auto_id`) REFERENCES `auto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tanken`
--

LOCK TABLES `tanken` WRITE;
/*!40000 ALTER TABLE `tanken` DISABLE KEYS */;
/*!40000 ALTER TABLE `tanken` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-21  8:38:49
