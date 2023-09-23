package org.example.transactions.team;


import jakarta.transaction.Transactional;
import org.shared.api.Command;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;

@Service
public class RemoveTeammateTransaction {
  private final Command<RemoveTeammateFromTeamRequest> command;

  public RemoveTeammateTransaction(Command<RemoveTeammateFromTeamRequest> command) {
    this.command = command;
  }

  @Transactional
  public void execute(RemoveTeammateFromTeamRequest request){
    command.execute(request);
  }
}
