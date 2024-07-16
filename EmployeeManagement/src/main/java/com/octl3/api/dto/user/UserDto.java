package com.octl3.api.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octl3.api.validator.anotations.Password;
import com.octl3.api.validator.anotations.Phone;
import com.octl3.api.validator.anotations.Username;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    @Username
    String username;
    @Password
    String password;
     Integer roleId;
    @NotBlank
    String name;
    @NotBlank
    String position;
    @Email
    String email;
    @Phone
    String phoneNumber;

}