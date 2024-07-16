package com.octl3.api.service;

import com.octl3.api.dto.ProfileEndDto;

import java.util.List;

public interface ProfileEndService {
    ProfileEndDto create(ProfileEndDto profileEndDto);
    List<ProfileEndDto> getAll();
    ProfileEndDto getById(long id);
    List<ProfileEndDto> getByStatus(String status);
    void submit(ProfileEndDto profileEndDto, long id);
    ProfileEndDto updateByManager(ProfileEndDto profileEndDto , long id);
    ProfileEndDto updateByLeader(ProfileEndDto profileEndDto , long id);
    void deleteById(long id);
}
