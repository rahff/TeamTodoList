package org.example.transactions.todo;

import jakarta.transaction.Transactional;
import org.todo.application.commands.CreateTodoListCommand;
import org.todo.port.dto.CreateTodoListRequest;


public class CreateTodoListTransaction {

  private final CreateTodoListCommand command;

  public CreateTodoListTransaction(CreateTodoListCommand command) {
    this.command = command;
  }

  @Transactional
  public void execute(CreateTodoListRequest request) {
    command.execute(request);
  }
}
