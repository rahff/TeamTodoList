package org.example.payment;

public class WebhookConfig {

    public static String KEY_ID = "id";
    public static String CHECKOUT_SESSION_COMPLETED_EVENT = "checkout.session.completed";
    private final String webhookSecret;

    public WebhookConfig(String webhookSecret) {
        this.webhookSecret = webhookSecret;
    }

    public String getWebhookSecret() {
        return webhookSecret;
    }
}
