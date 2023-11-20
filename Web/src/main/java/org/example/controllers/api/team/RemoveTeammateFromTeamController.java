package org.example.controllers.api.team;

import org.example.controllers.api.todo.jsonPayloads.response.IdJson;
import org.example.transactions.team.RemoveTeammateTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;

@RestController
public class RemoveTeammateFromTeamController {

  private final RemoveTeammateTransaction transaction;

  public RemoveTeammateFromTeamController(RemoveTeammateTransaction transaction) {
    this.transaction = transaction;
  }

  @PutMapping("remove-teammate-from-team")
  public IdJson removeTeammate(@RequestBody RemoveTeammateFromTeamRequest body){
    try{
      transaction.execute(body);
      return new IdJson(body.teammateId());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
