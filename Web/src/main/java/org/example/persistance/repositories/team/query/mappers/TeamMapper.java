package org.example.persistance.repositories.team.query.mappers;

import org.example.persistance.entities.team.Team;
import org.query.team.dto.TeamDto;

public class TeamMapper {

  public static TeamDto toDto(Team entity) {
    return new TeamDto(entity.getId(), entity.getName(), entity.getTeammates());
  }
}
