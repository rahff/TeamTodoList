package org.team.application.commands;


import org.team.ports.dto.FireTeammateRequest;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.spi.TeammateRepository;

public class FireTeammateCommand  {

    private final TeammateRepository teammateRepository;
    private final  RemoveTeammateFromTeam removeTeammateFromTeamCommand;
    public FireTeammateCommand(TeammateRepository teammateRepository, RemoveTeammateFromTeam removeTeammateFromTeamCommand) {
        this.teammateRepository = teammateRepository;
        this.removeTeammateFromTeamCommand = removeTeammateFromTeamCommand;
    }

    public void execute(FireTeammateRequest request) {
        removeTeammateFromTeamCommand.execute(new RemoveTeammateFromTeamRequest(request.teamId(), request.userId()));
        teammateRepository.removeTeammateUser(request.userId());
    }
}
