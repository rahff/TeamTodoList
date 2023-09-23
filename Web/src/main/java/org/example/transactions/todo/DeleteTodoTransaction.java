package org.example.transactions.todo;

import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.todo.port.dto.DeleteTodoRequest;
import org.springframework.stereotype.Service;

@Service
public class DeleteTodoTransaction {
  private final Command<DeleteTodoRequest> command;

  public DeleteTodoTransaction(Command<DeleteTodoRequest> command) {
    this.command = command;
  }


  @Transactional
  public void execute(DeleteTodoRequest request) {
    command.execute(request);
  }
}
