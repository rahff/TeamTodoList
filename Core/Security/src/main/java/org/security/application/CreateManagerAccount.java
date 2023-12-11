package org.security.application;

import org.security.ports.dto.CheckoutSession;
import org.security.ports.spi.AccountRepository;
import org.security.ports.spi.PaymentGateway;

public class CreateManagerAccount {
  private final AccountRepository accountRepository;

  private final PaymentGateway paymentGateway;

  public CreateManagerAccount(AccountRepository accountRepository, PaymentGateway paymentGateway) {
    this.accountRepository = accountRepository;
    this.paymentGateway = paymentGateway;
  }

  public CheckoutSession execute(String accountId, String priceId) throws Exception {
    accountRepository.save(accountId);
    return paymentGateway.createCheckoutSession(priceId);
  }
}
