import { Observable, catchError, map } from "rxjs";
import { CreateTeamFormData, CreateTeamRequest } from "../dto/CreateTeamFormData";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { Result } from "src/core/application/shared/dto/Result";
import { IDProvider } from "src/core/application/shared/interfaces/IDProvider";
import { Command } from "src/core/application/shared/command/Command";
import { Team } from "../dto/Team";



export class CreateTeam extends Command<Team, CreateTeamFormData> {

    public constructor(private commandHandler: TeamCommandHandler, 
                       private idProvider: IDProvider){super()}

    public execute(formData: CreateTeamFormData): Observable<Result<Team>> {
        const request: CreateTeamRequest = {...formData, id: this.idProvider.generate()}
        return this.commandHandler.createTeam(request)
        .pipe(map(this.onSuccess), 
        catchError(this.onError));
    }
}