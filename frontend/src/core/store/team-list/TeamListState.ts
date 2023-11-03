import { createSlice } from "@reduxjs/toolkit";
import { addTeam, deleteTeam, setTeamListView } from "./TeamListReducers";
import { ViewModel } from "../shared/State";
import { TeamCard } from "src/core/model/team/TeamCard";


interface TeamList {list: TeamCard[]}
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
