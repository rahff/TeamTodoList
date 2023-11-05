package org.example.transactions.team;

import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.springframework.stereotype.Service;
import org.team.ports.dto.AddTeammatesOnTeamRequest;

@Service
public class AddTeammateOnTeamTransaction {

  private final Command<AddTeammatesOnTeamRequest> command;

  public AddTeammateOnTeamTransaction(Command<AddTeammatesOnTeamRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(AddTeammatesOnTeamRequest request){
    command.execute(request);
  }
}
