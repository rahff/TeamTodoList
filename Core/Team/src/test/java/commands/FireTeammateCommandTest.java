package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shared.dto.UserDto;
import org.shared.spi.InMemoryUserRepository;
import org.team.application.commands.FireTeammateCommand;
import org.team.application.commands.RemoveTeammateFromTeam;
import org.team.ports.dto.FireTeammateRequest;
import org.team.ports.dto.TeamDto;
import org.team.ports.spi.inMemory.InMemoryTeamRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class FireTeammateCommandTest {

    private InMemoryUserRepository teammateRepository;
    private RemoveTeammateFromTeam removeTeammateFromTeamCommand;
    private InMemoryTeamRepository teamRepository;
    private FireTeammateDataFixture dataFixture;
    @BeforeEach
    void setup(){
        dataFixture = new FireTeammateDataFixture();
        teamRepository = new InMemoryTeamRepository(dataFixture.initialTeamRepository());
        teammateRepository = new InMemoryUserRepository();
        removeTeammateFromTeamCommand = new RemoveTeammateFromTeam(teamRepository);
    }
    @Test
    void AManagerFireTeammate() {
        var command = new FireTeammateCommand(teammateRepository, removeTeammateFromTeamCommand);
        var request = new FireTeammateRequest("teammateId", "teamId");
        command.execute(request);
        assertTrue(teammateRepository.items().isEmpty());
        assertTrue(teamRepository.items().contains(dataFixture.afterFireTeammateTeamFromRequest(request)));
    }
}


class FireTeammateDataFixture {
    public List<TeamDto> initialTeamRepository() {
        return List.of(new TeamDto("teamId", "Team1", List.of("teammateId"), "accountId"));
    }

    public TeamDto afterFireTeammateTeamFromRequest(FireTeammateRequest request) {
        return new TeamDto(request.teamId(), "Team1", List.of(), "accountId");
    }

    public UserDto deletedTeammateFromRequest(FireTeammateRequest request) {
        return new UserDto(
                request.userId(),
                "rahff@gmail.com",
                "rahff",
                "12345",
                "TEAMMATE",
                "accountId",
                Optional.empty(),
                Optional.empty());
    }
}