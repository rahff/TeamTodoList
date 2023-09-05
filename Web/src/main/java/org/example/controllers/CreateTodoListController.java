package org.example.controllers;

import org.core.port.api.CreateTodoList;
import org.core.port.dto.CreateTodoListRequest;
import org.core.port.dto.UserContext;
import org.core.port.dto.UserPlan;
import org.example.controllers.httpRequest.CreateTodoRequestBody;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class CreateTodoListController {
  private CreateTodoList command;

  public void createTodoList(@RequestBody CreateTodoRequestBody body) {
    try{
      var request = new CreateTodoListRequest(new UserContext("userId", UserPlan.FREE), body.id(), body.todoListName());
      command.execute(request);
    }catch (Exception exception){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
  }
}
