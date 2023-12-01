package org.team.application.commands;

import org.shared.api.Command;

import org.team.ports.dto.FireTeammateRequest;
import org.team.ports.dto.RemoveTeammateFromTeamRequest;
import org.team.ports.spi.TeammateRepository;

public class FireTeammateCommand implements Command<FireTeammateRequest> {

    private final TeammateRepository teammateRepository;
    private final  Command<RemoveTeammateFromTeamRequest> removeTeammateFromTeamCommand;
    public FireTeammateCommand(TeammateRepository teammateRepository, Command<RemoveTeammateFromTeamRequest> removeTeammateFromTeamCommand) {
        this.teammateRepository = teammateRepository;
        this.removeTeammateFromTeamCommand = removeTeammateFromTeamCommand;
    }

    public void execute(FireTeammateRequest request) {
        removeTeammateFromTeamCommand.execute(new RemoveTeammateFromTeamRequest(request.teamId(), request.userId()));
        teammateRepository.removeTeammateUser(request.userId());
    }
}
