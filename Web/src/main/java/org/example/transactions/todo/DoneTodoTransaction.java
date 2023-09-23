package org.example.transactions.todo;

import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.todo.port.dto.DoneTodoRequest;
import org.springframework.stereotype.Service;

@Service
public class DoneTodoTransaction {

  private final Command<DoneTodoRequest> command;

  public DoneTodoTransaction(Command<DoneTodoRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(DoneTodoRequest request) {
    command.execute(request);
  }
}
