package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.dto.user.UserResponseDto;
import com.octl3.api.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/leader")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LeaderController {

    UserService userService;

    @GetMapping
    public DataResponse<List<UserResponseDto>> getAllLeader() {
        return DataResponse.ok(userService.getAllLeader());
    }

    @GetMapping("/{id}")
    public DataResponse<UserResponseDto> getLeaderById(@PathVariable("id") long id) {
        return DataResponse.ok(userService.getLeaderById(id));
    }

}
