package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.shared.api.Command;
import org.team.application.commands.RemoveTeammateFromTeam;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.spi.TeamRepository;
import utils.RequestProvider;
import utils.TeamProvider;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RemoveTeammateFromTeamTest {

  private TeamRepository teamRepository;
  Command<RemoveTeammateFromTeamRequest> command;

  @BeforeEach
  void setup(){
    teamRepository = Mockito.mock(TeamRepository.class);
    command = new RemoveTeammateFromTeam(teamRepository);
  }

  @Test
  void AManagerRemoveATeammateFromATeam() {
    var request = RequestProvider.getRemoveTeammateRequest();
    when(teamRepository.getTeamById("teamId")).thenReturn(Optional.of(TeamProvider.teamDtoWithTwoTeammates()));
    command.execute(request);
    verify(teamRepository).saveTeam(eq(TeamProvider.teamDtoWithOneTeammates()));
  }
}
