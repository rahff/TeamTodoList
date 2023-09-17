package org.example.transactions;

import jakarta.transaction.Transactional;
import org.todo.port.api.Command;
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
