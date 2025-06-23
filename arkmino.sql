DROP DATABASE IF EXISTS `arkmino`;

CREATE DATABASE IF NOT EXISTS `arkmino` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `arkmino`;

CREATE TABLE `Habitats`  (
  `id_Habitat` INT AUTO_INCREMENT,
  `texto_Habitat` VARCHAR(30) UNIQUE NOT NULL,
  PRIMARY KEY (`id_Habitat`)
);

DROP TABLE IF EXISTS `Dinosaurios`;
CREATE TABLE `Dinosaurios` (
  `id_Dino` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `tipo_DietaGeneral` varchar(50) NOT NULL,
  `preferencia_Alimento` varchar(50) NOT NULL,
  `domesticable` boolean,
  CONSTRAINT pk_Dinosaurios PRIMARY KEY (id_Dino)
);

CREATE TABLE `Tameos` (
  `id_Tameo` int NOT NULL AUTO_INCREMENT,
  `id_Dino` int NOT NULL,
  `metodo_Usado` varchar(100) NOT NULL,
  `tiempo` double NOT NULL,
  PRIMARY KEY (`id_Tameo`),
  CONSTRAINT fk_idDino_Tameos FOREIGN KEY (`id_Dino`) REFERENCES `Dinosaurios` (`id_Dino`)
    ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS `Dino_Habitat`;
CREATE TABLE `Dino_Habitat` (
  `id_HabitatDino` int NOT NULL AUTO_INCREMENT,
  `id_Dino` int NOT NULL,
  `id_Habitat` int NOT NULL,
  `fechaInsertado` date NOT NULL,
  PRIMARY KEY (`id_HabitatDino`),
  CONSTRAINT fk_idDino_Habitat FOREIGN KEY (`id_Dino`) REFERENCES `Dinosaurios` (`id_Dino`)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_idHabitat_Habitat FOREIGN KEY (`id_Habitat`) REFERENCES `Habitats` (`id_Habitat`)
    ON UPDATE CASCADE ON DELETE NO ACTION
);