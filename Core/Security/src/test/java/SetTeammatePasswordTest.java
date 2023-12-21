import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.security.application.SetTeammatePassword;
import org.security.ports.dto.ChangePasswordRequest;
import org.shared.dto.UserDto;
import org.security.ports.spi.inMemory.FakePasswordEncoder;
import org.security.ports.spi.PasswordSecurity;
import org.shared.spi.InMemoryUserRepository;
import org.shared.spi.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SetTeammatePasswordTest {

  private SetTeammatePassword command;
  private InMemoryUserRepository userRepository;
    private SetTeammatePasswordDataFixture dataFixture;

  @BeforeEach
  void setup(){
    dataFixture = new SetTeammatePasswordDataFixture();
    PasswordSecurity passwordSecurity = new FakePasswordEncoder();
    userRepository = new InMemoryUserRepository(dataFixture.initialUserRepository());
    command = new SetTeammatePassword(userRepository, passwordSecurity);
  }
  @Test
  void NewTeammateChangeHisTemporaryPassword(){
    var request = new ChangePasswordRequest("rahff@gmail.com", "newPassword");
    command.registerNewPassword(request);
    assertTrue(userRepository.items().contains(dataFixture.userAfterChangePassword()));
  }
}


class SetTeammatePasswordDataFixture {

  public List<UserDto> initialUserRepository() {
     return List.of(
             new UserDto(
                     "teammateId",
                     "rahff@gmail.com",
                     "rahff",
                     "12345",
                     "TEAMMATE",
                     "accountId",
                     Optional.of("teamId"),
                     Optional.empty())
     );
  }

  public UserDto userAfterChangePassword(){
    return new UserDto(
            "teammateId",
            "rahff@gmail.com",
            "rahff",
            "NEWPASSWORD",
            "TEAMMATE",
            "accountId",
            Optional.of("teamId"),
            Optional.empty());
  }
}