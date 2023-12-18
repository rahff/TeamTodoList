package integration.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.stripe.model.Event;
import com.stripe.model.EventDataDeserializer;
import com.stripe.model.EventDataObjectDeserializer;
import org.example.MainTest;
import org.example.controllers.api.security.jsonPayload.ChangePasswordPayload;
import org.example.controllers.api.security.jsonPayload.CreateAccountResultJson;
import org.example.controllers.api.security.jsonPayload.JwtAuthenticationJson;
import org.example.controllers.api.security.jsonPayload.UserJson;
import org.example.controllers.api.team.jsonPayloads.response.TeammateJson;
import org.junit.jupiter.api.Test;
import org.security.ports.dto.*;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.team.ports.dto.CreateTeammateRequest;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest(classes = MainTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecurityControllerTest extends BaseControllerTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void createManagerAccount() throws Exception {
    var body = objectMapper.writeValueAsString(new SignupUserRequest("mikki@gmail.com", "Mikki", "12345", "userId", "accountId", "priceId"));
    var expected = objectMapper.writeValueAsString(new CreateAccountResultJson(new JwtAuthenticationJson(new UserJson("userId", "Mikki", "mikki@gmail.com", "MANAGER", "accountId"), "mikki@gmail.com$MANAGER", "mikki@gmail.com$"), "http://fakeUrl/priceId"));
    mockMvc.perform(post("/create-account").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk()).andExpect(content().json(expected));
    var manager = userRepository.findByEmail("mikki@gmail.com").orElse(null);
    assertNotNull(manager);
    assertEquals("MANAGER", manager.role());
    assertNotNull(manager.subscription().orElse(null));
    assertFalse(manager.subscription().get().paid());
  }

  @Test
  void  login() throws Exception {
    var body = objectMapper.writeValueAsString(new LoginUserRequest("rahff@gmail.com", "12345"));
    var expected = objectMapper.writeValueAsString(new JwtAuthenticationJson(new UserJson("teammateId", "rahff", "rahff@gmail.com", "TEAMMATE", "accountId"), "rahff@gmail.com$TEAMMATE", "rahff@gmail.com$"));
    mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(content().json(expected));
  }

  @Test
  void setTeammatePassword() throws Exception {
    createTeammate();
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

  private void  createTeammate() throws Exception {
    var body = objectMapper.writeValueAsString(new CreateTeammateRequest("teammateId2","teammate@gmail.com", "Mikki", "accountId"));
    mockMvc.perform(post("/create-teammate").contentType(MediaType.APPLICATION_JSON).content(body));
  }
}



