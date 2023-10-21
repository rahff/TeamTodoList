import { Command } from "src/core/application/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";
import { AddTeammateRequest } from "../dto/AddTeammateRequest";
import { Teammate } from "../dto/Teammate";



export class AddTeammate extends Command<{teammate: Teammate, teamId: string}, AddTeammateRequest> {

    public constructor(private commandHandler: TeamCommandHandler){super()}

    public execute(request: AddTeammateRequest): Observable<Result<{teammate: Teammate, teamId: string}>> {
        return this.commandHandler.addTeammateOnTeam(request)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}