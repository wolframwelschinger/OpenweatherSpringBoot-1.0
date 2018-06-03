-- MySQL dump 10.13  Distrib 5.1.73, for redhat-linux-gnu (x86_64)
--
-- Host: localhost    Database: openweather
-- ------------------------------------------------------
-- Server version	5.1.73

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
-- Table structure for table `ort`
--

DROP TABLE IF EXISTS `ort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ort` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `land` varchar(50) DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `url_openweather_ort_aktuell` varchar(255) DEFAULT NULL,
  `url_openweather_ort_vorhersage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wetteraufzeichnung`
--

DROP TABLE IF EXISTS `wetteraufzeichnung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wetteraufzeichnung` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `geo_breite` double DEFAULT NULL,
  `geo_laenge` double DEFAULT NULL,
  `icon_url` varchar(255) DEFAULT NULL,
  `land` varchar(50) DEFAULT NULL,
  `luftdruck` double DEFAULT NULL,
  `luftfeuchtigkeit` double DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `temperatur` bigint(20) DEFAULT NULL,
  `temperatur_max` bigint(20) DEFAULT NULL,
  `temperatur_min` bigint(20) DEFAULT NULL,
  `beschreibung` varchar(255) DEFAULT NULL,
  `windgeschwindigkeit` double DEFAULT NULL,
  `windrichtung` double DEFAULT NULL,
  `wolken` bigint(20) DEFAULT NULL,
  `zeitstempel` bigint(20) DEFAULT NULL,
  `zeitstempel_string` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3985 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-03 17:11:43
