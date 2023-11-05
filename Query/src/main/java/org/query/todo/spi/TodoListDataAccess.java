package org.query.todo.spi;

import org.query.todo.dto.TodoListDto;

import java.util.List;
import java.util.Optional;

public interface TodoListDataAccess {
    Optional<TodoListDto> getTodoListById(String id);
    List<TodoListDto> getUserTodoList(String userId);
}
