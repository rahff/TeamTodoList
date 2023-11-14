package org.example.controllers.api.todo;


import org.example.controllers.api.todo.jsonPayloads.request.CreateTodoRequestBody;
import org.example.controllers.api.todo.jsonPayloads.response.TodoListCardJson;
import org.example.transactions.todo.CreateTodoListTransaction;
import org.shared.dto.UserDto;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.todo.port.dto.CreateTodoListRequest;
import org.todo.port.dto.UserContext;
import org.todo.port.dto.UseRole;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RestController
public class CreateTodoListController {
  private final CreateTodoListTransaction transaction;

  public CreateTodoListController(CreateTodoListTransaction transaction) {
    this.transaction = transaction;
  }

  @PostMapping("/create-todo-list")
  public TodoListCardJson createTodoList(@RequestBody CreateTodoRequestBody body, @AuthenticationPrincipal UserDto user) {
    try{
      var request = createRequest(user, body);
      transaction.execute(request);
      return new TodoListCardJson(body.id(), body.todoListName(), body.createdAt().format(DateTimeFormatter.ISO_DATE));
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  private CreateTodoListRequest createRequest(UserDto user, CreateTodoRequestBody body){
    return new CreateTodoListRequest(new UserContext(user.email(), UseRole.valueOf(user.role()) ), body.id(), body.todoListName(), body.ref(), body.createdAt());
  }
}
