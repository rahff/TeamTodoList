import { createSlice } from "@reduxjs/toolkit";
import { Team } from "src/core/application/team/dto/Team";
import { addTeam, deleteTeam, setTeamListView } from "./TeamListReducers";
import { ViewModel } from "../shared/State";


interface TeamList {list: Team[]}
export interface TeamListViewModel extends ViewModel<TeamList> {}


export const teamListInitialState: TeamListViewModel = {
    viewModel: {
        list: []
    }
}

export const teamListSlice = createSlice({
    name: "teamList",
    initialState : teamListInitialState,
    reducers: {
        setTeamListView,
        addTeam,
        deleteTeam
    },
})
