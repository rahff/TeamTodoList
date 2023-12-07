import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.security.application.SetTeammatePassword;
import org.security.ports.dto.ChangePasswordRequest;
import org.shared.dto.UserDto;
import org.security.ports.spi.FakePasswordEncoder;
import org.security.ports.spi.PasswordSecurity;
import org.shared.spi.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SetTeammatePasswordTest {

  private SetTeammatePassword command;
  private UserRepository userRepository;
  private PasswordSecurity passwordSecurity;

  @BeforeEach
  void setup(){
    passwordSecurity = new FakePasswordEncoder();
    userRepository = Mockito.mock(UserRepository.class);
    command = new SetTeammatePassword(userRepository, passwordSecurity);
  }
  @Test
  void NewTeammateChangeHisTemporaryPassword(){
    var request = new ChangePasswordRequest("rahff@gmail.com", "newPassword");
    when(userRepository.findByEmail("rahff@gmail.com")).thenReturn(Optional.of(new UserDto("userId", "rahff@gmail.com", "rahff", "password", "TEAMMATE", "accountId", null)));
    command.registerNewPassword(request);
    verify(userRepository).save(eq(new UserDto("userId", "rahff@gmail.com", "rahff", "NEWPASSWORD", "TEAMMATE", "accountId", null)));
  }
}
