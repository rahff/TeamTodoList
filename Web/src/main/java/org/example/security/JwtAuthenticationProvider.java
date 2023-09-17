package org.example.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationProvider(JwtService jwtService, UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    var token = authentication.getCredentials();
    if(token == null) throw new BadCredentialsException("no authorization");
    var jwtClaims = jwtService.decode(token.toString());
    var userEmail = jwtClaims.get("username");
    var userPlan = jwtClaims.get("userPlan");
    var principal = userDetailsService.loadUserByUsername(userEmail.toString());
    return new JwtAuthenticationToken(principal, null, List.of(new SimpleGrantedAuthority(userPlan.toString())));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.isAssignableFrom(JwtAuthenticationToken.class);
  }
}
