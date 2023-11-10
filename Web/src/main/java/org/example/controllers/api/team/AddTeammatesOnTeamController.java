package org.example.controllers.api.team;

import org.example.controllers.api.team.jsonPayloads.response.TeammateJson;
import org.example.transactions.team.AddTeammateOnTeamTransaction;
import org.query.team.api.TeammatesAddedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.team.ports.dto.AddTeammatesOnTeamRequest;

import java.util.List;

@RestController
public class AddTeammatesOnTeamController {

  private final AddTeammateOnTeamTransaction transaction;
  private final TeammatesAddedQuery teammateAddedQuery;

  public AddTeammatesOnTeamController(AddTeammateOnTeamTransaction transaction, TeammatesAddedQuery teammateAddedQuery) {
    this.transaction = transaction;
    this.teammateAddedQuery = teammateAddedQuery;
  }

  @PutMapping("add-teammates")
  public List<TeammateJson> addTeammates(@RequestBody AddTeammatesOnTeamRequest body){
    try{
      transaction.execute(body);
      return queryResult(body.teammateToAdd(), body.teamId());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  private List<TeammateJson> queryResult(List<String> ids, String teamId) {
    return teammateAddedQuery.getTeammates(ids, teamId)
            .stream().map(t -> new TeammateJson(t.id(), t.name(), t.email(), t.teamId().orElse(teamId)))
            .toList();
  }
}
