import { createSlice } from "@reduxjs/toolkit";
import { Team } from "src/core/application/team/dto/Team";
import { addTeammatesOnTeam, addTodoList, deleteTodoList, removeTeammateFromTeam, setTeamDetails } from "./TeamDetailsReducer";
import { Teammate } from "src/core/application/team/dto/Teammate";
import { ViewModel } from "../shared/State";

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
