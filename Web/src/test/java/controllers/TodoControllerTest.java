package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Main;
import org.example.controllers.api.todo.jsonPayloads.CreateTodoRequestBody;
import org.example.controllers.api.todo.jsonPayloads.DeleteTodoRequestBody;
import org.example.controllers.api.todo.jsonPayloads.DoneTodoRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TodoControllerTest extends BaseControllerTest {

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendCreateTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = objectMapper.writeValueAsString(new CreateTodoRequestBody("sgf4fy5'e8j4io845z4gt", "My todo list", "ownerRef"));
    mockMvc.perform(post("http://localhost:8080/create-todo-list")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendAddTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = "{\n" +
      "    \"todoId\": \"1\",\n" +
      "    \"listId\": \"1\",\n" +
      "    \"description\": \"do something good\",\n" +
      "    \"deadline\": \"2023-12-05\"\n" +
      "}";
    mockMvc.perform(post("http://localhost:8080/add-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendDoneTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = objectMapper.writeValueAsString(new DoneTodoRequestBody("1", "1"));
    mockMvc.perform(put("http://localhost:8080/done-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendDeleteTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = objectMapper.writeValueAsString(new DeleteTodoRequestBody("1", "1"));

    mockMvc.perform(put("http://localhost:8080/delete-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendDeleteTodoListInTodoListHTTPRequestWithValidPayload() throws Exception {
    mockMvc.perform(delete("http://localhost:8080/delete-todo-list/1"))
      .andExpect(status().isOk());
  }
}
