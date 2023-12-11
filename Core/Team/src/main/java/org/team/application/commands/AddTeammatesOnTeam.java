package org.team.application.commands;


import org.team.entities.Team;
import org.team.ports.dto.AddTeammatesOnTeamRequest;
import org.team.ports.spi.TeamRepository;
import org.team.ports.spi.TeammateRepository;

public class AddTeammatesOnTeam {

  private final TeamRepository teamRepository;
  private final TeammateRepository teammateRepository;

  public AddTeammatesOnTeam(TeamRepository teamRepository, TeammateRepository teammateRepository) {
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
