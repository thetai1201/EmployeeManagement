package com.octl3.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationDto {
     long id;
    @NotNull
     long employeeId;
     long leaderId;
     LocalDate createDate;
     String createBy;
     String content;
     String status;
     LocalDate submitDate;
     LocalDate rejectDate;
     String rejectReason;
     LocalDate acceptDate;
     String note;

}
