import { createSlice } from "@reduxjs/toolkit";
import { Team } from "src/core/application/team/dto/Team";
import { teamDetailsInitialState } from "./data/inMemory.store";
import { addTodoList, deleteTodoList, removeTeammateFromTeam, setTeamDetails } from "./TeamDetailsReducer";


export interface TeamDetailsView {
    details: Team | null
}


export const teamDetailsSlice = createSlice({
    name: "teamDetails",
    initialState : {...teamDetailsInitialState},
    reducers: {
        deleteTodoList,
        setTeamDetails,
        addTodoList,
        removeTeammateFromTeam
    },
})
