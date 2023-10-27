import { Observable, catchError, map } from "rxjs";
import { Command } from "../../shared/command/Command";
import { Result } from "../../shared/dto/Result";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";

export class FireTeammate extends Command<string, string> {

    public constructor(private commandHandler: TeamCommandHandler ){super()}

    public execute(teammateId: string): Observable<Result<string>> {
        return this.commandHandler.fireTeammate(teammateId)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }

}