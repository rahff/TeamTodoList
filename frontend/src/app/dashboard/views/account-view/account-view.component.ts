import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { UserAccount } from 'src/core/application/account/query/UserAccount';
import { Result } from 'src/core/application/shared/dto/Result';
import { AccountDetails, AccountDetailsViewModel } from 'src/core/store/account-details/AccountDetailsState';
import { AccountDetailsStoreApi } from 'src/core/store/account-details/AccountDetailsStoreApi';
import { accountDetailsReceivedEvent } from 'src/core/store/account-details/Events';

@Component({
  selector: 'app-account',
  templateUrl: './account-view.component.html',
  styleUrls: ['./account-view.component.css']
})
export class AccountViewComponent implements OnInit, OnDestroy {

  public userAccount$!: Observable<AccountDetails>;
  private subscription!: Subscription;

  constructor(private storeApi: AccountDetailsStoreApi,
              private userAccount: UserAccount) { }

  public ngOnInit(): void {
    this.userAccount$ = this.storeApi.getAccountDetails();
    this.subscription = this.userAccount.query().subscribe({
      next: this.onQueryResult.bind(this)
    })
  }

  private onQueryResult(result: Result<AccountDetailsViewModel>): void {
    if(result.isOk()) 
      this.storeApi.fireEvent(accountDetailsReceivedEvent(result.getValue()));
  }

  public ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
