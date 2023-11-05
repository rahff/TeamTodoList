package controllers;

import org.example.Main;
import org.example.controllers.api.todo.jsonPayloads.request.CreateTodoRequestBody;
import org.example.controllers.api.todo.jsonPayloads.request.DeleteTodoRequestBody;
import org.example.controllers.api.todo.jsonPayloads.request.DoneTodoRequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.security.test.context.support.WithMockUser;
import utils.DateProvider;


import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TodoControllerTest extends BaseControllerTest {

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendCreateTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = "{\n" +
            "    \"id\": \"1\",\n" +
            "    \"todoListName\": \"My todo list\",\n" +
            "    \"ref\": \"todoOwner\",\n" +
            "    \"createdAt\": \"2023-11-05\"\n" +
            "}";
    mockMvc.perform(post("http://localhost:8080/create-todo-list")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
            .andExpect(content().json(
                    "{\"id\":\"1\"," +
                            "\"name\":\"My todo list\"," +
                            "\"createdAt\":\"2023-11-05\"}"));
  }

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendAddTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = "{\n" +
      "    \"todoId\": \"1\",\n" +
      "    \"listId\": \"1\",\n" +
      "    \"description\": \"do something good\",\n" +
      "    \"deadline\": \"2023-12-05\",\n" +
      "    \"createdAt\": \"2023-12-03\"\n" +
      "}";
    mockMvc.perform(post("http://localhost:8080/add-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
            .andExpect(content().json(
                    "{\"todoId\":\"1\"," +
                            "\"listId\":\"1\"," +
                            "\"description\":\"do something good\"," +
                            "\"createdAt\": \"2023-12-03\",\n" +
                            "\"deadline\":\"2023-12-05\"}"));
  }

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendDoneTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = objectMapper.writeValueAsString(new DoneTodoRequestBody("1", "1"));
    mockMvc.perform(put("http://localhost:8080/done-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
            .andExpect(content().json(
                    "{\"id\":\"1\"}"));
  }

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendDeleteTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = objectMapper.writeValueAsString(new DeleteTodoRequestBody("1", "1"));

    mockMvc.perform(put("http://localhost:8080/delete-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
            .andExpect(content().json(
                    "{\"id\":\"1\"}"));
  }

  @Test
  @WithMockUser(username = "user", password = "12345", authorities = {"MANAGER"})
  void sendDeleteTodoListInTodoListHTTPRequestWithValidPayload() throws Exception {
    mockMvc.perform(delete("http://localhost:8080/delete-todo-list/1"))
      .andExpect(status().isOk())
            .andExpect(content().json(
            "{\"id\":\"1\"}"));;;
  }
}
