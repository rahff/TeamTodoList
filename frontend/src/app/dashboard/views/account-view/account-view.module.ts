import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountViewRoutingModule } from './account-view-routing.module';
import { UserAccount } from 'src/core/application/account/query/UserAccount';
import { AccountQueryHandler } from 'src/core/application/account/spi/AccountQueryHandler';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryAccountQueryHandler } from '../../services/inMemory/InMemoryAccountQueryHandler';
import { FakeUserContextHolder } from '../../services/inMemory/FakeUserContextHolder';
import { AccountDetailsStoreApi } from 'src/core/store/account-details/AccountDetailsStoreApi';
import { store } from 'src/core/store/Store';
import {UserAccountDependencyProvider} from "./dependencyProvider";
import {environment} from "../../../../environments/environment";



@NgModule({
  declarations: [
    ...AccountViewRoutingModule.viewComponents
  ],
  imports: [
    CommonModule,
    AccountViewRoutingModule
  ],
  providers: [
    {
      provide: AccountDetailsStoreApi, useFactory: () => new AccountDetailsStoreApi(store)
    },
    {
      provide: UserAccount, useFactory: (q: AccountQueryHandler, u: UserContextHolder) => new UserAccount(q, u),
      deps: UserAccountDependencyProvider(environment.dataSource)
    }
  ]
})
export class AccountViewModule { }
