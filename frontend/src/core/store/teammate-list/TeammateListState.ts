import { createSlice } from "@reduxjs/toolkit"
import { addTeammate, fireTeammate, setTeammateList } from "./TeammateListReducers"
import { ViewModel } from "../shared/State"
import { Teammate } from "src/core/model/team/Teammate"


interface TeammateList {list: Teammate[]};

export interface TeammateListViewModel extends ViewModel<TeammateList> {}

export const teammateListInitialState: TeammateListViewModel = {
    viewModel: {
        list: []
    }
}

export const teammateListSlice = createSlice({
    name: "teammateList",
    initialState : {...teammateListInitialState},
    reducers: {
        setTeammateList,
        addTeammate,
        fireTeammate
    },
})
