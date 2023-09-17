package org.todo.application.mappers;

import org.todo.entities.Todo;
import org.todo.port.dto.TodoDto;

import java.util.List;
import java.util.stream.Collectors;

public class TodoMappers {
  public static List<Todo> todoDtoListToListOfTodo(List<TodoDto> dtos){
    return dtos.stream().map(Todo::fromDto).collect(Collectors.toList());
  }

  public static List<TodoDto> dtoListToValueObjectList(List<Todo> todos){
    return todos.stream().map(TodoMappers::fromValueObject).collect(Collectors.toList());
  }

  private static TodoDto fromValueObject(Todo todo){
    return new TodoDto(todo.getId(), todo.description(), todo.done(), todo.deadLine(), todo.createdAt());
  }
}
