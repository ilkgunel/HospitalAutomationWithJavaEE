-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 22, 2015 at 09:09 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `HospitalAutomation`
--

-- --------------------------------------------------------

--
-- Table structure for table `doktorlar`
--

CREATE TABLE IF NOT EXISTS `doktorlar` (
  `doktorid` int(11) NOT NULL,
  `bransid` int(11) DEFAULT NULL,
  `doktoradi` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `hastaneid` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `doktorlar`
--

INSERT INTO `doktorlar` (`doktorid`, `bransid`, `doktoradi`, `hastaneid`) VALUES
(1, 1, 'Hasan Karaoğlu', 1),
(2, 1, 'Muhammed Murat Tutar', 2),
(3, 1, 'Halil İbrahim Genç', 2),
(4, 2, 'Sezai Aydın', 3),
(5, 2, 'Oğuzcan Görücü', 3),
(6, 3, 'Tolga Durak', 4),
(7, 3, 'Doğa Sezgin', 4),
(8, 4, 'Engin Küllü', 5),
(9, 4, 'Mustafa Hastürk', 5),
(10, 2, 'Nektel Demir', 6);

-- --------------------------------------------------------

--
-- Table structure for table `hastaneler`
--

CREATE TABLE IF NOT EXISTS `hastaneler` (
  `id` int(11) NOT NULL,
  `hastaneadi` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `ilceid` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `hastaneler`
--

INSERT INTO `hastaneler` (`id`, `hastaneadi`, `ilceid`) VALUES
(1, 'Eyüp Devlet Hastanesi', 1),
(2, 'Okmeydanı Devlet Hastanesi', 2),
(3, 'Keçiören Eğitim Araştırma Hastanesi', 3),
(4, 'Atatürk Eğitim Ve Araştırma Hastanesi', 4),
(5, 'Alper Çizgenakat Çeşme Devlet Hastanesi', 5),
(6, 'Foça Devlet Hastanesi', 6);

-- --------------------------------------------------------

--
-- Table structure for table `ilceler`
--

CREATE TABLE IF NOT EXISTS `ilceler` (
  `id` int(11) NOT NULL,
  `ilce` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `sehir` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `ilceler`
--

INSERT INTO `ilceler` (`id`, `ilce`, `sehir`) VALUES
(1, 'Eyüp', 34),
(2, 'Şişli', 34),
(3, 'Keçiören', 6),
(4, 'Çankaya', 6),
(5, 'Çeşme', 35),
(6, 'Foça', 35);

-- --------------------------------------------------------

--
-- Table structure for table `iller`
--

CREATE TABLE IF NOT EXISTS `iller` (
  `id` int(11) NOT NULL,
  `sehir` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `iller`
--

INSERT INTO `iller` (`id`, `sehir`) VALUES
(6, 'Ankara'),
(34, 'İstanbul'),
(35, 'İzmir'),
(55, 'Samsun'),
(61, 'Trabzon');

-- --------------------------------------------------------

--
-- Table structure for table `klinikler`
--

CREATE TABLE IF NOT EXISTS `klinikler` (
  `id` int(11) NOT NULL,
  `klinikadi` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `klinikler`
--

INSERT INTO `klinikler` (`id`, `klinikadi`) VALUES
(1, 'Genel Cerrahi'),
(2, 'Dahiliye'),
(3, 'Ortopedi'),
(4, 'Göğüs Hastalıkları');

-- --------------------------------------------------------

--
-- Table structure for table `klinikyerleri`
--

CREATE TABLE IF NOT EXISTS `klinikyerleri` (
  `id` int(11) NOT NULL,
  `hastaneid` int(11) DEFAULT NULL,
  `klinikyeri` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `klinikyerleri`
--

INSERT INTO `klinikyerleri` (`id`, `hastaneid`, `klinikyeri`) VALUES
(1, 1, 'Merkez'),
(2, 2, 'Merkez'),
(3, 2, 'Gülbahar'),
(4, 3, 'Merkez'),
(5, 4, 'Merkez'),
(6, 5, 'Merkez'),
(7, 6, 'Merkez');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `identitynumber` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `birthdate` date DEFAULT NULL,
  `birthplace` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `emailaddress` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '0',
  `fathername` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `mothername` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `phonenumber` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE IF NOT EXISTS `patients` (
  `identitynumber` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `birthdate` date DEFAULT NULL,
  `birthplace` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `emailaddress` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '0',
  `fathername` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `mothername` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `phonenumber` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`identitynumber`, `birthdate`, `birthplace`, `emailaddress`, `enabled`, `fathername`, `gender`, `mothername`, `name`, `password`, `phonenumber`, `role`, `surname`) VALUES
('11111111111', '1993-01-01', 'Samsun', 'abc@abc.com', 1, 'Üzeyir', 'Erkek', 'Fatma', 'İlkay', '827ccb0eea8a706c4c34a16891f84e7b', '0(511) 111-1111', 'ROLE_GUEST', 'Günel'),
('11111111112', '0020-10-11', 'İstanbul', 'bc@bc.com', 1, 'İsmail', 'Erkek', 'Adile', 'Turgay', '827ccb0eea8a706c4c34a16891f84e7b', '0(511) 111-1111', 'ROLE_GUEST', 'Günel');

--
-- Triggers `patients`
--
DELIMITER $$
CREATE TRIGGER `addUserRole` AFTER INSERT ON `patients`
 FOR EACH ROW INSERT INTO userroles(role,useridentitynumber) VALUES(NEW.role,NEW.identitynumber)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `persistent_logins`
--

CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `series` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `token` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `RANDEVUSAATLERI`
--

CREATE TABLE IF NOT EXISTS `RANDEVUSAATLERI` (
  `SAATID` int(11) NOT NULL,
  `DOKTORID` int(11) DEFAULT NULL,
  `RANDEVUID` int(11) DEFAULT NULL,
  `SAAT` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `SAATALINDIMI` tinyint(1) DEFAULT '0',
  `TITLE` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `RANDEVUSAATLERI`
--

INSERT INTO `RANDEVUSAATLERI` (`SAATID`, `DOKTORID`, `RANDEVUID`, `SAAT`, `SAATALINDIMI`, `TITLE`) VALUES
(1, 1, 1, '09:00', 0, ''),
(2, 1, 1, '09:15', 0, ''),
(3, 1, 1, '09:30', 0, ''),
(4, 1, 1, '09:45', 0, ''),
(5, 1, 1, '10:00', 0, ''),
(6, 3, 3, '09:00', 0, ''),
(7, 3, 3, '09:20', 0, ''),
(8, 3, 3, '09:40', 0, ''),
(9, 3, 3, '10:00', 0, ''),
(10, 3, 3, '10:15', 0, ''),
(11, 3, 3, '10:30', 0, ''),
(12, 2, 4, '09:00', 0, ''),
(13, 2, 4, '09:15', 0, ''),
(14, 2, 4, '09:30', 0, ''),
(15, 2, 4, '09:45', 0, ''),
(16, 2, 4, '10:00', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `takenappointments`
--

CREATE TABLE IF NOT EXISTS `takenappointments` (
  `takedappointmentid` int(11) NOT NULL,
  `clockid` int(11) NOT NULL,
  `clinicname` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `clinicplace` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `doctorid` int(11) DEFAULT NULL,
  `hospitalname` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `hour` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `patientid` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `wasappointmentcancelled` tinyint(1) NOT NULL,
  `datepassed` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `takenappointments`
--

INSERT INTO `takenappointments` (`takedappointmentid`, `clockid`, `clinicname`, `clinicplace`, `date`, `doctorid`, `hospitalname`, `hour`, `patientid`, `wasappointmentcancelled`, `datepassed`) VALUES
(6, 15, 'Genel Cerrahi', 'Merkez', '2015-07-21', 2, 'Okmeydanı Devlet Hastanesi', '09:45', '11111111111', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `userroles`
--

CREATE TABLE IF NOT EXISTS `userroles` (
  `id` int(11) NOT NULL,
  `role` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `useridentitynumber` varchar(11) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `userroles`
--

INSERT INTO `userroles` (`id`, `role`, `useridentitynumber`) VALUES
(1, 'ROLE_GUEST', '11111111111'),
(2, 'ROLE_GUEST', '11111111112');

-- --------------------------------------------------------

--
-- Table structure for table `uygunrandevular`
--

CREATE TABLE IF NOT EXISTS `uygunrandevular` (
  `uygunrandevuid` int(11) NOT NULL,
  `doktoradi` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `doktorid` int(11) DEFAULT NULL,
  `hastaneid` int(11) DEFAULT NULL,
  `klinikid` int(11) DEFAULT NULL,
  `klinikyeri` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `randevualindimi` tinyint(1) DEFAULT '0',
  `tarih` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `uygunrandevular`
--

INSERT INTO `uygunrandevular` (`uygunrandevuid`, `doktoradi`, `doktorid`, `hastaneid`, `klinikid`, `klinikyeri`, `randevualindimi`, `tarih`) VALUES
(1, 'Hasan Karaoğlu', 1, 1, 1, 'Merkez', 0, '2015-06-14'),
(2, 'Muhammed Murat Tutar', 2, 2, 1, 'Merkez', 0, '2015-06-14'),
(3, 'Halil İbrahim Genç', 3, 2, 1, 'Merkez', 0, '2015-06-14'),
(4, 'Muhammed Murat Tutar', 2, 2, 1, 'Merkez', 0, '2015-07-21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doktorlar`
--
ALTER TABLE `doktorlar`
  ADD PRIMARY KEY (`doktorid`);

--
-- Indexes for table `hastaneler`
--
ALTER TABLE `hastaneler`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ilceler`
--
ALTER TABLE `ilceler`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `iller`
--
ALTER TABLE `iller`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `klinikler`
--
ALTER TABLE `klinikler`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `klinikyerleri`
--
ALTER TABLE `klinikyerleri`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`identitynumber`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`identitynumber`);

--
-- Indexes for table `persistent_logins`
--
ALTER TABLE `persistent_logins`
  ADD PRIMARY KEY (`series`);

--
-- Indexes for table `RANDEVUSAATLERI`
--
ALTER TABLE `RANDEVUSAATLERI`
  ADD PRIMARY KEY (`SAATID`);

--
-- Indexes for table `takenappointments`
--
ALTER TABLE `takenappointments`
  ADD PRIMARY KEY (`takedappointmentid`);

--
-- Indexes for table `userroles`
--
ALTER TABLE `userroles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uygunrandevular`
--
ALTER TABLE `uygunrandevular`
  ADD PRIMARY KEY (`uygunrandevuid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doktorlar`
--
ALTER TABLE `doktorlar`
  MODIFY `doktorid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `hastaneler`
--
ALTER TABLE `hastaneler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `ilceler`
--
ALTER TABLE `ilceler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `iller`
--
ALTER TABLE `iller`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=62;
--
-- AUTO_INCREMENT for table `klinikler`
--
ALTER TABLE `klinikler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `klinikyerleri`
--
ALTER TABLE `klinikyerleri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `RANDEVUSAATLERI`
--
ALTER TABLE `RANDEVUSAATLERI`
  MODIFY `SAATID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `takenappointments`
--
ALTER TABLE `takenappointments`
  MODIFY `takedappointmentid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `userroles`
--
ALTER TABLE `userroles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `uygunrandevular`
--
ALTER TABLE `uygunrandevular`
  MODIFY `uygunrandevuid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
