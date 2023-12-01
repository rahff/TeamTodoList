import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AccountQueryHandler } from "src/core/application/account/spi/AccountQueryHandler";
import { AccountDetailsViewModel } from "src/core/store/account-details/AccountDetailsState";
import { accountDetailsFakeState } from "src/core/store/account-details/data/inMemory.store";



@Injectable({
    providedIn: "root"
})
export class InMemoryAccountQueryHandler implements AccountQueryHandler {

    public getUserAccountView(userId: string): Observable<AccountDetailsViewModel> {
        return new Observable((observable) => {
            setTimeout(() => {
                observable.next(accountDetailsFakeState);
            }, 500);
        })
    }
}