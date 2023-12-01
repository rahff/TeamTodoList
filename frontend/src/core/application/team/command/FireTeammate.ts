import { Observable, catchError, map } from "rxjs";
import { Command } from "../../shared/command/Command";
import { Result } from "../../shared/dto/Result";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import {Teammate} from "../../../model/team/Teammate";

export class FireTeammate extends Command<string, Teammate> {

    public constructor(private commandHandler: TeamCommandHandler ){super()}

    public execute(teammate: Teammate): Observable<Result<string>> {
        return this.commandHandler.fireTeammate(teammate)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }

}