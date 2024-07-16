USE TestingSystem;

DELIMITER $$
DROP PROCEDURE IF EXISTS create_Relationship;
CREATE PROCEDURE create_Relationship (
    IN Relationship_data_json JSON
)
BEGIN
    DECLARE c_Relationship_id BIGINT;                  
    DECLARE c_employee_id BIGINT;
    DECLARE c_name VARCHAR(255);
    DECLARE c_gender VARCHAR(255);
    DECLARE c_dateOfBirth DATE;
    DECLARE c_address VARCHAR(255);
    DECLARE c_citizenId VARCHAR(12);
    DECLARE c_phoneNumber VARCHAR(10);
    DECLARE c_job VARCHAR(255);
    DECLARE c_relationship VARCHAR(255);
    
    SET c_employee_id = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.employeeId'));
    SET c_name = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.name'));
    SET c_gender = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.gender'));
    SET c_dateOfBirth = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.dateOfBirth'));
    SET c_address = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.address'));
    SET c_citizenId = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.citizenId'));
    SET c_phoneNumber = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.phoneNumber'));
    SET c_job = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.job'));
    SET c_relationship = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.relationship'));
    
    INSERT INTO Relationship (
        employeeId, name, gender, dateOfBirth, address, citizenId, phoneNumber, job, relationship
    ) VALUES (
        c_employee_id, c_name, c_gender, c_dateOfBirth, c_address, c_citizenId, c_phoneNumber, c_job, c_relationship
    );
    
    SET c_Relationship_id = LAST_INSERT_ID();
    
    SELECT * FROM Relationship WHERE id = c_Relationship_id;
END$$
DELIMITER ;

-- -----------------------------------update Family --------------------------------------

DELIMITER $$
DROP PROCEDURE IF EXISTS update_Relationship;
CREATE PROCEDURE update_Relationship (
	IN Relationship_id_param BIGINT, 
    IN Relationship_data_json JSON
)
BEGIN
    DECLARE c_Relationship_id BIGINT;
    DECLARE c_employeeId TINYINT;
    DECLARE c_name VARCHAR(255);
    DECLARE c_gender VARCHAR(255);
    DECLARE c_dateOfBirth DATE;
    DECLARE c_address VARCHAR(255);
    DECLARE c_citizenId VARCHAR(12);
    DECLARE c_phoneNumber VARCHAR(10);
    DECLARE c_job VARCHAR(255);
    DECLARE c_relationship VARCHAR(255);
    
    SET c_employeeId = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.employeeId'));
    SET c_name = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.name'));
    SET c_gender = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.gender'));
    SET c_dateOfBirth = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.dateOfBirth'));
    SET c_address = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.address'));
    SET c_citizenId = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.citizenId'));
    SET c_phoneNumber = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.phoneNumber'));
    SET c_job = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.job'));
    SET c_relationship = JSON_UNQUOTE(JSON_EXTRACT(Relationship_data_json, '$.relationship'));
    
    UPDATE FamilyRelationship
    SET
        employeeId = c_employeeId,
        name = c_name,
        gender = c_gender,
        dateOfBirth = c_dateOfBirth,
        address = c_address,
        citizenId = c_citizenId,
        phoneNumber = c_phoneNumber,
        job = c_job,
        relationship = c_relationship
        
    WHERE id = Relationship_id_param;

    SELECT * FROM Relationship WHERE id = Relationship_id_param;
END$$
DELIMITER ;


-- get all ----------------------------------------------------------------------------

DELIMITER //
DROP PROCEDURE IF EXISTS get_all_Relationship //
CREATE PROCEDURE get_all_Relationship ()
BEGIN
    SELECT * FROM Relationship;
END //
DELIMITER ;
call get_all_Relationship();
-- get by id ------------------------------------------------------------------------
DELIMITER //
DROP PROCEDURE IF EXISTS get_Relationship_by_id //
CREATE PROCEDURE get_Relationship_by_id (
    IN Relationship_id_param BIGINT
)
BEGIN
    SELECT * FROM Relationship WHERE id = Relationship_id_param;
END //
DELIMITER ;
-- get by EmployeeID
DELIMITER //
DROP PROCEDURE IF EXISTS get_Relationship_by_employeeId //
CREATE PROCEDURE get_Relationship_by_employeeId (
    IN employee_id_param BIGINT
)
BEGIN
    SELECT * FROM Relationship WHERE employeeId = employee_id_param;
END //
DELIMITER ;

-- delete familyRelationship
DELIMITER //
DROP PROCEDURE IF EXISTS delete_Relationship//
CREATE PROCEDURE delete_Relationship (
    IN Relationship_id_param BIGINT
)
BEGIN
    DELETE FROM Relationship  WHERE id = Relationship_id_param;
END //
DELIMITER ;

-- exists by id 
DELIMITER //

DROP PROCEDURE IF EXISTS exists_relationship_by_id //

CREATE PROCEDURE exists_relationship_by_id (
    IN Relationship_id_param BIGINT
)
BEGIN
    SELECT EXISTS (
        SELECT 1 
        FROM Relationship
        WHERE id = Relationship_id_param
    ) AS relationship_exists;
END //

DELIMITER ;
-- exists by phone 
DELIMITER //
DROP PROCEDURE IF EXISTS exists_Relationship_by_phone //
CREATE PROCEDURE exists_Relationship_by_phone (
	IN phone_param VARCHAR(15)
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM Relationship
		WHERE phoneNumber = phone_param);
END //
DELIMITER ;
-- exists by code
DELIMITER //
DROP PROCEDURE IF EXISTS exists_familyRelationship_by_citizenId //
CREATE PROCEDURE exists_familyRelationship_by_citizenId (
	IN citizenId_param VARCHAR(12)
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM Relationship
		WHERE code = citizenId_param);
END //
DELIMITER ;
