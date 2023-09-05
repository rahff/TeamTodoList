package org.example.config.services;

import org.core.application.useCase.CreateTodoListCommand;
import org.core.port.api.CreateTodoList;
import org.core.port.spi.TodoListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

  @Bean
  public CreateTodoList createTodoList(TodoListRepository repository){
    return new CreateTodoListCommand(repository);
  }
}
