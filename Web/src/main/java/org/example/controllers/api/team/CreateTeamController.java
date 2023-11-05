package org.example.controllers.api.team;

import org.example.controllers.api.team.jsonPayloads.response.TeamJson;
import org.example.transactions.team.CreateTeamTransaction;
import org.query.team.api.TeamCreatedQuery;
import org.query.team.model.TeamCard;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.team.ports.dto.CreateTeamRequest;

import java.util.concurrent.ExecutionException;

@RestController
public class CreateTeamController {

  private final CreateTeamTransaction transaction;
  private final TeamCreatedQuery teamCreatedQuery;

  public CreateTeamController(CreateTeamTransaction transaction, TeamCreatedQuery teamCreatedQuery) {
    this.transaction = transaction;
    this.teamCreatedQuery = teamCreatedQuery;
  }

  @PostMapping("create-team")
  public TeamJson createTeam(@RequestBody CreateTeamRequest body){
    try{
      transaction.execute(body);
      return getTeamFromQueryModel(body.id());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
  }

  private TeamJson getTeamFromQueryModel(String id) throws Exception {
    var model = teamCreatedQuery.getTeamCard(id);
    return new TeamJson(model.id(), model.name(), model.teammateCount(), model.todoListCount());
  }
}
