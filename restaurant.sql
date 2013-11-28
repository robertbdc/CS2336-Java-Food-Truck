-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 27, 2013 at 03:15 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `restaurant`
--
CREATE DATABASE IF NOT EXISTS `restaurant` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `restaurant`;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `MenuSeqNo` int(11) NOT NULL AUTO_INCREMENT,
  `Category` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `ItemNo` int(11) NOT NULL,
  `ItemName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Price` double NOT NULL,
  PRIMARY KEY (`MenuSeqNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=17 ;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`MenuSeqNo`, `Category`, `ItemNo`, `ItemName`, `Price`) VALUES
(1, 'A', 1, 'Bruschetta', 5.29),
(2, 'A', 2, 'Cabrese Flatbread', 6.1),
(3, 'A', 3, 'Artichoke-Spinach Dip', 3.99),
(4, 'A', 4, 'Lasagna Fritta', 4.99),
(5, 'A', 5, 'Mozzarella Fonduta', 5.99),
(6, 'E', 1, 'Lasagna Classico', 6.99),
(7, 'E', 2, 'Capellini Pomodoro', 7.99),
(8, 'E', 3, 'Eggplant Parmigiana', 8.99),
(9, 'E', 4, 'Fettuccine Alfredo', 7.49),
(10, 'E', 5, 'Tour of Italy', 14.99),
(11, 'D', 1, 'Tiramisu', 2.99),
(12, 'D', 2, 'Zepppoli', 2.49),
(13, 'D', 3, 'Dolcini', 3.49),
(14, 'S', 1, 'Soda', 1.99),
(15, 'S', 2, 'Bella Limonata', 0.99),
(16, 'S', 3, 'Berry Acqua Fresca', 2.88);

-- --------------------------------------------------------

--
-- Table structure for table `tablelist`
--

CREATE TABLE IF NOT EXISTS `tablelist` (
  `TableSeqNo` int(11) NOT NULL AUTO_INCREMENT,
  `TableNo` int(11) NOT NULL,
  `WaiterSeqNo` int(11) NOT NULL,
  PRIMARY KEY (`TableSeqNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

--
-- Dumping data for table `tablelist`
--

INSERT INTO `tablelist` (`TableSeqNo`, `TableNo`, `WaiterSeqNo`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 5, 1),
(4, 9, 1),
(5, 11, 1),
(6, 15, 1),
(7, 3, 2),
(8, 4, 2),
(9, 6, 2),
(10, 7, 2),
(11, 17, 2),
(12, 18, 2),
(13, 8, 3),
(14, 10, 3),
(15, 12, 3),
(16, 13, 4),
(17, 14, 4),
(18, 26, 4);

-- --------------------------------------------------------

--
-- Table structure for table `waiter`
--

CREATE TABLE IF NOT EXISTS `waiter` (
  `WaiterSeqNo` int(11) NOT NULL AUTO_INCREMENT,
  `WaiterName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`WaiterSeqNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `waiter`
--

INSERT INTO `waiter` (`WaiterSeqNo`, `WaiterName`) VALUES
(1, 'Nancy Jean'),
(2, 'Bubba Lee'),
(3, 'Jim Bob'),
(4, 'Laura Flo');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
