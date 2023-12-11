package org.example.transactions.todo;

import jakarta.transaction.Transactional;
import org.todo.application.commands.DoneTodoCommand;
import org.todo.port.dto.DoneTodoRequest;

public class DoneTodoTransaction {
  private final DoneTodoCommand command;
  public DoneTodoTransaction(DoneTodoCommand command) {
    this.command = command;
  }

  @Transactional
  public void execute(DoneTodoRequest request) {
    command.execute(request);
  }
}
