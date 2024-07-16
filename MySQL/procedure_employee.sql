USE TestingSystem;

-- create employee 
DROP PROCEDURE IF EXISTS create_employee;
DELIMITER $$

CREATE PROCEDURE create_employee (
    IN employee_data_json JSON
)
BEGIN
    DECLARE c_employee_id BIGINT;
    DECLARE c_name VARCHAR(255);
    DECLARE c_gender VARCHAR(255);
    DECLARE c_date_of_birth DATE;
    DECLARE c_address VARCHAR(255);
    DECLARE c_image VARCHAR(255);
    DECLARE c_citizenId VARCHAR(12);
    DECLARE c_phoneNumber VARCHAR(10);
    DECLARE c_email VARCHAR(255);
    DECLARE c_createBy VARCHAR(20);

    SET c_name = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.name'));
    SET c_gender = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.gender'));
    SET c_date_of_birth = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.dateOfBirth'));
    SET c_address = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.address'));
    SET c_image = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.image'));
    SET c_citizenId = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.citizenId'));
    SET c_phoneNumber = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.phoneNumber'));
    SET c_email = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.email'));
	SET c_createBy = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.createBy'));
    INSERT INTO Employee (name, gender, dateOfBirth, address, image, citizenId, phoneNumber, email,createBy)
    VALUES (c_name, c_gender, c_date_of_birth, c_address, c_image, c_citizenId, c_phoneNumber, c_email,c_createBy);
    
    SET c_employee_id = LAST_INSERT_ID();
    SELECT * FROM Employee WHERE id = c_employee_id;
END$$

DELIMITER ;

-- get by id
DELIMITER //
DROP PROCEDURE IF EXISTS get_employee_by_id //
CREATE PROCEDURE get_employee_by_id (
    IN employee_id_param BIGINT
)
BEGIN
    SELECT * FROM Employee WHERE id = employee_id_param;
END //
DELIMITER ;

-- get all 
DELIMITER //
DROP PROCEDURE IF EXISTS get_all_employees //
CREATE PROCEDURE get_all_employees ()
BEGIN
    SELECT * FROM Employee;
END //
DELIMITER ;
call get_all_employees();
-- update employee

DELIMITER //

DROP PROCEDURE IF EXISTS update_employee //

CREATE PROCEDURE update_employee (
    IN employee_id_param BIGINT, 
    IN employee_data_json JSON
)
BEGIN
    DECLARE c_name VARCHAR(255);
    DECLARE c_gender VARCHAR(255);
    DECLARE c_date_of_birth DATE;
    DECLARE c_address VARCHAR(255);
    DECLARE c_image VARCHAR(255);
    DECLARE c_citizenId VARCHAR(12);
    DECLARE c_phoneNumber VARCHAR(10);
    DECLARE c_email VARCHAR(255);
    DECLARE c_createBy VARCHAR(20);
    
    SET c_name = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.name'));
    SET c_gender = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.gender'));
    SET c_date_of_birth = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.dateOfBirth'));
    SET c_address = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.address'));
    SET c_image = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.image'));
    SET c_citizenId = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.citizenId'));
    SET c_phoneNumber = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.phoneNumber'));
    SET c_email = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.email'));
    SET c_createBy = JSON_UNQUOTE(JSON_EXTRACT(employee_data_json, '$.createBy'));
    UPDATE Employee 
    SET 
        name = c_name,
        gender = c_gender,
        dateOfBirth = c_date_of_birth,
        address = c_address,
        image = c_image,
        citizenId = citizenId,
        phoneNumber = c_phoneNumber,
        email = c_email,
        createBy = c_createBy
    WHERE
        id = employee_id_param; 
        
    SELECT * FROM Employee WHERE id = employee_id_param;
END //

DELIMITER ;
-- delete employee

DELIMITER //
DROP PROCEDURE IF EXISTS delete_employee //
CREATE PROCEDURE delete_employee (
    IN employee_id_param BIGINT
)
BEGIN
    DELETE FROM Employee  WHERE id = employee_id_param;
END //
DELIMITER ;
-- check exist 
DELIMITER //
DROP PROCEDURE IF EXISTS exists_employee_by_id //
CREATE PROCEDURE exists_employee_by_id (
	IN employee_id_param BIGINT
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM Employee
		WHERE id = employee_id_param);
END //
DELIMITER ;

-- check exits phone
DELIMITER //
DROP PROCEDURE IF EXISTS exists_employee_by_phone //
CREATE PROCEDURE exists_employee_by_phone (
	IN phone_param VARCHAR(15)
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM Employee
		WHERE phoneNumber = phone_param);
END //
DELIMITER ;
--  check exits email
DELIMITER //
DROP PROCEDURE IF EXISTS exists_employee_by_email //
CREATE PROCEDURE exists_employee_by_email (
	IN email_param VARCHAR(50)
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM Employee
		WHERE email = email_param);
END //
DELIMITER ;
-- check exits code
DELIMITER //
DROP PROCEDURE IF EXISTS exists_employee_by_code //
CREATE PROCEDURE exists_employee_by_code (
	IN code_param VARCHAR(15)
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM Employee
		WHERE code = code_param);
END //
DELIMITER ;
