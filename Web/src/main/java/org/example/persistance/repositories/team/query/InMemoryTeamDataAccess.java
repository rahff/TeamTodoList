package org.example.persistance.repositories.team.query;

import org.queryTeam.dto.TeamDto;
import org.queryTeam.spi.TeamDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("test")
public class InMemoryTeamDataAccess implements TeamDataAccess {

    public static TeamDto teamDto = new TeamDto("teamId1", "Team1", List.of("id_teammate1", "id_teammate2"));
    public TeamDto getTeamById(String id) {
        return teamDto;
    }

    public List<String> getAvailableTeammatesRef() {
        return List.of("id_teammate3", "id_teammate4");
    }

}
