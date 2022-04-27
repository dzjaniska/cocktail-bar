-- MySQL Script generated by MySQL Workbench
-- Mon Apr 25 14:42:54 2022
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
-- Table `cocktails`.`cocktail_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cocktails`.`cocktail_tag` (
  `cocktail_id` CHAR(36) NOT NULL,
  `tag_id` CHAR(36) NOT NULL,
  PRIMARY KEY (`cocktail_id`, `tag_id`),
  INDEX `fk_cocktail_has_tag_tag1_idx` (`tag_id` ASC) VISIBLE,
  INDEX `fk_cocktail_has_tag_cocktail1_idx` (`cocktail_id` ASC) VISIBLE,
  CONSTRAINT `fk_cocktail_has_tag_cocktail1`
    FOREIGN KEY (`cocktail_id`)
    REFERENCES `cocktails`.`cocktail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cocktail_has_tag_tag1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `cocktails`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;