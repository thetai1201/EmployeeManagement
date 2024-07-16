package com.octl3.api.mapper;

import com.octl3.api.dto.user.UserDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@MappedSuperclass
@SqlResultSetMapping(
        name = "UserDtoMapper",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "password", type = String.class),
                        @ColumnResult(name = "roleId", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "position", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "phoneNumber", type = String.class)
                }
        )
)
public class UserDtoMapper {
}
