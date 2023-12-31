package org.example.persistance.repositories.team.query;


import org.example.persistance.repositories.security.command.JpaUserRepositoryAdapter;
import org.example.persistance.repositories.team.springData.JpaTeamRepository;
import org.example.persistance.repositories.team.query.mappers.TeamMapper;
import org.query.team.dto.TeamDto;
import org.query.team.spi.TeamDataAccess;
import org.shared.dto.UserDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("prod")
public class JpaTeamQueryTeamDataAccess implements TeamDataAccess {

    private final JpaTeamRepository teamRepository;
    private final JpaUserRepositoryAdapter teammateRepository;

  public JpaTeamQueryTeamDataAccess(JpaTeamRepository teamRepository, JpaUserRepositoryAdapter teammateRepository) {
    this.teamRepository = teamRepository;
    this.teammateRepository = teammateRepository;
  }

  public Optional<TeamDto> getTeamById(String id) {
        return teamRepository.findById(id).map(TeamMapper::toDto);
    }

    public List<String> getAvailableTeammatesRef(String accountId) {
      return teammateRepository.findByAccountIdAndTeamIdIsNull(accountId)
        .stream().map(UserDto::id).toList();
    }

    public List<TeamDto> getAllTeam(String accountId) {
        var teamList = teamRepository.findByAccountId(accountId);
        return teamList.stream().map(TeamMapper::toDto).toList();
    }

    public Optional<String> getTeamIdOfTeammate(String teammateId) {
        var result = teammateRepository.findById(teammateId);
        return result.stream().findFirst().flatMap(UserDto::teamId);
    }
}
