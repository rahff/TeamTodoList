package org.example.transactions.todo;

import jakarta.transaction.Transactional;
import org.todo.application.commands.DeleteTodoCommand;
import org.todo.port.dto.DeleteTodoRequest;


public class DeleteTodoTransaction {
  private final DeleteTodoCommand command;

  public DeleteTodoTransaction(DeleteTodoCommand command) {
    this.command = command;
  }


  @Transactional
  public void execute(DeleteTodoRequest request) {
    command.execute(request);
  }
}
