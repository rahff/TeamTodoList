import { Command } from "src/core/application/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { Observable, catchError, map, of } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";



export class DeleteTeam extends Command<string, string> {

    public constructor(private commandHandler: TeamCommandHandler){super()}

    public execute(teamToDeleteId: string): Observable<Result<string>> {
        return this.commandHandler.deleteTeam(teamToDeleteId)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}


