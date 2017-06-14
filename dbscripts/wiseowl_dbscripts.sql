SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='';

-- -----------------------------------------------------
-- Schema wiseowl
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wiseowl` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `wiseowl` ;

-- -----------------------------------------------------
-- Table `wiseowl`.`booksource` -- Static Table
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wiseowl`.`booksource`;

CREATE TABLE IF NOT EXISTS `wiseowl`.`booksource` (
  `booksource_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `booksource_code` VARCHAR(3) NOT NULL COMMENT '',
  `booksource_name` VARCHAR(50) NOT NULL COMMENT '',
  `booksource_desc` VARCHAR(150) NOT NULL COMMENT '',
  CONSTRAINT pk_booksource PRIMARY KEY (booksource_id),
  CONSTRAINT unique_booksource_code unique(`booksource_code`))
ENGINE = InnoDB;


INSERT INTO booksource(booksource_code,booksource_name,booksource_desc) VALUES ('001','LIBRARY','Borrowed from a Library');
INSERT INTO booksource(booksource_code,booksource_name,booksource_desc) VALUES ('002','OWNED','Self Owned');
INSERT INTO booksource(booksource_code,booksource_name,booksource_desc) VALUES ('003','FRIEND','Borrowed from a Friend');
INSERT INTO booksource(booksource_code,booksource_name,booksource_desc) VALUES ('004','STORE','Borrowed from a Store');


-- -----------------------------------------------------
-- Table `wiseowl`.`bookformat` 
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wiseowl`.`bookformat`;

CREATE TABLE IF NOT EXISTS `wiseowl`.`bookformat` (
  `bookformat_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `bookformat_code` VARCHAR(3) NOT NULL COMMENT '',
  `bookformat_name` VARCHAR(50) NOT NULL COMMENT '',
  `bookformat_desc` VARCHAR(150) NOT NULL COMMENT '',
  CONSTRAINT pk_bookformat PRIMARY KEY (bookformat_id),
  CONSTRAINT unique_bookformat_code unique(`bookformat_code`))
ENGINE = InnoDB;

INSERT INTO bookformat(bookformat_code,bookformat_name,bookformat_desc) VALUES ('001','PDF','PDF format');
INSERT INTO bookformat(bookformat_code,bookformat_name,bookformat_desc) VALUES ('002','WORD','Any Word document');
INSERT INTO bookformat(bookformat_code,bookformat_name,bookformat_desc) VALUES ('003','HARDBOUND','Paper copy');
INSERT INTO bookformat(bookformat_code,bookformat_name,bookformat_desc) VALUES ('004','KINDLE','Amazon Kindle format');
INSERT INTO bookformat(bookformat_code,bookformat_name,bookformat_desc) VALUES ('005','IBOOKS','Apple iBooks format');



-- -----------------------------------------------------
-- Table `wiseowl`.`user_role` 
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wiseowl`.`user_role`;

CREATE TABLE IF NOT EXISTS `wiseowl`.`user_role` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `role_code` VARCHAR(3) NOT NULL COMMENT '',
  `role_name` VARCHAR(50) NOT NULL COMMENT '',
  `role_desc` VARCHAR(150) NOT NULL COMMENT '',
  CONSTRAINT pk_user_role PRIMARY KEY (user_role_id))
ENGINE = InnoDB;

INSERT INTO user_role(role_code,role_name,role_desc) VALUES ('001','ADMIN','Site Administrator');
INSERT INTO user_role(role_code,role_name,role_desc) VALUES ('002','SITEUSER','Regular Site User'); 





-- -----------------------------------------------------
-- Table `wiseowl`.`BOOK` 
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wiseowl`.`book`;

CREATE TABLE IF NOT EXISTS `wiseowl`.`book` (
  `book_id` INT NOT NULL AUTO_INCREMENT COMMENT '', -- Will be auto-incremented by Hibernate.Defined in java using annotations
  `book_title` VARCHAR(150) NOT NULL COMMENT '',
  `book_author_name` VARCHAR(150) NOT NULL COMMENT '',
  `book_publish_date`		DATE DEFAULT NULL,
  `book_isbn_13`          CHAR(13) DEFAULT NULL,-- unique,constraint check length
  `book_notes`            MEDIUMTEXT DEFAULT NULL,
   -- The next 5 fields tell whether a book has been read or not
  book_read_comments    TEXT, -- mandatory on UI
  book_owned_format_id  INTEGER,        -- FK
  book_read_date   	    DATE,			-- mandatory on UI 
  book_read_rating      CHAR(1),		-- mandatory on UI 
  book_read_source_id	INTEGER,        -- mandatory on UI      -- FK
  book_read_format_id   INTEGER,        -- mandatory on UI      -- FK
  
  create_user_id        INTEGER       NOT NULL,
  create_datetime       TIMESTAMP  NOT NULL, -- Auto inserted by the trigger at the time of row creation
  update_datetime       TIMESTAMP NULL,           -- Auto updated by the trigger at the time of row update
  CONSTRAINT pk_book PRIMARY KEY (book_id),
  CONSTRAINT fk_book_owned_fmt_id FOREIGN KEY(book_owned_format_id) REFERENCES bookformat(bookformat_id),
  CONSTRAINT fk_book_read_src_id FOREIGN KEY(book_read_source_id) REFERENCES booksource(booksource_id),
  CONSTRAINT fk_book_read_fmt_id FOREIGN KEY(book_read_format_id) REFERENCES bookformat(bookformat_id))  
ENGINE = InnoDB;


CREATE TRIGGER trig_book_create_datetime
BEFORE INSERT
ON book
FOR EACH ROW
SET NEW.create_datetime:= NOW();



CREATE TRIGGER trig_book_update_datetime
BEFORE UPDATE
ON book
FOR EACH ROW
SET NEW.update_datetime:= NOW();



-- -----------------------------------------------------
-- Table `wiseowl`.`USER_AUTH` 
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wiseowl`.`user_auth`;

CREATE TABLE IF NOT EXISTS `wiseowl`.`user_auth` (
  `user_auth_id`          INT NOT NULL AUTO_INCREMENT COMMENT '', -- Will be auto-incremented by Hibernate.Defined in java using annotations
  `username`		      VARCHAR(16)  NOT NULL,
  `password_hash`      	  BINARY(128)  NOT NULL,
  `create_datetime`       TIMESTAMP(0)  NOT NULL, -- Auto inserted by the trigger at the time of row creation
  `update_datetime`       TIMESTAMP(0) NULL,          -- Auto updated by the trigger at the time of row update
  CONSTRAINT pk_user_auth PRIMARY KEY (user_auth_id))
ENGINE = InnoDB;


CREATE TRIGGER trig_user_auth_create_datetime
BEFORE INSERT
ON user_auth
FOR EACH ROW
SET NEW.create_datetime:= NOW();


CREATE TRIGGER trig_user_auth_update_datetime
BEFORE UPDATE
ON user_auth
FOR EACH ROW
SET NEW.update_datetime:= NOW();





-- -----------------------------------------------------
-- Table `wiseowl`.`SITEUSER` 
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wiseowl`.`siteuser`;

CREATE TABLE IF NOT EXISTS `wiseowl`.`siteuser` (
  `user_id` INT NOT NULL AUTO_INCREMENT COMMENT '', -- Will be auto-incremented by Hibernate.Defined in java using annotations
  `firstname`		    VARCHAR(50)  NOT NULL,
  `lastname`       	    VARCHAR(50),
  `nickname`            VARCHAR(50),
  `dob`                 DATE,
  `user_role_id`        INTEGER       NOT NULL,-- FK
  `user_auth_id`        INTEGER       NOT NULL,-- FK
  `create_datetime`     TIMESTAMP(0)  NOT NULL, -- Auto inserted by the trigger at the time of row creation
  `update_datetime`     TIMESTAMP(0) NULL,          -- Auto updated by the trigger at the time of row update
  CONSTRAINT pk_user PRIMARY KEY (user_id),
  CONSTRAINT fk_user_role_id FOREIGN KEY(user_role_ID) REFERENCES user_role(user_role_id),
  CONSTRAINT fk_user_auth_id FOREIGN KEY(user_auth_id) REFERENCES user_auth(user_auth_id))
ENGINE = InnoDB;



CREATE TRIGGER trig_siteuser_create_datetime
BEFORE INSERT
ON siteuser
FOR EACH ROW
SET NEW.create_datetime:= NOW();



CREATE TRIGGER trig_siteuser_update_datetime
BEFORE UPDATE
ON siteuser
FOR EACH ROW
SET NEW.update_datetime:= NOW();


insert into user_auth(password_hash, username) values (SHA2('password1',512),'jatin');
insert into siteuser(firstname,lastname,nickname,dob,user_role_id,user_auth_id) values('Jatin','Taneja','jatin','1985-01-01',2,1);
