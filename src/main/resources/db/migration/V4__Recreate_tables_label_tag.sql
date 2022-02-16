-- MySQL Script generated by MySQL Workbench
-- Wed Feb 16 18:59:43 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cocktails
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cocktails` DEFAULT CHARACTER SET utf8 ;
USE `cocktails` ;

-- -----------------------------------------------------
-- Table `cocktails`.`label`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cocktails`.`label` ;
-- -----------------------------------------------------
-- Table `cocktails`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cocktails`.`tag` ;

CREATE TABLE IF NOT EXISTS `cocktails`.`label` (
  `id` CHAR(36) NOT NULL,
  `label_en` TINYTEXT NULL,
  `label_ru` TINYTEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `cocktails`.`tag` (
  `id` CHAR(36) NOT NULL,
  `name_id` CHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tag_label_idx` (`name_id` ASC) VISIBLE,
  UNIQUE INDEX `name_id_UNIQUE` (`name_id` ASC) VISIBLE,
  CONSTRAINT `fk_tag_label`
    FOREIGN KEY (`name_id`)
    REFERENCES `cocktails`.`label` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
