package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.constants.MessageConst;
import com.octl3.api.dto.ProfileEndDto;
import com.octl3.api.service.ProfileEndService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.octl3.api.constants.MessageConst.SUBMIT_SUCCESS;

@RestController
@RequestMapping("api/v1/profileEnd")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileEndController {
    ProfileEndService profileEndService;
    @PostMapping
    public DataResponse<ProfileEndDto> create (@RequestBody ProfileEndDto profileEndDto){
        return DataResponse.ok(profileEndService.create(profileEndDto));
    }

    @GetMapping()
    public DataResponse<List<ProfileEndDto>> getAll(){
        return DataResponse.ok(profileEndService.getAll());
    }
    @GetMapping("/{id}")
    public DataResponse<ProfileEndDto> getById(@PathVariable("id") long id){
        return DataResponse.ok(profileEndService.getById(id));
    }
    @GetMapping("/by-status")
    public DataResponse<List<ProfileEndDto>> getByStatus(@RequestParam("status") String status){
        return DataResponse.ok(profileEndService.getByStatus(status));
    }
    @PutMapping("/submit/{id}")
    public DataResponse<String> submit(@PathVariable("id") long id,
                                       @RequestBody ProfileEndDto profileEndDto){
        profileEndService.submit(profileEndDto, id);
        return DataResponse.ok(SUBMIT_SUCCESS);
    }
    @PutMapping("/{id}")
    public DataResponse<ProfileEndDto> updateByManager(@PathVariable("id") long id,
                                                       @RequestBody ProfileEndDto profileEndDto){
        return DataResponse.ok(profileEndService.updateByManager(profileEndDto , id));
    }
    @PutMapping("/by-leader/{id}")
    public DataResponse<ProfileEndDto> updateByLeader(@PathVariable("id") long id,
                                                      @RequestBody ProfileEndDto  profileEndDto){
        return DataResponse.ok(profileEndService.updateByLeader(profileEndDto, id));
    }
    @DeleteMapping("/{id}")
    public DataResponse<String> deleteById(@PathVariable("id") long id){
        profileEndService.deleteById(id);
        return DataResponse.ok(MessageConst.DELETE_SUCCESS);
    }
}
