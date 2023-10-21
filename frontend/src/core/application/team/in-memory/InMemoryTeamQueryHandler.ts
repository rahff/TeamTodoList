import { Observable, Subscriber, observable } from "rxjs";
import { Team } from "../dto/Team";
import { Teammate } from "../dto/Teammate";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";
import { team2, teamList, teammateList } from "./data/team.data";
import { TeamDetailsView } from "src/core/store/team-details/TeamDetailsState";
import { TeammateListView } from "src/core/store/teammate-list/TeammateListState";
import { TeamListView } from "src/core/store/team-list/TeamListState";

export class InMemoryTeamQueryHandler implements TeamQueryHandler {

    
    getTeammateList(accountId: string): Observable<TeammateListView> {
        return new Observable<TeammateListView>((observable) => {
            setTimeout(() => {
                observable.next({list: teammateList});
            }, 500);
        });
    }

    getTeamList(accountId: string): Observable<TeamListView> {
       
        return new Observable<TeamListView>((observable) => {
            setTimeout(() => {
                observable.next({list: teamList});
            }, 500);
        });
       
    }
    getTeamById(teamId: string): Observable<TeamDetailsView> {
        return new Observable<TeamDetailsView>((observable) => {
            setTimeout(() => {
                observable.next({details: team2});
            }, 500);
        });
    }
    
    getTeammateById(teammateId: string): Observable<Teammate> {
        throw new Error("Method not implemented.");
    }

}