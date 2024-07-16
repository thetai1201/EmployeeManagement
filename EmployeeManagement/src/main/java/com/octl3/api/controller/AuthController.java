package com.octl3.api.controller;

import com.octl3.api.commons.DataResponse;
import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.dto.TokenResponse;
import com.octl3.api.dto.user.UserDto;
import com.octl3.api.dto.user.UserLogin;
import com.octl3.api.dto.user.UserResponseDto;
import com.octl3.api.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.octl3.api.constants.MessageConst.UN_AUTHENTICATION;
import static com.octl3.api.constants.MessageConst.UN_AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    UserService userService;

    @PostMapping("/register")
    public DataResponse<UserResponseDto> register(@Valid @RequestBody UserDto userDto) {
        return DataResponse.ok(userService.saveUser(userDto));
    }

    @PostMapping("/login")
    public DataResponse<TokenResponse> login(@Valid @RequestBody UserLogin userLogin) {
        return DataResponse.ok(userService.login(userLogin));
    }

    @GetMapping("/un-authorization")
    public DataResponse<String> unAuthorization() {
        return DataResponse.build(UN_AUTHORIZATION, ErrorMessages.FORBIDDEN);
    }

    @GetMapping("/un-authentication")
    public DataResponse<String> unAuthentication() {
        return DataResponse.build(UN_AUTHENTICATION, ErrorMessages.UNAUTHORIZED);
    }
}
