package utils;

import org.todo.port.dto.TodoDto;
import org.todo.port.dto.TodoListDto;

import java.util.List;

public class TodoListProviders {

  public static TodoListDto emptyTodoListDto(){
    return new TodoListDto("todoListId", "userId", "My todo list", List.of(), DateProviders.now());
  }

  public static TodoListDto todoListDtoAfterAddingTodo(TodoDto addedTodo){
    return new TodoListDto("todoListId", "userId", "My todo list", List.of(addedTodo), DateProviders.now());
  }

  public static TodoListDto todoListDtoWithSeveralTodo(){
    return new TodoListDto("todoListId", "userId", "My todo list",
      List.of(TodoProviders.notDoneTodoDto(),
        new TodoDto("todoID2","do other thing", false, DateProviders.getDateInFuture(), DateProviders.now()),
        new TodoDto("todoID3","do another thing", false, DateProviders.getDateInFuture(), DateProviders.now())
        ), DateProviders.now());
  }

  public static TodoListDto todoListDtoWithSeveralTodoAndDoneSecondTodo(){
    return new TodoListDto("todoListId", "userId", "My todo list",
      List.of(TodoProviders.notDoneTodoDto(),
        new TodoDto("todoID2","do other thing", true, DateProviders.getDateInFuture(), DateProviders.now()),
        new TodoDto("todoID3","do another thing", false, DateProviders.getDateInFuture(), DateProviders.now())
      ), DateProviders.now());
  }

  public static TodoListDto todoListDtoWithSeveralTodoAfterDeleteSecondTodo(){
    return new TodoListDto("todoListId", "userId", "My todo list",
      List.of(TodoProviders.notDoneTodoDto(),
        new TodoDto("todoID3","do another thing", false, DateProviders.getDateInFuture(), DateProviders.now())
      ), DateProviders.now());
  }
}
