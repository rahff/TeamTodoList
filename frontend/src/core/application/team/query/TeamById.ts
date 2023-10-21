import { Observable, catchError, map } from "rxjs";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";
import { TeamDetailsView } from "src/core/store/team-details/TeamDetailsState";



export class TeamById extends Query<TeamDetailsView> {

    public constructor(private queryHandler: TeamQueryHandler){super()}

    public query(teamId: string): Observable<Result<TeamDetailsView>> {
        return this.queryHandler.getTeamById(teamId)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}