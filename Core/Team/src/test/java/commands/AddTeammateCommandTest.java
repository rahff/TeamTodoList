package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.shared.dto.UserDto;
import org.shared.spi.InMemoryUserRepository;
import org.team.application.commands.AddTeammatesOnTeam;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.dto.TeamDto;

import org.team.ports.spi.inMemory.InMemoryTeamRepository;
import utils.RequestProvider;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddTeammateCommandTest {

  private AddTeammatesOnTeam command;
  private InMemoryTeamRepository teamRepository;
  private InMemoryUserRepository teammateRepository;

  private  AddTeammateDataFixture dataFixture;
  @BeforeEach
  void setup(){
    dataFixture = new AddTeammateDataFixture();
    teammateRepository = new InMemoryUserRepository(dataFixture.getInitialTeammateRepository());
    teamRepository = new InMemoryTeamRepository(dataFixture.getInitialTeamRepository());
    command = new AddTeammatesOnTeam(teamRepository, teammateRepository);
  }
  @Test
  void AManagerAddATeammateOnATeam() {
    var request = RequestProvider.getAddTeammatesRequest();
    command.execute(request);
    assertTrue(teamRepository.items().contains(dataFixture.getTeamWhichTeammatesAddedIntoIt(request)));
    assertTrue(teammateRepository.items().contains(dataFixture.teammateWhoAddedOnTeam(request)));
  }
}

class AddTeammateDataFixture {

  public List<UserDto> getInitialTeammateRepository() {
    return List.of(
            new UserDto(
                    "teammate1Id",
                    "teammate@gmail.com",
                    "Bob",
                    "$$$$$$$$$$$",
                    "TEAMMATE",
                    "accountId",
                    Optional.empty(),
                    Optional.empty()),
            new UserDto(
                    "teammate2Id",
                    "teammate2@gmail.com",
                    "Alice",
                    "$$$$$$$$$$$",
                    "TEAMMATE",
                    "accountId",
                    Optional.empty(),
                    Optional.empty()));
  }
  public List<TeamDto> getInitialTeamRepository() {
    return List.of(new TeamDto("teamId", "Team1", List.of(), "accountId"));
  }

  public TeamDto getTeamWhichTeammatesAddedIntoIt(AddTeammatesOnTeamRequest request) {
    return new TeamDto("teamId", "Team1", request.teammateToAdd(), "accountId");
  }

  public UserDto teammateWhoAddedOnTeam(AddTeammatesOnTeamRequest request) {
    return new UserDto(
            "teammate1Id",
            "teammate@gmail.com",
            "Bob",
            "$$$$$$$$$$$",
            "TEAMMATE",
            "accountId",
            Optional.of("teamId"),
            Optional.empty());
  }
}
