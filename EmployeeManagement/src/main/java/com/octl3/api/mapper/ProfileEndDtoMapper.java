package com.octl3.api.mapper;
import com.octl3.api.dto.ProfileEndDto;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "ProfileEndDtoMapper",
        classes = @ConstructorResult(
                targetClass = ProfileEndDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "registrationId", type = Long.class),
                        @ColumnResult(name = "leaderId", type = Long.class),
                        @ColumnResult(name = "endDate", type = LocalDate.class),
                        @ColumnResult(name = "endBy", type = String.class),
                        @ColumnResult(name = "reason", type = String.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "submitDate", type = LocalDate.class),
                        @ColumnResult(name = "rejectDate", type = LocalDate.class),
                        @ColumnResult(name = "rejectReason", type = String.class),
                        @ColumnResult(name = "acceptDate", type = LocalDate.class),
                        @ColumnResult(name = "storageNumber", type = Integer.class),
                }
        )
)
public class ProfileEndDtoMapper {
}
