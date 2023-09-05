package org.core.application.useCase;

import org.core.entities.TodoList;
import org.core.port.dto.DeleteTodoRequest;
import org.core.port.spi.TodoListRepository;

public class DeleteTodoCommand {

  private final TodoListRepository todoListRepository;

  public DeleteTodoCommand(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  public void execute(DeleteTodoRequest request) {
    var foundedTodoList = todoListRepository.getTodoListById(request.todoListId()).orElseThrow();
    var todoList = TodoList.fromDto(foundedTodoList);
    todoList.deleteTodo(request.todoId());
    todoListRepository.saveTodoList(todoList.snapshot());
  }
}
