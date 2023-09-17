package utils;

import org.todo.port.dto.TodoDto;

public class TodoProviders {

  public static TodoDto notDoneTodoDto(){
    return new TodoDto("todoID","do something", false, DateProviders.getDateInFuture(), DateProviders.now());
  }

  public static TodoDto doneTodoDto(){
    return new TodoDto("todoID","do something", true, DateProviders.getDateInFuture(), DateProviders.now());
  }
}
