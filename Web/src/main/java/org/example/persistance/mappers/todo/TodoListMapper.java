package org.example.persistance.mappers.todo;

import org.todo.port.dto.TodoListDto;
import org.example.persistance.entities.todo.TodoList;


public class TodoListMapper {
  public  static TodoList fromDto(TodoListDto dto){
    var todos = TodoMapper.fromDtoList(dto.list());
    return new TodoList(dto.listId(), dto.todoListName(), dto.ref(), todos);
  }
}
