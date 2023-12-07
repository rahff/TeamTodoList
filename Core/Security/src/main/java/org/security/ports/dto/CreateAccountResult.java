package org.security.ports.dto;

public record CreateAccountResult(JwtAuthenticationResult authenticationResult, String checkoutSessionUrl) {
}
