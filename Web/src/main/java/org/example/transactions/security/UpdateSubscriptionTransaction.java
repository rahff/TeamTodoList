package org.example.transactions.security;

import jakarta.transaction.Transactional;
import org.security.application.UpdateManagerSubscription;
import org.security.ports.dto.PaymentSuccessEvent;

public class UpdateSubscriptionTransaction {

    private final UpdateManagerSubscription command;

    public UpdateSubscriptionTransaction(UpdateManagerSubscription command) {
        this.command = command;
    }

    @Transactional
    public void execute(PaymentSuccessEvent event) {
         command.execute(event);
    }
}
