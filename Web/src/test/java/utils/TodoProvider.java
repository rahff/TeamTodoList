package utils;

import org.example.persistance.entities.Todo;

public class TodoProvider {

  public static Todo getDefaultTodo(){
    return new Todo(StringProvider.unique(), "do something", false, DateProvider.dateInFuture(), DateProvider.now());
  }

  public static Todo getDoneTodo(){
    return new Todo(StringProvider.unique(), "do something", true, DateProvider.dateInFuture(), DateProvider.now());
  }
}
