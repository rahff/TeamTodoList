package org.example.config.todo;


import org.example.transactions.todo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.todo.application.commands.*;

import org.todo.port.spi.TodoListRepository;


@Configuration
@Profile("prod")
public class TodoModuleBeanConfiguration {
  @Autowired
  private TodoListRepository todoListrepository;


  @Bean
  public CreateTodoListTransaction createTodoList(){
    return new CreateTodoListTransaction(new CreateTodoListCommand(todoListrepository));
  }

  @Bean
  public AddTodoTransaction addTodoCommand(){
    return new AddTodoTransaction(new AddTodoCommand(todoListrepository));
  }

  @Bean
  public DoneTodoTransaction doneTodoCommand(){
    return new DoneTodoTransaction(new DoneTodoCommand(todoListrepository));
  }

  @Bean
  public DeleteTodoTransaction deleteTodoCommand(){
    return new DeleteTodoTransaction(new DeleteTodoCommand(todoListrepository));
  }

  @Bean
  DeleteTodoListTransaction deleteTodoListCommand() {
    return new DeleteTodoListTransaction(new DeleteTodoLIstCommand(todoListrepository));
  }

  @Bean
  DeleteUserTodoLists deleteUserTodoLists(){
    return new DeleteUserTodoLists(todoListrepository);
  }

}
