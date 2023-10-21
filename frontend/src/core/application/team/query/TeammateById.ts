import { Observable, catchError, map } from "rxjs";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";
import { Teammate } from "../dto/Teammate";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";

export class TeammateById extends Query<Teammate> {

    public constructor(private queryHandler: TeamQueryHandler){super()}

    public query(teammateId: string): Observable<Result<Teammate>> {
        return this.queryHandler.getTeammateById(teammateId)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}