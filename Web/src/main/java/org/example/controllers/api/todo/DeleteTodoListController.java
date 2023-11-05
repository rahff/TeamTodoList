package org.example.controllers.api.todo;


import org.example.controllers.api.todo.jsonPayloads.response.IdJson;
import org.shared.api.Command;
import org.todo.port.dto.DeleteTodoListRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class DeleteTodoListController {

  private final Command<DeleteTodoListRequest> command;

  public DeleteTodoListController(Command<DeleteTodoListRequest> command) {
    this.command = command;
  }

  @DeleteMapping("/delete-todo-list/{todoListId}")
  public IdJson deleteTodoList(@PathVariable("todoListId") String todoListId){
    try{
      var request = new DeleteTodoListRequest(todoListId);
      command.execute(request);
      return new IdJson(todoListId);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
