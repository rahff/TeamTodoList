import {Injectable} from "@angular/core";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {AccountQueryHandler} from "../../../core/application/account/spi/AccountQueryHandler";
import { Observable } from "rxjs";
import { AccountDetailsViewModel } from "src/core/store/account-details/AccountDetailsState";


@Injectable({
    providedIn: 'root'
})
export class AccountQueryService implements AccountQueryHandler {

    private baseServerUrl: string = environment.serverUrl;

    constructor(private http: HttpClient) {
    }

    getUserAccountView(accountId: string): Observable<AccountDetailsViewModel> {
        return this.http.get<AccountDetailsViewModel>(this.baseServerUrl+`/account/${accountId}`)
    }

}