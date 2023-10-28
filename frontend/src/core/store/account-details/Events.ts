import { PrepareAction, createAction } from "@reduxjs/toolkit";
import { AccountDetailsViewModel } from "./AccountDetailsState";



const  setAccountDetailsPrepareAction: PrepareAction<AccountDetailsViewModel> = (payload: AccountDetailsViewModel) =>({payload});
export const accountDetailsReceivedEvent = createAction("accountDetails/setAccountDetails", setAccountDetailsPrepareAction);