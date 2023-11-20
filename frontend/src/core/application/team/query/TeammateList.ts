import { Observable, catchError, map } from "rxjs";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";
import { TeamQueryHandler } from "../spi/TeamQueryHandler";
import { Query } from "../../shared/query/Query";
import { Result } from "../../shared/dto/Result";
import { TeammateListViewModel } from "src/core/store/teammate-list/TeammateListState";
import { UnAuthenticatedException } from "../../shared/execptions/UnAuthenticatedException";



export class TeammateList extends Query<TeammateListViewModel> {

    public constructor(private queryHandler: TeamQueryHandler, 
                       private userContextHolder: UserContextHolder){super()}


    public query(): Observable<Result<TeammateListViewModel>> {
        const accountId =  this.userContextHolder.getAccountId();
        if(!accountId) return this.onError(new UnAuthenticatedException());
        return this.queryHandler.getTeammateList(accountId)
        .pipe(map(this.onSuccess),
        catchError(this.onError));
    }
}