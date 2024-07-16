package com.octl3.api.service;

import com.octl3.api.dto.RelationshipDto;

import java.util.List;

public interface RelationshipService {
    List<RelationshipDto> getAll();
    RelationshipDto getById(long id);
    List<RelationshipDto> getByEmployeeId(long employeeId);
    RelationshipDto create(RelationshipDto relationshipDto);

    RelationshipDto update(long id,RelationshipDto relationshipDto);
    void deleteById(long id);
}
