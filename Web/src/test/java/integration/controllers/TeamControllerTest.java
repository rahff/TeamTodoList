package integration.controllers;


import org.example.MainTest;
import org.example.controllers.api.team.jsonPayloads.response.TeamJson;
import org.example.controllers.api.team.jsonPayloads.response.TeammateJson;
import org.example.controllers.api.todo.jsonPayloads.response.IdJson;
import org.example.email.FakeEmailService;
import org.junit.jupiter.api.Test;

import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.ActiveProfiles;
import org.team.ports.dto.*;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MainTest.class)
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
    var expected = objectMapper.writeValueAsString(new TeamJson("teamId1", "Team1", 2, 2));
    mockMvc.perform(post("/create-team").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk()).andExpect(content().json(expected));
  }

  @Test
  void  addTeammatesOnTeam() throws Exception {
    var body = objectMapper.writeValueAsString(new AddTeammatesOnTeamRequest("teamId", List.of("teammate5", "teammate6")));
    var expected = objectMapper.writeValueAsString(List.of(
            new TeammateJson("teammate5", "teammateNameteammate5", "teammateteammate5@gmail.com", "teamId"),
            new TeammateJson("teammate6", "teammateNameteammate6", "teammateteammate6@gmail.com", "teamId")));
    mockMvc.perform(put("/add-teammates-on-team").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk()).andExpect(content().json(expected));
  }
  @Test
   void  removeTeammatesFromTeam() throws Exception {
    var body = objectMapper.writeValueAsString(new RemoveTeammateFromTeamRequest("teamId", "teammate2Id"));
    var expected = objectMapper.writeValueAsString(new IdJson("teammate2Id"));
    mockMvc.perform(put("/remove-teammate-from-team").contentType(MediaType.APPLICATION_JSON).content(body))
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
    var body = objectMapper.writeValueAsString(new CreateTeammateRequest("teammateId2","teammate2@gmail.com", "Mikki", "accountId"));
    var expected = objectMapper.writeValueAsString(new TeammateJson("teammateId2", "teammateNameteammateId2", "teammateteammateId2@gmail.com", null));
    mockMvc.perform(post("/create-teammate").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isOk()).andExpect(content().json(expected));

    assertTrue(emailService.verify("teammate2@gmail.com", "Rahff"));
  }

  @Test
  void  fireTeammate() throws Exception {
    var request = objectMapper.writeValueAsString(new FireTeammateRequest("teammateId", "teamId"));
    var expected = objectMapper.writeValueAsString(new IdJson("teammateId"));
    mockMvc.perform(put("/fire-teammate").contentType(MediaType.APPLICATION_JSON)
                    .content(request)).andExpect(status().isOk())
                    .andExpect(content().json(expected));
  }
}
