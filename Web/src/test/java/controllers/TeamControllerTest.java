package controllers;


import org.example.Main;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.ActiveProfiles;
import org.team.ports.dto.*;

import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TeamControllerTest extends BaseControllerTest {

  @Test
  void  createTeam() throws Exception {
    var body = objectMapper.writeValueAsString(new CreateTeamRequest("teamId2", "Team2", List.of("teammate1", "teammate2", "teammate3"), "accountId"));
    mockMvc.perform(post("/create-team").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
  }

  @Test
  void  addTeammatesOnTeam() throws Exception {
    var body = objectMapper.writeValueAsString(new AddTeammatesOnTeamRequest("teamId3", List.of("teammate5", "teammate6")));
    mockMvc.perform(put("/add-teammates").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
  }
  @Test
   void  removeTeammatesFromTeam() throws Exception {
    var body = objectMapper.writeValueAsString(new RemoveTeammateFromTeamRequest("teamId", "teammate2Id"));
    mockMvc.perform(put("/remove-teammate").contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
  }

  @Test
  void  removeTeam() throws Exception {
    mockMvc.perform(delete("/delete-team/teamId"))
      .andExpect(status().isOk());
  }
}
