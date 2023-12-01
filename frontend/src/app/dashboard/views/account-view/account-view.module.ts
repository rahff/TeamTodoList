import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountViewRoutingModule } from './account-view-routing.module';
import { UserAccount } from 'src/core/application/account/query/UserAccount';
import { AccountQueryHandler } from 'src/core/application/account/spi/AccountQueryHandler';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { AccountDetailsStoreApi } from 'src/core/store/account-details/AccountDetailsStoreApi';
import { store } from 'src/core/store/Store';
import {ChangePasswordDependencyProvider, UserAccountDependencyProvider} from "./dependencyProvider";
import {environment} from "../../../../environments/environment";
import { ChangePasswordFormComponent } from './components/change-password-form/change-password-form.component';
import {AccountCommandHandler} from "../../../../core/application/account/spi/AccountCommandHandler";
import {ChangePassword} from "../../../../core/application/account/command/ChangePassword";



@NgModule({
  declarations: [
    ...AccountViewRoutingModule.viewComponents,
    ChangePasswordFormComponent
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
    },
    {
      provide: ChangePassword, useFactory: (c: AccountCommandHandler, u: UserContextHolder) => new ChangePassword(c, u),
      deps: ChangePasswordDependencyProvider(environment.dataSource)
    }
  ]
})
export class AccountViewModule { }
