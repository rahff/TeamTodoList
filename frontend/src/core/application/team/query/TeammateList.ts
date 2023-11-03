import { Observable, catchError, map } from "rxjs";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";
import { Teammate } from "../../../model/team/Teammate";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";
import { TeammateListViewModel } from "src/core/store/teammate-list/TeammateListState";



export class TeammateList extends Query<TeammateListViewModel> {

    public constructor(private queryHandler: TeamQueryHandler, 
                       private userContextHolder: UserContextHolder){super()}


    public query(): Observable<Result<TeammateListViewModel>> {
        const accountId =  this.userContextHolder.getAccountId()
        return this.queryHandler.getTeammateList(accountId)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}