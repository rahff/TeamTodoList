package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.shared.spi.UserRepository;
import org.team.application.commands.CreateTeammate;
import org.team.ports.api.AddTeammate;
import org.team.ports.dto.CreateTeammateRequest;
import org.team.ports.dto.GeneratedCodePair;
import org.team.ports.spi.CodeGenerator;
import utils.FakeData;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateTeammateTest {

  private AddTeammate command;
  private UserRepository userRepository;
  private CodeGenerator codeGenerator;
  @BeforeEach
  void setup(){
    codeGenerator = Mockito.mock(CodeGenerator.class);
    userRepository = Mockito.mock(UserRepository.class);
    command = new CreateTeammate(userRepository, codeGenerator);
  }
  @Test
  void AManagerCreateATeammate(){
    var request = new CreateTeammateRequest("teammateId", "teammate@gmail.com", "Bob", "accountId");
    when(codeGenerator.generateCode()).thenReturn(new GeneratedCodePair("$$$$$$$$$$$", "generatedPassword"));
    when(userRepository.save(any())).thenReturn(FakeData.fakeUserDto());
    var joining = command.execute(request);
    verify(userRepository).save(ArgumentMatchers.eq(FakeData.fakeUserDto()));
    assertEquals("generatedPassword", joining.code());
  }
}