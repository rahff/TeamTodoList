package org.core.application.mappers;

import org.core.entities.Todo;
import org.core.port.dto.TodoDto;

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
    return new TodoDto(todo.description(), todo.done(), todo.deadLine(), todo.createdAt());
  }
}
