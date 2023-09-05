package org.core.application.useCase;

import org.core.application.exception.TodoListAlreadyExistException;
import org.core.application.exception.TodoListCreationThresholdLimitReachedException;
import org.core.port.api.CreateTodoList;
import org.core.port.dto.CreateTodoListRequest;
import org.core.port.dto.TodoListDto;
import org.core.port.dto.UserContext;
import org.core.port.dto.UserPlan;
import org.core.port.spi.TodoListRepository;

import java.util.List;

public class CreateTodoListCommand implements CreateTodoList {
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
