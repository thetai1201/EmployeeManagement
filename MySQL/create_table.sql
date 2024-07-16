DROP DATABASE IF EXISTS TestingSystem;
-- Create database
CREATE DATABASE IF NOT EXISTS TestingSystem;
USE TestingSystem;

-- Bảng Nhân Viên
DROP TABLE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS Employee (
	id									BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name								VARCHAR(255) ,
    gender 								VARCHAR(255) ,
    dateOfBirth 						DATE  ,
    address 							VARCHAR(255) ,
    image								VARCHAR(255) ,
    citizenId							VARCHAR(12) ,
    phoneNumber	 						VARCHAR(10)  ,
    email 								VARCHAR(255) ,
    createBy 							VARCHAR(20)
);
-- Bảng văn bằng
DROP TABLE IF EXISTS Certificate;
CREATE TABLE IF NOT EXISTS Certificate (
    id									BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    employeeId 							BIGINT UNSIGNED,
    title 								VARCHAR(255) ,
    field 								VARCHAR(255) ,
    issuedDate 							DATE ,
    description						 	VARCHAR(255),
    FOREIGN KEY(employeeId) REFERENCES Employee(id)
);
-- Bảng quan hệ gia đình 
DROP TABLE IF EXISTS Relationship;
CREATE TABLE IF NOT EXISTS Relationship(
	 id									BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
	employeeId							BIGINT UNSIGNED,
    name								VARCHAR(255) ,
	gender 								VARCHAR(255) ,
	dateOfBirth 						DATE  ,
    address 							VARCHAR(255) ,
    citizenId							VARCHAR(12) ,
    phoneNumber	 						VARCHAR(10) ,
    job									VARCHAR(255) ,
    relationship						VARCHAR (255) ,
    FOREIGN KEY(employeeId) REFERENCES Employee(id)
    );
-- Bảng role 
DROP TABLE IF EXISTS `Role`;
CREATE TABLE IF NOT EXISTS `Role`(
	id									TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name								VARCHAR(20) NOT NULL
);
-- Bảng user 
DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User`(
	id 									BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
	username 							VARCHAR(50) NOT NULL UNIQUE KEY,
	password							VARCHAR(100) NOT NULL,
	roleId 								TINYINT UNSIGNED NOT NULL,
    name								VARCHAR(255) ,
	position							VARCHAR(255) ,
    email 								VARCHAR(255) NOT NULL UNIQUE KEY,
    phoneNumber	 						VARCHAR(10) ,
    FOREIGN KEY (roleId) REFERENCES `Role`(id)
);
DROP TABLE IF EXISTS Registration;
CREATE TABLE IF NOT EXISTS Registration (
	id									BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    employeeId							BIGINT UNSIGNED NOT NULL,
    leaderId							BIGINT UNSIGNED,
    createDate 							DATE,
    createBy 							VARCHAR(20) NOT NULL,
	content 							TEXT NOT NULL ,
	status 								VARCHAR(15),
	submitDate 							DATE,
	rejectDate 							DATE,
	rejectReason 						TEXT,
	acceptDate 							DATE,
	note 								TEXT,
    FOREIGN KEY (employeeId) REFERENCES Employee(id),
    FOREIGN KEY (leaderId) REFERENCES `User`(id)
);
DROP TABLE IF EXISTS Profile_end;
CREATE TABLE IF NOT EXISTS Profile_end(
	id 									BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
	registrationId 						BIGINT UNSIGNED,
	leaderId 							BIGINT UNSIGNED,
	endDate 							DATE,
	endBy 								VARCHAR(20),
	reason 								TEXT NOT NULL,
	status 								VARCHAR(15),
    submitDate 							DATE,
	rejectDate 							DATE,
	rejectReason 						TEXT,
	acceptDate 							DATE,
	storageNumber 						TINYINT NOT NULL,
    FOREIGN KEY (leaderId) REFERENCES `User`(id),
    FOREIGN KEY (registrationId) REFERENCES Registration(id)
);


