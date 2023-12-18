package org.example.security.webhook;

import org.example.security.jwt.JwtConfigurer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class WebhookConfigurer extends AbstractHttpConfigurer<JwtConfigurer, HttpSecurity> {

    private WebhookFilter webhookFilter;
    private AuthenticationProvider authenticationProvider;

    public WebhookConfigurer withAuthenticationProvider(AuthenticationProvider authenticationProvider){
        this.authenticationProvider = authenticationProvider;
        return this;
    }

    public WebhookConfigurer withJwtFilter(WebhookFilter filter){
        this.webhookFilter = filter;
        return this;
    }

    @Override
    public void init(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        var authManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        httpSecurity.addFilterAfter(webhookFilter.withManager(authManager), BasicAuthenticationFilter.class);
    }
}
