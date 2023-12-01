package org.todo.application.commands;

import org.shared.api.Command;
import org.todo.entities.TodoList;
import org.todo.port.dto.DoneTodoRequest;
import org.todo.port.spi.TodoListRepository;

public class DoneTodoCommand implements Command<DoneTodoRequest> {

  private final TodoListRepository todoListRepository;

  public DoneTodoCommand(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  public void execute(DoneTodoRequest request) {
    var foundedTodoList = todoListRepository.getTodoListById(request.todoListId()).orElseThrow(() -> new RuntimeException("catch in core"));
    var todoList = TodoList.fromDto(foundedTodoList);
    todoList.doneTodo(request.todoId());
    todoListRepository.saveTodoList(todoList.snapshot());
  }
}
