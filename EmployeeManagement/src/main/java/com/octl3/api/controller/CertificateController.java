package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.CertificateDto;
import com.octl3.api.service.CertificateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/certificate")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CertificateController {
    CertificateService certificateService;
    @GetMapping("/{id}")
    public DataResponse<List<CertificateDto>> getAllBy(@PathVariable("id") long id) {
        return DataResponse.ok(certificateService.getAllBy(id));
    }
    @GetMapping()
    public DataResponse<List<CertificateDto>> getAll(){
        return DataResponse.ok(certificateService.getAll());
    }

    @PostMapping()
    public DataResponse<CertificateDto> create(@RequestBody CertificateDto certificateDto){
        return DataResponse.ok(certificateService.create(certificateDto));
    }
    @PutMapping("/{id}")
    public DataResponse<CertificateDto> update(@PathVariable("id")Integer id,
                                               @RequestBody CertificateDto certificateDto){
        return DataResponse.ok(certificateService.update(certificateDto,id));
    }
    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable  Integer id){
        certificateService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }
}
