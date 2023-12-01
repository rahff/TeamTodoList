package org.example.persistance.repositories.team.command;

import org.example.persistance.entities.team.Teammate;
import org.example.persistance.repositories.security.springData.AppUserRepository;
import org.example.persistance.repositories.team.springData.JpaTeammateRepository;
import org.shared.dto.UserDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.team.ports.spi.TeammateRepository;

import java.util.List;

@Repository
@Profile("prod")
public class JpaTeammateRepositoryAdapter implements TeammateRepository {

  private final JpaTeammateRepository repository;
  private final AppUserRepository userRepository;

  public JpaTeammateRepositoryAdapter(JpaTeammateRepository repository, AppUserRepository userRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
  }

  public void saveUserAsTeammate(UserDto userDto) {
    var teammate = new Teammate(userDto.id(), userDto.accountId(), null);
    repository.save(teammate);
  }
  public void addTeamIdOnTeammate(List<String> ids, final String teamId) {
    var entities = ids.parallelStream().map(id -> repository.findByRef(id).stream().findFirst().orElseThrow()).toList();
    entities.parallelStream().forEach((entity) -> {
      entity.setTeamId(teamId);
      repository.save(entity);
    });
  }

  public void removeTeammateUser(String userId) {
    userRepository.deleteById(userId);
    repository.deleteByRef(userId);
  }
}
