
import { EventWithPayload } from "../shared/Event";
import { TeamListViewModel } from "./TeamListState";
import { addItemOnList, deleteById, filterList } from "../shared/functions";
import { Team } from "src/core/model/team/Team";
import { TeamCard } from "src/core/model/team/TeamCard";



type TeamReducer<T> = (state: TeamListViewModel, event: EventWithPayload<T>) => TeamListViewModel;

export const setTeamListView: TeamReducer<TeamListViewModel> = (_, event) => {
    return event.payload;
}

export const addTeam: TeamReducer<TeamCard> = (state, event) => {
    const list = state.viewModel.list;
    return {
        viewModel: {
            ...state.viewModel,
            list: addItemOnList(list)(event.payload)
        }
    };
}

export const deleteTeam: TeamReducer<string> = (state, event) => {
    const list = state.viewModel.list;
    return {
        viewModel: {
            ...state.viewModel,
            list: filterList(list, deleteById)(event.payload)

        }
    };
}
