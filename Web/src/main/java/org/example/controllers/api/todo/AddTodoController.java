package org.example.controllers.api.todo;

import org.example.controllers.api.todo.jsonPayloads.request.AddTodoRequestBody;
import org.example.controllers.api.todo.jsonPayloads.response.TodoJson;
import org.example.transactions.todo.AddTodoTransaction;
import org.todo.port.dto.AddTodoRequest;
import org.todo.port.dto.TodoDto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController
public class AddTodoController {
  private final AddTodoTransaction transaction;

  public AddTodoController(AddTodoTransaction transaction) {
    this.transaction = transaction;
  }

  @PostMapping("/add-todo")
  public TodoJson addTodo(@RequestBody AddTodoRequestBody body) {
    try{
      var todo = new TodoDto(body.todoId(), body.description(), false, body.deadline(), body.createdAt());
      var request = new AddTodoRequest(body.listId(), todo);
      transaction.execute(request);
      return TodoJson.from(todo, body.listId());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
