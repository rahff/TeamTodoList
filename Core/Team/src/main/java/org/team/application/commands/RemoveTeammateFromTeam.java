package org.team.application.commands;

import org.shared.api.Command;
import org.team.entities.Team;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.spi.TeamRepository;

public class RemoveTeammateFromTeam implements Command<RemoveTeammateFromTeamRequest> {
  private final TeamRepository teamRepository;

  public RemoveTeammateFromTeam(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }


  public void execute(RemoveTeammateFromTeamRequest request) {
    var foundedTeam = teamRepository.getTeamById(request.teamId()).orElseThrow(()-> new RuntimeException("not found"));
    var team = new Team(foundedTeam.id(), foundedTeam.name(), foundedTeam.teammates(), foundedTeam.accountId());
    team.removeTeammate(request.teammateId());
    teamRepository.saveTeam(team.snapshot());
  }
}
