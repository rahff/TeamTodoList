import { Observable, of, throwError } from "rxjs";
import { CreateTeamRequest } from "../dto/CreateTeamFormData";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { JoinTeammateRequest } from "../dto/CreateTeammateFormData";
import { AddTeammateRequest } from "../dto/AddTeammateRequest";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";
import { Teammate } from "../dto/Teammate";
import { newTeam, newTeammate } from "./data/team.data";
import { Team } from "../dto/Team";

export class InMemoryTeamCommandHandler implements TeamCommandHandler {

    private methodCalls: Map<TeamDataSourceMethods, Object>;

    private error: Error = new Error("bad request");

    public constructor(){
        this.methodCalls = new Map<TeamDataSourceMethods, Object>();
    }

    public createTeam(request: CreateTeamRequest): Observable<Team> {
        this.methodCalls.set("createTeam", request);
        if(request.name === "will fail"){
            return throwError(() => this.error);
        }
        return of(newTeam);
    }
    
    public joinTeammate(request: JoinTeammateRequest): Observable<Teammate> {
        this.methodCalls.set("joinTeammate", request);
        if(request.name === "failure") return throwError(() => this.error);
        return of(newTeammate)
    }

    public deleteTeam(teamId: string): Observable<string> {
        this.methodCalls.set("deleteTeam", teamId)
        if(teamId === "failure") return throwError(() => this.error);
        return of(teamId);
    }

    public addTeammateOnTeam(request: AddTeammateRequest): Observable<{teammate: Teammate, teamId: string}> {
        this.methodCalls.set("addTeammateOnTeam", request)
        if(request.teamId === "failure") return throwError(() => this.error);
        return of({teammate: newTeammate, teamId: request.teamId});
    }

    public removeTeammateFromTeam(request: RemoveTeammateFromTeamRequest): Observable<{teammateId: string, teamId: string}> {
        this.methodCalls.set("removeTeammateFromTeam", request)
        if(request.teamId === "failure") return throwError(() => this.error);
        return of({teammateId: request.teammateId, teamId: request.teamId});
    }

    public hasBeenCalled(methodName: TeamDataSourceMethods): any {
        return this.methodCalls.get(methodName)
    }

}

type TeamDataSourceMethods = "createTeam" | "joinTeammate" | "deleteTeam" | "addTeammateOnTeam" | "removeTeammateFromTeam"