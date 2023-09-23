package org.team.application.commands;

import org.shared.api.Command;
import org.team.entities.Team;
import org.team.ports.dto.CreateTeamRequest;
import org.team.ports.spi.TeamRepository;


public class CreateTeam implements Command<CreateTeamRequest> {
  private final TeamRepository teamRepository;

  public CreateTeam(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public void execute(CreateTeamRequest request) {
    var team = new Team(request.id(), request.name(), request.teammates(), request.accountId());
    teamRepository.saveTeam(team.snapshot());
  }
}
