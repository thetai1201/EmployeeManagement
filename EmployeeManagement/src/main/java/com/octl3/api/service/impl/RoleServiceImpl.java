package com.octl3.api.service.impl;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.dto.RoleDto;
import com.octl3.api.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static com.octl3.api.constants.Const.EXISTS_VALUE;
import static com.octl3.api.constants.StoredProcedure.Mapper.ROLE_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.ROLE_ID_PARAM;
import static com.octl3.api.constants.StoredProcedure.Parameter.ROLE_NAME_PARAM;
import static com.octl3.api.constants.StoredProcedure.User.*;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
      EntityManager entityManager;

    @Override
    public RoleDto getRoleByName(String roleName) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ROLE_BY_NAME, ROLE_DTO_MAPPER)
                .registerStoredProcedureParameter(ROLE_NAME_PARAM, String.class, ParameterMode.IN)
                .setParameter(ROLE_NAME_PARAM, roleName);
        try {
            return (RoleDto) query.getSingleResult();
        } catch (NoResultException e) {
            log.info(e.getMessage(), e);
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }

    @Override
    public boolean isExistRoleById(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(IS_EXIST_ROLE_BY_ID)
                .registerStoredProcedureParameter(ROLE_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(ROLE_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == EXISTS_VALUE;
    }

    @Override
    public RoleDto getRoleById(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ROLE_BY_ID, ROLE_DTO_MAPPER)
                .registerStoredProcedureParameter(ROLE_ID_PARAM, Integer.class, ParameterMode.IN)
                .setParameter(ROLE_ID_PARAM, id);
        try {
            return (RoleDto) query.getSingleResult();
        } catch (NoResultException e) {
            log.info(e.getMessage(), e);
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
    }
}
