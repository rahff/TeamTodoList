package org.example.controllers.api.todo;

import org.example.controllers.api.todo.jsonPayloads.request.DoneTodoRequestBody;
import org.example.controllers.api.todo.jsonPayloads.response.IdJson;
import org.todo.port.dto.DoneTodoRequest;
import org.example.transactions.todo.DoneTodoTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class DoneTodoController {

  private final DoneTodoTransaction transaction;

  public DoneTodoController(DoneTodoTransaction transaction) {
    this.transaction = transaction;
  }

  @PutMapping("/done-todo")
  public IdJson doneTodo(@RequestBody DoneTodoRequestBody body) {
    try{
      var request = new DoneTodoRequest(body.todoListId(), body.todoId());
      transaction.execute(request);
      return new IdJson(body.todoId());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
