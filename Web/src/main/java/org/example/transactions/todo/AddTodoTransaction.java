package org.example.transactions.todo;

import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.todo.port.dto.AddTodoRequest;
import org.springframework.stereotype.Service;

@Service
public class AddTodoTransaction {

  private final Command<AddTodoRequest> command;

  public AddTodoTransaction(Command<AddTodoRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(AddTodoRequest request) {
    command.execute(request);
  }

}
