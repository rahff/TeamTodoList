package org.example.transactions.team;


import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.team.ports.dto.DeleteTeamRequest;

@Service
public class DeleteTeamTransaction {

  private final Command<DeleteTeamRequest> command;

  public DeleteTeamTransaction(Command<DeleteTeamRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(DeleteTeamRequest request){
    command.execute(request);
  }
}
