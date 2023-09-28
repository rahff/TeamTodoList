import { Observable } from "rxjs";
import { CreateTeamRequest } from "../dto/CreateTeamFormData";
import { JoinTeammateRequest } from "../dto/CreateTeammateFormData";
import { AddTeammateRequest } from "../dto/AddTeammateRequest";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";



export interface TeamCommandHandler {
    createTeam(request: CreateTeamRequest): Observable<true>;
    joinTeammate(request: JoinTeammateRequest): Observable<true>;
    deleteTeam(teamId: string): Observable<true>;
    addTeammateOnTeam(request: AddTeammateRequest): Observable<true>;
    removeTeammateFromTeam(request: RemoveTeammateFromTeamRequest): Observable<true>;
}