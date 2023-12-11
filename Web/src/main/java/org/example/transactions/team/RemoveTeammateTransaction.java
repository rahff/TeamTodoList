package org.example.transactions.team;


import jakarta.transaction.Transactional;
import org.team.application.commands.RemoveTeammateFromTeam;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;


public class RemoveTeammateTransaction {
  private final RemoveTeammateFromTeam command;

  public RemoveTeammateTransaction(RemoveTeammateFromTeam command) {
    this.command = command;
  }

  @Transactional
  public void execute(RemoveTeammateFromTeamRequest request){
    command.execute(request);
  }
}
