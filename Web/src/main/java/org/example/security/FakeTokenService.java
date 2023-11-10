package org.example.security;

import io.jsonwebtoken.*;
import org.security.ports.dto.JwtTokenPair;
import org.security.ports.spi.JwtEncoder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("test")
public class FakeTokenService implements TokenService {

    public JwtTokenPair encode(String userEmail, String role) {
        var accessToken = userEmail+"$"+role;
        var refreshToken = userEmail+"$";
        return new JwtTokenPair(accessToken, refreshToken);
    }


    public Claims decode(String token) {
      return null;
    }
}
