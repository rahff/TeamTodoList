package org.example.controllers.api.team;

import org.example.transactions.team.AddTeammateTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.team.ports.dto.AddTeammatesOnTeamRequest;

@RestController
public class AddTeammatesController {

  private final AddTeammateTransaction transaction;

  public AddTeammatesController(AddTeammateTransaction transaction) {
    this.transaction = transaction;
  }

  @PutMapping("add-teammates")
  public void addTeammates(@RequestBody AddTeammatesOnTeamRequest body){
    try{
      transaction.execute(body);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
