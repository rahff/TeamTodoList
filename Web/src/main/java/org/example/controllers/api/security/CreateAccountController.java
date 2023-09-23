package org.example.controllers.api.security;

import org.example.transactions.security.CreateManagerAccountTransaction;
import org.security.ports.dto.JwtAuthenticationResult;
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
  public JwtAuthenticationResult createManagerAccount(@RequestBody SignupUserRequest signupBody) {
    try{
      return createManagerAccountTransaction.execute(signupBody);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
