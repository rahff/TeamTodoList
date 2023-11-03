package org.queryTeam.spi.fakes;

import org.queryTeam.dto.TeamDto;
import org.queryTeam.spi.TeamDataAccess;

import java.util.List;


public class InMemoryTeamDataAccess implements TeamDataAccess {

    public static TeamDto teamDto = new TeamDto("teamId1", "Team1", List.of("id_teammate1", "id_teammate2"));
    public static TeamDto teamDto2 = new TeamDto("teamId2", "Team2", List.of("id_teammate3", "id_teammate4"));
    public TeamDto getTeamById(String id) {
        return teamDto;
    }

    public List<String> getAvailableTeammatesRef() {
        return List.of("id_teammate3", "id_teammate4");
    }

    public List<TeamDto> getAllTeam(String accountId) {
        return List.of(teamDto, teamDto2);
    }
}
