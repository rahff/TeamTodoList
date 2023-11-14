package org.example.persistance.repositories.team.query.mappers;

import org.example.persistance.entities.team.Team;
import org.example.persistance.entities.team.Teammate;
import org.query.team.dto.TeamDto;

public class TeamMapper {

  public static TeamDto toDto(Team entity) {
    var teammateIds = entity.getTeammates().stream().map(Teammate::getRef).toList();
    return new TeamDto(entity.getId(), entity.getName(), teammateIds);
  }
}
