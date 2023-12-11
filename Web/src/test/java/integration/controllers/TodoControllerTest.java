package integration.controllers;

import org.example.MainTest;
import org.example.controllers.api.todo.jsonPayloads.request.DeleteTodoRequestBody;
import org.example.controllers.api.todo.jsonPayloads.request.DoneTodoRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MainTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TodoControllerTest extends BaseControllerTest {


  @Test
  void sendCreateTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = "{\n" +
            "    \"id\": \"12\",\n" +
            "    \"todoListName\": \"My todo list\",\n" +
            "    \"ref\": \"todoOwner\",\n" +
            "    \"createdAt\": \"2023-11-05\"\n" +
            "}";
    mockMvc.perform(post("http://localhost:8080/create-todo-list")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
            .andExpect(content().json(
                    "{\"id\":\"12\"," +
                            "\"name\":\"My todo list\"," +
                            "\"createdAt\":\"2023-11-05\"}"));
  }

  @Test
  void sendAddTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = "{\n" +
      "    \"todoId\": \"1\",\n" +
      "    \"listId\": \"todoListId\",\n" +
      "    \"description\": \"do something good\",\n" +
      "    \"deadline\": \"2023-12-05\",\n" +
      "    \"createdAt\": \"2023-12-03\"\n" +
      "}";
    mockMvc.perform(post("http://localhost:8080/add-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
            .andExpect(content().json(
                    "{\"todoId\":\"1\"," +
                            "\"listId\":\"todoListId\"," +
                            "\"description\":\"do something good\"," +
                            "\"createdAt\": \"2023-12-03\",\n" +
                            "\"deadline\":\"2023-12-05\"}"));
  }

  @Test
  void sendDoneTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = objectMapper.writeValueAsString(new DoneTodoRequestBody("todoListId", "1"));
    mockMvc.perform(put("http://localhost:8080/done-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
            .andExpect(content().json(
                    "{\"id\":\"1\"}"));
  }

  @Test
  void sendDeleteTodoInTodoListHTTPRequestWithValidPayload() throws Exception {
    var body = objectMapper.writeValueAsString(new DeleteTodoRequestBody("todoListId", "1"));
    mockMvc.perform(put("http://localhost:8080/delete-todo")
        .contentType(MediaType.APPLICATION_JSON).content(body))
      .andExpect(status().isOk())
            .andExpect(content().json(
                    "{\"id\":\"1\"}"));
  }

  @Test
  void sendDeleteTodoListInTodoListHTTPRequestWithValidPayload() throws Exception {
    createTodoList();
    mockMvc.perform(delete("http://localhost:8080/delete-todo-list/99"))
      .andExpect(status().isOk())
            .andExpect(content().json(
            "{\"id\":\"99\"}"));;;
  }

  private void createTodoList() throws Exception {
    var body = "{\n" +
            "    \"id\": \"99\",\n" +
            "    \"todoListName\": \"do something wrong\",\n" +
            "    \"ref\": \"todoOwner\",\n" +
            "    \"createdAt\": \"2023-11-05\"\n" +
            "}";
    mockMvc.perform(post("http://localhost:8080/create-todo-list")
            .contentType(MediaType.APPLICATION_JSON).content(body));
  }
}
