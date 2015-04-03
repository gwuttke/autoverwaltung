-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 03. Apr 2015 um 11:46
-- Server Version: 5.5.40-0ubuntu0.14.04.1
-- PHP-Version: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

--
-- Datenbank: `auto`
--

--
-- Daten für Tabelle `Benzinart`
--

INSERT INTO `Benzinart` VALUES
(1, 'Super'),
(2, 'Super E10'),
(4, 'Diesel');

--
-- Daten für Tabelle `Land`
--

INSERT INTO `Land` VALUES
(1, 'keine Angabe'),
(2, 'Deutschland'),
(3, 'Österreich'),
(4, 'Italien'),
(5, 'Polen');

--
-- Daten für Tabelle `Ort`
--

INSERT INTO `Ort` VALUES
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
(25, 'Taufkirchen');

--
-- Daten für Tabelle `Laenderorte`
--

INSERT INTO `Laenderorte` VALUES
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

--
-- Daten für Tabelle `Tank`
--

INSERT INTO `Tank` VALUES
(1, '1/4'),
(2, '1/2'),
(3, '3/4'),
(4, '4/4');

--
-- Daten für Tabelle `Version`
--

INSERT INTO `Version` VALUES
(1, 12, 'Desktop');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
