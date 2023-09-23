package org.security.ports.dto;



public record JwtAuthenticationResult(UserDto user, String accessToken) {
}
