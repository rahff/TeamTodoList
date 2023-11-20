import { AccountDetailsViewModel } from "src/core/store/account-details/AccountDetailsState";
import { Query } from "../../shared/query/Query";
import { AccountQueryHandler } from "../spi/AccountQueryHandler";
import { Observable, catchError, map } from "rxjs";
import { Result } from "../../shared/dto/Result";
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";
import { UnAuthenticatedException } from "../../shared/execptions/UnAuthenticatedException";



export class UserAccount extends Query<AccountDetailsViewModel> {

    public constructor(private queryHandler: AccountQueryHandler,
                       private userContextHolder: UserContextHolder){super()}

    public query(): Observable<Result<AccountDetailsViewModel>> {
        const accountId = this.userContextHolder.getAccountId();
        if(!accountId) return this.onError(new UnAuthenticatedException());
        return this.queryHandler.getUserAccountView(accountId)
        .pipe(map(this.onSuccess),
        catchError(this.onError))
    }
}