package org.example.security.webhook;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;


@Service
public class WebhookFilter extends OncePerRequestFilter {
    private AuthenticationManager authenticationManager;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public WebhookFilter withManager(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        return this;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            var signature = request.getHeader("Stripe-Signature");
            if(signature == null) {
                filterChain.doFilter(request, response);
                return;
            }
            var body = extractBody(request);
            Authentication authentication = authenticationManager.authenticate(new WebhookAuthenticationToken(signature, body));
            SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            this.securityContextHolderStrategy.setContext(context);
        }catch (AuthenticationException e){
            this.securityContextHolderStrategy.clearContext();
            response.setStatus(401);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String extractBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        char[] buffer = new char[1024];
        int bytesRead;
        while ((bytesRead = reader.read(buffer)) != -1) {
            requestBody.append(buffer, 0, bytesRead);
        }
        return requestBody.toString();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().contains("/webhook/");
    }
}
