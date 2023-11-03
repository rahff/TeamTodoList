
import { EventWithPayload } from "../shared/Event";
import { TeammateListViewModel } from "./TeammateListState";
import { addItemOnList, deleteById, filterList } from "../shared/functions";
import { Teammate } from "src/core/model/team/Teammate";


type TeammateListReducer<T> = (state: TeammateListViewModel, event: EventWithPayload<T>) => TeammateListViewModel;

export const setTeammateList: TeammateListReducer<TeammateListViewModel> = (_, event) => {
    return event.payload
}

export const addTeammate: TeammateListReducer<Teammate> = (state, event) => {
    const list = state.viewModel.list
    return {
        viewModel: {
            ...state.viewModel,
            list: addItemOnList(list)(event.payload)
        }
    }
}

export const fireTeammate: TeammateListReducer<string> = (state, event) => {
    const list = state.viewModel.list
    return {
        viewModel: {
            ...state.viewModel,
            list: filterList(list, deleteById)(event.payload)
        }
    }
}

