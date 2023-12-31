import { Observable, catchError, map } from "rxjs";
import { CreateTeamFormData, CreateTeamRequest } from "../dto/CreateTeamFormData";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { Result } from "src/core/application/shared/dto/Result";
import { IDProvider } from "src/core/application/shared/interfaces/IDProvider";
import { Command } from "src/core/application/shared/command/Command";
import { TeamCard } from "src/core/model/team/TeamCard";


export class CreateTeam extends Command<TeamCard, CreateTeamFormData> {

    public constructor(private commandHandler: TeamCommandHandler, 
                       private idProvider: IDProvider){super()}

    public execute(formData: CreateTeamFormData): Observable<Result<TeamCard>> {
        const request: CreateTeamRequest = {...formData, id: this.idProvider.generate()}
        return this.commandHandler.createTeam(request)
        .pipe(map(this.onSuccess), 
        catchError(this.onError));
    }
}