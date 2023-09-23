package utils;

import org.team.ports.dto.TeamDto;

import java.util.List;

public class TeamProvider {

  public static TeamDto teamDtoWithTwoTeammates(){
    return new TeamDto(StringProvider.unique(), "Team1", List.of(StringProvider.unique(), StringProvider.unique()), "accountId");
  }
  public static TeamDto teamDtoWithOneTeammates(){
    return new TeamDto(StringProvider.unique(), "Team1", List.of(StringProvider.unique()), "accountId");
  }
  public static TeamDto emptyTeamDto(){
    return new TeamDto(StringProvider.unique(), "Team1", List.of(), "accountId");
  }
}
