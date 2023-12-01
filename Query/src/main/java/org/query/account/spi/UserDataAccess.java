package org.query.account.spi;



import org.query.account.dto.UserDto;

import java.util.Optional;

public interface UserDataAccess {
  Optional<UserDto> getUserById(String userId);
}
