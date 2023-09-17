package org.example.controllers.api.endpoints;

import org.todo.port.dto.AddTodoRequest;
import org.todo.port.dto.TodoDto;
import org.example.controllers.api.httpRequestPayloads.AddTodoRequestBody;
import org.example.transactions.AddTodoTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

@RestController
public class AddTodoController {
  private final AddTodoTransaction transaction;

  public AddTodoController(AddTodoTransaction transaction) {
    this.transaction = transaction;
  }

  @PostMapping("/add-todo")
  public void addTodo(@RequestBody AddTodoRequestBody body) {
    try{
      var todo = new TodoDto(body.todoId(), body.description(), false, body.deadline(), LocalDate.now());
      var request = new AddTodoRequest(body.listId(), todo);
      transaction.execute(request);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
