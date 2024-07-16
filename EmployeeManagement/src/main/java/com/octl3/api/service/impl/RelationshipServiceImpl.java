package com.octl3.api.service.impl;

import com.octl3.api.dto.RelationshipDto;
import com.octl3.api.service.RelationshipService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.EmployeeValidator;
import com.octl3.api.validator.RelationshipValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;

import static com.octl3.api.constants.StoredProcedure.Mapper.RELATIONSHIP_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;
import static com.octl3.api.constants.StoredProcedure.Relationship.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class RelationshipServiceImpl implements RelationshipService {
    EntityManager entityManager;
    RelationshipValidator relationshipValidator;
    EmployeeValidator employeeValidator;


    @Override
    public List<RelationshipDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery( GET_ALL_RELATIONSHIP,RELATIONSHIP_DTO_MAPPER );
        return query.getResultList();
    }

    @Override
    public RelationshipDto getById(long id) {
       relationshipValidator.existsById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_BY_RELATIONSHIP_ID, RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(RELATIONSHIP_ID_PARAM,Long.class, ParameterMode.IN)
                .setParameter(RELATIONSHIP_ID_PARAM , id);
        return (RelationshipDto) query.getSingleResult();
    }

    @Override
    public List<RelationshipDto> getByEmployeeId(long employeeId) {
        employeeValidator.existsById(employeeId);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_BY_EMPLOYEE_ID, RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(EMPLOYEE_ID_PARAM , Long.class , ParameterMode.IN)
                .setParameter(EMPLOYEE_ID_PARAM, employeeId);

        return query.getResultList();
    }
    @Transactional
    @Override
    public RelationshipDto create(RelationshipDto relationshipDto) {
        employeeValidator.existsById(relationshipDto.getEmployeeId());
        relationshipValidator.checkCreate(relationshipDto);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_RELATIONSHIP, RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(RELATIONSHIP_JSON, String.class , ParameterMode.IN)
                .setParameter(RELATIONSHIP_JSON, JsonUtil.objectToJson(relationshipDto));
        query.execute();
        return (RelationshipDto) query.getSingleResult();
    }
    @Transactional
    @Override
    public RelationshipDto update( long id,RelationshipDto relationshipDto) {
        employeeValidator.existsById(relationshipDto.getEmployeeId());
        RelationshipDto existingRelationship = this.getById(id);
        relationshipValidator.checkUpdate(relationshipDto, existingRelationship);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_RELATIONSHIP, RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(RELATIONSHIP_ID_PARAM , Long.class, ParameterMode.IN)
                .setParameter(RELATIONSHIP_ID_PARAM, id)
                .registerStoredProcedureParameter(RELATIONSHIP_JSON , String.class, ParameterMode.IN)
                .setParameter(RELATIONSHIP_JSON, JsonUtil.objectToJson(relationshipDto));
        return (RelationshipDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        relationshipValidator.existsById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_RELATIONSHIP, RELATIONSHIP_DTO_MAPPER)
                .registerStoredProcedureParameter(RELATIONSHIP_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(RELATIONSHIP_ID_PARAM, id);
        query.execute();
    }
}
