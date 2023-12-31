package org.todo.application.commands;

import org.todo.application.exceptions.TodoListAlreadyExistException;
import org.todo.application.exceptions.TodoListCreationThresholdLimitReachedException;
import org.todo.port.dto.CreateTodoListRequest;
import org.todo.port.dto.TodoListDto;
import org.todo.port.dto.UserContext;
import org.todo.port.dto.UseRole;
import org.todo.port.spi.TodoListRepository;

import java.util.List;

public class CreateTodoListCommand {
  private final int TEAMMATE_LIMIT = 5;
  private final TodoListRepository repository;

  public CreateTodoListCommand(TodoListRepository repository) {
    this.repository = repository;
  }

  public void execute(CreateTodoListRequest request) {
    verifyUniquenessOfTodoList(request);
    verifyTodoListCreationLimit(request.userCtx());
    repository.saveTodoList(new TodoListDto(request.todoListId(), request.ref(), request.todoListName(), List.of(), request.createdAt()));
  }

  private void verifyTodoListCreationLimit(UserContext userContext){
    var todoListCount = repository.getTodoListCount(userContext.userId());
    if(isUserLimited(userContext)){
      if(todoListCount >= TEAMMATE_LIMIT) throw new TodoListCreationThresholdLimitReachedException();
    }
  }

  private boolean isUserLimited(UserContext userContext){
    return userContext.userRole() == UseRole.TEAMMATE;
  }

  private void verifyUniquenessOfTodoList(CreateTodoListRequest request) {
    var existingTodoList = repository.getTodoListByName(request.userCtx().userId(), request.todoListName());
    if(existingTodoList.isPresent()) throw new TodoListAlreadyExistException();
  }
}
