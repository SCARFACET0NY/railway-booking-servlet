-- CREATE USER 'anton'@'localhost' IDENTIFIED BY 'anton';
-- GRANT ALL PRIVILEGES ON * . * TO 'anton'@'localhost';
-- FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS railway_booking DEFAULT CHARACTER SET utf8;

USE railway_booking;

DROP TABLE IF EXISTS `locomotive`;

CREATE TABLE `locomotive` (
  `locomotive_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `manufacturing_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`locomotive_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `locomotive` WRITE;
INSERT INTO `locomotive` VALUES (1,'Alstrom','TGV POS',2006),(2,'Alstrom','TGV Atlantique',1989),(3,'Bombardier','Zefiro',2012),(4,'Siemens','Velaro',2007),(5,'Bombardier','ETR 400',2015),(6,'Siemens','ICE 3',2000),(7,'Nippon Sharyo','N700',2020),(8,'Hitachi','E 5',2011);
UNLOCK TABLES;

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_date` datetime(6) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK4spfnm9si9dowsatcqs5or42i` (`user_id`),
  CONSTRAINT `FK4spfnm9si9dowsatcqs5or42i` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

LOCK TABLES `payment` WRITE;
INSERT INTO `payment` VALUES (8,'2020-05-25 18:27:52.052921',1600.00,1),(12,'2020-05-25 21:32:21.555020',1800.00,1),(16,'2020-05-25 21:42:03.395707',1600.00,1),(17,'2020-06-08 11:21:55.205000',1200.00,1),(19,'2020-06-08 12:49:04.097000',1100.00,1),(23,'2020-06-17 19:09:45.724000',450.00,1),(24,'2020-07-07 13:12:13.017000',500.00,3),(25,'2020-07-07 13:13:01.350000',1200.00,3),(26,'2020-07-07 13:14:06.073000',600.00,4),(27,'2020-07-07 13:14:38.024000',900.00,4),(28,'2020-07-07 13:16:33.238000',675.00,5),(29,'2020-07-07 13:17:28.096000',1000.00,5),(30,'2020-07-07 13:18:40.781000',1700.00,6),(31,'2020-07-07 13:19:51.167000',1800.00,7),(32,'2020-07-07 13:21:05.928000',1125.00,8),(33,'2020-07-07 13:21:32.201000',1000.00,8),(34,'2020-07-07 13:22:38.051000',1675.00,9),(35,'2020-07-07 13:23:20.875000',1000.00,5),(36,'2020-07-07 13:23:54.971000',1725.00,5),(37,'2020-07-07 14:09:36.952000',1200.00,1),(38,'2020-07-07 14:12:49.525000',1350.00,1),(39,'2020-07-07 14:18:05.419000',600.00,1),(56,'2020-07-07 14:39:28.611282',1200.00,1),(57,'2020-07-07 14:43:03.191108',600.00,1),(58,'2020-07-07 14:45:57.405681',675.00,1),(60,'2020-07-07 14:50:53.175220',600.00,1),(61,'2020-07-07 14:55:07.905840',1200.00,1),(62,'2020-07-07 14:56:36.779507',500.00,1);
UNLOCK TABLES;

DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `route_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `departure_station_id` bigint(20) DEFAULT NULL,
  `arrival_station_id` bigint(20) DEFAULT NULL,
  `duration_in_minutes` int(11) DEFAULT NULL,
  `base_price` decimal(19,2) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`route_id`),
  KEY `FKauo7ax2m11lh7qbdf9yng6p2` (`departure_station_id`),
  KEY `FKjedn3882y4n1c0tlbl27atuck` (`arrival_station_id`),
  CONSTRAINT `FKauo7ax2m11lh7qbdf9yng6p2` FOREIGN KEY (`departure_station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `FKjedn3882y4n1c0tlbl27atuck` FOREIGN KEY (`arrival_station_id`) REFERENCES `station` (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

LOCK TABLES `route` WRITE;
INSERT INTO `route` VALUES (1,1,2,600,500.00,'001'),(2,1,3,420,400.00,'002'),(3,1,4,300,300.00,'003'),(4,1,5,480,450.00,'004');
UNLOCK TABLES;

DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat` (
  `seat_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `wagon_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `FKs11xmiofjqe833nd7vagxapp9` (`wagon_id`),
  CONSTRAINT `FKs11xmiofjqe833nd7vagxapp9` FOREIGN KEY (`wagon_id`) REFERENCES `wagon` (`wagon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=411 DEFAULT CHARSET=utf8;

LOCK TABLES `seat` WRITE;
INSERT INTO `seat` VALUES (1,'01',1),(2,'02',1),(3,'03',1),(4,'04',1),(5,'05',1),(6,'06',1),(7,'07',1),(8,'08',1),(9,'09',1),(10,'10',1),(11,'11',1),(12,'12',1),(13,'13',1),(14,'14',1),(15,'15',1),(16,'16',1),(17,'17',1),(18,'18',1),(19,'19',1),(20,'20',1),(21,'21',1),(22,'22',1),(23,'23',1),(24,'24',1),(25,'25',1),(26,'26',1),(27,'27',1),(28,'28',1),(29,'29',1),(30,'30',1),(31,'01',2),(32,'02',2),(33,'03',2),(34,'04',2),(35,'05',2),(36,'06',2),(37,'07',2),(38,'08',2),(39,'09',2),(40,'10',2),(41,'11',2),(42,'12',2),(43,'13',2),(44,'14',2),(45,'15',2),(46,'16',2),(47,'17',2),(48,'18',2),(49,'19',2),(50,'20',2),(51,'21',2),(52,'22',2),(53,'23',2),(54,'24',2),(55,'25',2),(56,'26',2),(57,'27',2),(58,'28',2),(59,'29',2),(60,'30',2),(61,'01',3),(62,'02',3),(63,'03',3),(64,'04',3),(65,'05',3),(66,'06',3),(67,'07',3),(68,'08',3),(69,'09',3),(70,'10',3),(71,'11',3),(72,'12',3),(73,'13',3),(74,'14',3),(75,'15',3),(76,'16',3),(77,'17',3),(78,'18',3),(79,'19',3),(80,'20',3),(81,'21',3),(82,'22',3),(83,'23',3),(84,'24',3),(85,'25',3),(86,'01',4),(87,'02',4),(88,'03',4),(89,'04',4),(90,'05',4),(91,'06',4),(92,'07',4),(93,'08',4),(94,'09',4),(95,'10',4),(96,'11',4),(97,'12',4),(98,'13',4),(99,'14',4),(100,'15',4),(101,'16',4),(102,'17',4),(103,'18',4),(104,'19',4),(105,'20',4),(106,'21',4),(107,'22',4),(108,'23',4),(109,'24',4),(110,'25',4),(111,'01',5),(112,'02',5),(113,'03',5),(114,'04',5),(115,'05',5),(116,'06',5),(117,'07',5),(118,'08',5),(119,'09',5),(120,'10',5),(121,'11',5),(122,'12',5),(123,'13',5),(124,'14',5),(125,'15',5),(126,'16',5),(127,'17',5),(128,'18',5),(129,'19',5),(130,'20',5),(131,'01',6),(132,'02',6),(133,'03',6),(134,'04',6),(135,'05',6),(136,'06',6),(137,'07',6),(138,'08',6),(139,'09',6),(140,'10',6),(141,'11',6),(142,'12',6),(143,'13',6),(144,'14',6),(145,'15',6),(146,'16',6),(147,'17',6),(148,'18',6),(149,'19',6),(150,'20',6),(151,'01',7),(152,'02',7),(153,'03',7),(154,'04',7),(155,'05',7),(156,'06',7),(157,'07',7),(158,'08',7),(159,'09',7),(160,'10',7),(161,'11',7),(162,'12',7),(163,'13',7),(164,'14',7),(165,'15',7),(166,'16',7),(167,'17',7),(168,'18',7),(169,'19',7),(170,'20',7),(171,'21',7),(172,'22',7),(173,'23',7),(174,'24',7),(175,'25',7),(176,'26',7),(177,'27',7),(178,'28',7),(179,'29',7),(180,'30',7),(181,'01',8),(182,'02',8),(183,'03',8),(184,'04',8),(185,'05',8),(186,'06',8),(187,'07',8),(188,'08',8),(189,'09',8),(190,'10',8),(191,'11',8),(192,'12',8),(193,'13',8),(194,'14',8),(195,'15',8),(196,'16',8),(197,'17',8),(198,'18',8),(199,'19',8),(200,'20',8),(201,'21',8),(202,'22',8),(203,'23',8),(204,'24',8),(205,'25',8),(206,'01',9),(207,'02',9),(208,'03',9),(209,'04',9),(210,'05',9),(211,'06',9),(212,'07',9),(213,'08',9),(214,'09',9),(215,'10',9),(216,'11',9),(217,'12',9),(218,'13',9),(219,'14',9),(220,'15',9),(221,'16',9),(222,'17',9),(223,'18',9),(224,'19',9),(225,'20',9),(226,'21',9),(227,'22',9),(228,'23',9),(229,'24',9),(230,'25',9),(231,'26',9),(232,'27',9),(233,'28',9),(234,'29',9),(235,'30',9),(236,'01',10),(237,'02',10),(238,'03',10),(239,'04',10),(240,'05',10),(241,'06',10),(242,'07',10),(243,'08',10),(244,'09',10),(245,'10',10),(246,'11',10),(247,'12',10),(248,'13',10),(249,'14',10),(250,'15',10),(251,'16',10),(252,'17',10),(253,'18',10),(254,'19',10),(255,'20',10),(256,'01',11),(257,'02',11),(258,'03',11),(259,'04',11),(260,'05',11),(261,'06',11),(262,'07',11),(263,'08',11),(264,'09',11),(265,'10',11),(266,'11',11),(267,'12',11),(268,'13',11),(269,'14',11),(270,'15',11),(271,'16',11),(272,'17',11),(273,'18',11),(274,'19',11),(275,'20',11),(276,'21',11),(277,'22',11),(278,'23',11),(279,'24',11),(280,'25',11),(281,'01',12),(282,'02',12),(283,'03',12),(284,'04',12),(285,'05',12),(286,'06',12),(287,'07',12),(288,'08',12),(289,'09',12),(290,'10',12),(291,'11',12),(292,'12',12),(293,'13',12),(294,'14',12),(295,'15',12),(296,'16',12),(297,'17',12),(298,'18',12),(299,'19',12),(300,'20',12),(301,'21',12),(302,'22',12),(303,'23',12),(304,'24',12),(305,'25',12),(306,'26',12),(307,'27',12),(308,'28',12),(309,'29',12),(310,'30',12),(311,'01',13),(312,'02',13),(313,'03',13),(314,'04',13),(315,'05',13),(316,'06',13),(317,'07',13),(318,'08',13),(319,'09',13),(320,'10',13),(321,'11',13),(322,'12',13),(323,'13',13),(324,'14',13),(325,'15',13),(326,'16',13),(327,'17',13),(328,'18',13),(329,'19',13),(330,'20',13),(331,'21',13),(332,'22',13),(333,'23',13),(334,'24',13),(335,'25',13),(336,'01',14),(337,'02',14),(338,'03',14),(339,'04',14),(340,'05',14),(341,'06',14),(342,'07',14),(343,'08',14),(344,'09',14),(345,'10',14),(346,'11',14),(347,'12',14),(348,'13',14),(349,'14',14),(350,'15',14),(351,'16',14),(352,'17',14),(353,'18',14),(354,'19',14),(355,'20',14),(356,'21',14),(357,'22',14),(358,'23',14),(359,'24',14),(360,'25',14),(361,'01',15),(362,'02',15),(363,'03',15),(364,'04',15),(365,'05',15),(366,'06',15),(367,'07',15),(368,'08',15),(369,'09',15),(370,'10',15),(371,'11',15),(372,'12',15),(373,'13',15),(374,'14',15),(375,'15',15),(376,'16',15),(377,'17',15),(378,'18',15),(379,'19',15),(380,'20',15),(381,'01',16),(382,'02',16),(383,'03',16),(384,'04',16),(385,'05',16),(386,'06',16),(387,'07',16),(388,'08',16),(389,'09',16),(390,'10',16),(391,'11',16),(392,'12',16),(393,'13',16),(394,'14',16),(395,'15',16),(396,'16',16),(397,'17',16),(398,'18',16),(399,'19',16),(400,'20',16);
UNLOCK TABLES;

DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `station_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

LOCK TABLES `station` WRITE;
INSERT INTO `station` VALUES (1,'Kyiv station','Kyiv','Ukraine','00001'),(2,'Lviv station','Lviv','Ukraine','00002'),(3,'Kharkiv station','Kharkiv','Ukraine','00003'),(4,'Dnipro station','Dnipro','Ukraine','00004'),(5,'Odessa station','Odessa','Ukraine','00005');
UNLOCK TABLES;

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticket_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trip_seat_id` bigint(20) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FKqbdf85jxbwaaetfr07c8sofgl` (`trip_seat_id`),
  KEY `FKksvt4tgnlwi1n5ckvd8lcgws5` (`payment_id`),
  CONSTRAINT `FKksvt4tgnlwi1n5ckvd8lcgws5` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`),
  CONSTRAINT `FKqbdf85jxbwaaetfr07c8sofgl` FOREIGN KEY (`trip_seat_id`) REFERENCES `trip_seat` (`trip_seat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

LOCK TABLES `ticket` WRITE;
INSERT INTO `ticket` VALUES (1,99,600.00,56),(2,101,600.00,56),(3,68,600.00,57),(4,195,675.00,58),(5,16,500.00,8),(6,40,500.00,8),(7,75,600.00,8),(9,112,600.00,12),(10,98,600.00,12),(11,133,600.00,12),(13,82,600.00,16),(14,38,500.00,16),(15,46,500.00,16),(16,146,600.00,17),(17,83,600.00,17),(20,53,500.00,19),(21,143,600.00,19),(25,151,450.00,23),(26,24,500.00,24),(27,102,600.00,25),(28,103,600.00,25),(29,122,600.00,26),(30,158,450.00,27),(31,159,450.00,27),(32,184,675.00,28),(33,212,500.00,29),(34,220,500.00,29),(35,273,600.00,30),(36,246,500.00,30),(37,282,600.00,30),(38,353,600.00,31),(39,328,600.00,31),(40,302,600.00,31),(41,410,675.00,32),(42,366,450.00,32),(43,8,500.00,33),(44,44,500.00,33),(45,193,675.00,34),(46,19,500.00,34),(47,43,500.00,34),(48,54,500.00,35),(49,55,500.00,35),(50,165,450.00,36),(51,181,675.00,36),(52,93,600.00,36),(53,324,600.00,37),(54,347,600.00,37),(55,393,675.00,38),(56,394,675.00,38),(57,127,600.00,39),(58,277,600.00,60),(59,339,600.00,61),(60,340,600.00,61),(61,48,500.00,62);
UNLOCK TABLES;

DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `train_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `locomotive_id` bigint(20) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`train_id`),
  KEY `FK7v274i2w7eox7xsn484xs5wqw` (`locomotive_id`),
  CONSTRAINT `FK7v274i2w7eox7xsn484xs5wqw` FOREIGN KEY (`locomotive_id`) REFERENCES `locomotive` (`locomotive_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `train` WRITE;
INSERT INTO `train` VALUES (1,1,'0001'),(2,2,'0002'),(3,3,'0003'),(4,4,'0004'),(5,5,'0005'),(6,6,'0006'),(7,7,'0007'),(8,8,'0008');
UNLOCK TABLES;

DROP TABLE IF EXISTS `trip`;
CREATE TABLE `trip` (
  `trip_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `departure_date` date DEFAULT NULL,
  `departure_time` time DEFAULT NULL,
  `trip_status` varchar(255) DEFAULT NULL,
  `route_id` bigint(20) DEFAULT NULL,
  `train_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`trip_id`),
  KEY `FKeva4adpyk6glllffnw5ypj20j` (`route_id`),
  KEY `FKevainym1c97f8s44bmpj8gmtv` (`train_id`),
  CONSTRAINT `FKeva4adpyk6glllffnw5ypj20j` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`),
  CONSTRAINT `FKevainym1c97f8s44bmpj8gmtv` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `trip` WRITE;
INSERT INTO `trip` VALUES (1,'2020-07-09','05:00:00','SCHEDULED',1,1),(2,'2020-07-09','06:30:00','SCHEDULED',2,2),(3,'2020-07-09','08:30:00','SCHEDULED',3,3),(4,'2020-07-09','11:00:00','SCHEDULED',4,4),(5,'2020-07-10','05:00:00','SCHEDULED',1,1),(6,'2020-07-10','06:30:00','SCHEDULED',2,2),(7,'2020-07-10','08:30:00','SCHEDULED',3,3),(8,'2020-07-10','11:00:00','SCHEDULED',4,4);
UNLOCK TABLES;

DROP TABLE IF EXISTS `trip`;
CREATE TABLE `trip` (
  `trip_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `departure_date` date DEFAULT NULL,
  `departure_time` time DEFAULT NULL,
  `trip_status` varchar(255) DEFAULT NULL,
  `route_id` bigint(20) DEFAULT NULL,
  `train_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`trip_id`),
  KEY `FKeva4adpyk6glllffnw5ypj20j` (`route_id`),
  KEY `FKevainym1c97f8s44bmpj8gmtv` (`train_id`),
  CONSTRAINT `FKeva4adpyk6glllffnw5ypj20j` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`),
  CONSTRAINT `FKevainym1c97f8s44bmpj8gmtv` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `trip` WRITE;
INSERT INTO `trip` VALUES (1,'2020-07-09','05:00:00','SCHEDULED',1,1),(2,'2020-07-09','06:30:00','SCHEDULED',2,2),(3,'2020-07-09','08:30:00','SCHEDULED',3,3),(4,'2020-07-09','11:00:00','SCHEDULED',4,4),(5,'2020-07-10','05:00:00','SCHEDULED',1,1),(6,'2020-07-10','06:30:00','SCHEDULED',2,2),(7,'2020-07-10','08:30:00','SCHEDULED',3,3),(8,'2020-07-10','11:00:00','SCHEDULED',4,4);
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `date_joined` datetime(6) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `account_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'Anton','Shevchenko','123-456-78-90','ssa.ssa.ssa11@gmail.com','2020-01-20 18:38:25.000000','1234567890','anton','$2a$11$avyZ9vqFQCaEufoT2npVku2uoAX3ibp/37d7H1DHu9460mG04TSMW','CUSTOMER'),(2,'John','Smith','987-654-32-10','admin@examle.com','2020-05-25 16:53:58.136474','1000000000','admin','$2a$11$jeAqBPG/JJkrlT9.XhUQFeCfhw0YsWbsmG3r/bcK51wgte3BzMHGe','ADMIN'),(3,'Johann Sebastian','Bach','111-111-11-11','jsbach@example.com','2020-07-07 12:03:59.482000','1111111111','JSBach','$2a$11$n8zhyzZPtMgmz.1MPMCsaOHPXbC.yBJsVGaG90dr8g8Fk6PVGhx8m','CUSTOMER'),(4,'Joseph','Haydn','222-222-22-22','haydn@example.com','2020-07-07 12:06:25.757000','2222222222','Haydn','$2a$11$N/CFHIeIFWYlFcASU0fHA.UuVT7lHzwH.OGOsrHBaszKS9pdVqcf2','CUSTOMER'),(5,'Wolfgang Amadeus','Mozart','333-333-33-33','mozart@example.com','2020-07-07 12:08:06.146000','3333333333','Mozart','$2a$11$mmV3Y7iOn6DKh7tpnU4mGumnbkGoTlezADB/sAWOYLJNjUXIlBWR6','CUSTOMER'),(6,'Ludwig van','Beethoven','444-444-44-44','beethoven@examle.com','2020-07-07 12:09:59.111000','4444444444','Beethoven','$2a$11$hxUIVxOsoOAKQc2l4pymxO14q1Xe3AH/RwUTezAztgTw2.H5RZpCu','CUSTOMER'),(7,'Franz','Schubert','555-555-55-55','schubert@example.com','2020-07-07 12:11:48.567000','5555555555','Schubert','$2a$11$L0KFqim0q4iRdAzI097oo./wae0mZMX3mFGFpJ83f.NdIve2Us0u.','CUSTOMER'),(8,'Robert','Schumann','666-666-66-66','schumann@example.com','2020-07-07 12:13:23.501000','6666666666','Schumann','$2a$11$1H/MGVML93R03gYM5KZbW..2T/3S7zLRX2DCA/JsW7MguvErMjXgO','CUSTOMER'),(9,'Richard','Wagner','777-777-77-77','wagner@example.com','2020-07-07 12:14:57.065000','7777777777','Wagner','$2a$11$i7XqpVBQmKDXxmsUFaepveo/Lz.fnGOf6Kp/XKmIZjZMJLuAb3BIe','CUSTOMER');
UNLOCK TABLES;

DROP TABLE IF EXISTS `wagon`;
CREATE TABLE `wagon` (
  `wagon_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `wagon_type_id` bigint(20) DEFAULT NULL,
  `train_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`wagon_id`),
  KEY `FKtp3xcmttadkjle2woktc7k3tt` (`train_id`),
  KEY `FKpey9ggp9pggaoawbhvu7rd49g` (`wagon_type_id`),
  CONSTRAINT `FKpey9ggp9pggaoawbhvu7rd49g` FOREIGN KEY (`wagon_type_id`) REFERENCES `wagon_type` (`wagon_type_id`),
  CONSTRAINT `FKtp3xcmttadkjle2woktc7k3tt` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

LOCK TABLES `wagon` WRITE;
INSERT INTO `wagon` VALUES (1,'01',1,1),(2,'02',1,1),(3,'01',2,2),(4,'02',2,2),(5,'01',3,3),(6,'02',3,3),(7,'01',1,4),(8,'02',2,4),(9,'01',1,5),(10,'02',3,5),(11,'01',2,6),(12,'02',3,6),(13,'01',2,7),(14,'02',2,7),(15,'01',3,8),(16,'02',3,8);
UNLOCK TABLES;

DROP TABLE IF EXISTS `wagon_type`;
CREATE TABLE `wagon_type` (
  `wagon_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `price_coefficient` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`wagon_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `wagon_type` WRITE;
INSERT INTO `wagon_type` VALUES (1,'ECONOMY',30,1.00),(2,'REGULAR',25,1.50),(3,'BUSINESS',20,2.00);
UNLOCK TABLES;