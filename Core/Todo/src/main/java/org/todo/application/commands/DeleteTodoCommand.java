package org.todo.application.commands;


import org.todo.entities.TodoList;
import org.todo.port.dto.DeleteTodoRequest;
import org.todo.port.spi.TodoListRepository;

public class DeleteTodoCommand {

  private final TodoListRepository todoListRepository;

  public DeleteTodoCommand(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  public void execute(DeleteTodoRequest request) {
    var foundedTodoList = todoListRepository.getTodoListById(request.todoListId()).orElseThrow(() -> new RuntimeException("catch in core"));
    var todoList = TodoList.fromDto(foundedTodoList);
    todoList.deleteTodo(request.todoId());
    todoListRepository.saveTodoList(todoList.snapshot());
  }
}
