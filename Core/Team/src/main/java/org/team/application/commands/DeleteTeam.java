package org.team.application.commands;


import org.team.ports.dto.DeleteTeamRequest;
import org.team.ports.spi.TeamRepository;


public class DeleteTeam {
  private final TeamRepository teamRepository;

  public DeleteTeam(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public void execute(DeleteTeamRequest request) {
    teamRepository.deleteTeam(request.teamId());
  }
}
