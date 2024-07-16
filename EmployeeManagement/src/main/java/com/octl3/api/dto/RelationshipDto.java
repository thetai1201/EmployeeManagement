package com.octl3.api.dto;

import com.octl3.api.validator.anotations.CitizenId;
import com.octl3.api.validator.anotations.Phone;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationshipDto {
    long id;
    long employeeId;
    String name;
    String gender;
    LocalDate dateOfBirth;
    String address;
    @CitizenId
    String citizenId;
    @Phone
    String phoneNumber;
    String job;
    String relationship;
}
