import { createSlice } from "@reduxjs/toolkit"
import { Teammate } from "src/core/application/team/dto/Teammate"
import { teammateListInitialState } from "./data/inMemory.store"
import { addTeammate, fireTeammate, setTeammateList } from "./TeammateListReducers"


export interface TeammateListView {
    list: Teammate[]
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
