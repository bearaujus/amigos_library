-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2021 at 09:01 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpus_pae`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_perpus`
--

CREATE TABLE `admin_perpus` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_perpus`
--

INSERT INTO `admin_perpus` (`id`, `nama`, `password`, `tanggal_lahir`, `username`) VALUES
(1, 'root', 'qweqwe', '1999-09-24', 'qweqwe'),
(2, 'qweqwe', 'asdasd', '2021-06-01', 'asdasd');

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `id` int(11) NOT NULL,
  `is_banned` tinyint(1) DEFAULT 0,
  `nama` varchar(255) DEFAULT NULL,
  `no_hp` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `tanggal_bergabung` date DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id`, `is_banned`, `nama`, `no_hp`, `password`, `tanggal_bergabung`, `tanggal_lahir`, `username`) VALUES
(1, 0, 'root', '04827481099', 'qweqwe', '2021-06-13', '1999-08-23', 'qweqwe'),
(2, 1, 'asdasd', 'as25125152', 'asdasd', '2021-06-20', '2021-06-01', 'asdasd');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id` int(11) NOT NULL,
  `gambar_link` varchar(255) DEFAULT NULL,
  `gambar_src` varchar(255) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `jumlah_halaman` int(11) DEFAULT NULL,
  `pengarang` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `tahun_terbit` date DEFAULT NULL,
  `id_kategori` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id`, `gambar_link`, `gambar_src`, `harga`, `judul`, `jumlah_halaman`, `pengarang`, `rating`, `stok`, `tahun_terbit`, `id_kategori`) VALUES
(1, 'https://imgv2-1-f.scribdassets.com/img/word_document/239488191/original/216x287/e2705c0b3d/1623260028?v=1', 'https://www.scribd.com/book/239488191/The-Innovators-How-a-Group-of-Hackers-Geniuses-and-Geeks-Created-the-Digital-Revolution', 250000, 'The Innovators: How a Group of Hackers, Geniuses, and Geeks Created the Digital Revolution', 873, 'Walter Isaacson', 4, 5, '2013-09-04', 1),
(2, 'https://imgv2-1-f.scribdassets.com/img/word_document/449815155/original/216x287/5d317a1cab/1620609801?v=1', 'https://www.scribd.com/book/449815155/AI-Superpowers-China-Silicon-Valley-and-the-New-World-Order', 175000, 'AI Superpowers: China, Silicon Valley, and the New World Order', 365, 'Kai-Fu Lee', 4, 6, '2013-09-05', 1),
(3, 'https://imgv2-2-f.scribdassets.com/img/word_document/314291430/original/216x287/b9b4f68690/1617290164?v=1', 'https://www.scribd.com/book/314291430/Coding-For-Dummies', 200000, 'Coding For Dummies', 434, 'Nikhil Abraham', 5, 7, '2013-09-06', 1),
(4, 'https://imgv2-1-f.scribdassets.com/img/word_document/163652058/original/432x574/d20aee3206/1622840675?v=1', 'https://www.scribd.com/book/163652058/Through-the-Ever-Night', 150000, 'Through the Ever Night', 314, 'Veronica rossi', 4, 7, '2017-06-27', 2),
(5, 'https://imgv2-1-f.scribdassets.com/img/word_document/225126950/original/432x574/79052cf8d6/1623072267?v=1', 'https://www.scribd.com/book/225126950/Uglies', 140000, 'Uglies', 423, 'Scott Westerfeld', 4, 4, '2006-05-10', 2),
(6, 'https://imgv2-2-f.scribdassets.com/img/word_document/489957388/original/216x287/9c495a7da6/1622269861?v=1', 'https://www.scribd.com/book/489957388/Renegades-Chapter-Sampler', 100000, 'Renegades Chapter Sampler', 98, 'Marissa Meyer', 5, 5, '2017-10-10', 2),
(7, 'https://imgv2-2-f.scribdassets.com/img/word_document/406535992/original/216x287/27e880328a/1623260172?v=1', 'https://www.scribd.com/book/406535992/The-Unhoneymooners', 180000, 'The Unhoneymooners', 354, 'Christina Lauren', 4, 7, '2013-09-07', 3),
(8, 'https://imgv2-2-f.scribdassets.com/img/word_document/233886933/original/216x287/e4fde59aaf/1623264908?v=1', 'https://www.scribd.com/book/233886933/Maybe-Someday', 190000, 'Maybe Someday', 443, 'Collen Hoover', 4, 5, '2013-09-08', 3),
(9, 'https://imgv2-2-f.scribdassets.com/img/word_document/163637573/original/216x287/827d8d1fb4/1623067136?v=1', 'https://www.scribd.com/book/163637573/Dancing-at-Midnight', 185000, 'Dancing at Midnight', 389, 'Julia Quinn', 4, 6, '2013-09-09', 3),
(10, 'https://imgv2-1-f.scribdassets.com/img/word_document/239946972/original/216x287/e81adac1fb/1617229623?v=1', 'https://www.scribd.com/book/239946972/Mastering-the-Art-of-Chinese-Cooking', 450000, 'Mastering the Art of Chinese Cooking', 776, 'Eileen Yin-Fei Lo and Susie Cushner', 4, 4, '2013-09-10', 4),
(11, 'https://imgv2-2-f.scribdassets.com/img/word_document/449814265/original/216x287/ac773fe009/1620349701?v=1', 'https://www.scribd.com/book/449814265/Betty-Crocker-Lost-Recipes-Beloved-Vintage-Recipes-for-Today-s-Kitchen', 187000, 'Betty Crocker Lost Recipes: Beloved Vintage Recipes for Today\'s Kitchen', 385, 'Betty Crocker', 4, 5, '2013-09-11', 4),
(12, 'https://imgv2-1-f.scribdassets.com/img/word_document/239832263/original/216x287/3c2dc8a5f0/1617229617?v=1', 'https://www.scribd.com/book/239832263/Flour-A-Baker-s-Collection-of-Spectacular-Recipes', 500000, 'Flour: A Baker\'s Collection of Spectacular Recipes', 575, 'Joanne Chang, Christie Matheson and Keller Keller', 4, 5, '2013-09-12', 4),
(13, 'https://imgv2-2-f.scribdassets.com/img/word_document/249309715/original/216x287/e07c88f0d3/1619054887?v=1', 'https://www.scribd.com/book/249309715/Merle-s-Door-Lessons-from-a-Freethinking-Dog', 125000, 'Merle\'s Door: Lessons from a Freethinking Dog', 539, 'Ted Kerasote', 4, 5, '2008-04-21', 5),
(14, 'https://imgv2-1-f.scribdassets.com/img/word_document/401747960/original/216x287/1a38520b35/1621394064?v=1', 'https://www.scribd.com/book/401747960/Lessons-From-Lucy-The-Simple-Joys-of-an-Old-Happy-Dog', 130000, 'Lessons From Lucy: The Simple Joys of an Old, Happy Dog', 179, 'Dave Berry', 4, 5, '2019-04-02', 5),
(15, 'https://imgv2-1-f.scribdassets.com/img/word_document/323917609/original/216x287/3fd02cd99b/1622840848?v=1', 'https://www.scribd.com/book/323917609/Lucky-Dog-Lessons-Train-Your-Dog-in-7-Days', 100000, 'Lucky Dog Lessons: Train Your Dog in 7 Days', 386, 'Brandon McMillan', 5, 6, '2016-10-04', 5),
(16, 'https://imgv2-2-f.scribdassets.com/img/word_document/356399357/original/216x287/2f5d7258f9/1623411170?v=1', 'https://www.scribd.com/book/356399357/Blood-Sweat-and-Pixels-The-Triumphant-Turbulent-Stories-Behind-How-Video-Games-Are-Made', 145000, 'Blood, Sweat, and Pixels: The Triumphant, Turbulent Stories Behind How Video Games Are Made', 378, 'Jason Schreier', 4, 5, '2017-09-05', 6),
(17, 'https://imgv2-1-f.scribdassets.com/img/word_document/306219423/original/216x287/2e0314272c/1618822200?v=1', 'https://www.scribd.com/book/306219423/Pokemon-X-Walkthrough-and-Pokemon-Y-Walkthrough-Ult%C4%B1mate-Game-Guides', 120000, 'Pokémon X Walkthrough and Pokémon Y Walkthrough Ultımate Game Guides', 258, 'Game Ultımate Game Guides', 4, 5, '2016-03-24', 6),
(18, 'https://imgv2-2-f.scribdassets.com/img/word_document/366624722/original/216x287/03ed5d63ee/1621393627?v=1', 'https://www.scribd.com/book/366624722/The-Ultimate-Roblox-Book-An-Unofficial-Guide-Learn-How-to-Build-Your-Own-Worlds-Customize-Your-Games-and-So-Much-More', 110000, 'The Ultimate Roblox Book: An Unofficial Guide: Learn How to Build Your Own Worlds, Customize Your Games, and So Much More!', 237, 'David Jagneaux', 5, 7, '2018-01-02', 6),
(19, 'https://imgv2-1-f.scribdassets.com/img/word_document/221042173/original/216x287/a3b2ebc5cd/1623148518?v=1', 'https://www.scribd.com/book/221042173/Console-Wars-Sega-Nintendo-and-the-Battle-that-Defined-a-Generation', 525000, 'Console Wars: Sega, Nintendo, and the Battle that Defined a Generation', 808, 'Blake J. Harris', 4, 3, '2019-12-07', 1),
(20, 'https://imgv2-1-f.scribdassets.com/img/word_document/383643735/original/216x287/27d573aaaf/1622840899?v=1', 'https://www.scribd.com/book/383643735/Chaos-Monkeys-Obscene-Fortune-and-Random-Failure-in-Silicon-Valley', 490000, 'Chaos Monkeys: Obscene Fortune and Random Failure in Silicon Valley', 757, 'Antonio Garcia Martinez', 4, 4, '2019-12-07', 1),
(21, 'https://imgv2-2-f.scribdassets.com/img/word_document/353201256/original/216x287/ca3fde8641/1623260918?v=1', 'https://www.scribd.com/book/353201256/Introducing-Artificial-Intelligence-A-Graphic-Guide', 195000, 'Introducing Artificial Intelligence: A Graphic Guide', 332, 'Henry Brighton and Howard Selina', 4, 6, '2015-03-09', 1),
(22, 'https://imgv2-2-f.scribdassets.com/img/word_document/449813372/original/216x287/b9f48c407f/1621819156?v=1', 'https://www.scribd.com/book/449813372/LikeWar-The-Weaponization-of-Social-Media', 350000, 'LikeWar: The Weaponization of Social Media', 632, 'P. W. Singer and Emerson T. Brooking', 4, 5, '2018-02-10', 1),
(23, 'https://imgv2-1-f.scribdassets.com/img/word_document/348099344/original/216x287/4dd4018ba7/1623243926?v=1', 'https://www.scribd.com/book/348099344/The-Seven-Husbands-of-Evelyn-Hugo-A-Novel', 250000, 'The Seven Husbands of Evelyn Hugo: A Novel', 477, 'Taylor Jenkins Reid', 4, 4, '2018-01-06', 3),
(24, 'https://imgv2-2-f.scribdassets.com/img/word_document/224421568/original/216x287/2329021f49/1621384282?v=1', 'https://www.scribd.com/book/224421568/Heartbreaker', 275000, 'Heartbreaker', 533, 'Julie Garwood', 4, 5, '2002-09-02', 3),
(25, 'https://imgv2-2-f.scribdassets.com/img/word_document/428513343/original/216x287/c6bd476534/1623260328?v=1', 'https://www.scribd.com/book/428513343/Twice-in-a-Blue-Moon', 165000, 'Twice in a Blue Moon', 358, 'Christina Lauren', 4, 6, '2020-10-10', 3),
(26, 'https://imgv2-1-f.scribdassets.com/img/word_document/372755316/original/216x287/d35e7bf53e/1623263332?v=1', 'https://www.scribd.com/book/372755316/Love-and-Other-Words', 150000, 'Love and Other Words', 375, 'Christina Lauren', 4, 7, '2018-10-04', 3),
(27, 'https://imgv2-1-f.scribdassets.com/img/word_document/354319649/original/216x287/98252e88ba/1621392794?v=1', 'https://www.scribd.com/book/354319649/The-Last-Tudor', 470000, 'The Last Tudor', 684, 'Philippa Gregory', 4, 5, '2017-08-08', 3),
(28, 'https://imgv2-1-f.scribdassets.com/img/word_document/239948233/original/216x287/ab845fcd76/1617229623?v=1', 'https://www.scribd.com/book/239948233/Sausage-Making-The-Definitive-Guide-with-Recipes', 300000, 'Sausage Making: The Definitive Guide with Recipes', 409, 'Ryan Farr, Jessica Battilana and Ed Anderson', 3, 4, '2015-01-05', 4),
(29, 'https://imgv2-2-f.scribdassets.com/img/word_document/239944370/original/216x287/2d1e55d526/1617229621?v=1', 'https://www.scribd.com/book/239944370/Quick-Easy-Korean-Cooking-More-Than-70-Everyday-Recipes', 180000, 'Quick & Easy Korean Cooking: More Than 70 Everyday Recipes', 256, 'Cecilia Hae-Jin Lee and Julie Toy', 4, 5, '2013-11-01', 4),
(30, 'https://imgv2-2-f.scribdassets.com/img/word_document/372674118/original/216x287/ff04cc4314/1623043229?v=1', 'https://www.scribd.com/book/372674118/Baked-Explorations-Classic-American-Desserts-Reinvented', 300000, 'Baked Explorations: Classic American Desserts Reinvented', 407, 'Matt Lewis, Renato Poliafito and Tina Rupp', 4, 5, '2011-12-12', 4),
(31, 'https://imgv2-1-f.scribdassets.com/img/word_document/163635913/original/216x287/7e38bb43d5/1623685025?v=1', 'https://www.scribd.com/book/163635913/Bradbury-Stories-100-of-His-Most-Celebrated-Tales', 250000, 'Bradbury Stories: 100 of His Most Celebrated Tales', 1470, 'Ray Bradbury', 4, 5, '2014-09-05', 2),
(32, 'https://imgv2-2-f.scribdassets.com/img/word_document/351160354/original/216x287/5533851fc2/1623687307?v=1', 'https://www.scribd.com/book/351160354/The-Rise-and-Fall-of-D-O-D-O-A-Novel', 180000, 'The Rise and Fall of D.O.D.O.: A Novel', 861, 'Neal Stephenson and Nicole Galland', 4, 7, '2018-01-06', 2),
(33, 'https://imgv2-1-f.scribdassets.com/img/word_document/364661651/original/216x287/df11527484/1623347568?v=1', 'https://www.scribd.com/book/364661651/Puppy-Training-Train-Your-Puppy-in-Obedience-Potty-Training-and-Leash-Training-in-Record-Time', 160000, 'https://www.scribd.com/book/364661651/Puppy-Training-Train-Your-Puppy-in-Obedience-Potty-Training-and-Leash-Training-in-Record-Time', 41, 'Anthony Portokaloglou', 5, 5, '2018-04-11', 5),
(34, 'https://imgv2-1-f.scribdassets.com/img/word_document/357227442/original/216x287/5a4d1cff12/1617228997?v=1', 'https://www.scribd.com/book/357227442/AGGRESSION-IN-DOGS-PRACTICAL-MANAGEMENT-PREVENTION-BEHAVIOUR-MODIFICATION', 150000, 'AGGRESSION IN DOGS: PRACTICAL MANAGEMENT, PREVENTION & BEHAVIOUR MODIFICATION', 960, 'Brenda Aloff', 4, 6, '2002-01-01', 5),
(35, 'https://imgv2-1-f.scribdassets.com/img/word_document/375723990/original/216x287/2d79882520/1617233823?v=1', 'https://www.scribd.com/book/375723990/Meet-Your-Dog', 165000, 'Meet Your Dog', 375, 'Kim Brophey', 4, 7, '2019-05-04', 5),
(36, 'https://imgv2-2-f.scribdassets.com/img/word_document/331453141/original/216x287/0ef3c33ede/1617221374?v=1', 'https://www.scribd.com/book/331453141/Ultimate-Guide-to-Advanced-Combat-Combat-Strategies-and-Battle-Techniques-for-Minecraft', 180000, 'Ultimate Guide to Advanced Combat: Combat Strategies and Battle Techniques for Minecraft®™', 223, 'Triumph Books', 3, 5, '2016-01-12', 6),
(37, 'https://imgv2-2-f.scribdassets.com/img/word_document/390778429/original/216x287/1848cb968b/1617228805?v=1', 'https://www.scribd.com/book/390778429/The-Fortnite-Guide-to-Staying-Alive-Tips-and-Tricks-for-Every-Kind-of-Player', 100000, 'The Fortnite Guide to Staying Alive: Tips and Tricks for Every Kind of Player', 111, 'Daniel Kuhn', 5, 5, '2018-12-10', 6),
(38, 'https://imgv2-1-f.scribdassets.com/img/word_document/396765799/original/432x574/de28a175aa/1621400867?v=1', 'https://www.scribd.com/book/396765799/The-Phantom-Virus-Herobrine-s-Revenge-Book-One-A-Gameknight999-Adventure-An-Unofficial-Minecrafter-s-Adventure', 120000, 'The Phantom Virus: Herobrine\'s Revenge Book One (A Gameknight999 Adventure): An Unofficial Minecrafter\'s Adventure', 239, 'MARK CHEVERTON', 3, 4, '2016-08-03', 6),
(39, 'https://imgv2-1-f.scribdassets.com/img/word_document/496227688/original/216x287/ea40936636/1617229559?v=1', 'https://www.scribd.com/book/496227688/The-Legend-of-Dragon-Quest-Creation-universe-decryption', 120000, 'The Legend of Dragon Quest: Creation - universe - decryption', 342, 'Daniel Andreyev', 3, 5, '2020-09-05', 6);

-- --------------------------------------------------------

--
-- Table structure for table `denda`
--

CREATE TABLE `denda` (
  `id` int(11) NOT NULL,
  `keterangan` varchar(255) DEFAULT NULL,
  `total_denda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `denda`
--

INSERT INTO `denda` (`id`, `keterangan`, `total_denda`) VALUES
(1, 'Buku Rusak.\nJumlah Denda Rusak (30% Harga Buku) = 56100', 56100),
(2, 'Buku Rusak.\nJumlah Denda Rusak (30% Harga Buku) = 60000', 60000);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `nama`) VALUES
(1, 'Technology'),
(2, 'Science Fiction'),
(3, 'Romance'),
(4, 'Cooking and Food'),
(5, 'Dogs'),
(6, 'Video Games');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id` int(11) NOT NULL,
  `tanggal_pengajuan` date DEFAULT NULL,
  `total_buku_yang_diajukan` int(11) DEFAULT NULL,
  `id_admin_perpus` int(11) DEFAULT NULL,
  `id_anggota` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id`, `tanggal_pengajuan`, `total_buku_yang_diajukan`, `id_admin_perpus`, `id_anggota`) VALUES
(1, '2021-06-20', 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman_details`
--

CREATE TABLE `peminjaman_details` (
  `id` int(11) NOT NULL,
  `status_konfirmasi` tinyint(1) DEFAULT 0,
  `tanggal_pengambilan` date DEFAULT NULL,
  `tanggal_pengembalian` date DEFAULT NULL,
  `id_buku` int(11) DEFAULT NULL,
  `id_denda` int(11) DEFAULT NULL,
  `id_peminjaman` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peminjaman_details`
--

INSERT INTO `peminjaman_details` (`id`, `status_konfirmasi`, `tanggal_pengambilan`, `tanggal_pengembalian`, `id_buku`, `id_denda`, `id_peminjaman`) VALUES
(1, 1, '2021-06-20', '2021-06-20', 3, 2, 1),
(2, 1, '2021-06-20', '2021-06-20', 11, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_perpus`
--
ALTER TABLE `admin_perpus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_buku_id_kategori` (`id_kategori`);

--
-- Indexes for table `denda`
--
ALTER TABLE `denda`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_peminjaman_id_admin_perpus` (`id_admin_perpus`),
  ADD KEY `FK_peminjaman_id_anggota` (`id_anggota`);

--
-- Indexes for table `peminjaman_details`
--
ALTER TABLE `peminjaman_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_peminjaman_details_id_buku` (`id_buku`),
  ADD KEY `FK_peminjaman_details_id_denda` (`id_denda`),
  ADD KEY `FK_peminjaman_details_id_peminjaman` (`id_peminjaman`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_perpus`
--
ALTER TABLE `admin_perpus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `anggota`
--
ALTER TABLE `anggota`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `denda`
--
ALTER TABLE `denda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `peminjaman_details`
--
ALTER TABLE `peminjaman_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `buku`
--
ALTER TABLE `buku`
  ADD CONSTRAINT `FK_buku_id_kategori` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id`);

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `FK_peminjaman_id_admin_perpus` FOREIGN KEY (`id_admin_perpus`) REFERENCES `admin_perpus` (`id`),
  ADD CONSTRAINT `FK_peminjaman_id_anggota` FOREIGN KEY (`id_anggota`) REFERENCES `anggota` (`id`);

--
-- Constraints for table `peminjaman_details`
--
ALTER TABLE `peminjaman_details`
  ADD CONSTRAINT `FK_peminjaman_details_id_buku` FOREIGN KEY (`id_buku`) REFERENCES `buku` (`id`),
  ADD CONSTRAINT `FK_peminjaman_details_id_denda` FOREIGN KEY (`id_denda`) REFERENCES `denda` (`id`),
  ADD CONSTRAINT `FK_peminjaman_details_id_peminjaman` FOREIGN KEY (`id_peminjaman`) REFERENCES `peminjaman` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
