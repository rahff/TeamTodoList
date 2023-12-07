package org.example.transactions.security;

import jakarta.transaction.Transactional;
import org.security.ports.api.CreateAccount;
import org.security.ports.api.Signup;
import org.security.ports.dto.CreateAccountResult;
import org.security.ports.dto.SignupUserRequest;
import org.shared.dto.SubscriptionDto;
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
  public CreateAccountResult execute(SignupUserRequest request) throws Exception {
    var checkoutSession = createAccount.execute(request.accountId(), request.subscriptionPriceId());
    return new CreateAccountResult(signup.doSignup(request, new SubscriptionDto("", false)), checkoutSession.checkoutUrl());
  }
}
