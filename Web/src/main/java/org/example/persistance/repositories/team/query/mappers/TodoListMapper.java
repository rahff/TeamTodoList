package org.example.persistance.repositories.team.query.mappers;

import org.example.persistance.entities.todo.TodoList;
import org.query.team.dto.TodoListDto;

public class TodoListMapper {

  public static TodoListDto toQueryTeamDto(TodoList entity) {
    return new TodoListDto(entity.getId(), entity.getName(), entity.getCreatedAt().toString());
  }
}
