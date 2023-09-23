package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.shared.api.Command;
import org.team.application.commands.AddTeammatesOnTeam;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.spi.TeamRepository;
import utils.RequestProvider;
import utils.TeamProvider;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddTeammateCommandTest {

  private Command<AddTeammatesOnTeamRequest> command;
  private TeamRepository teamRepository;

  @BeforeEach
  void setup(){
    teamRepository = Mockito.mock(TeamRepository.class);
    command = new AddTeammatesOnTeam(teamRepository);
  }
  @Test
  void AManagerAddATeammateOnATeam() {
    var request = RequestProvider.getAddTeammatesRequest();
    when(teamRepository.getTeamById("teamId")).thenReturn(Optional.of(TeamProvider.emptyTeamDto()));
    command.execute(request);
    verify(teamRepository).saveTeam(eq(TeamProvider.teamDtoWithTwoTeammates()));
  }
}
