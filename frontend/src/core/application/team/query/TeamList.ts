import { Observable, catchError, map } from "rxjs";
import { Team } from "../../../model/team/Team";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";
import { UserContextHolder } from "src/core/application/shared/interfaces/UserContextHolder";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";
import { TeamListViewModel } from "src/core/store/team-list/TeamListState";



export class TeamList extends Query<TeamListViewModel>{

    public constructor(private queryHandler: TeamQueryHandler, 
                       private userContextHolder: UserContextHolder){super()}

    public query(): Observable<Result<TeamListViewModel>> {
        const accountId = this.userContextHolder.getAccountId();
        return this.queryHandler.getTeamList(accountId)
        .pipe(map(this.onSuccess),
         catchError(this.onError));
    }
}