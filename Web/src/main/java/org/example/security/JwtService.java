package org.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class JwtService {

  @Value("${jwt.privateKey}")
  private String privateKey;


  public String encode(String userEmail, String userRole){

    var key = Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8));
   return Jwts.builder()
      .setClaims(Map.of("username", userEmail,"userPlan", userRole))
      .signWith(key)
      .compact();
  }

  public Claims decode(String token) {
    var key = Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8));
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }
}
