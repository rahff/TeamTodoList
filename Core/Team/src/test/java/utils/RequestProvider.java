package utils;

import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.dto.CreateTeamRequest;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;

import java.util.List;

public class RequestProvider {
  public static AddTeammatesOnTeamRequest getAddTeammatesRequest(){
    return new AddTeammatesOnTeamRequest("teamId", List.of("teammate1Id", "teammate2Id"));
  }
  public static CreateTeamRequest getACreateTeamRequest(){
    return new CreateTeamRequest("teamId", "Team1", List.of("teammate1Id"), "accountId");
  }
  public static RemoveTeammateFromTeamRequest getRemoveTeammateRequest(){
    return  new RemoveTeammateFromTeamRequest("teamId", "teammate2Id");
  }
}
