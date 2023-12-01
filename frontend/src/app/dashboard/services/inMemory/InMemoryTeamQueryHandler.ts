import { Observable } from "rxjs";
import { Teammate } from "../../../../core/model/team/Teammate";
import { TeamQueryHandler } from "../../../../core/application/team/spi/TeamQueryHandler";
import { availableTeammates, team2, teamCardList, teammateList } from "../../../../core/application/team/in-memory/data/team.data";
import { TeamDetailsViewModel } from "src/core/store/team-details/TeamDetailsState";
import { TeammateListViewModel } from "src/core/store/teammate-list/TeammateListState";
import { TeamListViewModel } from "src/core/store/team-list/TeamListState";
import { Injectable } from "@angular/core";



@Injectable({
    providedIn: "root"
})
export class InMemoryTeamQueryHandler implements TeamQueryHandler {

    
    getTeammateList(accountId: string): Observable<TeammateListViewModel> {
        return new Observable<TeammateListViewModel>((observable) => {
            setTimeout(() => {
                observable.next({viewModel:{list: teammateList}});
            }, 500);
        });
    }

    getTeamList(accountId: string): Observable<TeamListViewModel> {
       
        return new Observable<TeamListViewModel>((observable) => {
            setTimeout(() => {
                observable.next({viewModel: {list: teamCardList, availableTeammates}});
            }, 500);
        });
       
    }
    getTeamById(teamId: string): Observable<TeamDetailsViewModel> {
        return new Observable<TeamDetailsViewModel>((observable) => {
            setTimeout(() => {
                observable.next({viewModel:{details: team2, availableTeammates: availableTeammates}});
            }, 500);
        });
    }
    
    getTeammateById(teammateId: string): Observable<Teammate> {
        throw new Error("Method not implemented.");
    }

}