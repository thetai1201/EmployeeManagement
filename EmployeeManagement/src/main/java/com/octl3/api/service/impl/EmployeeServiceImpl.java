package com.octl3.api.service.impl;

import com.octl3.api.dto.EmployeeDto;
import com.octl3.api.service.EmployeeService;
import com.octl3.api.utils.JsonUtil;
import com.octl3.api.utils.UploadFile;
import com.octl3.api.validator.EmployeeValidator;
import com.octl3.api.validator.UserValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;

import static com.octl3.api.constants.FileConst.EMPLOYEE_IMAGE_PREFIX;
import static com.octl3.api.constants.StoredProcedure.Employee.*;
import static com.octl3.api.constants.StoredProcedure.Mapper.EMPLOYEE_DTO_MAPPER;
import static com.octl3.api.constants.StoredProcedure.Parameter.EMPLOYEE_ID_PARAM;
import static com.octl3.api.constants.StoredProcedure.Parameter.EMPLOYEE_JSON;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeServiceImpl implements EmployeeService {
    EntityManager entityManeger;
    EmployeeValidator employeeValidator;
    UserValidator userValidator;
    @Transactional
    @Override
    public EmployeeDto create(EmployeeDto employeeDto, MultipartFile fileImage) {
        employeeValidator.checkCreate(employeeDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        employeeDto.setCreateBy(authentication.getName());
        if(!fileImage.isEmpty()){
            employeeDto.setImage(UploadFile.uploadImage(fileImage,EMPLOYEE_IMAGE_PREFIX));
        }
        StoredProcedureQuery query = entityManeger.createStoredProcedureQuery(CREATE_EMPLOYEE , EMPLOYEE_DTO_MAPPER)
                .registerStoredProcedureParameter(EMPLOYEE_JSON, String.class, ParameterMode.IN)
                .setParameter(EMPLOYEE_JSON, JsonUtil.objectToJson(employeeDto));
        query.execute();
        return (EmployeeDto) query.getSingleResult();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<EmployeeDto> getAll() {
        StoredProcedureQuery query = entityManeger.createStoredProcedureQuery(GET_ALL, EMPLOYEE_DTO_MAPPER);
        return query.getResultList();
    }

    @Override
    public EmployeeDto getById(long id) {
        employeeValidator.existsById(id);
        StoredProcedureQuery query = entityManeger.createStoredProcedureQuery(GET_BY_ID, EMPLOYEE_DTO_MAPPER)
                .registerStoredProcedureParameter(EMPLOYEE_ID_PARAM, Long.class, ParameterMode.IN)
                .setParameter(EMPLOYEE_ID_PARAM, id);
        return (EmployeeDto) query.getSingleResult();
    }
    @Transactional
    @Override
    public EmployeeDto update(long id,EmployeeDto employeeDto,  MultipartFile fileImage) {
        userValidator.checkCreateByManager(getById(id).getCreateBy());
        EmployeeDto existingEmployeeDto = this.getById(id);
        employeeValidator.checkUpdate(employeeDto, existingEmployeeDto);
        if (!fileImage.isEmpty()) {
            UploadFile.deleteImage(getById(id).getImage());
            employeeDto.setImage(UploadFile.uploadImage(fileImage, EMPLOYEE_IMAGE_PREFIX));
        }
        StoredProcedureQuery query = entityManeger.createStoredProcedureQuery(UPDATE_EMPLOYEE, EMPLOYEE_DTO_MAPPER)
                .registerStoredProcedureParameter(EMPLOYEE_ID_PARAM , Long.class, ParameterMode.IN)
                .setParameter(EMPLOYEE_ID_PARAM , id)
                .registerStoredProcedureParameter(EMPLOYEE_JSON, String.class, ParameterMode.IN)
                .setParameter(EMPLOYEE_JSON, JsonUtil.objectToJson(employeeDto));
        return (EmployeeDto) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        employeeValidator.existsById(id);
        UploadFile.deleteImage(getById(id).getImage());
        StoredProcedureQuery query = entityManeger.createStoredProcedureQuery(DELETE_EMPLOYEE)
                .registerStoredProcedureParameter(EMPLOYEE_ID_PARAM , Long.class, ParameterMode.IN)
                .setParameter(EMPLOYEE_ID_PARAM , id);
        query.execute();
    }
}
