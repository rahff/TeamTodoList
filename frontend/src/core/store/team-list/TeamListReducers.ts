import { Team } from "src/core/application/team/dto/Team";
import { EventWithPayload } from "../shared/Event";
import { TeamListView } from "./TeamListState";
import { addItemOnList, deleteById, filterList } from "../shared/functions";



type TeamReducer<T> = (state: TeamListView, event: EventWithPayload<T>) => TeamListView;

export const setTeamListView: TeamReducer<TeamListView> = (_, event) => {
    return event.payload;
}

export const addTeam: TeamReducer<Team> = (state, event) => {
    return {
        ...state,
        list: addItemOnList(state.list)(event.payload)
    };
}

export const deleteTeam: TeamReducer<string> = (state, event) => {
    return {
        ...state,
        list: filterList(state.list, deleteById)(event.payload)
    };
}
