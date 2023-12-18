package org.example.payment;

public class StripeConfig {
    private final String successUrl;
    private final String cancelUrl;
    public StripeConfig(String successUrl, String cancelUrl){
        this.successUrl = successUrl;
        this.cancelUrl = cancelUrl;
    }
    String getSuccessUrl(){
        return successUrl;
    }
    String getCancelUrl(){
        return cancelUrl;
    }
}
