package org.team.application.commands;

import org.shared.api.Command;
import org.team.entities.Team;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.spi.TeamRepository;

public class AddTeammatesOnTeam implements Command<AddTeammatesOnTeamRequest> {

  private final TeamRepository teamRepository;

  public AddTeammatesOnTeam(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }


  public void execute(AddTeammatesOnTeamRequest request) {
    var teamDto = teamRepository.getTeamById(request.teamId()).orElseThrow(() -> new RuntimeException("not found"));
    var team = new Team(teamDto.id(), teamDto.name(), teamDto.teammates(), teamDto.accountId());
    team.addTeammates(request.teammateToAdd());
    teamRepository.saveTeam(team.snapshot());
  }
}
