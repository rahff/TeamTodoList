package org.example.controllers.api.endpoints;


import org.example.controllers.api.httpRequestPayloads.LoginUserRequestBody;
import org.example.controllers.api.httpRequestPayloads.SignupUserRequestBody;
import org.example.security.JwtAuthenticationResult;
import org.example.security.UsernamePasswordAuthenticationProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class AuthenticationController {

  private final UsernamePasswordAuthenticationProvider authenticationProvider;

  public AuthenticationController(UsernamePasswordAuthenticationProvider authenticationProvider) {
    this.authenticationProvider = authenticationProvider;
  }

  @PostMapping("/login")
  public JwtAuthenticationResult login(@RequestBody LoginUserRequestBody loginBody) {
    try{
      return authenticationProvider.loginUser(loginBody);
    }catch (AuthenticationException e){
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
  }

  @PostMapping("/signup")
  public JwtAuthenticationResult login(@RequestBody SignupUserRequestBody signupBody) {
    try{
      return authenticationProvider.signupUser(signupBody);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
