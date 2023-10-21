import { createSlice } from "@reduxjs/toolkit";
import { Team } from "src/core/application/team/dto/Team";
import { addTeam, deleteTeam, setTeamListView } from "./TeamListReducers";



export interface TeamListView {
    list: Team[],
}



export const teamListInitialState: TeamListView = {
    list: [],
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
