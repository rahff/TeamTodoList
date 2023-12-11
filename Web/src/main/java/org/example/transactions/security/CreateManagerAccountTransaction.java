package org.example.transactions.security;

import jakarta.transaction.Transactional;
import org.security.application.CreateManagerAccount;
import org.security.application.CreateUserManager;
import org.security.ports.dto.CreateAccountResult;
import org.security.ports.dto.SignupUserRequest;
import org.shared.dto.SubscriptionDto;

public class CreateManagerAccountTransaction {

  private final CreateManagerAccount createAccount;
  private final CreateUserManager signup;

  public CreateManagerAccountTransaction(CreateManagerAccount createAccount, CreateUserManager signup) {
    this.createAccount = createAccount;
    this.signup = signup;
  }

  @Transactional(rollbackOn = Throwable.class)
  public CreateAccountResult execute(SignupUserRequest request) throws Exception {
    var checkoutSession = createAccount.execute(request.accountId(), request.subscriptionPriceId());
    return new CreateAccountResult(signup.doSignup(request, new SubscriptionDto(checkoutSession.id(), false)), checkoutSession.checkoutUrl());
  }
}
