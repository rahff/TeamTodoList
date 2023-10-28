import { BehaviorSubject, Observable, map } from "rxjs";
import { StoreApi } from "../shared/StoreApi";
import { AccountDetails, AccountDetailsViewModel, accountDetailsInitialState } from "./AccountDetailsState";
import { Store } from "../Store";



export class AccountDetailsStoreApi extends StoreApi {

    private teamDetails = new BehaviorSubject<AccountDetailsViewModel>(accountDetailsInitialState);

    public constructor(store: Store){
        super(store)
    }

    protected emitNewState(): void {
        this.teamDetails.next(this.store.getState().accountDetails);
    }

    public getAccountDetails(): Observable<AccountDetails> {
        return this.teamDetails.asObservable()
        .pipe(map((view) => view.viewModel));
    }
}