package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team.application.commands.RemoveTeammateFromTeam;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.dto.TeamDto;
import org.team.ports.spi.inMemory.InMemoryTeamRepository;
import utils.RequestProvider;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RemoveTeammateFromTeamTest {

  private InMemoryTeamRepository teamRepository;
  private RemoveTeammateFromTeam command;
  private RemoveTeammateDataFixture dataFixture;

  @BeforeEach
  void setup(){
    dataFixture = new RemoveTeammateDataFixture();
    teamRepository = new InMemoryTeamRepository(dataFixture.initialTeamRepository());
    command = new RemoveTeammateFromTeam(teamRepository);
  }

  @Test
  void AManagerRemoveATeammateFromATeam() {
    var request = RequestProvider.getRemoveTeammateRequest();
    command.execute(request);
    assertTrue(teamRepository.items().contains(dataFixture.teamAfterRemovingTeammate(request)));
  }
}

class RemoveTeammateDataFixture {

  public List<TeamDto> initialTeamRepository() {
    return List.of(new TeamDto("teamId", "Team1", List.of("userId", "teammate2Id"), "accountId"));
  }

  public TeamDto teamAfterRemovingTeammate(RemoveTeammateFromTeamRequest request) {
    return new TeamDto(request.teamId(), "Team1", List.of("userId"), "accountId");
  }
}
