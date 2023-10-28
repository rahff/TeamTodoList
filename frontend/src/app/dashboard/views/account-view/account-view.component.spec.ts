import { accountDetailsFakeState } from 'src/core/store/account-details/data/inMemory.store';
import { AccountViewComponent } from './account-view.component';
import { AccountDetailsStoreApi } from 'src/core/store/account-details/AccountDetailsStoreApi';
import { UserAccount } from 'src/core/application/account/query/UserAccount';
import { createStore } from 'src/core/store/Store';
import { GLOBAL_INITIAL_STATE } from 'src/core/store/shared/inMemory.store';
import { InMemoryAccountQueryHandler } from '../../services/inMemory/InMemoryAccountQueryHandler';
import { FakeUserContextHolder } from '../../services/inMemory/FakeUserContextHolder';
import { fakeAsync } from '@angular/core/testing';
import { ngOnInitPast500Ms } from '../../root/views/utils/tests.utils';



describe('AccountViewComponent', () => {
  let component: AccountViewComponent;
  let storeApi: AccountDetailsStoreApi;
  let query: UserAccount
  beforeEach(() => {
    storeApi = new AccountDetailsStoreApi(createStore(GLOBAL_INITIAL_STATE));
    query = new UserAccount(new InMemoryAccountQueryHandler(), new FakeUserContextHolder());
    component = new AccountViewComponent(storeApi, query);
  });

  it('should load the user account', fakeAsync(() => {
    ngOnInitPast500Ms(component);
    component.userAccount$.subscribe((userAccount) => {
      expect(userAccount).toEqual(accountDetailsFakeState.viewModel)
    })
  }));
});
