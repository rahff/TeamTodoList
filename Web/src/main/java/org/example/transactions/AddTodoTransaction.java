package org.example.transactions;

import jakarta.transaction.Transactional;
import org.todo.port.api.Command;
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
