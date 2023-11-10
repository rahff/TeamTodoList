package org.example.controllers.api.security.mapper;

import org.example.controllers.api.security.jsonPayload.UserJson;
import org.shared.dto.UserDto;

public class UserDtoMapper {

    public static UserJson toJson(UserDto dto) {
        return new UserJson(dto.id(), dto.name(), dto.email(), dto.role(), dto.accountId());
    }
}
