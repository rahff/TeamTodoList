package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.shared.api.Command;
import org.team.application.commands.AddTeammatesOnTeam;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.spi.TeamRepository;
import org.team.ports.spi.TeammateRepository;
import utils.RequestProvider;
import utils.TeamProvider;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AddTeammateCommandTest {

  private Command<AddTeammatesOnTeamRequest> command;
  private TeamRepository teamRepository;
  private TeammateRepository teammateRepository;

  @BeforeEach
  void setup(){
    teammateRepository = mock(TeammateRepository.class);
    teamRepository = Mockito.mock(TeamRepository.class);
    command = new AddTeammatesOnTeam(teamRepository, teammateRepository);
  }
  @Test
  void AManagerAddATeammateOnATeam() {
    var request = RequestProvider.getAddTeammatesRequest();
    when(teamRepository.getTeamById("teamId")).thenReturn(Optional.of(TeamProvider.emptyTeamDto()));
    command.execute(request);
    verify(teamRepository).saveTeam(eq(TeamProvider.teamDtoWithTwoTeammates()));
    verify(teammateRepository).addTeamIdOnTeammate(request.teammateToAdd(), request.teamId());
  }
}
