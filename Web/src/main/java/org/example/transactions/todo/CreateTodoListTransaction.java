package org.example.transactions.todo;

import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.todo.port.dto.CreateTodoListRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateTodoListTransaction {

  private final Command<CreateTodoListRequest> command;

  public CreateTodoListTransaction(Command<CreateTodoListRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(CreateTodoListRequest request) {
    command.execute(request);
  }
}
