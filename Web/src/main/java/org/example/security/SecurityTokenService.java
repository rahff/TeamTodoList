package org.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.security.ports.dto.JwtTokenPair;
import org.security.ports.spi.JwtEncoder;
import org.shared.spi.DateProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
@Profile("prod")
public class SecurityTokenService implements TokenService {

  @Value("${jwt.privateKey}")
  private String privateKey;
  private final DateProvider dateProvider;

  public SecurityTokenService(DateProvider dateProvider) {
    this.dateProvider = dateProvider;
  }


  public JwtTokenPair encode(String userEmail, String userRole) {
     var accessToken = makeToken(Map.of("username", userEmail,"userRole", userRole), dateProvider.oneDayLater());
     var refreshToken = makeToken(Map.of("username", userEmail), dateProvider.oneWeekLater());
     return new JwtTokenPair(accessToken, refreshToken);
  }

  private String makeToken(Map<String, String> claims, Date expiration) {
    var key = Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8));
    return Jwts.builder().setClaims(claims)
            .setExpiration(expiration).signWith(key)
            .compact();
  }

  public Claims decode(String token) {
    var key = Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8));
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }
}
