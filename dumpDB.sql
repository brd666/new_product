-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: new_product
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Иван','ivanvamvrot@ex.com'),(2,'Алексей','alex@ex.com'),(3,'Kirill','mr0skif@gmail.com'),(4,'Yulia','yulipoli@gmail.com'),(5,'Andrey','dontcalljustwrite@gmail.com'),(6,'Вика','krasotka@yandex.ru'),(7,'Анатолий','eduvantaliyu@yandex.ru'),(8,'Sergey','seOgey@yandex.ru'),(9,'Konstantin','314@yandex.ru'),(10,'Alex','alexOnder@gmail.com'),(11,'Timur','kissmyassbaby@gmail.com'),(12,'Senya','mnebibabu@gmail.com'),(13,'Seraphim','howchangename@gmail.com'),(14,'Seraphim Olegovich','olegovich@gmail.com'),(15,'Kurt Kobein','kobeinrulezzz@mail.ru'),(16,'Arnold Schwarzenegger','beru300NaGrud@yandex.ru'),(17,'Pi','31415926535@yandex.ru'),(18,'Ded Makar','uliboktebededmakar@rambler.ru'),(19,'Цырен Максаров','proebalrabotumechti@yandex.ru');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `product_id` int NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`product_id`,`order_id`),
  KEY `order_id` (`order_id`),
  KEY `quantity_index` (`quantity` DESC),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (6,17,1000),(7,17,1000),(7,13,100),(11,14,53),(4,6,15),(14,14,11),(1,12,5),(2,12,5),(3,12,5),(4,12,5),(5,12,5),(6,12,5),(9,2,5),(13,19,5),(16,16,5),(2,3,3),(3,3,3),(4,7,3),(5,7,3),(6,7,3),(7,7,3),(8,7,3),(8,8,3),(1,18,2),(2,18,2),(3,5,2),(3,18,2),(4,3,2),(4,18,2),(5,1,2),(5,18,2),(6,18,2),(7,18,2),(8,18,2),(9,18,2),(10,18,2),(11,18,2),(12,18,2),(13,18,2),(14,4,2),(14,18,2),(15,18,2),(16,18,2),(17,18,2),(1,1,1),(1,3,1),(1,10,1),(4,2,1),(7,10,1),(8,1,1),(8,11,1),(9,8,1),(10,9,1),(11,1,1),(11,11,1),(12,11,1),(14,2,1),(14,11,1),(15,2,1),(15,3,1),(15,4,1),(15,15,1),(16,2,1),(16,4,1),(16,15,1),(17,2,1),(17,4,1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL,
  `orders_date` date NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2022-06-01',1),(2,'2022-06-03',2),(3,'2022-06-04',4),(4,'2022-06-04',3),(5,'2022-06-04',5),(6,'2022-06-06',10),(7,'2022-06-06',17),(8,'2022-06-10',18),(9,'2022-06-11',16),(10,'2022-06-16',15),(11,'2022-06-17',6),(12,'2022-06-18',7),(13,'2022-06-19',8),(14,'2022-07-16',9),(15,'2022-08-16',11),(16,'2022-08-01',12),(17,'2022-08-02',13),(18,'2022-08-03',14),(19,'2022-08-04',11);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` int NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `price_index` (`price` DESC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'iphone 7',1100,'phone'),(2,'iphone 8',1200,'phone'),(3,'iphone 10',1300,'phone'),(4,'iphone 11',1400,'phone'),(5,'ipad',1400,'pad'),(6,'apple watch',500,'watch'),(7,'icar',55000,'car'),(8,'iclown',99999,'clown'),(9,'mac',2000,'pc'),(10,'EarPods Max',460,'headphones'),(11,'oneplus 10',1150,'phone'),(12,'xiaomi',500,'phone'),(13,'aoc35',1500,'monitor'),(14,'kivi65',1666,'TV'),(15,'uf232',120,'headphones'),(16,'logitech lightspeed',220,'mouse'),(17,'logitech g pro',250,'keyboard');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'new_product'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-13 13:48:56
