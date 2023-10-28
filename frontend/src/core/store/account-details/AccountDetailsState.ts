import { createSlice } from "@reduxjs/toolkit"
import { ViewModel } from "../shared/State"
import { User } from "src/core/application/security/dto/User"
import { setAccountDetails } from "./AccountDetailsReducers"


export interface AccountDetails {
   user: User | null
}

export interface AccountDetailsViewModel extends ViewModel<AccountDetails> {}

export const accountDetailsInitialState: AccountDetailsViewModel = {
    viewModel: {
       user: null
    }
}


export const accountDetailsSlice = createSlice({
    name: "accountDetails",
    initialState : {...accountDetailsInitialState},
    reducers: {
        setAccountDetails
    },
})
