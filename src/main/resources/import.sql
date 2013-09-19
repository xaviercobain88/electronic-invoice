-- You can use this file to load seed data into the database using SQL statements
--INSERT INTO role(id) VALUES(1);
--INSERT INTO user(id, role_id) VALUES(1, 1);
--INSERT INTO territory(id, active, level, parent_id)	VALUES(1, 1, 'territory', null);
--INSERT INTO markup(id) VALUES(1);
--INSERT INTO geographic_location(id, active, level, parent_id, name)	VALUES(1, 1, 'level3', null, 'geographic location');
--INSERT INTO client(id, asigned_status, markup_id, user_id, territory_id, active) VALUES(1, 'not defined', 1, 1, 1, 1);
/*INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(1, 1, 'category', 'cat', 'Category', NULL);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(2, 1, 'type', 'typ', 'Type', NULL);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(3, 1, 'color', 'col', 'Color', NULL);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(4, 1, 'variety', 'var', 'Variety', NULL);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(5, 1, 'grade', 'gra', 'Grade', NULL);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(24, 1, 'uom', 'uom', 'UOM', NULL);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(6, 1, 'category 1', 'cat1', 'Category 1', 1);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(7, 1, 'category 2', 'cat2', 'Category 2', 1);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(8, 1, 'category 3', 'cat3', 'Category 3', 1);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(11, 1, 'type 3', 'typ3', 'Type 3', 2);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(10, 1, 'type 2', 'typ2', 'Type 2', 2);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(9, 1, 'type 1', 'typ1', 'Type 1', 2);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(14, 1, 'color 3', 'col3', 'Color 3', 3);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(13, 1, 'color 2', 'col2', 'Color 2', 3);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(12, 1, 'color 1', 'col1', 'Color 1', 3);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(17, 1, 'variety 3', 'var3', 'Variety 3', 4);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(16, 1, 'variety 2', 'var2', 'Variety 2', 4);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(15, 1, 'variety 1', 'var1', 'Variety 1', 4);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(20, 1, 'grade 3', 'gra3', 'Grade 3', 5);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(19, 1, 'grade 2', 'gra2', 'Grade 2', 5);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(18, 1, 'grade 1', 'gra1', 'Grade 1', 5);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id) VALUES(25, 1, 'steam', 'st', 'Steam', 24);
INSERT INTO catalog(id, active, description, mnemonic, name)
VALUES(21, true, 'box type', 'bty', 'Box Type');
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(22, true, 'box type 1', 'bty1', 'Box Type 1', 21);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(23, true, 'box type 2', 'bty2', 'Box Type 2', 21);
--INSERT INTO point(id, active, description, lead_time_days, name, point_type, product_list)
--VALUES(1, 1, 'FOB Quito', 0, 'Quito', 'POO', 'Limited');
--INSERT INTO shipping_address(id, number_street, suite_unit, zip_code, client_id, geographic_location_id, active)
--VALUES(1, 'Calle J', 's13-200', 'ec1234', 1, 1, 1);
--INSERT INTO grower(id, user_id, name)
--VALUES(1, 1, 'Grower 1');
--INSERT INTO item(id, active, approved, grower_price, item_description, max_quantity, order_multiples, status, tariff_code, category_catalog, color_catalog, grade_catalog, grower_id, type_catalog, variety_catalog, , uom_catalog)
--VALUES(1, true, true, 1, '1', 1, 1, '1', '1', 6, 12, 18, 1, 9, 15, 25);
INSERT INTO geographic_location(id, active, level, name, parent_id)
VALUES(2, 1, 'LEVEL_1', 'United States', NULL);
INSERT INTO geographic_location(id, active, level, name, parent_id)
VALUES(3, 1, 'LEVEL_2', 'California', 2);
INSERT INTO geographic_location(id, active, level, name, parent_id)
VALUES(4, 1, 'LEVEL_2', 'New York', 2);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(26, true, 'company_type', 'cty', 'Company Type', NULL);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(28, true, 'Company Type 2', 'cty2', 'COmpany Type 2', 26);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(27, true, 'Company Type 1', 'cty1', 'Company Type 1', 26);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(29, true, 'Legal Formation', 'lfm', 'Legal Formation', NULL);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(30, true, 'Legal Formation 2', 'lfm2', 'Legal Formation 2', 29);
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(31, true, 'Legal Formation 1', 'lfm1', 'Legal Formation 1', 29);
INSERT INTO `stf_db`.`role`(`id`, `role_name`)
VALUES(1, 'manager');*/
INSERT INTO geographic_location(id, active, level, name, parent_id)
VALUES(2, 1, 'LEVEL_1', 'United States', NULL)
go
INSERT INTO geographic_location(id, active, level, name, parent_id)
VALUES(3, 1, 'LEVEL_2', 'California', 2)
go
INSERT INTO geographic_location(id, active, level, name, parent_id)
VALUES(4, 1, 'LEVEL_2', 'New York', 2)
go
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(26, true, 'company_type', 'cty', 'Company Type', NULL)
go
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(28, true, 'Company Type 2', 'cty2', 'COmpany Type 2', 26)
go
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(27, true, 'Company Type 1', 'cty1', 'Company Type 1', 26)
go
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(29, true, 'Legal Formation', 'lfm', 'Legal Formation', NULL)
go
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(30, true, 'Legal Formation 2', 'lfm2', 'Legal Formation 2', 29)
go
INSERT INTO catalog(id, active, description, mnemonic, name, parent_id)
VALUES(31, true, 'Legal Formation 1', 'lfm1', 'Legal Formation 1', 29)
go
INSERT INTO `stf_db`.`role`(`id`, `role_name`)
VALUES(1, 'manager')
GO
INSERT INTO `stf_db`.`user_role`(`user_id`, `role_id`)
VALUES(1, 1)
GO
INSERT INTO `stf_db`.`permission`(`id`, `active`, `permission_name`, `permission_type`)
VALUES(1, 1, 'USER_PROFILE', 'WRITE_OTHERS')
GO
INSERT INTO `stf_db`.`role_permission`(`role_id`, `permission_id`)
VALUES(1, 1)
GO





