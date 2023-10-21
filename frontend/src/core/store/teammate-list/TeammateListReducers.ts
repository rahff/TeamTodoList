import { Teammate } from "src/core/application/team/dto/Teammate";
import { EventWithPayload } from "../shared/Event";
import { TeammateListView } from "./TeammateListState";
import { addItemOnList, deleteById, filterList } from "../shared/functions";


type TeammateListReducer<T> = (state: TeammateListView, event: EventWithPayload<T>) => TeammateListView;

export const setTeammateList: TeammateListReducer<TeammateListView> = (_, event) => {
    return event.payload
}

export const addTeammate: TeammateListReducer<Teammate> = (state, event) => {
    return {
        ...state,
        list: addItemOnList(state.list)(event.payload)
    }
}

export const fireTeammate: TeammateListReducer<string> = (state, event) => {
    return {
        ...state,
        list: filterList(state.list, deleteById)(event.payload)
    }
}

