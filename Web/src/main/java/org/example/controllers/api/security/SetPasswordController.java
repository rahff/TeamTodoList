package org.example.controllers.api.security;

import org.example.controllers.api.security.jsonPayload.ChangePasswordPayload;
import org.example.transactions.security.SetPasswordTransaction;
import org.security.ports.dto.ChangePasswordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class SetPasswordController {

  private final SetPasswordTransaction transaction;

  public SetPasswordController(SetPasswordTransaction transaction) {
    this.transaction = transaction;
  }

  @PostMapping("/set-password")
  public void setTeammatePassword(@RequestBody ChangePasswordPayload requestBody, @AuthenticationPrincipal UserDetails userDetails){
    try{
      transaction.execute(new ChangePasswordRequest(userDetails.getUsername(), requestBody.newPassword()));
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

}
