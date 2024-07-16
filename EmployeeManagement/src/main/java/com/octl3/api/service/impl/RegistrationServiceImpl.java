package com.octl3.api.service.impl;

import com.octl3.api.dto.RegistrationDto;
import com.octl3.api.security.CustomUserDetails;
import com.octl3.api.service.RegistrationService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.EmployeeValidator;
import com.octl3.api.validator.StatusValidator;
import com.octl3.api.validator.UserValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import static com.octl3.api.constants.Status.*;
import static com.octl3.api.constants.StoredProcedure.Mapper.REGISTRATION_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.Registration.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationServiceImpl implements RegistrationService {
    EntityManager entityManager;
    EmployeeValidator employeeValidator;
    StatusValidator statusValidator;
    UserValidator userValidator;
    @Override
    public RegistrationDto create(RegistrationDto registrationDto) {
        employeeValidator.existsById(registrationDto.getEmployeeId());
        registrationDto.setCreateDate(LocalDate.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        registrationDto.setLeaderId(customUserDetails.getUserId());
        registrationDto.setCreateBy(authentication.getName());
        registrationDto.setStatus(CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_REGISTRATION, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_JSON,String.class, ParameterMode.IN )
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));
        return (RegistrationDto) query.getSingleResult();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<RegistrationDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_REGISTRATION, REGISTRATION_DTO_MAPPER);
        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<RegistrationDto> getAllByRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String roleName = customUserDetails.getAuthorities().iterator().next().getAuthority();
        Long userId = customUserDetails.getUserId();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_BY_ROLE, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(USER_ID_PARAM, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(ROLE_NAME_PARAM, String.class, ParameterMode.IN)
                .setParameter(ROLE_NAME_PARAM,roleName)
                .setParameter(USER_ID_PARAM, userId);
        return query.getResultList();

    }

    @Override
    public RegistrationDto getById(long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_REGISTRATION_ID, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id);
        return (RegistrationDto) query.getSingleResult();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<RegistrationDto> getByStatus(String status) {
        statusValidator.checkValidStatus(status);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_REGISTRATION_STATUS, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(STATUS_PARAM, String.class, ParameterMode.IN)
                .setParameter(STATUS_PARAM, status);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        userValidator.checkCreateByManager(this.getById(id).getCreateBy());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_REGISTRATION_BY_ID, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id);
        query.execute();
    }
    @Transactional
    @Override
    public void submit(RegistrationDto registrationDto, long id) {
        RegistrationDto existedRegistrationDto = this.getById(id);
        userValidator.checkCreateByManager(existedRegistrationDto.getCreateBy());
        statusValidator.checkValidStatusForSubmit(existedRegistrationDto.getStatus());
        userValidator.checkExistLeaderId(registrationDto.getLeaderId());
        registrationDto.setStatus(PENDING.getValue());
        registrationDto.setSubmitDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SUBMIT_REGISTRATION, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id)
                .registerStoredProcedureParameter(REGISTRATION_JSON , String.class, ParameterMode.IN)
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));
        query.execute();
    }
    @Transactional
    @Override
    public void resubmit(RegistrationDto registrationDto, long id) {
        RegistrationDto existedRegistrationDto = this.getById(id);
        userValidator.checkCreateByManager(existedRegistrationDto.getCreateBy());
        statusValidator.checkValidStatusForResubmit(existedRegistrationDto.getStatus());
        userValidator.checkExistLeaderId(registrationDto.getLeaderId());
        registrationDto.setStatus(WAITING_REVIEW.getValue());
        registrationDto.setSubmitDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RESUBMIT_REGISTRATION, REGISTRATION_DTO_MAPPER)
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id)
                .registerStoredProcedureParameter(REGISTRATION_JSON , String.class, ParameterMode.IN)
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));
        query.execute();
    }

    @Transactional
    @Override
    public RegistrationDto updateByLeader(RegistrationDto registrationDto, long id) {
        RegistrationDto existsRegistrationDto = this.getById(id);
        statusValidator.checkValidStatusForLeaderUpdate(existsRegistrationDto.getStatus());
        userValidator.checkIsForLeader(existsRegistrationDto.getLeaderId());
        statusValidator.checkValidLeaderStatus(registrationDto.getStatus());
        if(registrationDto.getStatus().equals(ACCEPTED.getValue())){
            registrationDto.setAcceptDate(LocalDate.now());
        }
        if(registrationDto.getStatus().equals(REJECTED.getValue())){
            registrationDto.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_REGISTRATION_BY_LEADER,REGISTRATION_DTO_MAPPER )
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id)
                .registerStoredProcedureParameter(REGISTRATION_JSON , String.class, ParameterMode.IN)
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));
        return (RegistrationDto) query.getSingleResult();
    }
    @Transactional
    @Override
    public RegistrationDto updateByManager(RegistrationDto registrationDto, long id) {
        RegistrationDto existedRegistrationDto = this.getById(id);
        userValidator.checkCreateByManager(existedRegistrationDto.getCreateBy());
        statusValidator.checkValidStatusForManagerUpdate(existedRegistrationDto.getStatus());
        employeeValidator.existsById(registrationDto.getEmployeeId());
        registrationDto.setStatus(UPDATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_REGISTRATION_BY_MANAGER,REGISTRATION_DTO_MAPPER )
                .registerStoredProcedureParameter(REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(REGISTRATION_ID_PARAM, id)
                .registerStoredProcedureParameter(REGISTRATION_JSON , String.class, ParameterMode.IN)
                .setParameter(REGISTRATION_JSON, JsonUtil.objectToJson(registrationDto));

        return (RegistrationDto) query.getSingleResult();
    }
}
