package controllers;

import org.example.Main;
import org.example.controllers.api.security.jsonPayload.ChangePasswordPayload;
import org.example.controllers.api.security.jsonPayload.JwtAuthenticationJson;
import org.example.controllers.api.security.jsonPayload.UserJson;
import org.example.email.FakeEmailService;
import org.junit.jupiter.api.Test;
import org.security.ports.dto.*;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecurityControllerTest extends BaseControllerTest{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void createManagerAccount() throws Exception {
    var body = objectMapper.writeValueAsString(new SignupUserRequest("rahff@gmail.com", "Rahff", "12345", "userId", "accountId"));
    var expected = objectMapper.writeValueAsString(new JwtAuthenticationJson(new UserJson("userId", "Rahff", "rahff@gmail.com", "MANAGER", "accountId"), "rahff@gmail.com$MANAGER", "rahff@gmail.com$"));
    mockMvc.perform(post("/create-account").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk()).andExpect(content().json(expected));
    var manager = userRepository.findByEmail("rahff@gmail.com").orElse(null);
    assertNotNull(manager);
    assertEquals("MANAGER", manager.role());
  }

  @Test
  void  login() throws Exception {
    var body = objectMapper.writeValueAsString(new LoginUserRequest("teammate2@gmail.com", "12345"));
    var expected = objectMapper.writeValueAsString(new JwtAuthenticationJson(new UserJson("userId2", "Jacques", "teammate2@gmail.com", "TEAMMATE", "accountId"), "teammate2@gmail.com$TEAMMATE", "teammate2@gmail.com$"));
    mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(content().json(expected));
  }

  @Test
  void setTeammatePassword() throws Exception {
    initSecurityContext(fakeTeammateAuthentication);
    var userBefore = userRepository.findByEmail("teammate@gmail.com").orElse(null);
    assertNotNull(userBefore);
    var body = objectMapper.writeValueAsString(new ChangePasswordPayload("newPassword"));
    mockMvc.perform(post("/set-password").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
    var teammate = userRepository.findByEmail("teammate@gmail.com").orElse(null);
    assertNotNull(teammate);
    assertNotEquals(userBefore.password(), teammate.password());
  }
}
