package org.example.security.testing;

import io.jsonwebtoken.*;
import org.example.security.services.TokenService;
import org.security.ports.dto.JwtTokenPair;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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
