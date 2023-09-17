package org.example.transactions;


import jakarta.transaction.Transactional;
import org.todo.port.api.Command;
import org.todo.port.dto.DeleteTodoListRequest;
import org.springframework.stereotype.Service;

@Service
public class DeleteTodoListTransaction {

  private final Command<DeleteTodoListRequest> command;

  public DeleteTodoListTransaction(Command<DeleteTodoListRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(DeleteTodoListRequest request) {
    command.execute(request);
  }
}
