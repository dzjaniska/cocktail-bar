-- MySQL Script generated by MySQL Workbench
-- Mon Apr 18 12:41:08 2022
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
-- Table `cocktails`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cocktails`.`order` (
  `id` CHAR(36) NOT NULL,
  `set_id` CHAR(36) NOT NULL,
  `cocktail_id` CHAR(36) NOT NULL,
  `user_id` CHAR(36) NOT NULL,
  `status` VARCHAR(20) NULL,
  `time` TIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_set1_idx` (`set_id` ASC) VISIBLE,
  INDEX `fk_order_cocktail1_idx` (`cocktail_id` ASC) VISIBLE,
  INDEX `fk_order_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_set1`
    FOREIGN KEY (`set_id`)
    REFERENCES `cocktails`.`set` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_cocktail1`
    FOREIGN KEY (`cocktail_id`)
    REFERENCES `cocktails`.`cocktail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cocktails`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;