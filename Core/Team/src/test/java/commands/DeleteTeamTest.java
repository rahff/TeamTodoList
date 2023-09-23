package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.shared.api.Command;
import org.team.application.commands.DeleteTeam;
import org.team.ports.dto.DeleteTeamRequest;
import org.team.ports.spi.TeamRepository;

import static org.mockito.Mockito.verify;

public class DeleteTeamTest {

  private Command<DeleteTeamRequest> command;
  private TeamRepository teamRepository;

  @BeforeEach
  void setup(){
    teamRepository = Mockito.mock(TeamRepository.class);
    command = new DeleteTeam(teamRepository);
  }

  @Test
  void AManagerDeleteATeam(){
    var request = new DeleteTeamRequest("teamId");
    command.execute(request);
    verify(teamRepository).deleteTeam("teamId");
  }

}
