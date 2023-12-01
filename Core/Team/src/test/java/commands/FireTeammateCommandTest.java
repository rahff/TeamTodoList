package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.team.application.commands.FireTeammateCommand;
import org.team.application.commands.RemoveTeammateFromTeam;
import org.team.ports.dto.FireTeammateRequest;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.spi.TeammateRepository;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class FireTeammateCommandTest {

    private TeammateRepository teammateRepository;
    private RemoveTeammateFromTeam removeTeammateFromTeamCommand;
    @BeforeEach
    void setup(){
        teammateRepository = Mockito.mock(TeammateRepository.class);
        removeTeammateFromTeamCommand = Mockito.mock(RemoveTeammateFromTeam.class);
    }
    @Test
    void AManagerFireTeammate() {
        var command = new FireTeammateCommand(teammateRepository, removeTeammateFromTeamCommand);
        var request = new FireTeammateRequest("userId", "teamId");
        command.execute(request);
        verify(removeTeammateFromTeamCommand).execute(eq(new RemoveTeammateFromTeamRequest("teamId", "userId")));
        verify(teammateRepository).removeTeammateUser("userId");
    }
}
