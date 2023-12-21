package adapters.command;


import org.example.Main;
import org.example.persistance.repositories.security.springData.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.shared.dto.SubscriptionDto;
import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import utils.StringProvider;

import java.util.Optional;

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
  void saverUserTeammate(){
    var dto = new UserDto(StringProvider.unique(), StringProvider.unique(), "userName1", passwordEncoder.encode("12345"), "TEAMMATE", StringProvider.unique(), Optional.empty(),Optional.empty());
    var savedUser = userRepository.save(dto);
    var expectedUser = springUserRepository.findByEmail(dto.email()).orElse(null);
    assertNotNull(expectedUser);
    assertEquals(savedUser.id(), expectedUser.getId());
    assertEquals(savedUser.email(), expectedUser.getEmail());
  }

  @Test
  void saverUserManager(){
    var dto = new UserDto(StringProvider.unique(), StringProvider.unique(), "manager", passwordEncoder.encode("12345"), "MANAGER", StringProvider.unique(), Optional.empty(), Optional.of(new SubscriptionDto(StringProvider.unique(), false)));
    var savedUser = userRepository.save(dto);
    var expectedUser = springUserRepository.findByEmail(dto.email()).orElse(null);
    assertNotNull(expectedUser);
    assertEquals(savedUser.id(), expectedUser.getId());
    assertEquals(savedUser.email(), expectedUser.getEmail());
    assertNotNull(savedUser.subscription());
  }

  @Test
  void findUserBySubscriptionId(){
    var subscriptionId = StringProvider.unique();
    var dto = new UserDto(StringProvider.unique(), StringProvider.unique(), "manager", passwordEncoder.encode("12345"), "MANAGER", StringProvider.unique(),Optional.empty(), Optional.of(new SubscriptionDto(subscriptionId, false)));
    var savedUser = userRepository.save(dto);
    var foundBySubscriptionId = userRepository.findBySubscription(subscriptionId).orElse(null);
    assertNotNull(foundBySubscriptionId);
    assertEquals(savedUser.id(), foundBySubscriptionId.id());
  }
}
