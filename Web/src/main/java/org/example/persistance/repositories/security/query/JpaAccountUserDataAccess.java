package org.example.persistance.repositories.security.query;

import org.example.persistance.repositories.security.springData.AppUserRepository;
import org.example.persistance.repositories.security.query.mapper.UserEntityMapper;
import org.query.account.dto.UserDto;
import org.query.account.spi.UserDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@Profile("prod")
public class JpaAccountUserDataAccess implements UserDataAccess {


  private final AppUserRepository repository;

  public JpaAccountUserDataAccess(AppUserRepository repository) {
    this.repository = repository;
  }

  public Optional<UserDto> getUserById(String userId) {
    return repository.findById(userId)
      .map(UserEntityMapper::toDto);
  }
}


