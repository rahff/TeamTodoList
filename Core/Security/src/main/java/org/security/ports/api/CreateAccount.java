package org.security.ports.api;

import org.security.ports.dto.CheckoutSession;

public interface CreateAccount {
  CheckoutSession execute(String accountId, String subscriptionPrice) throws Exception;
}
