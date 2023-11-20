import { Observable } from "rxjs";
import { TeamDetailsViewModel } from "src/core/store/team-details/TeamDetailsState";
import { TeammateListViewModel } from "src/core/store/teammate-list/TeammateListState";
import { TeamListViewModel } from "src/core/store/team-list/TeamListState";



export interface TeamQueryHandler {
    getTeamList(accountId: string): Observable<TeamListViewModel>;
    getTeamById(teamId: string): Observable<TeamDetailsViewModel>;
    getTeammateList(accountId: string): Observable<TeammateListViewModel>;
}