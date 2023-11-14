package org.example.persistance.repositories.team.query;


import org.query.team.dto.TeamDto;
import org.query.team.spi.TeamDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class InMemoryTeamDataAccess implements TeamDataAccess {

    public static TeamDto teamDto = new TeamDto("teamId1", "Team1", List.of("id_teammate1", "id_teammate2"));
    public Optional<TeamDto> getTeamById(String id) {
        return Optional.of(teamDto);
    }

  @Override
  public List<String> getAvailableTeammatesRef(String accountId) {
    return null;
  }

  public List<String> getAvailableTeammatesRef() {
        return List.of("id_teammate3", "id_teammate4");
    }

    public List<TeamDto> getAllTeam(String accountId) {
        return List.of();
    }

    public Optional<String> getTeamIdOfTeammate(String teammateId) {
        return Optional.empty();
    }

}
