package com.octl3.api.service.impl;

import com.octl3.api.constants.Status;
import com.octl3.api.dto.ProfileEndDto;
import com.octl3.api.service.ProfileEndService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.ProfileEndValidator;
import com.octl3.api.validator.RegistrationValidator;
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
import static com.octl3.api.constants.StoredProcedure.Mapper.PROFILE_END_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.ProfileEnd.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileEndServiceImpl implements ProfileEndService {
    EntityManager entityManager;
    StatusValidator statusValidator;
    ProfileEndValidator profileEndValidator;
    UserValidator userValidator;
    RegistrationValidator registrationValidator;
    @Transactional
    @Override
    public ProfileEndDto create(ProfileEndDto profileEndDto) {
        registrationValidator.existsById(profileEndDto.getRegistrationId());
        profileEndDto.setEndDate(LocalDate.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        profileEndDto.setEndBy(authentication.getName());
        profileEndDto.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_PROFILE_END,PROFILE_END_DTO_MAPPER )
                .registerStoredProcedureParameter( PROFILE_END_JSON, String.class , ParameterMode.IN)
                .setParameter(PROFILE_END_JSON, JsonUtil.objectToJson(profileEndDto));

        return (ProfileEndDto) query.getSingleResult();
    }

    @Override
    public List<ProfileEndDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_PROFILE_END, PROFILE_END_DTO_MAPPER);

        return query.getResultList();
    }

    @Override
    public ProfileEndDto getById(long id) {
        profileEndValidator.existsById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_PROFILE_BY_ID, PROFILE_END_DTO_MAPPER)
                .registerStoredProcedureParameter(PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROFILE_END_ID_PARAM, id);

        return (ProfileEndDto) query.getSingleResult();
    }

    @Override
    public List<ProfileEndDto> getByStatus(String status) {
        statusValidator.checkValidStatus(status);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_PROFILE_BY_STATUS, PROFILE_END_DTO_MAPPER)
                .registerStoredProcedureParameter(STATUS_PARAM, String.class, ParameterMode.IN)
                .setParameter(STATUS_PARAM, status);

        return query.getResultList();
    }
    @Transactional
    @Override
    public void submit(ProfileEndDto profileEndDto, long id) {
        userValidator.checkCreateByManager(this.getById(id).getEndBy());
        userValidator.checkExistLeaderId(profileEndDto.getLeaderId());
        profileEndDto.setStatus(PENDING.getValue());
        profileEndDto.setSubmitDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SUBMIT_PROFILE_END, PROFILE_END_DTO_MAPPER)
                .registerStoredProcedureParameter(PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROFILE_END_ID_PARAM, id)
                .registerStoredProcedureParameter(PROFILE_END_JSON, String.class, ParameterMode.IN)
                .setParameter(PROFILE_END_JSON, JsonUtil.objectToJson(profileEndDto));
        query.execute();
    }
    @Transactional
    @Override
    public ProfileEndDto updateByManager(ProfileEndDto profileEndDto, long id) {
        userValidator.checkCreateByManager(this.getById(id).getEndBy());
        registrationValidator.existsById(profileEndDto.getRegistrationId());
        profileEndDto.setStatus(UPDATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATA_PROFILE_BY_MANAGER, PROFILE_END_DTO_MAPPER)
                .registerStoredProcedureParameter(PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROFILE_END_ID_PARAM, id)
                .registerStoredProcedureParameter(PROFILE_END_JSON, String.class, ParameterMode.IN)
                .setParameter(PROFILE_END_JSON, JsonUtil.objectToJson(profileEndDto));
        return (ProfileEndDto) query.getSingleResult();
    }
    @Transactional
    @Override
    public ProfileEndDto updateByLeader(ProfileEndDto profileEndDto, long id) {
        ProfileEndDto existedProfileEndDto = this.getById(id);
        userValidator.checkIsForLeader(existedProfileEndDto.getLeaderId());
        statusValidator.checkValidLeaderStatus(profileEndDto.getStatus());
        if(profileEndDto.getStatus().equals(ACCEPTED.getValue())){
            profileEndDto.setAcceptDate(LocalDate.now());
        }
        if (profileEndDto.getStatus().equals(REJECTED.getValue())){
            profileEndDto.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_PROFILE_BY_LEADER, PROFILE_END_DTO_MAPPER)
                .registerStoredProcedureParameter(PROFILE_END_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(PROFILE_END_ID_PARAM , id)
                .registerStoredProcedureParameter(PROFILE_END_JSON, String.class, ParameterMode.IN)
                .setParameter(PROFILE_END_JSON , JsonUtil.objectToJson(profileEndDto));
        return (ProfileEndDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        profileEndValidator.existsById(id);
        userValidator.checkCreateByManager(getById(id).getEndBy());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_PROFILE_END, PROFILE_END_DTO_MAPPER)
                .registerStoredProcedureParameter(PROFILE_END_ID_PARAM , Long.class,ParameterMode.IN)
                .setParameter(PROFILE_END_ID_PARAM, id);
        query.execute();
    }
}
