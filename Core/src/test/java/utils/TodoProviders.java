package utils;

import org.core.port.dto.TodoDto;

public class TodoProviders {

  public static TodoDto notDoneTodoDto(){
    return new TodoDto("do something", false, DateProviders.getDateInFuture(), DateProviders.now());
  }

  public static TodoDto doneTodoDto(){
    return new TodoDto("do something", true, DateProviders.getDateInFuture(), DateProviders.now());
  }
}
