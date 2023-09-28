import { Observable, catchError, map, of } from "rxjs";
import { CreateTeamFormData, CreateTeamRequest } from "../dto/CreateTeamFormData";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { Result } from "src/core/shared/dto/Result";
import { IDProvider } from "src/core/shared/interfaces/IDProvider";
import { Command } from "src/core/shared/command/Command";



export class CreateTeam extends Command {

    public constructor(private commandHandler: TeamCommandHandler, private idProvider: IDProvider){super()}

    public execute(formData: CreateTeamFormData): Observable<Result> {
        const request: CreateTeamRequest = {...formData, id: this.idProvider.generate()}
        return this.commandHandler.createTeam(request).pipe(map(this.onSuccess), 
        catchError(this.onError));
    }
}