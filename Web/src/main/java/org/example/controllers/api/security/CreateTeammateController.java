package org.example.controllers.api.security;

import org.example.transactions.security.CreateTeammateTransaction;
import org.security.ports.dto.CreateTeammateRequest;
import org.security.ports.spi.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class CreateTeammateController {

  private final CreateTeammateTransaction createTeammateTransaction;
  private final UserRepository userRepository;

  public CreateTeammateController(CreateTeammateTransaction createTeammateTransaction, UserRepository userRepository) {
    this.createTeammateTransaction = createTeammateTransaction;
    this.userRepository = userRepository;
  }

  @PostMapping("/add-teammate")
  public void addTeammateOnOrganization(@RequestBody CreateTeammateRequest bodyRequest, @AuthenticationPrincipal UserDetails userDetails) {
    try{
      var manager = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
      createTeammateTransaction.execute(bodyRequest, manager.name());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
