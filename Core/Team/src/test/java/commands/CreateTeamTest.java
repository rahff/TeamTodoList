package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.team.application.commands.CreateTeam;
import org.team.ports.dto.CreateTeamRequest;
import org.team.ports.dto.TeamDto;
import org.team.ports.spi.TeamRepository;
import org.team.ports.spi.inMemory.InMemoryTeamRepository;
import utils.RequestProvider;
import utils.TeamProvider;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CreateTeamTest {

  private InMemoryTeamRepository teamRepository;
  private CreateTeam command;
  private CreateTeamDataFixture dataFixture;

  @BeforeEach
  void setup(){
    dataFixture = new CreateTeamDataFixture();
    teamRepository = new InMemoryTeamRepository();
    command = new CreateTeam(teamRepository);
  }


  @Test
  void AManagerCreateATeam() {
    var request = RequestProvider.getACreateTeamRequest();
    command.execute(request);
    assertTrue(teamRepository.items().contains(dataFixture.getCreatedTeamFromRequest(request)));
  }

}

class CreateTeamDataFixture {

  public TeamDto getCreatedTeamFromRequest(CreateTeamRequest request) {
    return new TeamDto(request.id(), request.name(), request.teammates(), request.accountId());
  }
}