package org.example.transactions.team;

import jakarta.transaction.Transactional;


import org.team.application.commands.CreateTeam;
import org.team.ports.dto.CreateTeamRequest;


public class CreateTeamTransaction {
  private final CreateTeam command;

  public CreateTeamTransaction(CreateTeam command) {
    this.command = command;
  }

  @Transactional
  public void execute(CreateTeamRequest request) {
    command.execute(request);
  }

}
