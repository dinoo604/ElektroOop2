
CREATE TABLE `student` (
  `studentennummer` int(10) NOT NULL,
  `naam` varchar(20) NOT NULL,
  `achternaam` varchar(20) NOT NULL,
  `cohort` int(11) NOT NULL,
  `afstudeer_jaar` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `geslacht` varchar(5) NOT NULL,
  `orientatie` varchar(20) DEFAULT NULL,
  `studie_status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`studentennummer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `vak` (
  `idvak` int(5) NOT NULL,
  `vak_naam` varchar(80) DEFAULT NULL,
  `studiepunten` float DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `vak_orientatie` char(1) DEFAULT NULL,
  PRIMARY KEY (`idvak`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `rapport` (
	rapport_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `student_studentennummer` int(10) NOT NULL,
  `vak_idvak` int(5) NOT NULL,
  `datum` date NOT NULL,
  `cijfer` float DEFAULT NULL,
  KEY `fk_student_has_vak_vak1_idx` (`vak_idvak`),
  KEY `fk_student_has_vak_student_idx` (`student_studentennummer`),
  CONSTRAINT `fk_student_has_vak_student` FOREIGN KEY (`student_studentennummer`) REFERENCES `student` (`studentennummer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_has_vak_vak1` FOREIGN KEY (`vak_idvak`) REFERENCES `vak` (`idvak`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

