package org.example.persistance.mappers;

import org.todo.port.dto.TodoDto;
import org.example.persistance.entities.Todo;

import java.util.List;
import java.util.stream.Collectors;

public class TodoMapper {

  public static List<Todo> fromDtoList(List<TodoDto> dtos){
    return dtos.stream().map(TodoMapper::fromDto).collect(Collectors.toList());
  }

  private static Todo fromDto(TodoDto dto){
    return new Todo(dto.id(), dto.description(), dto.done(), dto.deadLine(), dto.createdAt());
  }
}
