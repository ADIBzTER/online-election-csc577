-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 04, 2021 at 05:55 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `election_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `a_userid` varchar(15) NOT NULL,
  `a_password` varchar(15) NOT NULL,
  `a_name` varchar(100) NOT NULL,
  `a_ended` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`a_userid`, `a_password`, `a_name`, `a_ended`) VALUES
('admin', 'admin', 'Admin', 0),
('dummy', 'password', 'Dummy', 0);

-- --------------------------------------------------------

--
-- Table structure for table `candidates`
--

CREATE TABLE `candidates` (
  `c_userid` varchar(10) NOT NULL,
  `c_image_location` varchar(500) NOT NULL,
  `c_achievement` varchar(500) NOT NULL,
  `c_manifesto` varchar(500) NOT NULL,
  `c_approved` tinyint(1) NOT NULL DEFAULT 0,
  `c_votes` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `candidates`
--

INSERT INTO `candidates` (`c_userid`, `c_image_location`, `c_achievement`, `c_manifesto`, `c_approved`, `c_votes`) VALUES
('2019415268', 'images/1628047679237.png', 'Champion of National Table Tenis Competition 2018', 'Improve sports facility for all sport that exists in the university', 1, 0),
('2019415604', 'images/1628047450667.png', 'Winner of national public speaking competition', 'Increase social activity by running weekly socials in different venues across Malacca', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `s_userid` varchar(15) NOT NULL,
  `s_password` varchar(15) NOT NULL,
  `s_name` varchar(100) NOT NULL,
  `s_faculty` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`s_userid`, `s_password`, `s_name`, `s_faculty`) VALUES
('2019218362', 'a', 'SARAH UMAIRAH BINTI MOHAMMAD RIJALUL HAQ', 'FAKULTI SAINS KOMPUTER DAN MATEMATIK'),
('2019295348', 'a', 'NUR FATIN NAJWA BINTI MOHD NIZAM', 'FAKULTI SAINS KOMPUTER DAN MATEMATIK'),
('2019415268', 'a', 'NURHAIKAL FIKHRI BIN NGADIMON', 'FAKULTI SAINS KOMPUTER DAN MATEMATIK'),
('2019415604', 'a', 'MUHAMMAD ADIB ZAINI BIN JEMANI', 'FAKULTI SAINS KOMPUTER DAN MATEMATIK'),
('2019685398', 'a', 'NUR EFFA ALYANA BINTI MUHAMMAD ALI', 'FAKULTI SAINS KOMPUTER DAN MATEMATIK');

-- --------------------------------------------------------

--
-- Table structure for table `voters`
--

CREATE TABLE `voters` (
  `v_userid` varchar(15) NOT NULL,
  `v_voted` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`a_userid`);

--
-- Indexes for table `candidates`
--
ALTER TABLE `candidates`
  ADD PRIMARY KEY (`c_userid`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`s_userid`);

--
-- Indexes for table `voters`
--
ALTER TABLE `voters`
  ADD PRIMARY KEY (`v_userid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `candidates`
--
ALTER TABLE `candidates`
  ADD CONSTRAINT `candidates_ibfk_1` FOREIGN KEY (`c_userid`) REFERENCES `students` (`s_userid`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `voters`
--
ALTER TABLE `voters`
  ADD CONSTRAINT `voters_ibfk_1` FOREIGN KEY (`v_userid`) REFERENCES `students` (`s_userid`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
