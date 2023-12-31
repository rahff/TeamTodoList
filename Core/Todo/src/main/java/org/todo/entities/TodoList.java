package org.todo.entities;

import org.todo.application.mappers.TodoMappers;
import org.todo.port.dto.TodoListDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoList {
  private final String ref;
  private List<Todo> list;
  private String name;
  private String id;
  private final LocalDate createdAt;

  public TodoList(String id, String ref, String name, List<Todo> list, LocalDate createdAt) {
    this.id = id;
    this.ref = ref;
    this.name = name;
    this.list = new ArrayList<>(list);
    this.createdAt = createdAt;
  }

    public static TodoList fromDto(TodoListDto todoList) {
      var listOfTodo = TodoMappers.todoDtoListToListOfTodo(todoList.list());
      return new TodoList(todoList.listId(), todoList.ref(), todoList.todoListName(), listOfTodo, todoList.createdAt());
    }

  public void addTodo(Todo todo) {
    list.add(todo);
  }

  public TodoListDto snapshot(){
    var dtoList = TodoMappers.dtoListToValueObjectList(list);
    return new TodoListDto(id, ref, name, dtoList, createdAt);
  }

    public void doneTodo(String id) {
      list.forEach(todo -> {
        if(todo.getId().equals(id)){
          todo.markAsDone();
        }
      });
    }

  public void deleteTodo(String id) {
    list = list.stream().filter(todo -> !todo.getId().equals(id)).collect(Collectors.toList());
  }

    public void emptying() {
      list.clear();
    }
}
