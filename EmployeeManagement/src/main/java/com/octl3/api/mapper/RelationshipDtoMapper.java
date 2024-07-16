package com.octl3.api.mapper;

import com.octl3.api.dto.RelationshipDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;

@MappedSuperclass
@SqlResultSetMapping(
        name = "RelationshipDtoMapper",
        classes = @ConstructorResult(
                targetClass = RelationshipDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "employeeId", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "gender", type = String.class),
                        @ColumnResult(name = "dateOfBirth", type = LocalDate.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "citizenId", type = String.class),
                        @ColumnResult(name = "phoneNumber", type = String.class),
                        @ColumnResult(name = "job", type = String.class),
                        @ColumnResult(name = "relationship", type = String.class)
                }
        )
)
public class RelationshipDtoMapper {
}
