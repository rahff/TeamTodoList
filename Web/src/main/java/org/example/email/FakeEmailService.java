package org.example.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("test")
public class FakeEmailService implements EmailService {
  private final List<JoiningMessageParameters> emailData;

  public FakeEmailService() {
    emailData = new ArrayList<>();
  }


  public void sendJoiningEmail(JoiningMessageParameters parameters) {
    emailData.add(parameters);
  }

  public boolean verify(String emailTo, String manager){
    var existing = emailData.stream()
      .filter(parameters ->parameters.email().equals(emailTo)
        && parameters.managerName().equals(manager)).findFirst();
    return existing.isPresent();
  }
}
