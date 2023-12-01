import { Observable, of, throwError } from "rxjs";
import { CreateTeamRequest } from "../../../../core/application/team/dto/CreateTeamFormData";
import { TeamCommandHandler } from "../../../../core/application/team/spi/TeamCommandHandler";
import { JoinTeammateRequest } from "../../../../core/application/team/dto/CreateTeammateFormData";
import { AddTeammateOnTeamRequest } from "../../../../core/application/team/dto/AddTeammateOnTeamRequest";
import { RemoveTeammateFromTeamRequest } from "../../../../core/application/team/dto/RemoveTeammateFromTeamRequest";
import { Teammate } from "../../../../core/model/team/Teammate";
import { newTeam, newTeamCard, newTeammate } from "../../../../core/application/team/in-memory/data/team.data";
import { Team } from "../../../../core/model/team/Team";
import { Injectable } from "@angular/core";
import { TeamCard } from "src/core/model/team/TeamCard";





@Injectable({
    providedIn: "root"
})
export class InMemoryTeamCommandHandler implements TeamCommandHandler {

    private methodCalls: Map<TeamDataSourceMethods, Object>;

    private error: Error = new Error("bad request");

    public constructor(){
        this.methodCalls = new Map<TeamDataSourceMethods, Object>();
    }

    public createTeam(request: CreateTeamRequest): Observable<TeamCard> {
        this.methodCalls.set("createTeam", request);
        if(request.name === "will fail"){
            return throwError(() => this.error);
        }
        return of(newTeamCard);
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

    public addTeammatesOnTeam(request: AddTeammateOnTeamRequest): Observable<Teammate[]> {
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

    public fireTeammate(teammate: Teammate): Observable<string> {
        this.methodCalls.set( "fireTeammate", teammate)
        if(teammate.id === "failure") return throwError(() => this.error);
        return of(teammate.id);
    }

}

type TeamDataSourceMethods = "createTeam" | "joinTeammate" | "deleteTeam" | "addTeammateOnTeam" | "removeTeammateFromTeam" | "fireTeammate"