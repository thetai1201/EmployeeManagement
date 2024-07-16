USE TestingSystem;
-- create profile end
DELIMITER //
DROP PROCEDURE IF EXISTS create_profile_end //
CREATE PROCEDURE create_profile_end (
	IN profile_end_data_json JSON
)
BEGIN
	DECLARE p_profile_end_id BIGINT;
	DECLARE p_registration_id BIGINT;
    DECLARE p_leader_id BIGINT;
    DECLARE p_end_date DATE;
    DECLARE p_end_by VARCHAR(20);
    DECLARE p_reason TEXT;
    DECLARE p_status VARCHAR(15);
    DECLARE p_storage_number TINYINT;
    
    SET p_registration_id = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.registrationId'));
     SET p_leader_id = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.leaderId'));
    SET p_end_date = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.endDate'));
    SET p_end_by = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.endBy'));
    SET p_reason = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.reason'));
    SET p_status = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.status'));
    SET p_storage_number = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.storageNumber'));
    
    INSERT INTO Profile_end (registrationId,leaderId, endDate, endBy, reason, status, storageNumber)
    VALUES (p_registration_id,p_leader_id, p_end_date, p_end_by, p_reason, p_status, p_storage_number);
    
    SET p_profile_end_id = LAST_INSERT_ID();

    SELECT * FROM Profile_end WHERE id = p_profile_end_id;
END //
DELIMITER ;
-- submit profile end
DELIMITER //
DROP PROCEDURE IF EXISTS submit_profileEnd //
CREATE PROCEDURE submit_profileEnd (
    IN profileEnd_id_param BIGINT,
    IN profile_end_data_json JSON
)
BEGIN
    DECLARE submit_date_param DATE;
    DECLARE leader_id_param BIGINT;
    DECLARE status_param VARCHAR(15);
    

    SET submit_date_param = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.submitDate'));
    SET leader_id_param = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.leaderId'));
    SET status_param = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.status'));

    UPDATE Profile_end
    SET
        submitDate = submit_date_param,
        leaderId = leader_id_param,
        status = status_param
    WHERE
        id = profileEnd_id_param;
        
    SELECT * FROM Profile_end WHERE id = profileEnd_id_param;
        
END //

DELIMITER ;
-- update by manager
DELIMITER //
DROP PROCEDURE IF EXISTS update_profile_end_by_manager //
CREATE PROCEDURE update_profile_end_by_manager (
	IN profileEnd_id_param BIGINT,
    IN profile_end_data_json JSON
)
BEGIN
	DECLARE p_registration_id BIGINT;
    DECLARE p_reason TEXT;
    DECLARE p_status VARCHAR(15);
    DECLARE p_storage_number TINYINT;
    
    SET p_registration_id = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.registrationId'));
    SET p_reason = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.reason'));
    SET p_status = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.status'));
    SET p_storage_number = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.storageNumber'));
    
    UPDATE Profile_end
    SET
        registrationId = p_registration_id,
        reason = p_reason,
        status = p_status,
        storageNumber = p_storage_number
    WHERE id = profileEnd_id_param;
    
    SELECT * FROM Profile_end WHERE id = profileEnd_id_param;
END //
DELIMITER ;
-- update By Leader
DELIMITER //
DROP PROCEDURE IF EXISTS update_profile_end_by_leader //
CREATE PROCEDURE update_profile_end_by_leader (
	IN profileEnd_id_param BIGINT,
    IN profile_end_data_json JSON
)
BEGIN
    DECLARE p_status VARCHAR(15);
    DECLARE p_reject_date DATE;
    DECLARE p_reject_reason TEXT;
    DECLARE p_accept_date DATE;
    
    SET p_status = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.status'));
    SET p_reject_date = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.rejectDate'));
    SET p_reject_reason = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.rejectReason'));
    SET p_accept_date = JSON_UNQUOTE(JSON_EXTRACT(profile_end_data_json, '$.acceptDate'));
    
    UPDATE Profile_end
    SET
        status = p_status,
        rejectDate = p_reject_date,
        rejectReason = p_reject_reason,
        acceptDate = p_accept_date
    WHERE id = profileEnd_id_param;
    
    SELECT * FROM Profile_end WHERE id = profileEnd_id_param;
END //
DELIMITER ;
-- get all profile end
DELIMITER //
DROP PROCEDURE IF EXISTS get_all_profileEnd //
CREATE PROCEDURE get_all_profileEnd ()
BEGIN
    SELECT * FROM Profile_end;
END //
DELIMITER ;
call get_all_profileEnd();
-- get by id 
DELIMITER //
DROP PROCEDURE IF EXISTS get_profileEnd_by_id //
CREATE PROCEDURE get_profileEnd_by_id (
    IN profileEnd_id_param BIGINT
)
BEGIN
    SELECT * FROM Profile_end WHERE id = profileEnd_id_param;
END //
DELIMITER ;
call get_profileEnd_by_id(1);

-- get by status
DELIMITER //
DROP PROCEDURE IF EXISTS get_profileEnd_by_status //
CREATE PROCEDURE get_profileEnd_by_status (
    IN status_param VARCHAR(15)
)
BEGIN
    SELECT * FROM Profile_end WHERE status = status_param;
END //
DELIMITER ;
call get_profileEnd_by_status("Pending");
-- delete profiele by id 
DELIMITER //
DROP PROCEDURE IF EXISTS delete_profileEnd//
CREATE PROCEDURE delete_profileEnd (
    IN profileEnd_id_param BIGINT
)
BEGIN
    DELETE FROM Profile_end   WHERE id = profileEnd_id_param;
END //
DELIMITER ;