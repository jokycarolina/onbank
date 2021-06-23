DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id_account` int NOT NULL AUTO_INCREMENT,
  `currency` int NOT NULL,
  `type_account` int NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`id_account`),
  KEY `fk_ACCOUNT_CURRENCY` (`currency`),
  KEY `fk_ACCOUNT_TYPEACCOUNT` (`type_account`),
  CONSTRAINT `fk_ACCOUNT_CURRENCY` FOREIGN KEY (`currency`) REFERENCES `currency` (`id_currency`),
  CONSTRAINT `fk_ACCOUNT_TYPEACCOUNT` FOREIGN KEY (`type_account`) REFERENCES `type_account` (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,1,10000),(2,2,2,400),(3,1,1,10000),(4,1,1,1000),(5,1,2,0),(6,2,1,10),(7,1,1,0),(8,1,1,0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;


----------------------------------- CURRENCY -------------------------

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency` (
  `id_currency` int NOT NULL AUTO_INCREMENT,
  `currency` varchar(50) NOT NULL,
  PRIMARY KEY (`id_currency`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'pesos'),(2,'dolares');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

 ----------------------------- CUSTOMER----------------------
 DROP TABLE IF EXISTS `customer`;
 /*!40101 SET @saved_cs_client     = @@character_set_client */;
 /*!50503 SET character_set_client = utf8mb4 */;
 CREATE TABLE `customer` (
   `customer_id` int NOT NULL AUTO_INCREMENT,
   `customer_dni` varchar(50) NOT NULL,
   `fullname` varchar(100) NOT NULL,
   `Date_birth` date NOT NULL,
   `email` varchar(100) NOT NULL,
   `password` int NOT NULL,
   PRIMARY KEY (`customer_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 /*!40101 SET character_set_client = @saved_cs_client */;

 --
 -- Dumping data for table `customer`
 --

 LOCK TABLES `customer` WRITE;
 /*!40000 ALTER TABLE `customer` DISABLE KEYS */;
 INSERT INTO `customer` VALUES (1,'8888888','Jokasta Carolina Perdomo','1994-04-06','jokasta_carolina@hotmail.com',2323),
 (2,'95592487','Jose Luis Gil Sanchez','1994-09-23','joselg_1994@hotmail.com',1234),
 (3,'99999999','Nairobi Martinez','1957-12-27','nairobimartinez@hotmail.com',406),
 (4,'77775266','Pedro Perez','1999-01-01','perez_pedro@gmail.com',2210),
 (5,'22531682','Vanessa Godoy','1956-11-11','gdyvanessa@gmail.com',9852),
 (6,'33252165','Gonzalo Lopez','1950-09-08','gonzalo123@gmail.com',8852),
 (7,'22222','Laura Fernandez','1990-01-19','f_laura0@hotmail.com',5060);
 /*!40000 ALTER TABLE `customer` ENABLE KEYS */;
 UNLOCK TABLES;
 /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

------------------------ CUSTOMER_ACCOUNT ------------------------

DROP TABLE IF EXISTS `customer_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_account` (
  `id_customer` int NOT NULL,
  `id_account` int NOT NULL,
  KEY `fk_CA_customer` (`id_customer`),
  KEY `fk_CA_ACCOUNT` (`id_account`),
  CONSTRAINT `fk_CA_ACCOUNT` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`),
  CONSTRAINT `fk_CA_customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_account`
--

LOCK TABLES `customer_account` WRITE;
/*!40000 ALTER TABLE `customer_account` DISABLE KEYS */;
INSERT INTO `customer_account` VALUES (1,1),(2,2),(3,3),(3,4),(4,5),(5,6),(6,7),(7,8);
/*!40000 ALTER TABLE `customer_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-------------------------------- DEPOSIT ------------------------------
DROP TABLE IF EXISTS `deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deposit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `id_account` int NOT NULL,
  `id_currency` int NOT NULL,
  `id_cliente` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idAccount_account_deposit` (`id_account`),
  KEY `fk_deposit_currency` (`id_currency`),
  CONSTRAINT `fk_deposit_currency` FOREIGN KEY (`id_currency`) REFERENCES `currency` (`id_currency`),
  CONSTRAINT `fk_idAccount_account_deposit` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit`
--

LOCK TABLES `deposit` WRITE;
/*!40000 ALTER TABLE `deposit` DISABLE KEYS */;
INSERT INTO `deposit` VALUES (1,400,2,2,'95592487'),(2,800,2,2,'95592487'),(3,100,2,2,'95592487'),(4,400,2,2,'95592487'),(5,10000,1,1,'8888888');
/*!40000 ALTER TABLE `deposit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

---------------------------------------------- EXTRACTIONORDER-------------------------

DROP TABLE IF EXISTS `extraction_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extraction_order` (
  `id_extraction` int NOT NULL AUTO_INCREMENT,
  `id_account` int NOT NULL,
  `amount_extraction` double DEFAULT NULL,
  `date_extraction` date DEFAULT NULL,
  PRIMARY KEY (`id_extraction`),
  KEY `fk_EXTRACTION_ACCOUNT` (`id_account`),
  CONSTRAINT `fk_EXTRACTION_ACCOUNT` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extraction_order`
--

LOCK TABLES `extraction_order` WRITE;
/*!40000 ALTER TABLE `extraction_order` DISABLE KEYS */;
INSERT INTO `extraction_order` VALUES (1,1,5,'2021-06-05'),(2,2,100,'2021-06-05'),(3,2,1000,'2021-06-15'),(4,2,600,'2021-06-22'),(5,2,100,'2021-06-22');
/*!40000 ALTER TABLE `extraction_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

------------------- PAYMENT --------------
DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id_payment` int NOT NULL AUTO_INCREMENT,
  `date_payment` date NOT NULL,
  `currency` int NOT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id_payment`),
  KEY `fk_currency_payment` (`currency`),
  CONSTRAINT `fk_currency_payment` FOREIGN KEY (`currency`) REFERENCES `currency` (`id_currency`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (5,'2021-06-04',1,19900);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-------------------- PAYMENT_CUSTOMER -------------------------------


DROP TABLE IF EXISTS `payment_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_customer` (
  `id_payment` int NOT NULL,
  `id_customer` int NOT NULL,
  KEY `fk_Payment_customer` (`id_payment`),
  KEY `fk_customer_Payment` (`id_customer`),
  CONSTRAINT `fk_customer_Payment` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `fk_Payment_customer` FOREIGN KEY (`id_payment`) REFERENCES `payment` (`id_payment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_customer`
--

LOCK TABLES `payment_customer` WRITE;
/*!40000 ALTER TABLE `payment_customer` DISABLE KEYS */;
INSERT INTO `payment_customer` VALUES (5,1);
/*!40000 ALTER TABLE `payment_customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

---------------------------------- PHONENUMBER --------------------------------------------
DROP TABLE IF EXISTS `phone_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phone_number` (
  `customer_id` int NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_customer_PhoneNumber` (`customer_id`),
  CONSTRAINT `fk_customer_PhoneNumber` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_number`
--

LOCK TABLES `phone_number` WRITE;
/*!40000 ALTER TABLE `phone_number` DISABLE KEYS */;
INSERT INTO `phone_number` VALUES (1,'1132287262',1),(2,'11355587262',2),(3,'11858585',4),(4,'11858585',5),(4,'11858585',6),(4,'11858585',7),(3,'11858585',8);
/*!40000 ALTER TABLE `phone_number` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

---------------------------- TRANSFER -----------------------------------------
DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `id_transfer` int NOT NULL AUTO_INCREMENT,
  `id_customer` int NOT NULL,
  `id_account` int NOT NULL,
  `amount` double NOT NULL,
  `date_transfer` date DEFAULT NULL,
  PRIMARY KEY (`id_transfer`),
  KEY `fk_TRANSFER_customer` (`id_customer`),
  KEY `fk_TRANSFER_ACCOUNT` (`id_account`),
  CONSTRAINT `fk_TRANSFER_ACCOUNT` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`),
  CONSTRAINT `fk_TRANSFER_customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-------------------------------------------- TYPEACCOUNT --------------------------------------
DROP TABLE IF EXISTS `type_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_account` (
  `id_type` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_account`
--

LOCK TABLES `type_account` WRITE;
/*!40000 ALTER TABLE `type_account` DISABLE KEYS */;
INSERT INTO `type_account` VALUES (1,'Ahorro'),(2,'Corriente');
/*!40000 ALTER TABLE `type_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

