package org.example.security.testing;


import org.example.security.webhook.WebhookAuthenticationToken;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class WebhookAuthenticationForTesting implements AuthenticationProvider{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(WebhookAuthenticationToken.class);
    }
}


