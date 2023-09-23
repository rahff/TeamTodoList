package utils;

import org.example.persistance.entities.todo.TodoList;

import java.util.List;

public class TodoListProvider {

  public static TodoList getEmptyTodoList(){
    return new TodoList(StringProvider.unique(),
      StringProvider.unique(),
      StringProvider.unique(),
      List.of());
  }

  public static TodoList getTodoListWithOneTodoNotDone(){
    return new TodoList(StringProvider.unique(),
      StringProvider.unique(),
      StringProvider.unique(),
      List.of(TodoProvider.getDefaultTodo()));
  }

  public static TodoList getTodoListWithTwoTodoTheSecondIsDone(){
    return new TodoList(StringProvider.unique(),
      StringProvider.unique(),
      StringProvider.unique(),
      List.of(TodoProvider.getDefaultTodo(),
              TodoProvider.getDoneTodo()));
  }
}
