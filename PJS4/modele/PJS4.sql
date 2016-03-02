-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.6.20-log - MySQL Community Server (GPL)
-- SE du serveur:                Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Export de la structure de la base pour pjs4
CREATE DATABASE IF NOT EXISTS `pjs4` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pjs4`;


-- Export de la structure de table pjs4. personne
CREATE TABLE IF NOT EXISTS `personne` (
  `IdPersonne` int(11) NOT NULL AUTO_INCREMENT,
  `NomPers` varchar(20) NOT NULL,
  `PrenomPers` varchar(20) NOT NULL,
  `Pseudo` varchar(20) NOT NULL,
  `Mdp` varchar(20) NOT NULL,
  `Departement` varchar(20) NOT NULL,
  PRIMARY KEY (`IdPersonne`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Export de données de la table pjs4.personne : ~11 rows (environ)
/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
INSERT INTO `personne` (`IdPersonne`, `NomPers`, `PrenomPers`, `Pseudo`, `Mdp`, `Departement`) VALUES
	(1, 'Ziane', 'Mikal', 'Ziane2', 'Mikal2', 'INFO'),
	(2, 'Brette', 'Jean-François', 'Brette2', 'Jean-François2', 'INFO'),
	(3, 'Ouziri', 'Mourad', 'Ouziri2', 'Mourad2', 'INFO'),
	(4, 'Moungla', 'Hassine', 'Moungla2', 'Hassine2', 'INFO'),
	(6, 'Zinedine', 'Zinae', 'Ziane2', 'Mikal2', 'INFO'),
	(7, 'Besson', 'Jules', 'Laurent Outan', 'izi', 'OKZN'),
	(9, 'Test', 'Test', 'Test', 'Test', 'Test'),
	(10, 'Test', 'Test', 'Test', 'Test', 'Test'),
	(11, 'Test', 'Test', 'Test', 'Test', 'Test'),
	(12, 'Fiora', 'Ducouteau', 'Laurent', 'Outan', 'INFO'),
	(13, 'Mon Pic', 'De Dragon', 'smb', 'plsv', 'INFOCOM');
/*!40000 ALTER TABLE `personne` ENABLE KEYS */;


-- Export de la structure de table pjs4. reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `IdReservation` int(11) NOT NULL AUTO_INCREMENT,
  `IdPersonne` int(11) NOT NULL,
  `IdSalle` int(11) NOT NULL,
  `DureeReservation` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdReservation`),
  KEY `fk_reservation_personne` (`IdPersonne`),
  KEY `fk_reservation_salle` (`IdSalle`),
  CONSTRAINT `fk_reservation_personne` FOREIGN KEY (`IdPersonne`) REFERENCES `personne` (`IdPersonne`) ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_salle` FOREIGN KEY (`IdSalle`) REFERENCES `salle` (`IdSalle`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Export de données de la table pjs4.reservation : ~1 rows (environ)
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` (`IdReservation`, `IdPersonne`, `IdSalle`, `DureeReservation`) VALUES
	(1, 1, 1, 1);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;


-- Export de la structure de table pjs4. salle
CREATE TABLE IF NOT EXISTS `salle` (
  `IdSalle` int(11) NOT NULL AUTO_INCREMENT,
  `NumEtage` int(11) NOT NULL,
  `NumSalle` int(11) NOT NULL,
  `HeureReserv` time NOT NULL,
  `NbPlace` int(11) NOT NULL,
  PRIMARY KEY (`IdSalle`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Export de données de la table pjs4.salle : ~6 rows (environ)
/*!40000 ALTER TABLE `salle` DISABLE KEYS */;
INSERT INTO `salle` (`IdSalle`, `NumEtage`, `NumSalle`, `HeureReserv`, `NbPlace`) VALUES
	(1, 0, 13, '00:01:30', 25),
	(2, 1, 16, '00:01:30', 10),
	(3, 0, 12, '00:01:30', 35),
	(4, 7, 43, '00:01:30', 23),
	(5, 1, 1, '15:53:55', 1),
	(6, 1, 1, '15:54:48', 1);
/*!40000 ALTER TABLE `salle` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
