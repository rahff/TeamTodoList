import { Observable } from "rxjs";
import { AccountDetailsViewModel } from "src/core/store/account-details/AccountDetailsState";

export interface AccountQueryHandler {
    getUserAccountView(userId: string): Observable<AccountDetailsViewModel>
}