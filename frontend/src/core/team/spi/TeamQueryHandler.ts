import { Observable } from "rxjs";
import { Team } from "../dto/Team";
import { Teammate } from "../dto/Teammate";



export interface TeamQueryHandler {
    getTeamList(accountId: string): Observable<Team[]>;
    getTeamById(teamId: string): Observable<Team>;
    getTeammateById(teammateId: string): Observable<Teammate>;
}