ASSIGNMENT:PARKING MANAGEMENT SYSTEM
GROUP 13
YEAR:3 BIT
NAMES                                REG.NO
======                              =========    
1.NYIRANZIZA Felicite                  223005134
2.MUJAWIMANA Claudine                  223011219  
3.NEZERWA GIKUNDIRO Sandrine           222007415        




-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 26, 2025 at 04:38 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parkingmanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `parkingrecords`
--

DROP TABLE IF EXISTS `parkingrecords`;
CREATE TABLE IF NOT EXISTS `parkingrecords` (
  `recordid` int NOT NULL AUTO_INCREMENT,
  `vehicleid` int DEFAULT NULL,
  `slotid` int DEFAULT NULL,
  `attendantid` int DEFAULT NULL,
  `entrytime` datetime NOT NULL,
  `exittime` datetime DEFAULT NULL,
  `totalamount` decimal(8,2) DEFAULT NULL,
  `status` enum('Active','Completed') DEFAULT 'Active',
  PRIMARY KEY (`recordid`),
  KEY `vehicleid` (`vehicleid`),
  KEY `slotid` (`slotid`),
  KEY `attendantid` (`attendantid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `parkingslots`
--

DROP TABLE IF EXISTS `parkingslots`;
CREATE TABLE IF NOT EXISTS `parkingslots` (
  `slotid` int NOT NULL AUTO_INCREMENT,
  `slotnumber` varchar(10) NOT NULL,
  `slottype` enum('Regular','Disabled','VIP') NOT NULL,
  `status` enum('Available','Occupied','Maintenance') DEFAULT 'Available',
  `hourlyrate` decimal(5,2) NOT NULL,
  PRIMARY KEY (`slotid`),
  UNIQUE KEY `slotnumber` (`slotnumber`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `parkingslots`
--

INSERT INTO `parkingslots` (`slotid`, `slotnumber`, `slottype`, `status`, `hourlyrate`) VALUES
(1, 'A-001', 'Regular', 'Available', 5.00),
(2, 'A-002', 'Regular', 'Available', 5.00),
(3, 'B-001', 'Disabled', 'Available', 3.00),
(4, 'C-001', 'VIP', 'Available', 10.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` enum('admin','attendant','manager') NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `username`, `password`, `phone`, `email`, `role`) VALUES
(1, 'admin', 'admin123', '1234567890', 'admin@parking.com', 'admin'),
(2, 'attendant1', 'att123', '0987654321', 'att1@parking.com', 'attendant'),
(3, 'manager1', 'mgr123', '1122334455', 'mgr@parking.com', 'manager');

-- --------------------------------------------------------

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE IF NOT EXISTS `vehicles` (
  `vehicleid` int NOT NULL AUTO_INCREMENT,
  `platenumber` varchar(20) NOT NULL,
  `vehicletype` enum('Car','Motorcycle','Truck','SUV') NOT NULL,
  `color` varchar(20) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`vehicleid`),
  UNIQUE KEY `platenumber` (`platenumber`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vehicles`
--

INSERT INTO `vehicles` (`vehicleid`, `platenumber`, `vehicletype`, `color`, `brand`) VALUES
(1, 'RAC 123T', 'SUV', 'black', 'TOYOTA');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
