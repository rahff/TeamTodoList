package org.example.security;



import org.example.persistance.repositories.security.springData.AppUserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final TokenService jwtService;
  private final AppUserRepository userDetailsService;

  public JwtAuthenticationProvider(TokenService jwtService, AppUserRepository userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    var token = authentication.getCredentials();
    var jwtClaims = jwtService.decode(token.toString());
    var userEmail = jwtClaims.get("username");
    var userPlan = jwtClaims.get("userRole");
    var principal = userDetailsService.findByEmail(userEmail.toString()).orElseThrow(() -> new BadCredentialsException("bad credentials"));
    return new JwtAuthenticationToken(principal.toDto(), null, List.of(new SimpleGrantedAuthority(userPlan.toString())));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.isAssignableFrom(JwtAuthenticationToken.class);
  }
}
