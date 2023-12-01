package org.example.controllers.api.team;

import org.example.controllers.api.team.jsonPayloads.response.TeammateJson;
import org.example.transactions.security.CreateTeammateTransaction;
import org.query.team.api.TeammateCreatedQuery;
import org.shared.dto.UserDto;
import org.team.ports.dto.CreateTeammateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;



@RestController
public class CreateTeammateController {

  private final CreateTeammateTransaction createTeammateTransaction;
  private final TeammateCreatedQuery teammateCreatedQuery;

  public CreateTeammateController(CreateTeammateTransaction createTeammateTransaction, TeammateCreatedQuery teammateCreatedQuery) {
    this.createTeammateTransaction = createTeammateTransaction;
    this.teammateCreatedQuery = teammateCreatedQuery;
  }

  @PostMapping("/create-teammate")
  public TeammateJson addTeammateOnOrganization(@RequestBody CreateTeammateRequest bodyRequest, @AuthenticationPrincipal UserDto userDetails) {
    try{
      createTeammateTransaction.execute(bodyRequest, userDetails.name());
      return getCreatedTeammate(bodyRequest.teammateId());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  private TeammateJson getCreatedTeammate(String id) {
    var dto = teammateCreatedQuery.getTeammate(id);
    return new TeammateJson(dto.id(), dto.name(), dto.email(), dto.teamId());
  }
}
