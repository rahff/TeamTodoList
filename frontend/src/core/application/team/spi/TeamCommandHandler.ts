import { Observable } from "rxjs";
import { CreateTeamRequest } from "../dto/CreateTeamFormData";
import { JoinTeammateRequest } from "../dto/CreateTeammateFormData";
import { AddTeammateRequest } from "../dto/AddTeammateRequest";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";
import { Teammate } from "../dto/Teammate";
import { Team } from "../dto/Team";



export interface TeamCommandHandler {
    createTeam(request: CreateTeamRequest): Observable<Team>;
    joinTeammate(request: JoinTeammateRequest): Observable<Teammate>;
    deleteTeam(teamId: string): Observable<string>;
    addTeammateOnTeam(request: AddTeammateRequest): Observable<{teammate: Teammate, teamId: string}>;
    removeTeammateFromTeam(request: RemoveTeammateFromTeamRequest): Observable<{teammateId: string, teamId: string}>;
}