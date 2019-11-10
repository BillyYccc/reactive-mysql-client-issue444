DROP TABLE IF EXISTS news;
CREATE TABLE `news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` text NOT NULL,
  `Summery` mediumtext NOT NULL,
  `Story` mediumtext NOT NULL,
  `Time` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
