package org.example.security;

import io.jsonwebtoken.Claims;

public interface JwtDecoder {
  Claims decode(String token);
}
