import { Observable } from "rxjs";
import { Team } from "../dto/Team";
import { Teammate } from "../dto/Teammate";
import { TeamDetailsView } from "src/core/store/team-details/TeamDetailsState";
import { TeammateListView } from "src/core/store/teammate-list/TeammateListState";
import { TeamListView } from "src/core/store/team-list/TeamListState";



export interface TeamQueryHandler {
    getTeamList(accountId: string): Observable<TeamListView>;
    getTeamById(teamId: string): Observable<TeamDetailsView>;
    getTeammateById(teammateId: string): Observable<Teammate>;
    getTeammateList(accountId: string): Observable<TeammateListView>;
}