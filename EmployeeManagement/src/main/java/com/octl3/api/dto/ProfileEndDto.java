package com.octl3.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileEndDto {
     long id;
     long registrationId;
     long leaderId;
     LocalDate endDate;
     String endBy;
     String reason;
     String status;
     LocalDate submitDate;
     LocalDate rejectDate;
     String rejectReason;
     LocalDate acceptDate;
     Integer storageNumber;
}
