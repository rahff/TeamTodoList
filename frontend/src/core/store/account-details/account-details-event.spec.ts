import { createStore } from "../Store"
import { GLOBAL_INITIAL_STATE } from "../shared/inMemory.store"
import { AccountDetailsStoreApi } from "./AccountDetailsStoreApi";
import { accountDetailsReceivedEvent } from "./Events";
import { accountDetailsFakeState } from "./data/inMemory.store";

describe("AccountDetailsViewSlice", () => {

    it("when account details received event fired", () => {
        const store = createStore({...GLOBAL_INITIAL_STATE});
        const storeApi =  new AccountDetailsStoreApi(store);
        const event = accountDetailsReceivedEvent(accountDetailsFakeState);
        storeApi.fireEvent(event);
        storeApi.getAccountDetails().subscribe((viewModel) => {
            expect(viewModel).toEqual(accountDetailsFakeState.viewModel);
        })
    })
})