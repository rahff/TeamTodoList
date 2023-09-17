package org.example.controllers.api.endpoints;

import org.todo.port.dto.CreateTodoListRequest;
import org.todo.port.dto.UserContext;
import org.todo.port.dto.UserPlan;
import org.example.controllers.api.httpRequestPayloads.CreateTodoRequestBody;
import org.example.transactions.CreateTodoListTransaction;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

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
      var userPlan = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
      var request = new CreateTodoListRequest(new UserContext(user.getUsername(), UserPlan.valueOf(userPlan) ), body.id(), body.todoListName());
      transaction.execute(request);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @GetMapping("/test")
  public String tester(){
    return "ok";
  }
}
