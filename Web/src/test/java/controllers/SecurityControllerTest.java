package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.Main;
import org.example.controllers.api.security.jsonPayload.ChangePasswordPayload;
import org.example.email.FakeEmailService;
import org.junit.jupiter.api.Test;
import org.security.ports.dto.CreateTeammateRequest;
import org.security.ports.dto.LoginUserRequest;
import org.security.ports.dto.SignupUserRequest;
import org.security.ports.spi.UserRepository;
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
  private FakeEmailService emailService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void  createManagerAccount() throws Exception {
    var body = objectMapper.writeValueAsString(new SignupUserRequest("rahff@gmail.com", "Rahff", "12345", "userId", "accountId"));
    mockMvc.perform(post("/create-account").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
    var manager = userRepository.findByEmail("rahff@gmail.com").orElse(null);
    assertNotNull(manager);
    assertEquals("MANAGER", manager.role());
  }

  @Test
  @WithMockUser(username = "manager@gmail.com", authorities = "MANAGER")
  void  addTeammateOnOrganization() throws Exception {
    var body = objectMapper.writeValueAsString(new CreateTeammateRequest("teammateId","mikki@gmail.com", "Mikki", "accountId"));
    mockMvc.perform(post("/add-teammate").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
    var teammate = userRepository.findByEmail("mikki@gmail.com").orElse(null);
    assertNotNull(teammate);
    assertEquals("TEAMMATE", teammate.role());
    assertTrue(emailService.verify("mikki@gmail.com", "Rahff"));
  }

  @Test
  void  login() throws Exception {
    var body = objectMapper.writeValueAsString(new LoginUserRequest("teammate@gmail.com", "12345"));
    mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.accessToken").exists());
  }

  @Test
  @WithMockUser(username = "teammate@gmail.com", authorities = "TEAMMATE")
  void setTeammatePassword() throws Exception {
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
