package utils;

import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.dto.TeamDto;

import java.util.List;

public class TeamProvider {

  public static TeamDto teamDtoWithTwoTeammates(){
    return new TeamDto("teamId", "Team1", List.of("teammate1Id", "teammate2Id"), "accountId");
  }
  public static TeamDto teamDtoWithOneTeammates(){
    return new TeamDto("teamId", "Team1", List.of("teammate1Id"), "accountId");
  }
  public static TeamDto emptyTeamDto(){
    return new TeamDto("teamId", "Team1", List.of(), "accountId");
  }
}


