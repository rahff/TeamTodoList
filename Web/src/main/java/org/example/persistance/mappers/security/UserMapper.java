package org.example.persistance.mappers.security;

import org.example.persistance.entities.security.AppUser;
import org.example.persistance.entities.security.Authority;
import org.example.persistance.entities.security.Subscription;
import org.shared.dto.UserDto;

public class UserMapper {

  public static AppUser fromDto(UserDto userDto){
    var role = new Authority(userDto.role());
    return new AppUser(userDto.id(), userDto.email(), userDto.password(), userDto.name(), role, userDto.accountId(), SubscriptionMapper.fromDto(userDto.subscription()).orElse(null));
  }
}
