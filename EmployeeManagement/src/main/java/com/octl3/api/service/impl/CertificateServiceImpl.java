package com.octl3.api.service.impl;

import com.octl3.api.dto.CertificateDto;
import com.octl3.api.service.CertificateService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.validator.CertificateValidator;
import com.octl3.api.validator.EmployeeValidator;
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

import static com.octl3.api.constants.StoredProcedure.Certificate.*;
import static com.octl3.api.constants.StoredProcedure.Mapper.CERTIFICATE_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class CertificateServiceImpl implements CertificateService {
    EntityManager entityManager;
    CertificateValidator certificateValidator;
    EmployeeValidator employeeValidator;
    @Override
    public List<CertificateDto> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_CERTIFICATE, CERTIFICATE_DTO_MAPPER);
        return query.getResultList();
    }
    @Override
    public List<CertificateDto> getAllBy(long id) {
        employeeValidator.existsById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(GET_ALL_BY_EMPLOYEE_ID, CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(EMPLOYEE_ID_PARAM, id);
        return query.getResultList();
    }
    @Transactional
    @Override
    public CertificateDto create(CertificateDto certificateDto) {
        employeeValidator.existsById(certificateDto.getEmployeeId());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(CREATE_CERTIFICATE , CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(CERTIFICATE_JSON, String.class, ParameterMode.IN)
                .setParameter(CERTIFICATE_JSON, JsonUtil.objectToJson(certificateDto));
        query.execute();
        return (CertificateDto) query.getSingleResult();
    }
    @Transactional
    @Override
    public CertificateDto update(CertificateDto certificateDto, long id) {
        certificateValidator.existsById(id);
        employeeValidator.existsById(certificateDto.getEmployeeId());
        certificateValidator.checkCreateAndUpdate(certificateDto);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(UPDATE_CERTIFICATE, CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(CERTIFICATE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(CERTIFICATE_ID_PARAM, id)
                .registerStoredProcedureParameter(CERTIFICATE_JSON, String.class, ParameterMode.IN)
                .setParameter(CERTIFICATE_JSON, JsonUtil.objectToJson(certificateDto));
        return (CertificateDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        certificateValidator.existsById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(DELETE_CERTIFICATE , CERTIFICATE_DTO_MAPPER)
                .registerStoredProcedureParameter(CERTIFICATE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(CERTIFICATE_ID_PARAM, id);
        query.execute();
    }
}
