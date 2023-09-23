package org.example.transactions.security;

import jakarta.transaction.Transactional;
import org.security.ports.api.CreateAccount;
import org.security.ports.api.Signup;
import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.SignupUserRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateManagerAccountTransaction {

  private final CreateAccount createAccount;
  private final Signup signup;

  public CreateManagerAccountTransaction(CreateAccount createAccount, Signup signup) {
    this.createAccount = createAccount;
    this.signup = signup;
  }

  @Transactional(rollbackOn = Throwable.class)
  public JwtAuthenticationResult execute(SignupUserRequest request) throws Exception {
    createAccount.execute(request.accountId());
    return signup.doSignup(request);
  }
}
