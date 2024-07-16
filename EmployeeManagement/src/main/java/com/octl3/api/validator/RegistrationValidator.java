package com.octl3.api.validator;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import com.octl3.api.constants.Const;
import com.octl3.api.constants.StoredProcedure.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RegistrationValidator {
    EntityManager entityManager;

    public void existsById(Long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(Registration.EXISTS_BY_ID)
                        .registerStoredProcedureParameter(Parameter.REGISTRATION_ID_PARAM, Long.class, ParameterMode.IN)
                        .setParameter(Parameter.REGISTRATION_ID_PARAM, id);
        Number result = (Number) query.getSingleResult();
        if (result.intValue() != Const.EXISTS_VALUE) {
            throw new OctException(ErrorMessages.NOT_FOUND_REGISTRATION_ID);
        }
    }
}
