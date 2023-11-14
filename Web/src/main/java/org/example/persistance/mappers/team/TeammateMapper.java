package org.example.persistance.mappers.team;

import org.example.persistance.entities.team.Teammate;
import org.team.ports.dto.TeamDto;

import java.util.List;
import java.util.stream.Collectors;

public class TeammateMapper {

  public static List<Teammate> fromDto(final TeamDto dto){
    return dto.teammates().stream().map(id -> new Teammate(id, dto.accountId(), dto.id())).collect(Collectors.toList());
  }
}
