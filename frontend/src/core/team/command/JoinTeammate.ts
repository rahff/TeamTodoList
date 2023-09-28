import { Command } from "src/core/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { IDProvider } from "src/core/shared/interfaces/IDProvider";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/shared/dto/Result";
import { CreateTeammateFormData, JoinTeammateRequest } from "../dto/CreateTeammateFormData";



export class JoinTeammate extends Command {

    public constructor(private commandHandler: TeamCommandHandler, private idProvider: IDProvider){super()}

    public execute(formData: CreateTeammateFormData): Observable<Result> {
        const request: JoinTeammateRequest = {...formData, id: this.idProvider.generate()};
        return this.commandHandler.joinTeammate(request)
        .pipe(map(this.onSuccess),
        catchError(this.onError))
    }
}