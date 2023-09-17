package org.todo.application.useCase;

import org.todo.application.exceptions.TodoListAlreadyExistException;
import org.todo.application.exceptions.TodoListCreationThresholdLimitReachedException;
import org.todo.port.api.Command;
import org.todo.port.dto.CreateTodoListRequest;
import org.todo.port.dto.TodoListDto;
import org.todo.port.dto.UserContext;
import org.todo.port.dto.UserPlan;
import org.todo.port.spi.TodoListRepository;

import java.util.List;

public class CreateTodoListCommand implements Command<CreateTodoListRequest> {
  private final int FREE_USER_LIMIT = 5;
  private final TodoListRepository repository;

  public CreateTodoListCommand(TodoListRepository repository) {
    this.repository = repository;
  }

  public void execute(CreateTodoListRequest request) {
    verifyUniquenessOfTodoList(request);
    verifyTodoListCreationLimit(request.userCtx());
    repository.saveTodoList(new TodoListDto(request.id(), request.userCtx().userId(), request.todoListName(), List.of()));
  }

  private void verifyTodoListCreationLimit(UserContext userContext){
    var todoListCount = repository.getTodoListCount(userContext.userId());
    if(isUserLimited(userContext)){
      if(todoListCount >= FREE_USER_LIMIT) throw new TodoListCreationThresholdLimitReachedException();
    }
  }

  private boolean isUserLimited(UserContext userContext){
    return userContext.userPlan() == UserPlan.FREE;
  }

  private void verifyUniquenessOfTodoList(CreateTodoListRequest request) {
    var existingTodoList = repository.getTodoListByName(request.userCtx().userId(), request.todoListName());
    if(existingTodoList.isPresent()) throw new TodoListAlreadyExistException();
  }
}
