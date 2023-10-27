package org.example.transactions.security;

import jakarta.transaction.Transactional;
import org.example.email.EmailService;
import org.example.email.JoiningMessageParameters;
import org.security.ports.dto.CreateTeammateRequest;
import org.security.ports.spi.AddTeammate;
import org.springframework.stereotype.Service;

@Service
public class CreateTeammateTransaction {
  private final AddTeammate addTeammate;
  private final String ROLE = "TEAMMATE";
  private final EmailService emailService;

  public CreateTeammateTransaction(AddTeammate addTeammate, EmailService emailService) {
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