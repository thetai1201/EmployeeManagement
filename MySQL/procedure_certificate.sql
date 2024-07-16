USE TestingSystem;

DROP PROCEDURE IF EXISTS create_certificate;
DELIMITER $$

CREATE PROCEDURE create_certificate (
    IN certificate_data_json JSON
)
BEGIN
    DECLARE c_certificate_id BIGINT;
    DECLARE c_employee_id BIGINT;
    DECLARE c_title VARCHAR(255);
    DECLARE c_tield VARCHAR(255);
    DECLARE c_issued_date DATE;
	DECLARE c_description VARCHAR(255);

    SET c_employee_id = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.employeeId'));
    SET c_title = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.title'));
    SET  c_tield = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.field'));
    SET c_issued_date = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.issuedDate'));
    SET c_description = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.description'));

    INSERT INTO Certificate (employeeId, title, field, issuedDate, description)
    VALUES (c_employee_id, c_title, c_tield, c_issued_date, c_description);
    
    SET c_certificate_id = LAST_INSERT_ID();
    SELECT * FROM Certificate WHERE id = c_certificate_id;
END$$

DELIMITER ;

-- get by id
DELIMITER //
DROP PROCEDURE IF EXISTS get_certificate_by_id //
CREATE PROCEDURE get_certificate_by_id (
    IN certificate_id_param BIGINT
)
BEGIN
    SELECT * FROM Certificate WHERE id = certificate_id_param;
END //
DELIMITER ;
call get_certificate_by_id(1);

-- get by employee id
DELIMITER //
DROP PROCEDURE IF EXISTS get_certificate_by_employee_id //
CREATE PROCEDURE get_certificate_by_employee_id (
    IN employee_id_param BIGINT
)
BEGIN
    SELECT * FROM Certificate WHERE employeeId = employee_id_param;
END //
DELIMITER ;
call get_certificate_by_employee_id(2);

-- get all certificate
DELIMITER //
DROP PROCEDURE IF EXISTS get_all_certificate //
CREATE PROCEDURE get_all_certificate ()
BEGIN
    SELECT * FROM Certificate ;
END //
DELIMITER ;
call get_all_certificate();

-- update certificate
DELIMITER //

DROP PROCEDURE IF EXISTS update_certificate //

CREATE PROCEDURE update_certificate (
    IN certificate_id_param BIGINT, 
    IN certificate_data_json JSON
)
BEGIN
    DECLARE c_employee_id BIGINT;
    DECLARE c_title VARCHAR(255);
    DECLARE c_tield VARCHAR(255);
    DECLARE c_issued_date DATE;
	DECLARE c_description VARCHAR(255);
    
	SET c_employee_id = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.employeeId'));
    SET c_title = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.title'));
    SET c_tield = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.field'));
    SET c_issued_date = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.issuedDate'));
    SET c_description = JSON_UNQUOTE(JSON_EXTRACT(certificate_data_json, '$.description'));
    
    UPDATE Certificate 
    SET 
        employeeId = c_employee_id,
        title = c_title,
        field = c_tield,
        issuedDate = c_issued_date,
        description = c_description
    WHERE
        id = certificate_id_param; 
        
    SELECT * FROM Certificate WHERE id = certificate_id_param;
END //

DELIMITER ;

-- delete certificate
DELIMITER //
DROP PROCEDURE IF EXISTS delete_certificate//
CREATE PROCEDURE delete_certificate (
    IN certificate_id_param TINYINT
)
BEGIN
    DELETE FROM Certificate  WHERE id = certificate_id_param;
END //
DELIMITER ;

-- exist by id 
DELIMITER //
DROP PROCEDURE IF EXISTS exists_certificate_by_id //
CREATE PROCEDURE exists_certificate_by_id (
    IN certificate_id_param BIGINT
)
BEGIN
    SELECT EXISTS (
        SELECT 1 
        FROM Certificate 
        WHERE id = certificate_id_param
    ) AS certificate_exists;
END //
DELIMITER ;
call exists_certificate_by_id(1);