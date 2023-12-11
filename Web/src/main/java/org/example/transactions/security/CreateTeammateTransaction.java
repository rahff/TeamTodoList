package org.example.transactions.security;

import jakarta.transaction.Transactional;
import org.example.email.EmailService;
import org.example.email.JoiningMessageParameters;
import org.team.application.commands.CreateTeammate;
import org.team.ports.dto.CreateTeammateRequest;
import org.springframework.stereotype.Service;


public class CreateTeammateTransaction {
  private final CreateTeammate addTeammate;
  private final EmailService emailService;

  public CreateTeammateTransaction(CreateTeammate addTeammate, EmailService emailService) {
    this.addTeammate = addTeammate;
    this.emailService = emailService;
  }

  @Transactional(rollbackOn = Throwable.class)
  public void execute(CreateTeammateRequest request, String managerName) throws Exception {
    var joiningRequest = addTeammate.execute(request);
    var messageParameters = new JoiningMessageParameters(
      joiningRequest.email(), managerName, joiningRequest.name(),
      joiningRequest.code());
    emailService.sendJoiningEmail(messageParameters);
  }
}
