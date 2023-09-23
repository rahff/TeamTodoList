package org.security.ports.spi;

import org.security.ports.dto.UserDto;
import java.util.Optional;

public interface UserRepository {
  Optional<UserDto> findByEmail(String email);

  UserDto save(UserDto newUser);
}
