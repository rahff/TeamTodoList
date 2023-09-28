import { Command } from "src/core/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/shared/dto/Result";



export class DeleteTeam extends Command {

    public constructor(private commandHandler: TeamCommandHandler){super()}

    public execute(teamToDeleteId: string): Observable<Result> {
        return this.commandHandler.deleteTeam(teamToDeleteId)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}