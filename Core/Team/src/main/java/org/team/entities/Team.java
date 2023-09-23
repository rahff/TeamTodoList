package org.team.entities;

import org.team.ports.dto.TeamDto;

import java.util.ArrayList;
import java.util.List;

public class Team {
  private final String id;
  private final String name;
  private final List<String> teammates;
  private final String accountId;
  public Team(String id, String name, List<String>teammates, String accountId){
    this.name = name;
    this.id = id;
    this.teammates = new ArrayList<>(teammates);
    this.accountId = accountId;
  }


  public TeamDto snapshot() {
    return new TeamDto(id, name, teammates, accountId);
  }

  public void addTeammates(List<String> teammatesToAdd) {
    teammates.addAll(teammatesToAdd);
  }

  public void removeTeammate(String teammateToRemove) {
    teammates.removeIf(teammate -> teammate.equals(teammateToRemove));
  }
}
