package org.core.entities;

import org.core.application.mappers.TodoMappers;
import org.core.port.dto.TodoListDto;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
  private final String userId;
  private final List<Todo> list;
  private String name;
  private String id;

  public TodoList(String id, String userId, String name, List<Todo> list) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.list = new ArrayList<>(list);
  }

    public static TodoList fromDto(TodoListDto todoList) {
      var listOfTodo = TodoMappers.todoDtoListToListOfTodo(todoList.list());
      return new TodoList(todoList.listId(), todoList.userId(), todoList.todoListName(), listOfTodo);
    }

  public void addTodo(Todo todo) {
    list.add(todo);
  }

  public TodoListDto snapshot(){
    var dtoList = TodoMappers.dtoListToValueObjectList(list);
    return new TodoListDto(id, userId, name, dtoList);
  }

    public void doneTodo(int i) {
      var doneTodo = list.get(i);
      doneTodo.setDone();
      list.set(i, doneTodo);
    }

  public void deleteTodo(int i) {
    list.remove(i);
  }
}
