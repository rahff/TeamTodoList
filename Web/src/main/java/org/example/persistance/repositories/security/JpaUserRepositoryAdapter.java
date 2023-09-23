package org.example.persistance.repositories.security;

import org.example.persistance.entities.security.AppUser;
import org.example.persistance.mappers.security.UserMapper;
import org.security.ports.dto.UserDto;
import org.security.ports.spi.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@Profile("prod")
public class JpaUserRepositoryAdapter implements UserRepository {
  private final AppUserRepository userRepository;

  public JpaUserRepositoryAdapter(AppUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<UserDto> findByEmail(String email) {
    return userRepository.findByEmail(email).map(AppUser::toDto);
  }

  public UserDto save(UserDto newUser) {
    return userRepository.save(UserMapper.fromDto(newUser)).toDto();
  }
}
