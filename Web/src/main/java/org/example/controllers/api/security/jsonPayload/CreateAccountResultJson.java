package org.example.controllers.api.security.jsonPayload;

public record CreateAccountResultJson(JwtAuthenticationJson authenticationJson, String checkoutSessionUrl) {
}
