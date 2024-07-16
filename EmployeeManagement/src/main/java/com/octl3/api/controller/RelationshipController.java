package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.RelationshipDto;
import com.octl3.api.service.RelationshipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/relationship")
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class RelationshipController {
    RelationshipService relationshipService;
    @GetMapping()
    public DataResponse<List<RelationshipDto>> getAll(){
        return DataResponse.ok(relationshipService.getAll());
    }
    @GetMapping("/{id}")
    public DataResponse<RelationshipDto>  getById(@PathVariable("id") long id) {
        return DataResponse.ok(relationshipService.getById(id));
    }
    @GetMapping("/employeeId")
    public DataResponse <List<RelationshipDto>> getByEmployeeId(@RequestParam("employeeId") long employeeId){
        return DataResponse.ok(relationshipService.getByEmployeeId(employeeId));
    }
    @PostMapping()
    public DataResponse<RelationshipDto> create(@RequestBody RelationshipDto relationshipDto){

        return DataResponse.ok(relationshipService.create(relationshipDto));
    }
    @PutMapping("/{id}")
    public DataResponse<RelationshipDto> update(@PathVariable("id") long id,
                                                      @RequestBody RelationshipDto relationshipDto){
        return DataResponse.ok(relationshipService.update(id, relationshipDto));
    }
    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable long id){
        relationshipService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }
}
