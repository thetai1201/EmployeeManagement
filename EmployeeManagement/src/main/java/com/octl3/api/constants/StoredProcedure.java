package com.octl3.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoredProcedure {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Parameter {
        public static final String USER_JSON = "user_data_json";
        public static final String USER_ID_PARAM = "user_id_param";
        public static final String USERNAME_PARAM = "username_param";
        public static final String EMAIL_PARAM = "email_param";
        public static final String PHONE_PARAM = "phone_param";
        public static final String CITIZEN_ID_PARAM = "citizenId_param";
        public static final String ROLE_NAME_PARAM = "role_name_param";
        public static final String ROLE_ID_PARAM = "role_id_param";
        public static final String REGISTRATION_JSON = "registration_data_json";
        public static final String REGISTRATION_ID_PARAM = "registration_id_param";
        public static final String STATUS_PARAM = "status_param";
        public static final String EMPLOYEE_JSON = "employee_data_json";
        public static final String EMPLOYEE_ID_PARAM = "employee_id_param";
        public static final String CERTIFICATE_JSON = "certificate_data_json";
        public static final String CERTIFICATE_ID_PARAM = "certificate_id_param";
        public static final String RELATIONSHIP_JSON = "relationship_data_json";
        public static final String RELATIONSHIP_ID_PARAM = "relationship_id_param";
        public static final String PROFILE_END_JSON = "profile_end_data_json";
        public static final String PROFILE_END_ID_PARAM = "profileEnd_id_param";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
        public static final String USER_RESPONSE_DTO_MAPPER = "UserResponseDtoMapper";
        public static final String ROLE_DTO_MAPPER = "RoleDtoMapper";
        public static final String USER_DTO_MAPPER = "UserDtoMapper";
        public static final String REGISTRATION_DTO_MAPPER = "RegistrationDtoMapper";
        public static final String EMPLOYEE_DTO_MAPPER = "EmployeeDtoMapper";
        public static final String CERTIFICATE_DTO_MAPPER = "CertificateDtoMapper";
        public static final String RELATIONSHIP_DTO_MAPPER = "RelationshipDtoMapper";

        public static final String PROFILE_END_DTO_MAPPER = "ProfileEndDtoMapper";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Employee {
        public static final String CREATE_EMPLOYEE = "create_employee";
        public static final String GET_ALL = "get_all_employees";
        public static final String GET_BY_ID = "get_employee_by_id";
        public static final  String UPDATE_EMPLOYEE ="update_employee";
        public static final String DELETE_EMPLOYEE = "delete_employee";
        public static final String EXISTS_BY_ID =  "exists_employee_by_id";
        public static final String EXISTS_BY_PHONE = "exists_employee_by_phone";
        public static final String EXISTS_BY_EMAIL = "exists_employee_by_email";
        public static final String EXISTS_BY_CITIZEN_ID = "exists_employee_by_citizenId";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Certificate {
        public static final String GET_ALL_CERTIFICATE = "get_all_certificate";
        public static final String GET_ALL_BY_EMPLOYEE_ID = "get_certificate_by_employee_id";
        public static final String CREATE_CERTIFICATE = "create_certificate";
        public  static final String UPDATE_CERTIFICATE = "update_certificate";
        public static final String DELETE_CERTIFICATE = "delete_certificate";
        public static final String EXISTS_BY_ID = "exists_certificate_by_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Relationship {
        public static final String GET_ALL_RELATIONSHIP = "get_all_Relationship";
        public static final String GET_BY_RELATIONSHIP_ID = "get_relationship_by_id";
        public static final String GET_BY_EMPLOYEE_ID ="get_relationship_by_employeeId";
        public static final String CREATE_RELATIONSHIP ="create_Relationship";
        public static final String UPDATE_RELATIONSHIP = "update_Relationship";
        public static final String DELETE_RELATIONSHIP = "delete_Relationship";
        public static final String EXISTS_BY_ID = "exists_relationship_by_id";
        public static final String EXISTS_BY_PHONE = "exists_relationship_by_phone";
        public static final String EXISTS_BY_CITIZENID = "exists_relationship_by_citizenId";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Registration {
        public static final String GET_ALL_REGISTRATION = "get_all_registration";
        public static final String GET_REGISTRATION_ID = "get_registration_by_id";
        public static final String GET_REGISTRATION_STATUS ="get_registration_by_status";
        public static final String CREATE_REGISTRATION = "create_registration";
        public static final String DELETE_REGISTRATION_BY_ID = "delete_registration";
        public static final String SUBMIT_REGISTRATION = "submit_registration";
        public static final String RESUBMIT_REGISTRATION ="resubmit_registration";
        public static final String UPDATE_REGISTRATION_BY_LEADER = "update_registration_by_leader";
        public static final String UPDATE_REGISTRATION_BY_MANAGER = "update_registration_by_manager";
        public static final String EXISTS_BY_ID = "exists_registration_by_id";
        public static final String GET_ALL_CREATEBY = "get_registration_by_createBy";
        public static final String GET_BY_ROLE = "get_register_by_role";
    }
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ProfileEnd {
        public static final String GET_ALL_PROFILE_END = "get_all_profileEnd";
        public static final String GET_PROFILE_BY_ID = "get_profileEnd_by_id";
        public static final String GET_PROFILE_BY_STATUS = "get_profileEnd_by_status";
        public static final String DELETE_PROFILE_END = "delete_profileEnd";
        public static final String CREATE_PROFILE_END ="create_profile_end";
        public static final String SUBMIT_PROFILE_END = "submit_profileEnd";
        public static final String UPDATA_PROFILE_BY_MANAGER = "update_profile_end_by_manager";
        public static final String UPDATE_PROFILE_BY_LEADER = "update_profile_end_by_leader";
        public static final String EXISTS_BY_ID = "exists_profileEnd_by_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class User {
        public static final String CREATE_USER = "create_user";
        public static final String GET_USER_BY_USERNAME = "get_user_by_username";
        public static final String GET_ROLE_BY_NAME = "get_role_by_name";
        public static final String GET_ROLE_BY_ID = "get_role_by_id";
        public static final String IS_EXIST_ROLE_BY_ID = "is_exist_role_by_id";
        public static final String IS_EXIST_USERNAME = "is_exist_username";
        public static final String IS_EXIST_EMAIL = "is_exist_email";
        public static final String GET_ALL_LEADER = "get_all_leader";
        public static final String GET_LEADER_BY_ID = "get_leader_by_id";
        public static final String IS_EXIST_LEADER_ID = "is_exist_leader_id";
    }

}
