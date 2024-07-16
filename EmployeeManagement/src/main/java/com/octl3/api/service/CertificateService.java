package com.octl3.api.service;

import com.octl3.api.dto.CertificateDto;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> getAll();
    List<CertificateDto> getAllBy(long id);
    CertificateDto create(CertificateDto certificateDto);
    CertificateDto update(CertificateDto certificateDto, long id);
    void deleteById(long id);
}
