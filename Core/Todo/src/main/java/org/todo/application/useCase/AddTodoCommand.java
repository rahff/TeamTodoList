package org.todo.application.useCase;

import org.todo.entities.Todo;
import org.todo.entities.TodoList;
import org.todo.port.api.Command;
import org.todo.port.dto.AddTodoRequest;
import org.todo.port.spi.TodoListRepository;



public class AddTodoCommand implements Command<AddTodoRequest> {

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
