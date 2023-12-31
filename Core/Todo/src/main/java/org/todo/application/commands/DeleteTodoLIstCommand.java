package org.todo.application.commands;


import org.todo.entities.TodoList;
import org.todo.port.dto.DeleteTodoListRequest;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.TodoListRepository;

public class DeleteTodoLIstCommand {

  private final TodoListRepository todoListRepository;

  public DeleteTodoLIstCommand(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  public void execute(DeleteTodoListRequest request) {
    var foundedTodoList = todoListRepository.getTodoListById(request.todoListId()).orElseThrow(() -> new RuntimeException("catch in core"));
    emptyingTodoList(foundedTodoList);
    todoListRepository.delete(request.todoListId());
  }

  private void emptyingTodoList(TodoListDto todoListDto) {
    if(isNonEmptyTodoList(todoListDto)){
      var todoList = TodoList.fromDto(todoListDto);
      todoList.emptying();
      todoListRepository.saveTodoList(todoList.snapshot());
    }
  }

  private boolean isNonEmptyTodoList(TodoListDto todoListDto) {
    return !todoListDto.list().isEmpty();
  }
}
