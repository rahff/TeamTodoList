import { createSlice } from "@reduxjs/toolkit"
import { Teammate } from "src/core/application/team/dto/Teammate"
import { addTeammate, fireTeammate, setTeammateList } from "./TeammateListReducers"
import { ViewModel } from "../shared/State"

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
