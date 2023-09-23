package org.security.application;

import org.security.ports.api.CreateAccount;
import org.security.ports.api.Signup;
import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.SignupUserRequest;
import org.security.ports.spi.AccountRepository;

public class CreateManagerAccount implements CreateAccount {

  private final AccountRepository accountRepository;

  public CreateManagerAccount(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public void execute(String accountId) {
    accountRepository.save(accountId);
  }

}
