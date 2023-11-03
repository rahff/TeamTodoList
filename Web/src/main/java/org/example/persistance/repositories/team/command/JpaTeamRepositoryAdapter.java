package org.example.persistance.repositories.team.command;

import org.example.persistance.entities.team.Team;
import org.example.persistance.mappers.team.TeamMapper;
import org.example.persistance.repositories.team.command.JpaTeamRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.team.ports.dto.TeamDto;
import org.team.ports.spi.TeamRepository;

import java.util.Optional;

@Repository
@Profile("prod")
public class JpaTeamRepositoryAdapter implements TeamRepository {

  private final JpaTeamRepository teamRepository;

  public JpaTeamRepositoryAdapter(JpaTeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public void saveTeam(TeamDto teamDto) {
    var teamToSave = TeamMapper.fromDto(teamDto);
    teamRepository.save(teamToSave);
  }

  public Optional<TeamDto> getTeamById(String teamId) {
    return teamRepository.findById(teamId).map(Team::toDto);
  }

  public void deleteTeam(String teamId) {
    teamRepository.deleteById(teamId);
  }
}
