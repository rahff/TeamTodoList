package org.example.transactions.team;

import jakarta.transaction.Transactional;
import org.team.application.commands.AddTeammatesOnTeam;
import org.team.ports.dto.AddTeammatesOnTeamRequest;


public class AddTeammateOnTeamTransaction {

  private final AddTeammatesOnTeam command;

  public AddTeammateOnTeamTransaction(AddTeammatesOnTeam command) {
    this.command = command;
  }

  @Transactional
  public void execute(AddTeammatesOnTeamRequest request){
    command.execute(request);
  }
}
