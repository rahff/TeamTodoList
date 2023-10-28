import { EventWithPayload } from "../shared/Event"
import { AccountDetailsViewModel } from "./AccountDetailsState"


type AccountDetailsReducer<T> = (state: AccountDetailsViewModel, event: EventWithPayload<T>) => AccountDetailsViewModel

export const setAccountDetails: AccountDetailsReducer<AccountDetailsViewModel> = (_, event) => {
    return event.payload;
}