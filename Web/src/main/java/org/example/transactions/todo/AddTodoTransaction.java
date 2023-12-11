package org.example.transactions.todo;

import jakarta.transaction.Transactional;
import org.todo.application.commands.AddTodoCommand;
import org.todo.port.dto.AddTodoRequest;


public class AddTodoTransaction {

  private final AddTodoCommand command;

  public AddTodoTransaction(AddTodoCommand command) {
    this.command = command;
  }

  @Transactional
  public void execute(AddTodoRequest request) {
    command.execute(request);
  }

}
