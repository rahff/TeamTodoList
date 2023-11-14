package controllers;


import org.example.Main;
import org.example.controllers.api.team.jsonPayloads.response.TeamJson;
import org.example.controllers.api.team.jsonPayloads.response.TeammateJson;
import org.example.controllers.api.todo.jsonPayloads.response.IdJson;
import org.example.email.FakeEmailService;
import org.example.security.JwtAuthenticationToken;
import org.junit.jupiter.api.Test;

import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.team.ports.dto.*;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TeamControllerTest extends BaseControllerTest {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private FakeEmailService emailService;


  @Test
  void  createTeam() throws Exception {
    var body = objectMapper.writeValueAsString(new CreateTeamRequest("teamId1", "Team1", List.of("teammate1", "teammate2"), "accountId"));
    var expected = objectMapper.writeValueAsString(new TeamJson("teamId1", "Team1", 2, 0));
    mockMvc.perform(post("/create-team").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk()).andExpect(content().json(expected));
  }

  @Test
  void  addTeammatesOnTeam() throws Exception {
    var body = objectMapper.writeValueAsString(new AddTeammatesOnTeamRequest("teamId3", List.of("teammate5", "teammate6")));
    var expected = objectMapper.writeValueAsString(List.of(
            new TeammateJson("teammate5", "teammateNameteammate5", "teammateteammate5@gmail.com", "teamId3"),
            new TeammateJson("teammate6", "teammateNameteammate6", "teammateteammate6@gmail.com", "teamId3")));
    mockMvc.perform(put("/add-teammates").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk()).andExpect(content().json(expected));
  }
  @Test
   void  removeTeammatesFromTeam() throws Exception {
    var body = objectMapper.writeValueAsString(new RemoveTeammateFromTeamRequest("teamId", "teammate2Id"));
    var expected = objectMapper.writeValueAsString(new IdJson("teammate2Id"));
    mockMvc.perform(put("/remove-teammate").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk()).andExpect(content().json(expected));
  }

  @Test
  void  removeTeam() throws Exception {
    var expected = objectMapper.writeValueAsString(new IdJson("teamId"));
    mockMvc.perform(delete("/delete-team/teamId"))
      .andExpect(status().isOk()).andExpect(content().json(expected));
  }

  @Test
  void  addTeammateOnOrganization() throws Exception {
    var body = objectMapper.writeValueAsString(new CreateTeammateRequest("teammateId","teammate@gmail.com", "Mikki", "accountId"));
    var expected = objectMapper.writeValueAsString(new TeammateJson("teammateId", "teammateNameteammateId", "teammateteammateId@gmail.com", null));
    mockMvc.perform(post("/add-teammate").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk()).andExpect(content().json(expected));
    var teammate = userRepository.findByEmail("teammate@gmail.com").orElse(null);
    assertNotNull(teammate);
    assertEquals("TEAMMATE", teammate.role());
    assertTrue(emailService.verify("teammate@gmail.com", "Rahff"));
  }
}
