import { Observable } from "rxjs";
import { CreateTeamRequest } from "../dto/CreateTeamFormData";
import { JoinTeammateRequest } from "../dto/CreateTeammateFormData";
import { AddTeammateOnTeamRequest } from "../dto/AddTeammateOnTeamRequest";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";
import { Teammate } from "../dto/Teammate";
import { Team } from "../dto/Team";



export interface TeamCommandHandler {
    createTeam(request: CreateTeamRequest): Observable<Team>;
    joinTeammate(request: JoinTeammateRequest): Observable<Teammate>;
    deleteTeam(teamId: string): Observable<string>;
    addTeammateOnTeam(request: AddTeammateOnTeamRequest): Observable<Teammate[]>;
    removeTeammateFromTeam(request: RemoveTeammateFromTeamRequest): Observable<string>;
    fireTeammate(teammateId: string): Observable<string>;
}