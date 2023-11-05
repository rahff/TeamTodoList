package org.query.team.spi.fakes;

import org.query.team.dto.TeamDto;
import org.query.team.spi.TeamDataAccess;

import java.util.List;
import java.util.Optional;


public class InMemoryTeamDataAccess implements TeamDataAccess {

    public static TeamDto teamDto = new TeamDto("teamId1", "Team1", List.of("id_teammate1", "id_teammate2"));
    public static TeamDto teamDto2 = new TeamDto("teamId2", "Team2", List.of("id_teammate3", "id_teammate4"));
    public Optional<TeamDto> getTeamById(String id) {
        return Optional.of(teamDto);
    }

    public List<String> getAvailableTeammatesRef() {
        return List.of("id_teammate3", "id_teammate4");
    }

    public List<TeamDto> getAllTeam(String accountId) {
        return List.of(teamDto, teamDto2);
    }


    public Optional<String> getTeamIdOfTeammate(String teammateId) {
        return switch (teammateId){
            case "teammateId1", "teammateId2" ->  Optional.of("teamId1");
            case "teammateId3", "teammateId4" ->  Optional.of("teamId2");
            default -> Optional.empty();
        };

    }
}
