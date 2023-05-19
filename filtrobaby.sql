-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: filtro
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `danhmuc`
--

DROP TABLE IF EXISTS `danhmuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmuc` (
  `madanhmuc` int NOT NULL AUTO_INCREMENT,
  `TenDanhMuc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `TinhTrang` int DEFAULT NULL,
  PRIMARY KEY (`madanhmuc`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmuc`
--

LOCK TABLES `danhmuc` WRITE;
/*!40000 ALTER TABLE `danhmuc` DISABLE KEYS */;
INSERT INTO `danhmuc` VALUES (1,'Cà phê bột',0),(2,'Cà phê nén',NULL),(3,'Cà phê nhân xanh',NULL),(4,'Cà phê hạt đã rang',NULL),(5,'Cà phê hạt',NULL),(6,'TET',1),(7,'ATCAT',1),(8,'Test',0);
/*!40000 ALTER TABLE `danhmuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dathang`
--

DROP TABLE IF EXISTS `dathang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dathang` (
  `madathang` int NOT NULL AUTO_INCREMENT,
  `MaKH` int DEFAULT NULL,
  `NgayDatHang` date DEFAULT NULL,
  `SDT` varchar(45) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Tong` int DEFAULT NULL,
  `TinhTrang` int DEFAULT NULL,
  `PhuongThucThanhToan` int DEFAULT NULL,
  `Zip` int DEFAULT NULL,
  `ThanhPho` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`madathang`),
  KEY `FK_DH_MAKH_KH_idx` (`MaKH`),
  KEY `FK_DH_PTTT_ID_idx` (`PhuongThucThanhToan`),
  CONSTRAINT `FK_DH_MAKH_KH` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`makh`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_DH_PTTT_ID` FOREIGN KEY (`PhuongThucThanhToan`) REFERENCES `phuongthuc_thanhtoan` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dathang`
--

LOCK TABLES `dathang` WRITE;
/*!40000 ALTER TABLE `dathang` DISABLE KEYS */;
INSERT INTO `dathang` VALUES (8,1010,'2023-04-10','123456789','phu@gmail.com','ACDC/12',1380000,2,1,2580,'HN'),(9,1014,'2023-04-11','0789654123','letgo@gmail.com','quan 12',1175000,1,1,70000,'HCM'),(10,1010,'2023-04-11','07721113120','phutv1990@gmail.com','TPHCM',1950000,2,1,4508,'HCM'),(11,1010,'2023-04-11','0789654123',NULL,'ACDC/12',1800000,2,1,2580,'HCM'),(12,1014,'2023-04-11','0789654123','letgo@gmail.com','TPHCM',185000,1,1,70000,'HCM'),(13,1015,'2023-04-12','07721113120','cf96@gmail.com','TPHCM',185000,1,1,70000,'HCM'),(14,1016,'2023-04-12','0789654123','sangpro123@gmail.com','Quang Trung',1545000,1,1,70000,'HCM'),(15,1018,'2023-04-16','0789654123','dk8@gmail.com','Q12',185000,1,1,70000,'HCM'),(16,1010,'2023-04-16','0789654123','phu123@gmail.com','',2175000,4,1,2580,'HCM'),(17,1018,'2023-04-17','0789654123','dk8@gmail.com','Tôn Đảng',970000,6,1,70000,'HCM'),(18,1015,'2023-04-17','07721132120','cf96@gmail.com','',510000,4,1,70000,'HCM'),(19,1010,'2023-04-19','01472583690','phutv1990@gmail.com','TPHCM',805000,4,1,70000,'HCM'),(20,1019,'2023-04-22','0789654123','jnt12@gmail.com','ACDC/12',185000,1,1,70000,'HCM'),(21,1019,'2023-04-22','0789654123','jnt12@gmail.com','TPHCM',1235000,1,1,70000,'HCM'),(22,1020,'2023-04-22','0789654123','tk1@gmail.com','Lê Thánh Tôn',405000,1,1,4508,'HCM'),(23,1010,'2023-05-14','345678','tyhuj@mgi.com','Chau Thoi',0,1,2,23434654,'Binh Duong'),(24,1010,'2023-05-14','34234234','Phu@gmail.com','asjdjsa',375000,1,1,1242343,'Been Hoa'),(25,1010,'2023-05-14','345678','tyhuj@mgi.com','Chau Thoi',0,1,2,23434654,'Binh Duong'),(26,1010,'2023-05-14','345678','tyhuj@mgi.com','Chau Thoi',0,1,2,23434654,'Binh Duong'),(27,1010,'2023-05-14','234324234','adasdsad','dasdas',1125000,1,1,24324324,'asdasd'),(28,1010,'2023-05-14','12321434','sdassdas\n','adasdsad',200000,1,1,213324345,'asdasd'),(29,1010,'2023-05-14','3243243','adasd','asdasdsad',750000,1,1,123,'sddad'),(30,1010,'2023-05-14','2423424','asdasd','asdasdxzce',400000,1,1,34234,'asdasd'),(31,1010,'2023-05-14','4234324','asdasd','weqweqw',950000,1,1,22423432,'sadasd'),(32,1010,'2023-05-14','34324234','sadsad','asdsad',600000,1,2,23434,'sdasd'),(33,1010,'2023-05-14','3234','asdasd','asdasd',400000,1,1,34324,'sadasd'),(34,1010,'2023-05-14','45435','asdasd','qweqwewq',400000,1,1,34234234,'dadsad'),(35,1010,'2023-05-14','34234','adasdsda','asdad',375000,1,1,34234,'adasd'),(36,1010,'2023-05-14','124534545','fsfdfdfsd','123123123',800000,1,1,124214214,'12312321'),(37,1010,'2023-05-14','234243','adsad','asdasd',600000,1,2,1232423,'asdasd'),(38,1010,'2023-05-14','434','asdasd','asdasd',375000,1,1,123123,'asdasd'),(39,1010,'2023-05-14','235235','dsadasd','qweqweqwe',3150000,1,1,1234324,'asdasd');
/*!40000 ALTER TABLE `dathang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dathang_chitiet`
--

DROP TABLE IF EXISTS `dathang_chitiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dathang_chitiet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `MaDatHang` int DEFAULT NULL,
  `MaSP` int DEFAULT NULL,
  `SoLuong` int DEFAULT NULL,
  `GiaTien` int DEFAULT NULL,
  `Tong` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DHCT_MADH_DH_idx` (`MaDatHang`),
  KEY `FK_DHCT_MASP_SP_idx` (`MaSP`),
  CONSTRAINT `FK_DHCT_MADH_DH` FOREIGN KEY (`MaDatHang`) REFERENCES `dathang` (`madathang`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_DHCT_MASP_SP` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dathang_chitiet`
--

LOCK TABLES `dathang_chitiet` WRITE;
/*!40000 ALTER TABLE `dathang_chitiet` DISABLE KEYS */;
INSERT INTO `dathang_chitiet` VALUES (1,NULL,18,5,185000,NULL),(2,NULL,18,5,185000,NULL),(3,NULL,18,5,185000,925000),(4,NULL,6,4,80000,320000),(5,NULL,22,5,600000,3000000),(6,NULL,18,5,185000,925000),(7,NULL,6,4,80000,320000),(8,NULL,22,5,600000,3000000),(9,NULL,18,5,185000,925000),(10,NULL,6,4,80000,320000),(11,NULL,22,5,600000,3000000),(12,NULL,18,5,185000,925000),(13,NULL,6,4,80000,320000),(14,NULL,22,5,600000,3000000),(15,NULL,18,1,185000,185000),(16,NULL,6,4,80000,320000),(17,NULL,22,5,600000,3000000),(18,NULL,21,2,170000,340000),(19,8,21,2,170000,340000),(20,8,3,1,200000,200000),(21,8,18,1,185000,185000),(22,8,16,1,220000,220000),(23,8,18,1,185000,185000),(24,8,19,1,250000,250000),(25,9,18,3,185000,555000),(26,9,17,2,310000,620000),(27,10,22,2,600000,1200000),(28,10,13,1,750000,750000),(29,11,22,3,600000,1800000),(30,12,18,1,185000,185000),(31,13,18,1,185000,185000),(32,14,18,1,185000,185000),(33,14,22,2,600000,1200000),(34,14,6,2,80000,160000),(35,15,18,1,185000,185000),(36,16,22,3,600000,1800000),(37,16,2,1,375000,375000),(38,17,18,2,185000,370000),(39,17,22,1,600000,600000),(40,18,21,3,170000,510000),(41,19,17,2,310000,620000),(42,19,18,1,185000,185000),(43,20,18,1,185000,185000),(44,21,18,5,185000,925000),(45,21,17,1,310000,310000),(46,22,18,1,185000,185000),(47,22,16,1,220000,220000),(48,24,2,1,375000,375000),(49,27,2,3,375000,1125000),(50,28,3,1,200000,200000),(51,29,2,2,375000,750000),(52,30,3,2,200000,400000),(53,31,1,1,950000,950000),(54,32,3,3,200000,600000),(55,33,3,2,200000,400000),(56,34,3,2,200000,400000),(57,35,2,1,375000,375000),(58,36,3,4,200000,800000),(59,37,3,3,200000,600000),(60,38,2,1,375000,375000),(61,39,3,5,200000,1000000),(62,39,4,4,300000,1200000),(63,39,1,1,950000,950000);
/*!40000 ALTER TABLE `dathang_chitiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailcode`
--

DROP TABLE IF EXISTS `emailcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emailcode` (
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `maxacthuc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailcode`
--

LOCK TABLES `emailcode` WRITE;
/*!40000 ALTER TABLE `emailcode` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giohang`
--

DROP TABLE IF EXISTS `giohang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giohang` (
  `MaGioHang` int NOT NULL AUTO_INCREMENT,
  `MaKH` int DEFAULT NULL,
  `ThoiGianTao` date DEFAULT NULL,
  `ThoiGianCapNhat` date DEFAULT NULL,
  `Tong` int DEFAULT NULL,
  `TrangThai` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`MaGioHang`),
  KEY `FK_GH_MATK_TK_idx` (`MaKH`),
  CONSTRAINT `FK_GH_MATK_TK` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`makh`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giohang`
--

LOCK TABLES `giohang` WRITE;
/*!40000 ALTER TABLE `giohang` DISABLE KEYS */;
INSERT INTO `giohang` VALUES (22,1019,'2023-04-22','2023-04-22',NULL,1),(24,1010,'2023-05-13','2023-05-14',NULL,1),(25,1025,'2023-05-13',NULL,NULL,1);
/*!40000 ALTER TABLE `giohang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giohang_chitiet`
--

DROP TABLE IF EXISTS `giohang_chitiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giohang_chitiet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `magiohang` int DEFAULT NULL,
  `MaSP` int DEFAULT NULL,
  `SoLuong` int DEFAULT NULL,
  `GiaTien` int DEFAULT NULL,
  `Tong` int DEFAULT NULL,
  `ThoiGianMua` date DEFAULT NULL,
  `magiohangtam` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_GHCT_ID_MGH_idx` (`magiohang`),
  KEY `FK_GHCT_MASP_SP_idx` (`MaSP`),
  KEY `FK_GHCT_MGHT_ID_idx` (`magiohangtam`),
  CONSTRAINT `FK_GHCT_ID_MGH` FOREIGN KEY (`magiohang`) REFERENCES `giohang` (`MaGioHang`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_GHCT_MASP_SP` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`masp`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_GHCT_MGHT_ID` FOREIGN KEY (`magiohangtam`) REFERENCES `giohang_temp` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giohang_chitiet`
--

LOCK TABLES `giohang_chitiet` WRITE;
/*!40000 ALTER TABLE `giohang_chitiet` DISABLE KEYS */;
INSERT INTO `giohang_chitiet` VALUES (1,NULL,6,1,80000,80000,NULL,NULL),(2,NULL,18,1,185000,185000,NULL,NULL),(3,NULL,21,2,170000,340000,NULL,NULL),(4,NULL,3,1,200000,200000,'2023-04-07',NULL),(14,NULL,18,1,185000,185000,'2023-04-07',NULL),(15,NULL,16,1,220000,220000,'2023-04-07',NULL),(16,NULL,18,1,185000,185000,'2023-04-07',NULL),(17,NULL,19,1,250000,250000,'2023-04-07',NULL),(26,NULL,18,1,185000,185000,'2023-04-07',4),(27,NULL,19,1,250000,250000,'2023-04-07',4),(28,NULL,21,4,170000,680000,'2023-04-07',5),(29,NULL,19,4,250000,1000000,'2023-04-07',5),(30,NULL,14,5,350000,1750000,'2023-04-07',6),(31,NULL,22,3,600000,1800000,'2023-04-07',6),(32,NULL,22,4,600000,2400000,'2023-04-07',7),(33,NULL,6,4,80000,320000,'2023-04-07',7),(34,NULL,19,4,250000,1000000,'2023-04-07',8),(35,NULL,17,3,310000,930000,'2023-04-07',8),(36,NULL,22,2,600000,1200000,'2023-04-07',9),(38,NULL,18,1,185000,185000,'2023-04-09',10),(39,NULL,18,1,185000,185000,'2023-04-09',NULL),(40,NULL,6,4,80000,320000,'2023-04-09',NULL),(41,NULL,22,5,600000,3000000,'2023-04-09',NULL),(42,NULL,21,2,170000,340000,'2023-04-09',NULL),(45,NULL,22,1,600000,600000,'2023-04-10',11),(77,NULL,18,3,185000,555000,'2023-04-11',NULL),(78,NULL,17,2,310000,620000,'2023-04-11',NULL),(79,NULL,22,2,600000,1200000,'2023-04-11',NULL),(80,NULL,13,1,750000,750000,'2023-04-11',NULL),(81,NULL,22,3,600000,1800000,'2023-04-11',NULL),(82,NULL,18,1,185000,185000,'2023-04-11',NULL),(83,NULL,18,1,185000,185000,'2023-04-12',23),(84,NULL,19,1,250000,250000,'2023-04-12',23),(85,NULL,18,2,185000,370000,'2023-04-12',24),(86,NULL,17,1,310000,310000,'2023-04-12',24),(87,NULL,18,1,185000,185000,'2023-04-12',25),(88,NULL,16,1,220000,220000,'2023-04-12',25),(89,NULL,6,1,80000,80000,'2023-04-12',26),(90,NULL,6,1,80000,80000,'2023-04-12',27),(91,NULL,14,1,350000,350000,'2023-04-12',28),(92,NULL,20,1,380000,380000,'2023-04-12',29),(93,NULL,18,1,185000,185000,'2023-04-12',30),(94,NULL,18,1,185000,185000,'2023-04-12',NULL),(95,NULL,18,1,185000,185000,'2023-04-12',31),(96,NULL,18,1,185000,185000,'2023-04-12',NULL),(97,NULL,22,2,600000,1200000,'2023-04-12',NULL),(98,NULL,6,2,80000,160000,'2023-04-12',NULL),(99,NULL,18,1,185000,185000,'2023-04-16',NULL),(100,NULL,21,1,170000,170000,'2023-04-16',NULL),(101,NULL,12,1,170000,170000,'2023-04-16',NULL),(102,NULL,16,1,220000,220000,'2023-04-16',NULL),(105,NULL,22,3,600000,1800000,'2023-04-16',NULL),(106,NULL,2,1,375000,375000,'2023-04-16',NULL),(107,NULL,18,2,185000,370000,'2023-04-17',NULL),(108,NULL,22,1,600000,600000,'2023-04-17',NULL),(109,NULL,21,3,170000,510000,'2023-04-17',NULL),(110,NULL,17,2,310000,620000,'2023-04-19',NULL),(111,NULL,18,1,185000,185000,'2023-04-19',NULL),(115,22,6,2,80000,160000,'2023-04-22',NULL),(116,22,2,2,375000,750000,'2023-04-22',NULL),(117,22,13,3,750000,2250000,'2023-04-22',NULL),(118,22,18,3,185000,555000,'2023-04-22',NULL),(122,NULL,18,1,185000,185000,'2023-05-13',33),(128,NULL,18,1,185000,185000,'2023-05-13',34),(166,24,2,2,375000,750000,'2023-05-14',NULL);
/*!40000 ALTER TABLE `giohang_chitiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giohang_temp`
--

DROP TABLE IF EXISTS `giohang_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giohang_temp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ThoiGianTao` date DEFAULT NULL,
  `ThoiGianCapNhat` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giohang_temp`
--

LOCK TABLES `giohang_temp` WRITE;
/*!40000 ALTER TABLE `giohang_temp` DISABLE KEYS */;
INSERT INTO `giohang_temp` VALUES (1,'2023-04-07',NULL),(2,'2023-04-07',NULL),(3,'2023-04-07',NULL),(4,'2023-04-07',NULL),(5,'2023-04-07','2023-04-07'),(6,'2023-04-07','2023-04-07'),(7,'2023-04-07','2023-04-07'),(8,'2023-04-07','2023-04-07'),(9,'2023-04-07','2023-04-07'),(10,'2023-04-09','2023-04-09'),(11,'2023-04-10','2023-04-10'),(12,'2023-04-10','2023-04-10'),(13,'2023-04-10','2023-04-10'),(14,'2023-04-10','2023-04-10'),(15,'2023-04-10','2023-04-10'),(16,'2023-04-10','2023-04-10'),(17,'2023-04-10','2023-04-10'),(18,'2023-04-10','2023-04-10'),(19,'2023-04-10','2023-04-10'),(20,'2023-04-10','2023-04-10'),(21,'2023-04-10','2023-04-10'),(22,'2023-04-10','2023-04-10'),(23,'2023-04-12','2023-04-12'),(24,'2023-04-12','2023-04-12'),(25,'2023-04-12','2023-04-12'),(26,'2023-04-12','2023-04-12'),(27,'2023-04-12','2023-04-12'),(28,'2023-04-12','2023-04-12'),(29,'2023-04-12','2023-04-12'),(30,'2023-04-12','2023-04-12'),(31,'2023-04-12','2023-04-12'),(32,'2023-05-12','2023-05-12'),(33,'2023-05-13','2023-05-13'),(34,'2023-05-13','2023-05-13');
/*!40000 ALTER TABLE `giohang_temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `mahoadon` int NOT NULL AUTO_INCREMENT,
  `MaKH` int DEFAULT NULL,
  `NgayMua` date DEFAULT NULL,
  `Tong` int DEFAULT NULL,
  PRIMARY KEY (`mahoadon`),
  KEY `FK_HD_MKH_KH` (`MaKH`),
  CONSTRAINT `FK_HD_MKH_KH` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`makh`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1000,1015,'2023-04-12',185000),(1001,1016,'2023-04-12',1545000),(1002,1010,'2023-04-10',1380000),(1003,1018,'2023-04-16',185000),(1004,1010,'2023-04-16',2175000),(1005,1010,'2023-04-16',2175000),(1006,1010,'2023-04-10',1380000),(1007,1010,'2023-04-11',1950000),(1008,1010,'2023-04-11',1800000),(1009,1018,'2023-04-17',970000),(1010,1015,'2023-04-17',510000),(1011,1015,'2023-04-17',510000),(1012,1010,'2023-04-16',2175000),(1013,1010,'2023-04-19',805000);
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon_chitiet`
--

DROP TABLE IF EXISTS `hoadon_chitiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon_chitiet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `MaHoaDon` int DEFAULT NULL,
  `MaSP` int DEFAULT NULL,
  `SoLuong` int DEFAULT NULL,
  `GiaTien` int DEFAULT NULL,
  `Tong` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_HDCT_MSP_SP_idx` (`MaSP`),
  KEY `FK_HDCT_MHD_HD_idx` (`MaHoaDon`),
  CONSTRAINT `FK_HDCT_MASP_SP` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`masp`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_HDCT_MHD_HD` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`mahoadon`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon_chitiet`
--

LOCK TABLES `hoadon_chitiet` WRITE;
/*!40000 ALTER TABLE `hoadon_chitiet` DISABLE KEYS */;
INSERT INTO `hoadon_chitiet` VALUES (1,1000,18,1,185000,185000),(2,1001,18,1,185000,185000),(3,1001,22,2,600000,1200000),(4,1001,6,2,80000,160000),(5,1002,21,2,170000,340000),(6,1002,3,1,200000,200000),(7,1002,18,1,185000,185000),(8,1002,16,1,220000,220000),(9,1002,18,1,185000,185000),(10,1002,19,1,250000,250000),(11,1003,18,1,185000,185000),(12,1004,22,3,600000,1800000),(13,1004,2,1,375000,375000),(14,1005,22,3,600000,1800000),(15,1005,2,1,375000,375000),(16,1006,21,2,170000,340000),(17,1006,3,1,200000,200000),(18,1006,18,1,185000,185000),(19,1006,16,1,220000,220000),(20,1006,18,1,185000,185000),(21,1006,19,1,250000,250000),(22,1007,22,2,600000,1200000),(23,1007,13,1,750000,750000),(24,1008,22,3,600000,1800000),(25,1009,18,2,185000,370000),(26,1009,22,1,600000,600000),(27,1010,21,3,170000,510000),(28,1011,21,3,170000,510000),(29,1012,22,3,600000,1800000),(30,1012,2,1,375000,375000),(31,1013,17,2,310000,620000),(32,1013,18,1,185000,185000);
/*!40000 ALTER TABLE `hoadon_chitiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `huongvi`
--

DROP TABLE IF EXISTS `huongvi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `huongvi` (
  `mahuongvi` int NOT NULL AUTO_INCREMENT,
  `TenHuongVi` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `MoTa` varchar(100) DEFAULT NULL,
  `TinhTrang` int DEFAULT NULL,
  PRIMARY KEY (`mahuongvi`),
  UNIQUE KEY `TenHuongVi_UNIQUE` (`TenHuongVi`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `huongvi`
--

LOCK TABLES `huongvi` WRITE;
/*!40000 ALTER TABLE `huongvi` DISABLE KEYS */;
INSERT INTO `huongvi` VALUES (1,'Nhẹ','Hương vị nhẹ nhàng',1),(2,'Trung bình','Hương vị trung bình',1),(3,'Mạnh','Hương vị mạnh',1),(4,'Đậm đà','Đậm đà',1),(5,'Đắng','Hương vị đắng',1);
/*!40000 ALTER TABLE `huongvi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `makh` int NOT NULL AUTO_INCREMENT,
  `HoTen` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` varchar(50) DEFAULT NULL,
  `DiaChi` varchar(200) DEFAULT NULL,
  `ThanhPho` varchar(100) DEFAULT NULL,
  `Zip` int DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `SDT` varchar(50) DEFAULT NULL,
  `TinhTrang` int DEFAULT NULL,
  `MaTK` int DEFAULT NULL,
  PRIMARY KEY (`makh`),
  KEY `FK_KH_MATK_TK_idx` (`MaTK`),
  CONSTRAINT `FK_KH_MATK_TK` FOREIGN KEY (`MaTK`) REFERENCES `taikhoan` (`matk`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1010,'ongiaphu1','2000-06-15','Nam','Tra Vinh','HCM',23456789,'ongia@gmail.com','123456789',NULL,12),(1011,'ogp',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,13),(1012,'zafuvip',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14),(1013,'real',NULL,NULL,NULL,NULL,NULL,'okh1k59@gmail.com',NULL,NULL,15),(1014,'letgo',NULL,NULL,NULL,NULL,NULL,'letgo@gmail.com',NULL,NULL,16),(1015,'Nguyễn Công Phượng','1996-08-11','Nam','TPHCM','HCM',70000,'cf96@gmail.com','077211321201',NULL,17),(1016,'Thanh Sang',NULL,NULL,NULL,NULL,NULL,'sangpro123@gmail.com',NULL,NULL,18),(1017,'On Gia Phu','1998-08-12','Nam','123 Quang Trung','HCM',70000,'zafu1@gmail.com','0123456987',NULL,19),(1018,'Jason','1998-08-17','Nam','123 Quang Trung','HCM',70000,'dk8@gmail.com','0123456988',NULL,20),(1019,'Jonathan',NULL,NULL,NULL,NULL,NULL,'jnt12@gmail.com',NULL,NULL,21),(1020,'Testing1',NULL,NULL,NULL,NULL,NULL,'tk1@gmail.com',NULL,NULL,22),(1024,'VVD',NULL,NULL,NULL,NULL,NULL,'vvd@ok.com',NULL,NULL,33),(1025,'hellobro',NULL,NULL,NULL,NULL,NULL,'iadjisdio@gmail.com',NULL,NULL,34);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `luong`
--

DROP TABLE IF EXISTS `luong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `luong` (
  `maluong` int NOT NULL AUTO_INCREMENT,
  `VaiTro` varchar(50) DEFAULT NULL,
  `HinhThuc` varchar(50) DEFAULT NULL,
  `LuongTheoGio` int DEFAULT NULL,
  PRIMARY KEY (`maluong`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luong`
--

LOCK TABLES `luong` WRITE;
/*!40000 ALTER TABLE `luong` DISABLE KEYS */;
INSERT INTO `luong` VALUES (1,'Nhan Vien','Part-time',50000),(2,'Admin','Full-time',80000);
/*!40000 ALTER TABLE `luong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `manv` int NOT NULL AUTO_INCREMENT,
  `HoTen` varchar(50) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` varchar(50) NOT NULL,
  `SDT` varchar(50) DEFAULT NULL,
  `MaLuong` int DEFAULT NULL,
  `MaTK` int DEFAULT NULL,
  `TinhTrang` int DEFAULT NULL,
  PRIMARY KEY (`manv`),
  UNIQUE KEY `UQ__NhanVien__CA1930A552DDABDA` (`SDT`),
  KEY `FK_NHANVIEN_MATK_TK_idx` (`MaTK`),
  KEY `FK_NHANVIEN_ML_LUONG_idx` (`MaLuong`),
  CONSTRAINT `FK_NHANVIEN_MATK_TK` FOREIGN KEY (`MaTK`) REFERENCES `taikhoan` (`matk`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_NHANVIEN_ML_LUONG` FOREIGN KEY (`MaLuong`) REFERENCES `luong` (`maluong`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Huynh Banh','1996-10-10','Nam','0123456789',1,31,0),(2,'Nguyen An','1998-06-12','Nu','0987654321',1,23,1),(3,'Tran Bich Thuy','2000-01-11','Nu','0111111111',2,28,1),(4,'Tran Canh','1997-07-26','Nam','0147258369',2,29,1),(11,'Nguyễn Công Phượng','1998-08-17','Nam','07721132120',NULL,32,1);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phanhoi`
--

DROP TABLE IF EXISTS `phanhoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phanhoi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `masp` int NOT NULL,
  `makh` int NOT NULL,
  `noidung` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ngayph` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PHANHOI_MASP_SP_idx` (`masp`),
  KEY `FK_PHANHOI_MAKH_KH_idx` (`makh`),
  CONSTRAINT `FK_PHANHOI_MAKH_KH` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PHANHOI_MASP_SP` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phanhoi`
--

LOCK TABLES `phanhoi` WRITE;
/*!40000 ALTER TABLE `phanhoi` DISABLE KEYS */;
INSERT INTO `phanhoi` VALUES (101,11,1010,'ok','2023-05-13'),(102,11,1010,'\"nhu loz\"','2023-05-14'),(103,11,1010,'haha','2023-05-14'),(104,3,1010,'hahahaa','2023-05-14'),(105,3,1010,'chao` thay`','2023-05-14');
/*!40000 ALTER TABLE `phanhoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phuongthuc_thanhtoan`
--

DROP TABLE IF EXISTS `phuongthuc_thanhtoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phuongthuc_thanhtoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Ten` varchar(100) DEFAULT NULL,
  `MoTa` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phuongthuc_thanhtoan`
--

LOCK TABLES `phuongthuc_thanhtoan` WRITE;
/*!40000 ALTER TABLE `phuongthuc_thanhtoan` DISABLE KEYS */;
INSERT INTO `phuongthuc_thanhtoan` VALUES (1,'Thanh toán COD','Chỉ nhận tiền khi lấy được hàng'),(2,'Thanh toán qua thẻ ngân hàng','Giao dịch qua thẻ credit hoặc debit của ngân hàng');
/*!40000 ALTER TABLE `phuongthuc_thanhtoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `masp` int NOT NULL AUTO_INCREMENT,
  `TenSanPham` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `SoLuong` int DEFAULT NULL,
  `DaBan` int DEFAULT NULL,
  `GiaTien` int DEFAULT NULL,
  `MaHuongVi` int DEFAULT NULL,
  `MoTa` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Anh` varchar(500) DEFAULT NULL,
  `NgayTao` date DEFAULT NULL,
  `TinhTrang` int DEFAULT NULL,
  `GiamGia` int DEFAULT NULL,
  `MaDanhMuc` int DEFAULT NULL,
  PRIMARY KEY (`masp`),
  KEY `FK_SANPHAM_DM_DM_idx` (`MaDanhMuc`),
  KEY `FK_SANPHAM_HV_HV_idx` (`MaHuongVi`),
  CONSTRAINT `FK_SANPHAM_DM_DM` FOREIGN KEY (`MaDanhMuc`) REFERENCES `danhmuc` (`madanhmuc`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_SANPHAM_HV_THV` FOREIGN KEY (`MaHuongVi`) REFERENCES `huongvi` (`mahuongvi`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'Cà phê hạt Lavazza Gran Espresso',30,48,950000,4,'Với sự kế hợp đặt biệt của hạt Arabica, và hạt Robusta tạo nên loại cà phê đậm đà phù hợp với những khách hàng yêu thích Espreso','b0d26b60-2e09-4652-ab0c-707858e7bb6c-gran_espresso.png','2023-04-24',1,10,5),(2,'Cà phê Blagu hạt đã rang',45,72,375000,3,'Hương hoa\r\nSi-rô cùng với hương vị của quả boysenberry và blueberry và một số hương vị khác tạo nên cảm giác mới mẻ cho người uống.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2022/05/blagu-sieuthicafevn-ethiopia-nensebo-refisa.jpg&h=400&w=600&fit=outside&il=1',NULL,1,20,4),(3,'Cà phê nén Nespresso Ristretto',70,94,200000,2,'Trọng tâm duy nhất của Nespresso là cung cấp cà phê chất lượng cao nhất và trải nghiệm cà phê sau mỗi tách','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2022/05/ffee-ispirazione-ristretto-italiano-decaffeinato-capsule-nespresso-50g_f6fa7fcd48584bfe910275490e02781c.webp&h=400&w=600&fit=outside&il=1',NULL,1,15,2),(4,'Cà phê nhân xanh Brazil Fazenda',65,67,300000,1,'Cà phê Brazil từ vùng Fazenda da Lagoa samba mang đến độ chua tròn đầy, vị ngọt dễ chịu, độ phức tạp tốt, hạt Hazelnut, đường nâu, caramel, táo đỏ, mơ, trà hoa hồng, mật đường.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2021/07/greenbeanbrazil-sieuthicafe-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,3),(5,'Cà phê bột Lavazza Ground',80,55,280000,2,'Một loại cà phê đầy đủ hương vị nổi bật với những nốt hương caramel khói','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2021/10/Lavazza-Ground-Coffee-Intenso-sieuthicafe-01.jpg&h=160&w=160&fit=contain&cbg=white&il=1',NULL,1,NULL,1),(6,'Cà phê Blagu hạt đã rang Tazania Kilimanjaro',115,134,80000,3,'Hương hoa cùng độ chua nhẹ, tròn vị, hậu vị ngọt và thơm hương hoa quả nhiệt đới cùng trà đen  ','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2020/03/blagu-sieuthicafevn-tanzania.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,4),(7,'Cà phê hạt Carraro Globo Arabic',0,34,650000,5,'100% arabica- sự phối hợp giữa 4 loại arabica chất lượng tạo nên vị ngọt kéo dài, tinh tế bổi bật hương hoa','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2018/05/Carraro-Globo-Arabica-sieuthicafe-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,5),(8,'Cà phê nén Illy Iperespresso Itenso Bold Roasted',115,32,400000,2,'Cà phê rang được rang đậm, thể hiện đầy đủ hương vị. Một kết thúc mạnh mẽ với các nốt hương sống động của ca cao và trái cây khô.  ','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2021/12/illyiperespressocapsulesintenso-sieuthicafe-02.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,2),(9,'Cà phê nhân xanh Sidamo Guji Bensa',55,27,450000,1,'Hương vị: Hoa cơm cháy (Elderflower), ổi hồng, vỏ nho, rượu vang trắng, quả mọng, mượt','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2021/10/Ethiopia-Arabica-Sidamo-Guji-Bensa-sieuthicafe-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,3),(10,'\r\n\r\nCà phê hạt Carraro Globo Rosso',100,15,600000,4,'Sự pha trộn Globo Rosso bao gồm ba loại Arabica và ba loại Robusta được chọn lọc theo từng đợt','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2018/05/carraro-globo-rosso-sieuthicafe-01.png&h=400&w=600&fit=outside&il=1',NULL,1,NULL,5),(11,'Cà phê hạt Carraro Globo Oro',100,44,650000,4,'100% arabica- sự phối hợp giữa 4 loại arabica chất lượng tạo nên vị ngọt kéo dài, tinh tế bổi bật hương hoa','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2018/05/carroglobooro-sieuthicafe-01.png&h=400&w=600&fit=outside&il=1',NULL,1,NULL,5),(12,'Cà phê nén Blagu Kiểu Ý',150,69,170000,2,'Hương vị cà phê rang xay tự nhiên','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2018/02/capsule-5-sieuthicafe-03.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,2),(13,'Cà phê Blagu hạt đã rang, Gesha Panama vùng Finca Lerida',20,14,750000,3,'Hương vị trái cây và hương hoa đặc trưng, hậu vị với độ chua, ngọt thanh tao và thể chất cân bằng hoàn hảo.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2022/06/gesha-lon.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,4),(14,'Cà phê nhân xanh Golden Arabica Bourbon',80,41,350000,2,'BLAGU nguyên hạt chưa rang Golden Arabica Bourbon 100% (1 kg)','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2016/04/BLAGU-Golden-Arabica-Bourbon-.png&h=400&w=600&fit=outside&il=1',NULL,1,NULL,3),(15,'Cà phê nhân xanh Green Bean Costa Rica Hacienda Sonora Centroamericano',50,48,420000,3,'Cà phê Costa Rica rang sáng có hương vị sống động, với độ chua nhẹ từ trước tạo ra một kết thúc ngọt ngào.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2022/01/costarica-sieuthicafe-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,3),(16,'Cà phê bột Lavazza Crema E Gusto Arabica/Robusta Blend',90,52,220000,5,'Đầy đủ hương vị, và hương thơm, dư vị phong phú với ghi chú sô cô la.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2018/05/Lavazza-Coffee-Crema-E-Gusto-sieuthicafe-04.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,1),(17,'Cà phê bột Lavazza Club 100% Arabica',60,73,310000,5,'Một loại cà phê đặc biệt với hương vị mãnh liệt và hương thơm tinh tế.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2014/05/Lavazza-Coffee-Club-100-Arabica-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,1),(18,'Cà phê viên nén Nespresso Ispirazione Venezia Switzerland',25,115,185000,5,'Hương thơm cà phê tinh tế, sự cân bằng, hài hòa giữa vị Caramel và cà phê sóng sánh.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2021/05/nespressovenezia-sieuthicafe-05.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,2),(19,'Cà phê Viên Nén Starbucks by Nespresso',110,54,250000,5,'Hương thơm ngào ngạt, cơ thể và hương vị đều cân bằng — với vị của các loại hạt và ca cao do quá trình rang mang lại.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2021/02/starbuckshouseblend-sieuthicafe-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,10,2),(20,'Cà phê hạt đã rang Illy Arabica Guatemala',70,38,380000,4,'Vị đậm đà. Cà phê được trồng ở vùng núi hoang sơ của Guatemala có hương thơm phức hợp với hương sô cô la, caramel và mật ong nổi bật','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2020/11/ILLY-COFFEE-ARABICA-ORIGINAL-GUATEMALA-250GR-WHOLE-BEAN-SIEUTHICAFE-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,4),(21,'Cà phê Blagu hạt đã rang Guru 3 blend Cold brew',15,92,170000,2,'Những nốt cho chocolate đậm đà, hòa quyện với sự ngọt ngào của kẹo cam cùng vị trà đen, hậu ngọt kéo dài kèm theo vị chua thanh từ các hạt arabica.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2022/11/blagu-guru-3-cold-brew-bold-cold.png&h=400&w=600&fit=outside&il=1',NULL,1,NULL,4),(22,'Cà phê bột Lavazza Qualita Rossa',35,87,600000,3,'Hỗn hợp Globo Oro bao gồm 5 loại hạt Arabica và ba loại Robusta chất lượng được chọn theo từng đợt. Nhẹ nhàng, hương thơm dai dẳng','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2018/05/rossa-lavazza-sieuthicafe-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,1),(23,'Cà phê hạt Lavazza Espresso Top Class',55,13,950000,1,'Sự pha trộn của nó là thành phần của những hạt cà phê Arabica tốt nhất từ ​​Nam và Trung Mỹ và những hạt cà phê Robusta hảo hạng của Indonesia.','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2021/12/Lavazza-Espresso-Top-Class-Coffee-Beans-sieuthicafe-01.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,5),(24,'Cà phê nhân xanh Ethiopia Nensebo Refisa',10,30,670000,2,'Khi uống bạn có thể cảm nhận được vị ngọt như Si-rô, hương thơm nồng nàn của trái cây, một chút của nốt hương ngọt của đường nâu và mứt anh đào','https://images.weserv.nl/?url=wp.sieuthicafe.vn/wp-content/uploads/2022/04/Ca-phe-nhan-xanh-Green-Beans-Ethiopia-Dry-Process-Nensebo-Refisa-%E2%80%93-1kg-sieuthicafe-1.jpg&h=400&w=600&fit=outside&il=1',NULL,1,NULL,3),(116,'Test',123,12,123,3,'A','10748d81-a9b4-4b8e-97b0-da91bf5cbba1-_.png',NULL,0,12,1),(117,'Test',123,12,123,3,'A',NULL,'2023-04-24',1,13,1);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysdiagrams`
--

DROP TABLE IF EXISTS `sysdiagrams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sysdiagrams` (
  `name` varchar(160) NOT NULL,
  `principal_id` int NOT NULL,
  `diagram_id` int NOT NULL,
  `version` int DEFAULT NULL,
  `definition` longblob,
  PRIMARY KEY (`diagram_id`),
  UNIQUE KEY `UK_principal_name` (`principal_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysdiagrams`
--

LOCK TABLES `sysdiagrams` WRITE;
/*!40000 ALTER TABLE `sysdiagrams` DISABLE KEYS */;
/*!40000 ALTER TABLE `sysdiagrams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `matk` int NOT NULL AUTO_INCREMENT,
  `TaiKhoan` varchar(50) DEFAULT NULL,
  `MatKhau` varchar(500) DEFAULT NULL,
  `NgayTao` date DEFAULT NULL,
  `MaVaiTro` int DEFAULT NULL,
  `TinhTrang` int DEFAULT NULL,
  `password_reset_token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`matk`),
  UNIQUE KEY `UQ__TaiKhoan__D5B8C7F039955D82` (`TaiKhoan`),
  UNIQUE KEY `UQ__TaiKhoan__D5B8C7F08B36CD08` (`TaiKhoan`),
  UNIQUE KEY `UQ__TaiKhoan__D5B8C7F0C701C38D` (`TaiKhoan`),
  UNIQUE KEY `UQ__TaiKhoan__D5B8C7F0AC18A387` (`TaiKhoan`),
  UNIQUE KEY `password_reset_token` (`password_reset_token`),
  KEY `FK_TAIKHOAN_TK_VT_idx` (`MaVaiTro`),
  CONSTRAINT `FK_TAIKHOAN_TK_VT` FOREIGN KEY (`MaVaiTro`) REFERENCES `vaitro` (`mavaitro`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (12,'zafu','$2a$10$yggrV/i6HxymQQVILSLec.2qxBaGXUjXfbQkKbmump3OhVSIA/YGS','2023-04-03',3,1,NULL),(13,'taolaongiaphu','$2a$10$9D6EnTNpo7qx5sAM4s5.1O/26FC7E/cBdkyQa0QbzA0q2G44jeWoK','2023-04-03',3,1,NULL),(14,'giaphuvip','$2a$10$/CSQMc3viQHnudGGxMGIuOf0yID4ZDJ96dXyHUymv5V6bQeb4FwJ6','2023-04-03',3,1,NULL),(15,'real','$2a$10$H6AUfmzm6CjpJ.W5xRmynO3MSCl04YCO7IIOgM1BVe4sXmo2hr4iG','2023-04-04',3,1,NULL),(16,'letgo','$2a$10$MenJs/fgq1ibKU2JtPuQgOFcnnSW4ZD542leZ4/qV/GSGvwbq/MKa','2023-04-09',3,1,NULL),(17,'CongFung','$2a$10$zwsmraH066/HqTXoUsO4iuJdpAmBnMWZ4yGCUfXW7vpPR0N/MO3Hm','2023-04-12',3,1,NULL),(18,'sangpro123','$2a$10$53j8.1b5P91iEcifTX5tfuoGdriDXmoRLpTmVwtbCS0I7olSGwavG','2023-04-12',3,1,NULL),(19,'zafu1','$2a$10$PjdcWqhK8fYuHWU0rY9zLO3dXE3eZ6mTEnRi4OXvoF6vtuUhJPaRm','2023-04-15',3,1,NULL),(20,'daka8','$2a$10$k9.ptaLhRi7fmIDoLynoA.ZDzTVsIMauxUtDMfv65kBav8j5lmRf2','2023-04-16',3,1,NULL),(21,'jnt12','$2a$10$MR8gtIXkRIyvr59n8B8M1OIUkG0fd/tc418Qxd/krZT4mGZchY522','2023-04-22',3,1,NULL),(22,'tk1','$2a$10$Wm9gBBpP3PG9Sxa6.a5st.JSta..P/fIUKXKtWaY5dbProjCmaaru','2023-04-22',3,1,NULL),(23,'test1','$2a$10$OhxT/xU1g5YpffZjeHmRW.OutiA3hSzy5qnk/OTX1BU5FTLU/vLHm','2023-04-24',2,1,NULL),(28,'temp1','$2a$10$O6KRhhBfgjHZi3KYjwsaA.8gPuTACD8A1kiY7/EyW2v2XXpx60NYq','2023-04-24',2,1,NULL),(29,'temp2','123','2023-04-23',2,1,NULL),(30,'me','123','2023-04-23',2,1,NULL),(31,'admin','$2a$10$cWjk2hCTjzzeYmLh52Fa8eQaZHioWfRHsloxccXaDCqYvoIkqOLqW','2023-04-28',1,1,NULL),(32,'test2','123',NULL,2,1,NULL),(33,'vovanduc','$2a$10$GdeMnMTomMHy0ISFwi7vpOyn7Dbm8eHVYnIQICxTqiVGFzVeSQ70W','2023-05-09',3,1,NULL),(34,'hellobro','$2a$10$ct6ICfNh9Yw/.zG5BBHay.0Li1S4XwrruyHxX4bvc8he8yX9ohgMC','2023-05-12',3,1,NULL);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaitro`
--

DROP TABLE IF EXISTS `vaitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaitro` (
  `mavaitro` int NOT NULL AUTO_INCREMENT,
  `TenVaiTro` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`mavaitro`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaitro`
--

LOCK TABLES `vaitro` WRITE;
/*!40000 ALTER TABLE `vaitro` DISABLE KEYS */;
INSERT INTO `vaitro` VALUES (1,'Admin'),(2,'Nhan Vien'),(3,'KhachHang');
/*!40000 ALTER TABLE `vaitro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-14 21:18:10
ALTER TABLE khachhang ADD CONSTRAINT unique_email UNIQUE (Email);
Alter table taikhoan ADD Constraint taikhoan_unique UNIQUE (TaiKhoan);
