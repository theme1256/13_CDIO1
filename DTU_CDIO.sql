-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 09, 2020 at 10:47 AM
-- Server version: 5.7.29-0ubuntu0.16.04.1-log
-- PHP Version: 7.1.32-1+ubuntu16.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `DTU_CDIO`
--
CREATE DATABASE IF NOT EXISTS `DTU_CDIO` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `DTU_CDIO`;

-- --------------------------------------------------------

--
-- Table structure for table `Userlist`
--

DROP TABLE IF EXISTS `Userlist`;
CREATE TABLE `Userlist` (
  `UserID` int(11) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `INI` varchar(4) DEFAULT NULL,
  `CPR` varchar(11) NOT NULL,
  `userPS` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `UserRoles`
--

DROP TABLE IF EXISTS `UserRoles`;
CREATE TABLE `UserRoles` (
  `UserID` int(11) NOT NULL,
  `Role` enum('Admin','Pharmacist','Foreman','Operator') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Userlist`
--
ALTER TABLE `Userlist`
  ADD PRIMARY KEY (`UserID`,`UserName`,`CPR`);

--
-- Indexes for table `UserRoles`
--
ALTER TABLE `UserRoles`
  ADD PRIMARY KEY (`UserID`,`Role`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `UserRoles`
--
ALTER TABLE `UserRoles`
  ADD CONSTRAINT `UserRoles_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `Userlist` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
