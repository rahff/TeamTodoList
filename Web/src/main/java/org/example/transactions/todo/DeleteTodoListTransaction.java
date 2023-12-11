package org.example.transactions.todo;


import jakarta.transaction.Transactional;
import org.todo.application.commands.DeleteTodoLIstCommand;
import org.todo.port.dto.DeleteTodoListRequest;

public class DeleteTodoListTransaction {

  private final DeleteTodoLIstCommand command;

  public DeleteTodoListTransaction(DeleteTodoLIstCommand command) {
    this.command = command;
  }

  @Transactional
  public void execute(DeleteTodoListRequest request) {
    command.execute(request);
  }
}
