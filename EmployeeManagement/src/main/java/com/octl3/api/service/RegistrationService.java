package com.octl3.api.service;

import com.octl3.api.dto.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    RegistrationDto create(RegistrationDto registrationDto);
    List<RegistrationDto> getAll();
    List<RegistrationDto> getAllByRole();
    RegistrationDto getById(long id);
    List<RegistrationDto> getByStatus(String status);
    void deleteById(long id);
    void submit(RegistrationDto registrationDto, long id);
    void resubmit(RegistrationDto registrationDto, long id);
    RegistrationDto updateByLeader(RegistrationDto registrationDto , long id);
    RegistrationDto updateByManager(RegistrationDto registrationDto , long id);

}
