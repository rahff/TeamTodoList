package utils;

import org.todo.port.dto.AddTodoRequest;
import org.todo.port.dto.CreateTodoListRequest;
import org.todo.port.dto.TodoDto;

public class RequestProviders {

  public static CreateTodoListRequest createTodoListRequest(){
    return new CreateTodoListRequest(UserCtxProvider.freeUser(), "todoListId","My todo list", "ownerRef", DateProviders.getDateInFuture());
  }

  public static CreateTodoListRequest createTodoListRequestWithPremiumUser(){
    return new CreateTodoListRequest(UserCtxProvider.premiumUser(), "todoListId","My todo list", "ownerRef", DateProviders.getDateInFuture());
  }

  public static AddTodoRequest addTodoRequest(){
    return new AddTodoRequest("todoListId",
      new TodoDto("todoID", "do something", false,
        DateProviders.getDateInFuture(), DateProviders.now()));
  }
}
