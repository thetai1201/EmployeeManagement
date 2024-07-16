USE TestingSystem;
-- get all registration
DELIMITER //
DROP PROCEDURE IF EXISTS get_all_registration //
CREATE PROCEDURE get_all_registration ()
BEGIN
    SELECT * FROM Registration;
END //
DELIMITER ;
call get_all_registration();
-- get By id 

DELIMITER //
DROP PROCEDURE IF EXISTS get_registration_by_id //
CREATE PROCEDURE get_registration_by_id (
    IN registration_id_param BIGINT
)
BEGIN
    SELECT * FROM Registration WHERE id = registration_id_param;
END //
DELIMITER ;
call get_registration_by_id(1);
-- get by status
DELIMITER //
DROP PROCEDURE IF EXISTS get_registration_by_status //
CREATE PROCEDURE get_registration_by_status (
    IN status_param VARCHAR(15)
)
BEGIN
    SELECT * FROM Registration WHERE status = status_param;
END //
DELIMITER ;
call get_registration_by_status('Pending');
---- get by createBy
DELIMITER //
DROP PROCEDURE IF EXISTS get_registration_by_createBy //
CREATE PROCEDURE get_registration_by_createBy (
    IN createBy_param VARCHAR(20)
)
BEGIN
    SELECT * FROM Registration WHERE createBy = createBy_param;
END //
DELIMITER ;
call get_registration_by_createBy('admin1');
-- create registration-------------------------------------
-- ----------------------------------------------------------
DELIMITER //
DROP PROCEDURE IF EXISTS create_registration //
CREATE PROCEDURE create_registration(
    IN registration_data_json JSON
)
BEGIN
	DECLARE registration_id_param BIGINT;
    DECLARE employee_id_param BIGINT;
    DECLARE leader_id_param BIGINT;
    DECLARE create_date_param DATE;
    DECLARE create_by_param VARCHAR(20);
    DECLARE content_param TEXT;
    DECLARE status_param VARCHAR(15);
    DECLARE note_param TEXT;

    SET employee_id_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.employeeId'));
    SET leader_id_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.leaderId'));
    SET create_date_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.createDate'));
    SET create_by_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.createBy'));
    SET content_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.content'));
    SET status_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.status'));
    SET note_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.note'));

    INSERT INTO Registration (
        employeeId,
        leaderId,
        createDate,
        createBy,
        content,
        status,
        note
    ) VALUES (
        employee_id_param,
        leader_id_param,
        create_date_param,
        create_by_param,
        content_param,
        status_param,
        note_param
    );
    
    SET registration_id_param = LAST_INSERT_ID();

    SELECT * FROM Registration WHERE id = registration_id_param;
END //
-- submit ---------------------------
DELIMITER //
DROP PROCEDURE IF EXISTS submit_registration //
CREATE PROCEDURE submit_registration (
    IN registration_id_param BIGINT,
    IN registration_data_json JSON
)
BEGIN
    DECLARE submit_date_param DATE;
    DECLARE leader_id_param BIGINT;
    DECLARE status_param VARCHAR(15);
    DECLARE note_param TEXT;

    SET submit_date_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.submitDate'));
    SET leader_id_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.leaderId'));
    SET status_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.status'));
    SET note_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.note'));

    UPDATE Registration
    SET
        submitDate = submit_date_param,
        leaderId = leader_id_param,
        status = status_param,
        note = note_param
    WHERE
        id = registration_id_param;
    SELECT * FROM Registration WHERE id = registration_id_param;
        
END //
DELIMITER ;

-------- update by leader----------
DELIMITER //
DROP PROCEDURE IF EXISTS update_registration_by_leader //
CREATE PROCEDURE update_registration_by_leader (
    IN registration_id_param BIGINT,
    IN registration_data_json JSON
)
BEGIN
    DECLARE status_param VARCHAR(15);
    DECLARE reject_date_param DATE;
    DECLARE reject_reason_param TEXT;
    DECLARE accept_date_param DATE;
    DECLARE note_param TEXT;

    SET status_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.status'));
    SET reject_date_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.rejectDate'));
    SET reject_reason_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.rejectReason'));
    SET accept_date_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.acceptDate'));
    SET note_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.note'));
    
    UPDATE Registration
    SET
        status = status_param,
        rejectDate = reject_date_param,
        rejectReason = reject_reason_param,
        acceptDate = accept_date_param,
        note = note_param
    WHERE
        id = registration_id_param;
		SELECT * FROM Registration WHERE id = registration_id_param;
END //
DELIMITER ;
-- update by manager
DELIMITER //
DROP PROCEDURE IF EXISTS update_registration_by_manager //
CREATE PROCEDURE update_registration_by_manager (
    IN registration_id_param BIGINT,
    IN registration_data_json JSON
)
BEGIN
    DECLARE employee_id_param BIGINT;
    DECLARE content_param TEXT;
    DECLARE status_param VARCHAR(15);
    DECLARE note_param TEXT;

    SET employee_id_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.employeeId'));
    SET content_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.content'));
    SET status_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.status'));
    SET note_param = JSON_UNQUOTE(JSON_EXTRACT(registration_data_json, '$.note'));
    
    UPDATE Registration
    SET
        employeeId = employee_id_param,
        content = content_param,
        status = status_param,
        note = note_param
    WHERE
        id = registration_id_param;
		SELECT * FROM Registration WHERE id = registration_id_param;
END //
DELIMITER ;

-- delete registration by id 
DELIMITER //
DROP PROCEDURE IF EXISTS delete_registration//
CREATE PROCEDURE delete_registration (
    IN registration_id_param BIGINT
)
BEGIN
    DELETE FROM Registration  WHERE id = registration_id_param;
END //
DELIMITER ;
-- exist By id 
DELIMITER //

DROP PROCEDURE IF EXISTS exists_registration_by_id //

CREATE PROCEDURE exists_registration_by_id (
    IN registration_id_param BIGINT
)
BEGIN
    SELECT EXISTS (
        SELECT 1 
        FROM Registration 
        WHERE id = registration_id_param
    ) AS registration_exists;
END //

DELIMITER ;
