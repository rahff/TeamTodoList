package org.example.controllers.api.team;

import org.example.transactions.team.CreateTeamTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.team.ports.dto.CreateTeamRequest;

@RestController
public class CreateTeamController {

  private final CreateTeamTransaction transaction;

  public CreateTeamController(CreateTeamTransaction transaction) {
    this.transaction = transaction;
  }

  @PostMapping("create-team")
  public void createTeam(@RequestBody CreateTeamRequest body){
    try{
      transaction.execute(body);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
  }
}
