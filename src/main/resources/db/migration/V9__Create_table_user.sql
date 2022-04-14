-- MySQL Script generated by MySQL Workbench
-- Thu Mar 10 16:10:44 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cocktails
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cocktails
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cocktails` DEFAULT CHARACTER SET utf8 ;
USE `cocktails` ;

-- -----------------------------------------------------
-- Table `cocktails`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cocktails`.`user` (
  `id` CHAR(36) NOT NULL,
  `title` VARCHAR(200) NULL,
  `login` VARCHAR(200) NULL,
  `password` VARCHAR(200) NULL,
  `role` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
