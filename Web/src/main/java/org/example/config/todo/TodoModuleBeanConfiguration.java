package org.example.config.todo;


import org.shared.api.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.todo.application.commands.*;
import org.todo.port.dto.*;
import org.todo.port.spi.TodoListRepository;

@Configuration
public class TodoModuleBeanConfiguration {
  @Autowired
  private TodoListRepository todoListrepository;


  @Bean
  public Command<CreateTodoListRequest> createTodoList(){
    return new CreateTodoListCommand(todoListrepository);
  }

  @Bean
  public Command<AddTodoRequest> addTodoCommand(){
    return new AddTodoCommand(todoListrepository);
  }

  @Bean
  public Command<DoneTodoRequest> doneTodoCommand(){
    return new DoneTodoCommand(todoListrepository);
  }

  @Bean
  public Command<DeleteTodoRequest> deleteTodoCommand(){
    return new DeleteTodoCommand(todoListrepository);
  }

  @Bean Command<DeleteTodoListRequest> deleteTodoListCommand() {
    return new DeleteTodoLIstCommand(todoListrepository);
  }

  @Bean
  Command<DeleteUserTodoListsRequest> deleteUserTodoLists(){
    return new DeleteUserTodoLists(todoListrepository);
  }

}
