package org.example.persistance.repositories.team;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.team.ports.dto.TeamDto;
import org.team.ports.spi.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class TeamInMemoryRepository implements TeamRepository {

  private final List<TeamDto> data = new ArrayList<>();

  public TeamInMemoryRepository() {
    data.addAll(List.of(TeamProvider.teamDtoWithTwoTeammates(), TeamProvider.teamDtoWithOneTeammates()));
  }

  public void saveTeam(TeamDto teamDto) {
    var existingTeam = getTeamById(teamDto.id()).orElse(null);
    if(existingTeam != null){
      deleteTeam(existingTeam.id());
      data.add(teamDto);
    }else{
      data.add(teamDto);
    }
  }

  public Optional<TeamDto> getTeamById(String teamId) {
    return data.stream().filter(dto -> dto.id().equals(teamId)).findFirst();
  }

  @Override
  public void deleteTeam(String teamId) {
    data.removeIf(team -> team.id().equals(teamId));
  }
}


 class TeamProvider {

  public static TeamDto teamDtoWithTwoTeammates(){
    return new TeamDto("teamId", "Team1", List.of("teammate1Id", "teammate2Id"), "accountId");
  }

   public static TeamDto teamDtoWithOneTeammates(){
     return new TeamDto("teamId3", "Team3", List.of("teammate3Id"), "accountId");
   }
}
