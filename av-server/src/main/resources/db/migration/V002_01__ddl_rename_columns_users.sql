ALTER TABLE `auto`.`users` 
CHANGE COLUMN `benutzername` `username` VARCHAR(255) NULL DEFAULT NULL ,
CHANGE COLUMN `passwort` `password` VARCHAR(255) NULL DEFAULT NULL ;