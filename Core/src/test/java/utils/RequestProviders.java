package utils;

import org.core.port.dto.AddTodoRequest;
import org.core.port.dto.CreateTodoListRequest;
import org.core.port.dto.TodoDto;

public class RequestProviders {

  public static CreateTodoListRequest createTodoListRequest(){
    return new CreateTodoListRequest(UserCtxProvider.freeUser(), "todoListId","My todo list");
  }

  public static CreateTodoListRequest createTodoListRequestWithPremiumUser(){
    return new CreateTodoListRequest(UserCtxProvider.premiumUser(), "todoListId","My todo list");
  }

  public static AddTodoRequest addTodoRequest(){
    return new AddTodoRequest("todoListId",
      new TodoDto("do something", false,
        DateProviders.getDateInFuture(), DateProviders.now()));
  }
}
