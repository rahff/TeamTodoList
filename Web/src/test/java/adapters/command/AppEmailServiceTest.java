package adapters.command;


import org.example.Main;
import org.example.email.EmailService;
import org.example.email.JoiningMessageParameters;
import org.junit.jupiter.api.Test;
import org.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = Main.class)
@Profile("prod")
public class AppEmailServiceTest {

  @Autowired
  EmailService emailService;

  @Test
  void sendJoiningEmail() {
    var teammate = new UserDto("userId", "rahff@gmail.com", "Rahff", "12345", "TEAMMATE", "123458745", null);
    var joiningRequest = new JoiningMessageParameters(teammate.email(), "Paul", teammate.name(), "145258");
    assertDoesNotThrow(() -> emailService.sendJoiningEmail(joiningRequest));
  }
}
