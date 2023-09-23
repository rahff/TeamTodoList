package org.team.ports.spi;

import org.team.ports.dto.TeamDto;

import java.util.Optional;

public interface TeamRepository {
  void saveTeam(TeamDto teamDto);

  Optional<TeamDto> getTeamById(String teamId);

    void deleteTeam(String teamId);
}
