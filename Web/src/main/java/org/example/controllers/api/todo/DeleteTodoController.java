package org.example.controllers.api.todo;

import org.example.controllers.api.todo.jsonPayloads.response.IdJson;
import org.todo.port.dto.DeleteTodoRequest;
import org.example.controllers.api.todo.jsonPayloads.request.DeleteTodoRequestBody;
import org.example.transactions.todo.DeleteTodoTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class DeleteTodoController {

  private final DeleteTodoTransaction transaction;

  public DeleteTodoController(DeleteTodoTransaction transaction) {
    this.transaction = transaction;
  }

  @PutMapping("/delete-todo")
  public IdJson deleteTodo(@RequestBody DeleteTodoRequestBody body) {
    try{
      var request = new DeleteTodoRequest(body.todoListId(), body.todoId());
      transaction.execute(request);
      return new IdJson(body.todoId());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
