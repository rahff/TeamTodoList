package org.team.application.commands;


import org.shared.spi.UserRepository;
import org.team.entities.Team;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.spi.TeamRepository;

public class AddTeammatesOnTeam {

  private final TeamRepository teamRepository;
  private final UserRepository teammateRepository;

  public AddTeammatesOnTeam(TeamRepository teamRepository, UserRepository teammateRepository) {
    this.teamRepository = teamRepository;
    this.teammateRepository = teammateRepository;
  }


  public void execute(AddTeammatesOnTeamRequest request) {
    var teamDto = teamRepository.getTeamById(request.teamId()).orElseThrow(() -> new RuntimeException("not found"));
    var team = new Team(teamDto.id(), teamDto.name(), teamDto.teammates(), teamDto.accountId());
    team.addTeammates(request.teammateToAdd());
    teammateRepository.addTeamIdOnTeammate(request.teammateToAdd(), request.teamId());
    teamRepository.saveTeam(team.snapshot());
  }
}
