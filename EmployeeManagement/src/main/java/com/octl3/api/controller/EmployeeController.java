package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.EmployeeDto;
import com.octl3.api.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;
    @GetMapping()
    public DataResponse<List<EmployeeDto>> getAll(){
        return DataResponse.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public DataResponse<EmployeeDto>getById(@PathVariable("id") Integer id) {
        return DataResponse.ok(employeeService.getById(id));
    }
    @PostMapping()
    public DataResponse<EmployeeDto> create(@Valid @RequestPart("employeeDto") EmployeeDto employeeDto,
                                            @RequestPart("fileImage") MultipartFile fileImage) {
        return DataResponse.ok(employeeService.create(employeeDto,fileImage));
    }
    @PutMapping("/{id}")
    public DataResponse<EmployeeDto> update(@PathVariable("id") long id,
                                            @RequestPart("employeeDto") EmployeeDto employeeDto,
                                            @RequestPart("fileImage") MultipartFile fileImage){
        return DataResponse.ok(employeeService.update(id,employeeDto, fileImage));
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable  long id){
        employeeService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }
}
