package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.RegistrationDto;
import com.octl3.api.service.RegistrationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.octl3.api.constants.MessageConst.RESUBMIT_SUCCESS;
import static com.octl3.api.constants.MessageConst.SUBMIT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/registration")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationController {
    RegistrationService registrationService;
    @PostMapping
    public DataResponse<RegistrationDto> create (@RequestBody RegistrationDto registrationDto){
        return DataResponse.ok( registrationService.create(registrationDto));
    }

    @GetMapping()
    public DataResponse <List<RegistrationDto>> getAll(){
        return DataResponse.ok(registrationService.getAll());
    }
    @GetMapping("/getAllByRole")
    public DataResponse <List<RegistrationDto>> getAllByByRole(){
        return DataResponse.ok(registrationService.getAllByRole());
    }
    @GetMapping("/{id}")
    public DataResponse <RegistrationDto> getById(@PathVariable("id") long id){
        return DataResponse.ok(registrationService.getById(id));
    }
    @GetMapping("/by-status")
    public DataResponse <List<RegistrationDto>> getByStatus(@RequestParam("status") String status){
        return DataResponse.ok(registrationService.getByStatus(status));
    }
    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable long id){
        registrationService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }
    @PutMapping("/submit/{id}")
    public DataResponse<String> submit(@PathVariable("id") long id,
                                       @RequestBody RegistrationDto registrationDto){
        registrationService.submit(registrationDto, id);
        return DataResponse.ok(SUBMIT_SUCCESS);
    }
    @PutMapping("/resubmit/{id}")
    public DataResponse<String> resubmit(@PathVariable("id") long id,
                                         @RequestBody RegistrationDto registrationDto){
        registrationService.resubmit(registrationDto, id);
        return DataResponse.ok(RESUBMIT_SUCCESS);
    }

    @PutMapping("/by-leader/{id}")
    public DataResponse <RegistrationDto> updateByLeader(@PathVariable("id") long id,
                                                         @RequestBody RegistrationDto registrationDto){
        return DataResponse.ok(registrationService.updateByLeader(registrationDto, id));
    }
    @PutMapping("/{id}")
    public DataResponse <RegistrationDto> updateByManager(@PathVariable("id") long id,
                                                          @RequestBody RegistrationDto registrationDto){
        return DataResponse.ok(registrationService.updateByManager(registrationDto, id));
    }
}
