package org.example.controllers.api.todo;

import org.todo.port.dto.DeleteTodoRequest;
import org.example.controllers.api.todo.jsonPayloads.DeleteTodoRequestBody;
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

  @PutMapping("delete-todo")
  public void deleteTodo(@RequestBody DeleteTodoRequestBody body) {
    try{
      var request = new DeleteTodoRequest(body.todoListId(), body.todoId());
      transaction.execute(request);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
