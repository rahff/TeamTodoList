import { Observable, of, throwError } from "rxjs";
import { CreateTeamRequest } from "../dto/CreateTeamFormData";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { JoinTeammateRequest } from "../dto/CreateTeammateFormData";
import { AddTeammateRequest } from "../dto/AddTeammateRequest";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";

export class InMemoryTeamCommandHandler implements TeamCommandHandler {

    private methodCalls: Map<TeamDataSourceMethods, Object>;

    private error: Error = new Error("bad request");

    public constructor(){
        this.methodCalls = new Map<TeamDataSourceMethods, Object>();
    }

    public createTeam(request: CreateTeamRequest): Observable<true> {
        this.methodCalls.set("createTeam", request);
        if(request.name === "will fail"){
            return throwError(() => this.error);
        }
        return of(true);
    }
    
    public joinTeammate(request: JoinTeammateRequest): Observable<true> {
        this.methodCalls.set("joinTeammate", request);
        if(request.name === "failure") return throwError(() => this.error);
        return of(true)
    }

    public deleteTeam(teamId: string): Observable<true> {
        this.methodCalls.set("deleteTeam", teamId)
        if(teamId === "failure") return throwError(() => this.error);
        return of(true);
    }

    public addTeammateOnTeam(request: AddTeammateRequest): Observable<true> {
        this.methodCalls.set("addTeammateOnTeam", request)
        if(request.teamId === "failure") return throwError(() => this.error);
        return of(true);
    }

    public removeTeammateFromTeam(request: RemoveTeammateFromTeamRequest): Observable<true> {
        this.methodCalls.set("removeTeammateFromTeam", request)
        if(request.teamId === "failure") return throwError(() => this.error);
        return of(true);
    }

    public hasBeenCalled(methodName: TeamDataSourceMethods): any {
        return this.methodCalls.get(methodName)
    }

}

type TeamDataSourceMethods = "createTeam" | "joinTeammate" | "deleteTeam" | "addTeammateOnTeam" | "removeTeammateFromTeam"