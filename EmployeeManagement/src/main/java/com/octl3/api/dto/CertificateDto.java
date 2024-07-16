package com.octl3.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateDto {
    long id;
    long employeeId;
    String title;
    String field;
    LocalDate issuedDate;
    String description;
}
