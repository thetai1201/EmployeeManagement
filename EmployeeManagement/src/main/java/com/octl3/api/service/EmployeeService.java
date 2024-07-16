package com.octl3.api.service;

import com.octl3.api.dto.EmployeeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto , MultipartFile fileImage);
    List<EmployeeDto> getAll();
    EmployeeDto getById(long id);
    EmployeeDto update(long id,EmployeeDto employeeDto ,  MultipartFile fileImage);
    void  deleteById(long id);
}
