package org.example.persistance.repositories.security.query.mapper;

import org.example.persistance.entities.security.AppUser;
import org.query.account.dto.UserDto;

public class UserEntityMapper {

  public static UserDto toDto(AppUser entity) {
    return new UserDto(entity.getId(),
      entity.getName(),
      entity.getEmail(),
      entity.getAuthority().getUserRole(),
      entity.getAccountId()
    );
  }
}
