package org.example.controllers.api.team;

import org.example.controllers.api.team.jsonPayloads.response.TeammateJson;
import org.example.transactions.security.CreateTeammateTransaction;
import org.query.team.api.TeammateCreatedQuery;
import org.team.ports.dto.CreateTeammateRequest;
import org.shared.spi.UserRepository;
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
  private final TeammateCreatedQuery teammateCreatedQuery;

  public CreateTeammateController(CreateTeammateTransaction createTeammateTransaction, UserRepository userRepository, TeammateCreatedQuery teammateCreatedQuery) {
    this.createTeammateTransaction = createTeammateTransaction;
    this.userRepository = userRepository;
    this.teammateCreatedQuery = teammateCreatedQuery;
  }

  @PostMapping("/add-teammate")
  public TeammateJson addTeammateOnOrganization(@RequestBody CreateTeammateRequest bodyRequest, @AuthenticationPrincipal UserDetails userDetails) {
    try{
      var manager = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
      createTeammateTransaction.execute(bodyRequest, manager.name());
      return getCreatedTeammate(bodyRequest.teammateId());
    }catch (Exception e){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  private TeammateJson getCreatedTeammate(String id) {
    var dto = teammateCreatedQuery.getTeammate(id);
    return new TeammateJson(dto.id(), dto.name(), dto.email(), dto.teamId().orElse(null));
  }
}
