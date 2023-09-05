package org.core.application.useCase;

import org.core.entities.Todo;
import org.core.entities.TodoList;
import org.core.port.dto.AddTodoRequest;
import org.core.port.spi.TodoListRepository;



public class AddTodoCommand {

  private final TodoListRepository todoListRepository;

  public AddTodoCommand(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  public void execute(AddTodoRequest request){
    var todoList = todoListRepository.getTodoListById(request.todoListId()).orElseThrow();
    var todoListEntity = TodoList.fromDto(todoList);
    todoListEntity.addTodo(Todo.fromDto(request.todoDto()));
    todoListRepository.saveTodoList(todoListEntity.snapshot());
  }
}
