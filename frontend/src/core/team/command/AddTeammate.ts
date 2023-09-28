import { Command } from "src/core/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/shared/dto/Result";
import { AddTeammateRequest } from "../dto/AddTeammateRequest";



export class AddTeammate extends Command {

    public constructor(private commandHandler: TeamCommandHandler){super()}

    public execute(request: AddTeammateRequest): Observable<Result> {
        return this.commandHandler.addTeammateOnTeam(request)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}