import { Observable } from "rxjs";
import { CreateTeamRequest } from "../dto/CreateTeamFormData";
import { JoinTeammateRequest } from "../dto/CreateTeammateFormData";
import { AddTeammateOnTeamRequest } from "../dto/AddTeammateOnTeamRequest";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";
import { Teammate } from "../../../model/team/Teammate";
import { TeamCard } from "src/core/model/team/TeamCard";



export interface TeamCommandHandler {
    createTeam(request: CreateTeamRequest): Observable<TeamCard>;
    joinTeammate(request: JoinTeammateRequest): Observable<Teammate>;
    deleteTeam(teamId: string): Observable<string>;
    addTeammatesOnTeam(request: AddTeammateOnTeamRequest): Observable<Teammate[]>;
    removeTeammateFromTeam(request: RemoveTeammateFromTeamRequest): Observable<string>;
    fireTeammate(teammate: Teammate): Observable<string>;
}