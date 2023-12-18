package org.example.security.webhook;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WebhookAuthenticationToken implements Authentication {

    private final String signature;
    private String body;
    private StripeEvent event;

    public WebhookAuthenticationToken(String signature, String body) {
        this.signature = signature;
        this.body = body;
    }

    public WebhookAuthenticationToken(String signature, StripeEvent event) {
        this.signature = signature;
        this.event = event;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return signature;
    }

    @Override
    public Object getDetails() {
        return body;
    }

    @Override
    public Object getPrincipal() {
        return event;
    }

    @Override
    public boolean isAuthenticated() {
        return event != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
