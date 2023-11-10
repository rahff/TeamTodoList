package adapters;


import org.example.Main;
import org.example.persistance.repositories.security.command.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import utils.StringProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Main.class)
@Profile("prod")
public class JpaUserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  AppUserRepository springUserRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  void saverUser(){
    var dto = new UserDto(StringProvider.unique(), StringProvider.unique(), "userName1", passwordEncoder.encode("12345"), "TEAMMATE", StringProvider.unique());
    var savedUser = userRepository.save(dto);
    var expectedUser = springUserRepository.findByEmail(dto.email()).orElse(null);
    assertNotNull(expectedUser);
    assertEquals(savedUser.id(), expectedUser.getId());
    assertEquals(savedUser.email(), expectedUser.getEmail());
  }
}
