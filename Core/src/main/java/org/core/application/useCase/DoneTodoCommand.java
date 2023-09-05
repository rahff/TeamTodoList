package org.core.application.useCase;

import org.core.entities.TodoList;
import org.core.port.dto.DoneTodoRequest;
import org.core.port.spi.TodoListRepository;

public class DoneTodoCommand {

  private final TodoListRepository todoListRepository;

  public DoneTodoCommand(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  public void execute(DoneTodoRequest request) {
    var foundedTodoList = todoListRepository.getTodoListById(request.todoListId()).orElseThrow();
    var todoList = TodoList.fromDto(foundedTodoList);
    todoList.doneTodo(request.todoId());
    todoListRepository.saveTodoList(todoList.snapshot());
  }
}
