package org.example.security.jwt;

import io.jsonwebtoken.Claims;

public interface JwtDecoder {
  Claims decode(String token);
}
