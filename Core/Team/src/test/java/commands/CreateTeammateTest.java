package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.shared.dto.UserDto;

import org.shared.spi.InMemoryUserRepository;
import org.team.application.commands.CreateTeammate;
import org.team.ports.dto.CreateTeammateRequest;

import org.team.ports.spi.CodeGenerator;

import org.team.ports.spi.inMemory.FakeCodeGenerator;
import org.team.ports.spi.inMemory.InMemoryTeammateRepository;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CreateTeammateTest {

  private CreateTeammate command;
  private InMemoryUserRepository userRepository;
  private InMemoryTeammateRepository teammateRepository;
  private CreateTeammateDataFixture dataFixture;

  @BeforeEach
  void setup(){
    dataFixture = new CreateTeammateDataFixture();
    CodeGenerator codeGenerator = new FakeCodeGenerator();
    teammateRepository = new InMemoryTeammateRepository(dataFixture.getInitialUserRepository());
    userRepository = new InMemoryUserRepository(dataFixture.getInitialUserRepository());
    command = new CreateTeammate(userRepository, codeGenerator, teammateRepository);
  }
  @Test
  void AManagerCreateATeammate(){
    var request = new CreateTeammateRequest("teammateId", "teammate@gmail.com", "Bob", "accountId");
    var joining = command.execute(request);
    assertTrue(userRepository.items().contains(dataFixture.theNewTeammateFromRequest(request)));
    assertTrue(teammateRepository.items().contains(dataFixture.theNewTeammateInTeammateContext(request)));
    assertEquals("generatedPassword", joining.code());
  }
}

class CreateTeammateDataFixture {
  public List<UserDto> getInitialUserRepository() {
    return List.of(
            new UserDto(
                    "teammateId",
                    "teammate@gmail.com",
                    "Bob",
                    "$$$$$$$$$$$",
                    "TEAMMATE",
                    "accountId",
                    Optional.empty()));
  }
  public UserDto theNewTeammateFromRequest(CreateTeammateRequest request) {
    return new UserDto(
            request.teammateId(),
            request.email(),
            request.name(),
            "generatedPassword",
            "TEAMMATE",
            request.accountId(),
            Optional.empty());
  }

  public InMemoryTeammateRepository.TeammateDto theNewTeammateInTeammateContext(CreateTeammateRequest request) {
    return new InMemoryTeammateRepository.TeammateDto(request.teammateId(), request.accountId(), Optional.empty());
  }

}