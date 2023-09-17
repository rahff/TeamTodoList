package org.example.transactions;

import jakarta.transaction.Transactional;
import org.todo.port.api.Command;
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
