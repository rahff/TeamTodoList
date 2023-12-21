package org.example.persistance.mappers.team;

import org.example.persistance.entities.team.Team;
import org.team.ports.dto.TeamDto;

public class TeamMapper {

  public static Team fromDto(TeamDto dto){
    var team = new Team();
    team.setId(dto.id());
    team.setName(dto.name());
    team.setTeammates(dto.teammates());
    team.setAccountId(dto.accountId());
    return team;
  }
}
