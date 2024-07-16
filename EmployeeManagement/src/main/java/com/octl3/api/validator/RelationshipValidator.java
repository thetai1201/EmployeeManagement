package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.StoredProcedure.Parameter;
import com.octl3.api.constants.StoredProcedure.Relationship;
import com.octl3.api.dto.RelationshipDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import static com.octl3.api.constants.Const.EXISTS_VALUE;
import static com.octl3.api.constants.StoredProcedure.Parameter.CITIZEN_ID_PARAM;
import static com.octl3.api.constants.StoredProcedure.Parameter.PHONE_PARAM;
import static com.octl3.api.constants.StoredProcedure.Relationship.EXISTS_BY_CITIZENID;
import static com.octl3.api.constants.StoredProcedure.Relationship.EXISTS_BY_PHONE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RelationshipValidator {
     EntityManager entityManager;
     CommonValidator commonValidator;
    public void existsById(Long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Relationship.EXISTS_BY_ID)
                        .registerStoredProcedureParameter(Parameter.RELATIONSHIP_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.RELATIONSHIP_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        if (result.intValue() != EXISTS_VALUE) {
            throw new OctException(ErrorMessages.NOT_FOUND_RELATIONSHIP_ID);
        }
    }
    public void checkCreate(RelationshipDto relationshipDto){
        commonValidator.checkDateOfBirth(relationshipDto.getDateOfBirth());
        if(isPhoneExists(relationshipDto.getPhoneNumber())){
            throw  new OctException(ErrorMessages.DUPLICATE_PHONE);
        }
        if(isCitizenIdExists(relationshipDto.getCitizenId())){
            throw new OctException(ErrorMessages.DUPLICATE_CITIZEN_ID);
        }
    }
    public void checkUpdate(RelationshipDto relationshipDto, RelationshipDto existingRelationship){
        if(!existingRelationship.getPhoneNumber().equals(relationshipDto.getPhoneNumber()) && isPhoneExists(relationshipDto.getPhoneNumber())){
            throw new OctException(ErrorMessages.DUPLICATE_PHONE);
        }
        if(!existingRelationship.getCitizenId().equals(relationshipDto.getCitizenId()) && isCitizenIdExists(relationshipDto.getCitizenId())){
            throw new OctException(ErrorMessages.DUPLICATE_CITIZEN_ID);
        }
    }
    public boolean isPhoneExists(String phone){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EXISTS_BY_PHONE)
                .registerStoredProcedureParameter(PHONE_PARAM, String.class, ParameterMode.IN)
                .setParameter(PHONE_PARAM, phone);
        Number result = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == EXISTS_VALUE;
    }
    public boolean isCitizenIdExists(String citizenId ){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EXISTS_BY_CITIZENID)
                .registerStoredProcedureParameter(CITIZEN_ID_PARAM, String.class, ParameterMode.IN)
                .setParameter(CITIZEN_ID_PARAM , citizenId);
        Number result  = (Number) query.getSingleResult();
        return !ObjectUtils.isEmpty(result) && result.intValue() == EXISTS_VALUE;
    }

}