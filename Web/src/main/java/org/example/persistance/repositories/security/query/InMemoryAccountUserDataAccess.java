package org.example.persistance.repositories.security.query;

import org.query.account.dto.UserDto;
import org.query.account.spi.UserDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@Profile("test")
public class InMemoryAccountUserDataAccess implements UserDataAccess {

  public Optional<UserDto> getUserById(String userId) {
    return Optional.of(new UserDto("userId", "User1", "useremail@gmail.com", "TEAMMATE", "accountId1"));
  }
}
