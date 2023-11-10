package org.security.ports.dto;

public record JwtTokenPair(String accessToken, String refreshToken) {
}
