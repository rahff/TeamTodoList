package org.example.controllers.api.todo;


import org.example.controllers.api.todo.jsonPayloads.CreateTodoRequestBody;
import org.example.transactions.todo.CreateTodoListTransaction;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.todo.port.dto.CreateTodoListRequest;
import org.todo.port.dto.UserContext;
import org.todo.port.dto.UseRole;

import java.util.stream.Collectors;

@RestController
public class CreateTodoListController {
  private final CreateTodoListTransaction transaction;

  public CreateTodoListController(CreateTodoListTransaction transaction) {
    this.transaction = transaction;
  }

  @PostMapping("/create-todo-list")
  public void createTodoList(@RequestBody CreateTodoRequestBody body, @AuthenticationPrincipal UserDetails user) {
    try{
      var request = createRequest(user, body);
      transaction.execute(request);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  private CreateTodoListRequest createRequest(UserDetails user, CreateTodoRequestBody body){
    var userRole = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
    return new CreateTodoListRequest(new UserContext(user.getUsername(), UseRole.valueOf(userRole) ), body.id(), body.todoListName());
  }

  @GetMapping("/test")
  public String tester(){
    return "ok";
  }
}
