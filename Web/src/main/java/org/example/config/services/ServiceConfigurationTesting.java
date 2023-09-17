package org.example.config.services;

import org.todo.application.useCase.*;
import org.todo.port.api.Command;
import org.todo.port.dto.*;
import org.todo.port.spi.TodoListRepository;
import org.example.persistance.repositories.TodoListInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ServiceConfigurationTesting {

  @Bean
  TodoListRepository todoListRepository(){
    return new TodoListInMemoryRepository();
  }

  @Bean
  public Command<CreateTodoListRequest> createTodoList(){
    return new CreateTodoListCommand(todoListRepository());
  }

  @Bean
  public Command<AddTodoRequest> addTodoCommand(){
    return new AddTodoCommand(todoListRepository());
  }

  @Bean
  public Command<DoneTodoRequest> doneTodoCommand(){
    return new DoneTodoCommand(todoListRepository());
  }

  @Bean
  public Command<DeleteTodoRequest> deleteTodoCommand(){
    return new DeleteTodoCommand(todoListRepository());
  }

  @Bean Command<DeleteTodoListRequest> deleteTodoListCommand() {
    return new DeleteTodoLIstCommand(todoListRepository());
  }


}
