import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.security.ports.dto.CreateTeammateRequest;
import org.security.ports.dto.GeneratedCodePair;
import org.security.ports.dto.UserDto;
import org.security.ports.spi.AddTeammate;
import org.security.ports.spi.CodeGenerator;
import org.security.ports.spi.UserRepository;
import org.shared.api.Command;
import org.security.application.CreateTeammate;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    var joining = command.execute(request);
    verify(userRepository).save(new UserDto("teammateId", "teammate@gmail.com", "Bob", "$$$$$$$$$$$", "TEAMMATE", "accountId"));
    assertEquals("generatedPassword", joining.code());
  }
}
