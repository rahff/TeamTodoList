package org.core.port.spi;

import org.core.port.dto.TodoListDto;

import java.util.Optional;

public interface TodoListRepository {
  void saveTodoList(TodoListDto todoList);
  Optional<TodoListDto> getTodoListByName(String userId, String todoListName);
  Optional<TodoListDto> getTodoListById(String todoListId);
  int getTodoListCount(String userId);
}
