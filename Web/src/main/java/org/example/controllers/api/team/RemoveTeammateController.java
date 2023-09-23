package org.example.controllers.api.team;

import org.example.transactions.team.RemoveTeammateTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;

@RestController
public class RemoveTeammateController {

  private final RemoveTeammateTransaction transaction;

  public RemoveTeammateController(RemoveTeammateTransaction transaction) {
    this.transaction = transaction;
  }

  @PutMapping("remove-teammate")
  public void removeTeammate(@RequestBody RemoveTeammateFromTeamRequest body){
    try{
      transaction.execute(body);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
