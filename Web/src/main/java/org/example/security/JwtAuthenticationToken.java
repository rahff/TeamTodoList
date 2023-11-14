package org.example.security;

import org.shared.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken implements Authentication {

  private final String token;
  private Collection<? extends GrantedAuthority> authorities;
  private UserDto principal;

  public JwtAuthenticationToken(String token) {
    this.token = token;
  }

  public JwtAuthenticationToken(UserDto principal, String token, Collection<? extends GrantedAuthority> authorities) {
    this.token = token;
    this.authorities = authorities;
    this.principal = principal;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  @Override
  public Object getDetails() {
    return principal;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }

  @Override
  public boolean isAuthenticated() {
    return authorities != null;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }
}
