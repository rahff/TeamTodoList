package org.example.transactions.team;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.team.application.commands.DeleteTeam;
import org.team.ports.dto.DeleteTeamRequest;


public class DeleteTeamTransaction {

  private final DeleteTeam command;

  public DeleteTeamTransaction(DeleteTeam command) {
    this.command = command;
  }

  @Transactional
  public void execute(DeleteTeamRequest request){
    command.execute(request);
  }
}
