package org.example.transactions.team;

import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.team.ports.dto.CreateTeamRequest;

@Service
public class CreateTeamTransaction {
  private final Command<CreateTeamRequest> command;

  public CreateTeamTransaction(Command<CreateTeamRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(CreateTeamRequest request) {
    command.execute(request);
  }

}
