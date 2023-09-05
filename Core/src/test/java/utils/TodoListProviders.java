package utils;

import org.core.port.dto.TodoDto;
import org.core.port.dto.TodoListDto;

import java.util.List;

public class TodoListProviders {

  public static TodoListDto emptyTodoListDto(){
    return new TodoListDto("todoListId", "userId", "My todo list", List.of());
  }

  public static TodoListDto todoListDtoAfterAddingTodo(TodoDto addedTodo){
    return new TodoListDto("todoListId", "userId", "My todo list", List.of(addedTodo));
  }

  public static TodoListDto todoListDtoWithSeveralTodo(){
    return new TodoListDto("todoListId", "userId", "My todo list",
      List.of(TodoProviders.notDoneTodoDto(),
        new TodoDto("do other thing", false, DateProviders.getDateInFuture(), DateProviders.now()),
        new TodoDto("do another thing", false, DateProviders.getDateInFuture(), DateProviders.now())
        ));
  }

  public static TodoListDto todoListDtoWithSeveralTodoAndDoneSecondTodo(){
    return new TodoListDto("todoListId", "userId", "My todo list",
      List.of(TodoProviders.notDoneTodoDto(),
        new TodoDto("do other thing", true, DateProviders.getDateInFuture(), DateProviders.now()),
        new TodoDto("do another thing", false, DateProviders.getDateInFuture(), DateProviders.now())
      ));
  }

  public static TodoListDto todoListDtoWithSeveralTodoAfterDeleteSecondTodo(){
    return new TodoListDto("todoListId", "userId", "My todo list",
      List.of(TodoProviders.notDoneTodoDto(),
        new TodoDto("do another thing", false, DateProviders.getDateInFuture(), DateProviders.now())
      ));
  }
}
