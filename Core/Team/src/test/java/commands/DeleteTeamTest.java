package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team.application.commands.DeleteTeam;
import org.team.ports.dto.DeleteTeamRequest;
import org.team.ports.dto.TeamDto;
import org.team.ports.spi.inMemory.InMemoryTeamRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class DeleteTeamTest {

  private DeleteTeam command;
  private InMemoryTeamRepository teamRepository;

  @BeforeEach
  void setup(){
    DeleteTeamDataFixture dataFixture = new DeleteTeamDataFixture();
    teamRepository = new InMemoryTeamRepository(dataFixture.initialTeamRepository());
    command = new DeleteTeam(teamRepository);
  }

  @Test
  void AManagerDeleteATeam(){
    var request = new DeleteTeamRequest("teamId");
    command.execute(request);
    assertTrue(teamRepository.items().isEmpty());
  }

}

class DeleteTeamDataFixture {
  public List<TeamDto> initialTeamRepository(){
    return List.of(new TeamDto("teamId", "Team1", List.of(), "accountId"));
  }
}