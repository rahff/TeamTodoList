import { Observable, of, throwError } from "rxjs";
import { CreateTeamRequest } from "../../../../core/application/team/dto/CreateTeamFormData";
import { TeamCommandHandler } from "../../../../core/application/team/spi/TeamCommandHandler";
import { JoinTeammateRequest } from "../../../../core/application/team/dto/CreateTeammateFormData";
import { AddTeammateOnTeamRequest } from "../../../../core/application/team/dto/AddTeammateOnTeamRequest";
import { RemoveTeammateFromTeamRequest } from "../../../../core/application/team/dto/RemoveTeammateFromTeamRequest";
import { Teammate } from "../../../../core/application/team/dto/Teammate";
import { newTeam, newTeammate } from "../../../../core/application/team/in-memory/data/team.data";
import { Team } from "../../../../core/application/team/dto/Team";
import { Injectable } from "@angular/core";





@Injectable({
    providedIn: "root"
})
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

    public addTeammateOnTeam(request: AddTeammateOnTeamRequest): Observable<Teammate[]> {
        this.methodCalls.set("addTeammateOnTeam", request)
        if(request.teamId === "failure") return throwError(() => this.error);
        return of([newTeammate]);
    }

    public removeTeammateFromTeam(request: RemoveTeammateFromTeamRequest): Observable<string> {
        this.methodCalls.set("removeTeammateFromTeam", request)
        if(request.teamId === "failure") return throwError(() => this.error);
        return of(request.teammateId);
    }

    public hasBeenCalled(methodName: TeamDataSourceMethods): any {
        return this.methodCalls.get(methodName)
    }

    public fireTeammate(teammateId: string): Observable<string> {
        this.methodCalls.set( "fireTeammate", teammateId)
        if(teammateId === "failure") return throwError(() => this.error);
        return of(teammateId);
    }

}

type TeamDataSourceMethods = "createTeam" | "joinTeammate" | "deleteTeam" | "addTeammateOnTeam" | "removeTeammateFromTeam" | "fireTeammate"