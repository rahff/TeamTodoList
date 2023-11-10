package org.security.ports.dto;


import org.shared.dto.UserDto;

public record JwtAuthenticationResult(UserDto user, String accessToken, String refreshToken) {
}
