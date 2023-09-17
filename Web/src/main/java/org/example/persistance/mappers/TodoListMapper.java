package org.example.persistance.mappers;

import org.todo.port.dto.TodoListDto;
import org.example.persistance.entities.TodoList;


public class TodoListMapper {
  public  static TodoList fromDto(TodoListDto dto){
    var todos = TodoMapper.fromDtoList(dto.list());
    return new TodoList(dto.listId(), dto.todoListName(), dto.userId(), todos);
  }
}
