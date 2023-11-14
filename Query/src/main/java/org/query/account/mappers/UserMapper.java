package org.query.account.mappers;

import org.query.account.dto.UserDto;
import org.query.account.model.User;

public class UserMapper {

  public static User fromDto(UserDto dto) {
    return new User(dto.id(), dto.name(), dto.email(), dto.role(), dto.accountId());
  }
}
