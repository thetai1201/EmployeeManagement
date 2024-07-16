package com.octl3.api.mapper;

import com.octl3.api.dto.CertificateDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "CertificateDtoMapper",
        classes = @ConstructorResult(
                targetClass = CertificateDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "employeeId", type = Long.class),
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "field", type = String.class),
                        @ColumnResult(name = "issuedDate", type = LocalDate.class),
                        @ColumnResult(name = "description", type = String.class)
                }
        )
)
public class CertificateDtoMapper {
}
