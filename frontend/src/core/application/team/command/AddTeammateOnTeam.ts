import { Command } from "src/core/application/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";
import { AddTeammateOnTeamRequest } from "../dto/AddTeammateOnTeamRequest";
import { Teammate } from "../../../model/team/Teammate";



export class AddTeammateOnTeam extends Command<Teammate[], AddTeammateOnTeamRequest> {

    public constructor(private commandHandler: TeamCommandHandler){super()}

    public execute(request: AddTeammateOnTeamRequest): Observable<Result<Teammate[]>> {
        return this.commandHandler.addTeammateOnTeam(request)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}