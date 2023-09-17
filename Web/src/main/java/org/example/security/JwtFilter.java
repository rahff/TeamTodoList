package org.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

  private  AuthenticationManager authenticationManager;

  public JwtFilter withManager(AuthenticationManager authenticationManager){
    this.authenticationManager = authenticationManager;
    return this;
  }
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var token = request.getHeader("Authorization");
    Authentication authentication = authenticationManager.authenticate(new JwtAuthenticationToken(token));
    var context = SecurityContextHolder.getContext();
    context.setAuthentication(authentication);
    SecurityContextHolder.setContext(context);
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath().equals("/login")
      || request.getServletPath().equals("/signup");
  }
}
