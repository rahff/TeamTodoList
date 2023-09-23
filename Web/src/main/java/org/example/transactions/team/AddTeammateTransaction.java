package org.example.transactions.team;

import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.team.ports.dto.AddTeammatesOnTeamRequest;

@Service
public class AddTeammateTransaction {

  private final Command<AddTeammatesOnTeamRequest> command;

  public AddTeammateTransaction(Command<AddTeammatesOnTeamRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(AddTeammatesOnTeamRequest request){
    command.execute(request);
  }
}
