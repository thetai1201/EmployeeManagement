USE TestingSystem;

DELIMITER //
-- ------------------------------------------
-- create
-- -------------------------------------------
DROP PROCEDURE IF EXISTS create_user //
CREATE PROCEDURE create_user(
    IN user_data_json VARCHAR(1000)
)
BEGIN
	DECLARE user_id_param BIGINT;
    DECLARE username_param VARCHAR(50);
    DECLARE password_param VARCHAR(100);
    DECLARE role_id_param INT;
    DECLARE name_param VARCHAR(50);
    DECLARE position_param VARCHAR(20);
    DECLARE email_param VARCHAR(50);
    DECLARE phone_param VARCHAR(15);

    SET @user_data_json = user_data_json;
    SET username_param = JSON_UNQUOTE(JSON_EXTRACT(@user_data_json, '$.username'));
    SET password_param = JSON_UNQUOTE(JSON_EXTRACT(@user_data_json, '$.password'));
    SET role_id_param = JSON_UNQUOTE(JSON_EXTRACT(@user_data_json, '$.roleId'));
    SET name_param = JSON_UNQUOTE(JSON_EXTRACT(@user_data_json, '$.name'));
    SET position_param = JSON_UNQUOTE(JSON_EXTRACT(@user_data_json, '$.position'));
    SET email_param = JSON_UNQUOTE(JSON_EXTRACT(@user_data_json, '$.email'));
    SET phone_param = JSON_UNQUOTE(JSON_EXTRACT(@user_data_json, '$.phoneNumber'));

    INSERT INTO User (
        username,
        password,
        role_id,
        name,
        position,
        email,
        phoneNumber
    ) VALUES (
        username_param,
        password_param,
        role_id_param,
        name_param,
        position_param,
        email_param,
        phone_param
    );
    
    SET user_id_param = LAST_INSERT_ID();
    
    SELECT username, roleId, name, position, email, phoneNumber
    FROM User WHERE id = user_id_param;
    
END //

-- ------------------------------------------
-- get user by username
-- -------------------------------------------
DROP PROCEDURE IF EXISTS get_user_by_username //
CREATE PROCEDURE get_user_by_username(
IN username_param VARCHAR(50)
)
BEGIN
    SELECT * FROM User
    WHERE username = username_param;
END //


-- ------------------------------------------
-- get user by id
-- -------------------------------------------
DROP PROCEDURE IF EXISTS get_user_by_id //
CREATE PROCEDURE get_user_by_id(
IN user_id_param BIGINT
)
BEGIN
    SELECT * FROM User
    WHERE id = user_id_param;
END //


-- ------------------------------------------
-- get all leader
-- -------------------------------------------
DROP PROCEDURE IF EXISTS get_all_leader //
CREATE PROCEDURE get_all_leader()
BEGIN
    SELECT * FROM User
    WHERE roleId = (
		SELECT id FROM Role WHERE name = 'LEADER'
    );
END //


-- ------------------------------------------
-- get leader by id
-- -------------------------------------------
DROP PROCEDURE IF EXISTS get_leader_by_id //
CREATE PROCEDURE get_leader_by_id(
IN user_id_param BIGINT
)
BEGIN
SELECT * FROM User
		WHERE id = user_id_param 
			AND roleId = (
				SELECT roleId FROM User WHERE name = 'LEADER'
							);
END //

-- ------------------------------------------
-- check leader id exist
-- -------------------------------------------
DROP PROCEDURE IF EXISTS is_exist_leader_id //
CREATE PROCEDURE is_exist_leader_id(
IN user_id_param BIGINT
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM User
			WHERE id = user_id_param 
				AND roleId = (
					SELECT id FROM Role WHERE name = 'LEADER'
								)
		);
END //


-- ------------------------------------------
-- check username exist
-- -------------------------------------------
DROP PROCEDURE IF EXISTS is_exist_username //
CREATE PROCEDURE is_exist_username(
IN username_param VARCHAR(50)
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM User
		WHERE username = username_param);
END //


-- ------------------------------------------
-- check email exist
-- -------------------------------------------
DROP PROCEDURE IF EXISTS is_exist_email //
CREATE PROCEDURE is_exist_email(
IN email_param VARCHAR(50)
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM User
		WHERE email = email_param);
END //


-- ------------------------------------------
-- get role by name
-- -------------------------------------------
DROP PROCEDURE IF EXISTS get_role_by_name //
CREATE PROCEDURE get_role_by_name(
IN role_name_param VARCHAR(50)
)
BEGIN
    SELECT * FROM Role
    WHERE name = role_name_param;
END //

-- ------------------------------------------
-- get role by id
-- -------------------------------------------
DROP PROCEDURE IF EXISTS get_role_by_id //
CREATE PROCEDURE get_role_by_id(
IN role_id_param INT
)
BEGIN
    SELECT * FROM Role
    WHERE id = role_id_param;
END //


-- ------------------------------------------
-- check role exist
-- -------------------------------------------
DROP PROCEDURE IF EXISTS is_exist_role_by_id //
CREATE PROCEDURE is_exist_role_by_id(
IN role_id_param INT
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM Role
		WHERE id = role_id_param);
END //


-- ------------------------------------------
-- check exist employee id
-- -------------------------------------------
DROP PROCEDURE IF EXISTS exists_employee_by_id //
CREATE PROCEDURE exists_employee_by_id(
IN employee_id_param BIGINT
)
BEGIN
    SELECT EXISTS 
		(SELECT 1 FROM Employee
		WHERE id =employee_id_param);
END //

DELIMITER ;