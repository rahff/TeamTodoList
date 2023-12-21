package org.example.persistance.repositories.security.command;

import org.example.persistance.entities.security.AppUser;
import org.example.persistance.mappers.security.UserMapper;
import org.example.persistance.repositories.security.springData.AppUserRepository;
import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
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

  public Optional<UserDto> findBySubscription(String subscriptionId) {
    return userRepository.findBySubscriptionId(subscriptionId).map(AppUser::toDto);
  }

  public Optional<UserDto> findByAccountIdAndTeamIdIsNull(String accountId) {
    return userRepository.findByAccountIdAndTeamIdIsNull(accountId).stream().findFirst().map(AppUser::toDto);
  }

  public Optional<UserDto> findById(String teammateId) {
    return userRepository.findById(teammateId).map(AppUser::toDto);
  }
  public void addTeamIdOnTeammate(List<String> ids, final String teamId) {
    var entities = ids.parallelStream().map(id -> userRepository.findById(id).stream().findFirst().orElseThrow()).toList();
    entities.parallelStream().forEach((entity) -> {
      entity.setTeamId(teamId);
      userRepository.save(entity);
    });
  }

  @Override
  public void removeTeammateUser(String teammateId) {

  }
}
