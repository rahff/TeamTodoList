package org.example.controllers.api.security;

import org.example.controllers.api.security.jsonPayload.CreateAccountResultJson;
import org.example.controllers.api.security.jsonPayload.JwtAuthenticationJson;
import org.example.controllers.api.security.mapper.UserDtoMapper;
import org.example.transactions.security.CreateManagerAccountTransaction;
import org.security.ports.dto.SignupUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class CreateAccountController {

  private final CreateManagerAccountTransaction createManagerAccountTransaction;

  public CreateAccountController(CreateManagerAccountTransaction createManagerAccountTransaction) {
    this.createManagerAccountTransaction = createManagerAccountTransaction;
  }

  @PostMapping("/create-account")
  public CreateAccountResultJson createManagerAccount(@RequestBody SignupUserRequest signupBody) {
    try{
      var result = createManagerAccountTransaction.execute(signupBody);
      return new CreateAccountResultJson(new JwtAuthenticationJson(UserDtoMapper.toJson(result.authenticationResult().user()), result.authenticationResult().accessToken(), result.authenticationResult().refreshToken()), result.checkoutSessionUrl());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
