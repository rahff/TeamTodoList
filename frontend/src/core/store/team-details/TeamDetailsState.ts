import { createSlice } from "@reduxjs/toolkit";
import { addTeammatesOnTeam, addTodoList, deleteTodoList, removeTeammateFromTeam, setTeamDetails } from "./TeamDetailsReducer";
import { ViewModel } from "../shared/State";
import { Team } from "src/core/model/team/Team";
import { Teammate } from "src/core/model/team/Teammate";

interface TeamDetails {
    details: Team | null,
    availableTeammates: Teammate[]
}

export interface TeamDetailsViewModel extends ViewModel<TeamDetails> {}

export const teamDetailsInitialState: TeamDetailsViewModel = {
    viewModel: {
        details: null,
        availableTeammates: []
    }
}


export const teamDetailsSlice = createSlice({
    name: "teamDetails",
    initialState : {...teamDetailsInitialState},
    reducers: {
        deleteTodoList,
        setTeamDetails,
        addTodoList,
        removeTeammateFromTeam,
        addTeammatesOnTeam
    },
})
