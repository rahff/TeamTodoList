package org.example.controllers.api.security.jsonPayload;


public record JwtAuthenticationJson(UserJson user, String accessToken, String refreshToken) {

}
