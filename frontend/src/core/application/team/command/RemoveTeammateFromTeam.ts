import { Command } from "src/core/application/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";




export class RemoveTeammateFromTeam extends Command<string, RemoveTeammateFromTeamRequest> {

    public constructor(private commandHandler: TeamCommandHandler){super()}

    public execute(request: RemoveTeammateFromTeamRequest): Observable<Result<string>> {
        return this.commandHandler.removeTeammateFromTeam(request)
        .pipe(map(this.onSuccess),
         catchError(this.onError))
    }
}