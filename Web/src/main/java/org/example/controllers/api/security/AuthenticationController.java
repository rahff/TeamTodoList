package org.example.controllers.api.security;


import org.example.transactions.security.CreateTeammateTransaction;
import org.security.ports.api.UsernamePasswordAuthentication;
import org.security.ports.dto.CreateTeammateRequest;
import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.LoginUserRequest;
import org.security.ports.dto.SignupUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class AuthenticationController {

  private final UsernamePasswordAuthentication authenticationProvider;


  public AuthenticationController(UsernamePasswordAuthentication authenticationProvider) {
    this.authenticationProvider = authenticationProvider;
  }

  @PostMapping("/login")
  public JwtAuthenticationResult login(@RequestBody LoginUserRequest loginBody) {
    try{
      return authenticationProvider.loginUser(loginBody);
    }catch (AuthenticationException e){
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
  }


}
