import { Command } from "src/core/application/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";




export class RemoveTeammateFromTeam extends Command<{teamId: string, teammateId: string}, RemoveTeammateFromTeamRequest> {

    public constructor(private commandHandler: TeamCommandHandler){super()}

    public execute(request: RemoveTeammateFromTeamRequest): Observable<Result<{teamId: string, teammateId: string}>> {
        return this.commandHandler.removeTeammateFromTeam(request)
        .pipe(map(this.onSuccess),
         catchError(this.onError))
    }
}