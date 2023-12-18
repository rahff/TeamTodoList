package org.example.controllers.api.security;


import org.example.payment.WebhookConfig;
import org.example.security.webhook.StripeEvent;
import org.example.transactions.security.UpdateSubscriptionTransaction;
import org.security.ports.dto.PaymentSuccessEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.payment.WebhookConfig.*;


@RestController
public class PaymentWebHookController {

    private final UpdateSubscriptionTransaction transaction;

    public PaymentWebHookController(UpdateSubscriptionTransaction transaction) {
        this.transaction = transaction;
    }

    @PostMapping("/webhook/payment")
    public ResponseEntity<Void> paymentWebhook(@AuthenticationPrincipal StripeEvent stripeEvent){
        try {
            if(stripeEvent.type().equals(CHECKOUT_SESSION_COMPLETED_EVENT)){
                String subscriptionId = stripeEvent.json().getString(KEY_ID);
                transaction.execute(new PaymentSuccessEvent(subscriptionId));
            }
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
