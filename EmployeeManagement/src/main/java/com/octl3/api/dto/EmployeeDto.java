package com.octl3.api.dto;

import com.octl3.api.validator.anotations.CitizenId;
import com.octl3.api.validator.anotations.Phone;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {
    long id;
    String name;
    String gender;
    LocalDate dateOfBirth;
    String address;
    String image;
    @CitizenId
    String citizenId;
    @Phone
    String phoneNumber;
    @Email
    String email;
    String createBy;

}
