package org.example.controllers.api.security;

import org.example.controllers.api.security.jsonPayload.JwtAuthenticationJson;
import org.example.controllers.api.security.mapper.UserDtoMapper;
import org.security.ports.api.UsernamePasswordAuthentication;
import org.security.ports.dto.LoginUserRequest;
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
  public JwtAuthenticationJson login(@RequestBody LoginUserRequest loginBody) {
    try{
      var result = authenticationProvider.loginUser(loginBody);
      return new JwtAuthenticationJson(UserDtoMapper.toJson(result.user()), result.accessToken(), result.refreshToken());
    }catch (AuthenticationException e){
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
  }
}
