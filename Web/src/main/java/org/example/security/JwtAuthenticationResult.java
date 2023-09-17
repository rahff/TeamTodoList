package org.example.security;

import org.example.persistance.entities.AppUser;


public record JwtAuthenticationResult(AppUser user, String accessToken) {
}
