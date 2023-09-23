package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.team.application.commands.CreateTeam;
import org.team.ports.dto.CreateTeamRequest;
import org.team.ports.dto.TeamDto;
import org.team.ports.spi.TeamRepository;
import utils.RequestProvider;
import utils.TeamProvider;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CreateTeamTest {

  private TeamRepository teamRepository;
  private CreateTeam command;

  @BeforeEach
  void setup(){
    teamRepository = Mockito.mock(TeamRepository.class);
    command = new CreateTeam(teamRepository);
  }


  @Test
  void AManagerCreateATeam() {
    var request = RequestProvider.getACreateTeamRequest();
    command.execute(request);
    verify(teamRepository).saveTeam(eq(TeamProvider.teamDtoWithOneTeammates()));
  }

}
