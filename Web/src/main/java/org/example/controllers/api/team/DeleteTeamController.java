package org.example.controllers.api.team;


import org.example.controllers.api.todo.jsonPayloads.response.IdJson;
import org.example.transactions.team.DeleteTeamTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.team.ports.dto.DeleteTeamRequest;

@RestController
public class DeleteTeamController {

  private final DeleteTeamTransaction transaction;

  public DeleteTeamController(DeleteTeamTransaction transaction) {
    this.transaction = transaction;
  }

  @DeleteMapping("/delete-team/{teamId}")
  public IdJson deleteTeam(@PathVariable("teamId") String teamId){
    try{
      transaction.execute(new DeleteTeamRequest(teamId));
      return new IdJson(teamId);
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
