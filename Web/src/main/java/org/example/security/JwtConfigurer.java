package org.example.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtConfigurer extends AbstractHttpConfigurer<JwtConfigurer, HttpSecurity> {

  private JwtAuthenticationProvider authenticationProvider;
  private JwtFilter jwtFilter;

  public JwtConfigurer withAuthenticationProvider(JwtAuthenticationProvider authenticationProvider){
    this.authenticationProvider = authenticationProvider;
    return this;
  }

  public JwtConfigurer withJwtFilter(JwtFilter filter){
    this.jwtFilter = filter;
    return this;
  }
  @Override
  public void init(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authenticationProvider(authenticationProvider);
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    var authManager = httpSecurity.getSharedObject(AuthenticationManager.class);
    httpSecurity.addFilterAfter(jwtFilter.withManager(authManager), BasicAuthenticationFilter.class);
  }
}
