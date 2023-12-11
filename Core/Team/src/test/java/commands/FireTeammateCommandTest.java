package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.team.application.commands.FireTeammateCommand;
import org.team.application.commands.RemoveTeammateFromTeam;
import org.team.ports.dto.FireTeammateRequest;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.dto.TeamDto;
import org.team.ports.spi.TeammateRepository;
import org.team.ports.spi.inMemory.InMemoryTeamRepository;
import org.team.ports.spi.inMemory.InMemoryTeammateRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class FireTeammateCommandTest {

    private InMemoryTeammateRepository teammateRepository;
    private RemoveTeammateFromTeam removeTeammateFromTeamCommand;
    private InMemoryTeamRepository teamRepository;
    private FireTeammateDataFixture dataFixture;
    @BeforeEach
    void setup(){
        dataFixture = new FireTeammateDataFixture();
        teamRepository = new InMemoryTeamRepository(dataFixture.initialTeamRepository());
        teammateRepository = new InMemoryTeammateRepository();
        removeTeammateFromTeamCommand = new RemoveTeammateFromTeam(teamRepository);
    }
    @Test
    void AManagerFireTeammate() {
        var command = new FireTeammateCommand(teammateRepository, removeTeammateFromTeamCommand);
        var request = new FireTeammateRequest("userId", "teamId");
        command.execute(request);
        assertFalse(teammateRepository.items().contains(dataFixture.deletedTeammateFromRequest(request)));
        assertTrue(teamRepository.items().contains(dataFixture.afterFireTeammateTeamFromRequest(request)));
    }
}


class FireTeammateDataFixture {
    public List<TeamDto> initialTeamRepository() {
        return List.of(new TeamDto("teamId", "Team1", List.of("userId"), "accountId"));
    }

    public TeamDto afterFireTeammateTeamFromRequest(FireTeammateRequest request) {
        return new TeamDto(request.teamId(), "Team1", List.of(), "accountId");
    }

    public InMemoryTeammateRepository.TeammateDto deletedTeammateFromRequest(FireTeammateRequest request) {
        return new InMemoryTeammateRepository.TeammateDto(request.userId(), "accountId", Optional.of(request.teamId()));
    }
}