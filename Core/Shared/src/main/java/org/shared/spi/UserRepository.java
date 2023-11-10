package org.shared.spi;


import org.shared.dto.UserDto;

import java.util.Optional;

public interface UserRepository {
  Optional<UserDto> findByEmail(String email);

  UserDto save(UserDto newUser);
}
