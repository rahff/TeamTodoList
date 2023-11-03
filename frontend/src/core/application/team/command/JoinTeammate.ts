import { Command } from "src/core/application/shared/command/Command";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { IDProvider } from "src/core/application/shared/interfaces/IDProvider";
import { Observable, catchError, map } from "rxjs";
import { Result } from "src/core/application/shared/dto/Result";
import { CreateTeammateFormData, JoinTeammateRequest } from "../dto/CreateTeammateFormData";
import { Teammate } from "../../../model/team/Teammate";



export class JoinTeammate extends Command<Teammate, CreateTeammateFormData> {

    public constructor(private commandHandler: TeamCommandHandler, private idProvider: IDProvider){super()}

    public execute(formData: CreateTeammateFormData): Observable<Result<Teammate>> {
        const request: JoinTeammateRequest = {...formData, id: this.idProvider.generate()};
        return this.commandHandler.joinTeammate(request)
        .pipe(map(this.onSuccess),
        catchError(this.onError))
    }
}