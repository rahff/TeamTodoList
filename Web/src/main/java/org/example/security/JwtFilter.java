package org.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

  private  AuthenticationManager authenticationManager;
  private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

  public JwtFilter withManager(AuthenticationManager authenticationManager){
    this.authenticationManager = authenticationManager;
    return this;
  }
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try{
      var token = request.getHeader("Authorization");
      if(token == null) {
        filterChain.doFilter(request, response);
        return;
      }
      Authentication authentication = authenticationManager.authenticate(new JwtAuthenticationToken(token));
      SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
      context.setAuthentication(authentication);
      this.securityContextHolderStrategy.setContext(context);
    }catch (AuthenticationException e){
      this.securityContextHolderStrategy.clearContext();
      return;
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath().equals("/login")
      || request.getServletPath().equals("/signup");
  }
}
