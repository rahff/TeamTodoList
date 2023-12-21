package org.team.application.commands;


import org.shared.spi.UserRepository;
import org.team.ports.dto.FireTeammateRequest;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;

public class FireTeammateCommand  {

    private final UserRepository teammateRepository;
    private final  RemoveTeammateFromTeam removeTeammateFromTeamCommand;
    public FireTeammateCommand(UserRepository teammateRepository, RemoveTeammateFromTeam removeTeammateFromTeamCommand) {
        this.teammateRepository = teammateRepository;
        this.removeTeammateFromTeamCommand = removeTeammateFromTeamCommand;
    }

    public void execute(FireTeammateRequest request) {
        removeTeammateFromTeamCommand.execute(new RemoveTeammateFromTeamRequest(request.teamId(), request.userId()));
        teammateRepository.removeTeammateUser(request.userId());
    }
}
