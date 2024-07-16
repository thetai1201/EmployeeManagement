package com.octl3.api.mapper;

import com.octl3.api.dto.RegistrationDto;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "RegistrationDtoMapper",
        classes = @ConstructorResult(
                targetClass = RegistrationDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "employeeId", type = Long.class),
                        @ColumnResult(name = "leaderId", type = Long.class),
                        @ColumnResult(name = "createDate", type = LocalDate.class),
                        @ColumnResult(name = "createBy", type = String.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "submitDate", type = LocalDate.class),
                        @ColumnResult(name = "rejectDate", type = LocalDate.class),
                        @ColumnResult(name = "rejectReason", type = String.class),
                        @ColumnResult(name = "acceptDate", type = LocalDate.class),
                        @ColumnResult(name = "note", type = String.class),
                }
        )
)
public class RegistrationDtoMapper {
}
