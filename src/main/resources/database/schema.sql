SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `stf_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `stf_db` ;

-- -----------------------------------------------------
-- Table `stf_db`.`catalog`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`catalog` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `parent_id` INT NULL ,
  `description` VARCHAR(2048) NULL ,
  `mnemonic` CHAR(4) NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_catalog_catalog1_idx` (`parent_id` ASC) ,
  CONSTRAINT `fk_catalog_catalog1`
    FOREIGN KEY (`parent_id` )
    REFERENCES `stf_db`.`catalog` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `role_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_user_role1_idx` (`role_id` ASC) ,
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id` )
    REFERENCES `stf_db`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`grower`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`grower` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_grower_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_grower_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `stf_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`item`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`item` (
  `type_catalog` INT NOT NULL ,
  `id` INT NOT NULL AUTO_INCREMENT ,
  `category_catalog` INT NOT NULL ,
  `color_catalog` INT NOT NULL ,
  `variety_catalog` INT NOT NULL ,
  `grower_id` INT NOT NULL ,
  `approved` TINYINT(1) NOT NULL ,
  `grower_price` DECIMAL NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  `order_multiples` INT NOT NULL ,
  `max_quantity` INT NOT NULL ,
  `item_description` VARCHAR(2048) NULL ,
  `tariff_code` VARCHAR(64) NULL ,
  `status` VARCHAR(64) NOT NULL ,
  INDEX `fk_item_catalog1_idx` (`type_catalog` ASC) ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_item_catalog2_idx` (`category_catalog` ASC) ,
  INDEX `fk_item_catalog3_idx` (`color_catalog` ASC) ,
  INDEX `fk_item_catalog4_idx` (`variety_catalog` ASC) ,
  INDEX `fk_item_grower1_idx` (`grower_id` ASC) ,
  CONSTRAINT `fk_item_catalog1`
    FOREIGN KEY (`type_catalog` )
    REFERENCES `stf_db`.`catalog` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_catalog2`
    FOREIGN KEY (`category_catalog` )
    REFERENCES `stf_db`.`catalog` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_catalog3`
    FOREIGN KEY (`color_catalog` )
    REFERENCES `stf_db`.`catalog` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_catalog4`
    FOREIGN KEY (`variety_catalog` )
    REFERENCES `stf_db`.`catalog` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_grower1`
    FOREIGN KEY (`grower_id` )
    REFERENCES `stf_db`.`grower` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`geographic_location`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`geographic_location` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `parent_id` INT NOT NULL ,
  `level` VARCHAR(64) NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_geographic_location_geographic_location1_idx` (`parent_id` ASC) ,
  CONSTRAINT `fk_geographic_location_geographic_location1`
    FOREIGN KEY (`parent_id` )
    REFERENCES `stf_db`.`geographic_location` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`markup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`markup` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`client`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `asigned_status` VARCHAR(64) NOT NULL ,
  `geographic_location_id` INT NOT NULL ,
  `markup_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_client_user1_idx` (`user_id` ASC) ,
  INDEX `fk_client_geographic_location1_idx` (`geographic_location_id` ASC) ,
  INDEX `fk_client_markup1_idx` (`markup_id` ASC) ,
  CONSTRAINT `fk_client_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `stf_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_geographic_location1`
    FOREIGN KEY (`geographic_location_id` )
    REFERENCES `stf_db`.`geographic_location` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_markup1`
    FOREIGN KEY (`markup_id` )
    REFERENCES `stf_db`.`markup` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`order`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `system_id` CHAR(10) NOT NULL ,
  `status` VARCHAR(45) NOT NULL ,
  `create_date` TIMESTAMP NOT NULL ,
  `modify_date` TIMESTAMP NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  `client_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `system_id_UNIQUE` (`system_id` ASC) ,
  INDEX `fk_order_client1_idx` (`client_id` ASC) ,
  CONSTRAINT `fk_order_client1`
    FOREIGN KEY (`client_id` )
    REFERENCES `stf_db`.`client` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`box_configuration`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`box_configuration` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `order_id` INT NOT NULL ,
  `amount` INT NOT NULL ,
  `create_date` TIMESTAMP NOT NULL ,
  `modify_date` TIMESTAMP NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_box_configuration_order1_idx` (`order_id` ASC) ,
  CONSTRAINT `fk_box_configuration_order1`
    FOREIGN KEY (`order_id` )
    REFERENCES `stf_db`.`order` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`suborder`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`suborder` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `order_id` INT NOT NULL ,
  `grower_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_participant_grower_order1_idx` (`order_id` ASC) ,
  INDEX `fk_participant_grower_grower1_idx` (`grower_id` ASC) ,
  CONSTRAINT `fk_participant_grower_order1`
    FOREIGN KEY (`order_id` )
    REFERENCES `stf_db`.`order` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participant_grower_grower1`
    FOREIGN KEY (`grower_id` )
    REFERENCES `stf_db`.`grower` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`suborder_box_configuration`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`suborder_box_configuration` (
  `box_configuration_id` INT NOT NULL ,
  `selected_grower_id` INT NOT NULL ,
  `grouped_boxes` INT NOT NULL ,
  `ungrouped_boxes` INT NOT NULL ,
  `id` INT UNSIGNED NOT NULL ,
  INDEX `fk_s_grower_box_conf_box_configuration1_idx` (`box_configuration_id` ASC) ,
  INDEX `fk_s_grower_box_conf_selected_grower1_idx` (`selected_grower_id` ASC) ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_s_grower_box_conf_box_configuration1`
    FOREIGN KEY (`box_configuration_id` )
    REFERENCES `stf_db`.`box_configuration` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_s_grower_box_conf_selected_grower1`
    FOREIGN KEY (`selected_grower_id` )
    REFERENCES `stf_db`.`suborder` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`box`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`box` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `system_id` CHAR(20) NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  `status` VARCHAR(64) NOT NULL ,
  `shipment_status` VARCHAR(64) NOT NULL ,
  `s_grower_box_conf_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `system_id_UNIQUE` (`system_id` ASC) ,
  INDEX `fk_box_s_grower_box_conf1_idx` (`s_grower_box_conf_id` ASC) ,
  CONSTRAINT `fk_box_s_grower_box_conf1`
    FOREIGN KEY (`s_grower_box_conf_id` )
    REFERENCES `stf_db`.`suborder_box_configuration` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`box_configuration_item`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`box_configuration_item` (
  `box_configuration_id` INT NOT NULL ,
  `item_id` INT NOT NULL ,
  `id` INT NOT NULL AUTO_INCREMENT ,
  `amount` INT NOT NULL ,
  INDEX `fk_box_configuration_item_box_configuration1_idx` (`box_configuration_id` ASC) ,
  INDEX `fk_box_configuration_item_item1_idx` (`item_id` ASC) ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_box_configuration_item_box_configuration1`
    FOREIGN KEY (`box_configuration_id` )
    REFERENCES `stf_db`.`box_configuration` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_box_configuration_item_item1`
    FOREIGN KEY (`item_id` )
    REFERENCES `stf_db`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`box_tracking`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`box_tracking` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `box_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_track_shipment_box1_idx` (`box_id` ASC) ,
  CONSTRAINT `fk_track_shipment_box1`
    FOREIGN KEY (`box_id` )
    REFERENCES `stf_db`.`box` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`box_comment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`box_comment` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `box_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_box_note_box1_idx` (`box_id` ASC) ,
  CONSTRAINT `fk_box_note_box1`
    FOREIGN KEY (`box_id` )
    REFERENCES `stf_db`.`box` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`permission`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`permission` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`role_permission`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`role_permission` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `option_id` INT NOT NULL ,
  `role_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_opcion_role_option1_idx` (`option_id` ASC) ,
  INDEX `fk_opcion_role_role1_idx` (`role_id` ASC) ,
  CONSTRAINT `fk_opcion_role_option1`
    FOREIGN KEY (`option_id` )
    REFERENCES `stf_db`.`permission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_opcion_role_role1`
    FOREIGN KEY (`role_id` )
    REFERENCES `stf_db`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`employee`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `superior_id` INT NULL ,
  `level` VARCHAR(64) NOT NULL ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `idemployee_UNIQUE` (`id` ASC) ,
  INDEX `fk_employee_employee1_idx` (`superior_id` ASC) ,
  INDEX `fk_employee_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_employee_employee1`
    FOREIGN KEY (`superior_id` )
    REFERENCES `stf_db`.`employee` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `stf_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`territory_asigned`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`territory_asigned` (
  `employee_id` INT NOT NULL ,
  `geographic_location_id` INT NOT NULL ,
  `level` VARCHAR(64) NOT NULL ,
  `id` INT NOT NULL AUTO_INCREMENT ,
  INDEX `fk_table1_employee1_idx` (`employee_id` ASC) ,
  INDEX `fk_table1_geographic_location1_idx` (`geographic_location_id` ASC) ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_table1_employee1`
    FOREIGN KEY (`employee_id` )
    REFERENCES `stf_db`.`employee` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_geographic_location1`
    FOREIGN KEY (`geographic_location_id` )
    REFERENCES `stf_db`.`geographic_location` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`client_asigned`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`client_asigned` (
  `employee_id` INT NOT NULL ,
  `client_id` INT NOT NULL ,
  `id` INT NOT NULL AUTO_INCREMENT ,
  INDEX `fk_client_asigned_employee1_idx` (`employee_id` ASC) ,
  INDEX `fk_client_asigned_client1_idx` (`client_id` ASC) ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_client_asigned_employee1`
    FOREIGN KEY (`employee_id` )
    REFERENCES `stf_db`.`employee` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_asigned_client1`
    FOREIGN KEY (`client_id` )
    REFERENCES `stf_db`.`client` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`shipping_address`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`shipping_address` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `client_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_shipping_address_client1_idx` (`client_id` ASC) ,
  CONSTRAINT `fk_shipping_address_client1`
    FOREIGN KEY (`client_id` )
    REFERENCES `stf_db`.`client` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`comission`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`comission` (
  `order_id` INT NOT NULL ,
  `employee_id` INT NOT NULL ,
  `id` INT NOT NULL AUTO_INCREMENT ,
  INDEX `fk_comission_order1_idx` (`order_id` ASC) ,
  INDEX `fk_comission_employee1_idx` (`employee_id` ASC) ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_comission_order1`
    FOREIGN KEY (`order_id` )
    REFERENCES `stf_db`.`order` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comission_employee1`
    FOREIGN KEY (`employee_id` )
    REFERENCES `stf_db`.`employee` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`client_invoice_detail`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`client_invoice_detail` (
  `id` INT NOT NULL ,
  `order_id` INT NOT NULL ,
  `client_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_client_invoice_order1_idx` (`order_id` ASC) ,
  INDEX `fk_client_invoice_detail_client1_idx` (`client_id` ASC) ,
  CONSTRAINT `fk_client_invoice_order1`
    FOREIGN KEY (`order_id` )
    REFERENCES `stf_db`.`order` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_invoice_detail_client1`
    FOREIGN KEY (`client_id` )
    REFERENCES `stf_db`.`client` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`grower_invoice_detail`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`grower_invoice_detail` (
  `id` INT NOT NULL ,
  `order_id` INT NOT NULL ,
  `grower_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_grower_invoice_detail_order1_idx` (`order_id` ASC) ,
  INDEX `fk_grower_invoice_detail_grower1_idx` (`grower_id` ASC) ,
  CONSTRAINT `fk_grower_invoice_detail_order1`
    FOREIGN KEY (`order_id` )
    REFERENCES `stf_db`.`order` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grower_invoice_detail_grower1`
    FOREIGN KEY (`grower_id` )
    REFERENCES `stf_db`.`grower` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`freight_forwarder`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`freight_forwarder` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_freight_forwarder_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_freight_forwarder_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `stf_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`freight_forwarder_invoice_detail`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`freight_forwarder_invoice_detail` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `order_id` INT NOT NULL ,
  `freight_forwarder_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_freight_forwarder_invoice_detail_order1_idx` (`order_id` ASC) ,
  INDEX `fk_freight_forwarder_invoice_detail_freight_forwarder1_idx` (`freight_forwarder_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_freight_forwarder_invoice_detail_order1`
    FOREIGN KEY (`order_id` )
    REFERENCES `stf_db`.`order` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_freight_forwarder_invoice_detail_freight_forwarder1`
    FOREIGN KEY (`freight_forwarder_id` )
    REFERENCES `stf_db`.`freight_forwarder` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`tariff`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`tariff` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`parameter`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`parameter` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(64) NOT NULL ,
  `value` VARCHAR(1024) NOT NULL ,
  `type` VARCHAR(64) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`credit`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`credit` (
  `id` INT NOT NULL ,
  `client_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_credit_client1_idx` (`client_id` ASC) ,
  CONSTRAINT `fk_credit_client1`
    FOREIGN KEY (`client_id` )
    REFERENCES `stf_db`.`client` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stf_db`.`credit_transaction`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `stf_db`.`credit_transaction` (
  `id` INT NOT NULL ,
  `credit_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_credit_trasaction_credit1_idx` (`credit_id` ASC) ,
  CONSTRAINT `fk_credit_trasaction_credit1`
    FOREIGN KEY (`credit_id` )
    REFERENCES `stf_db`.`credit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
