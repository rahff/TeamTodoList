
import { EventWithPayload } from "../shared/Event";
import { TeamDetailsViewModel } from "./TeamDetailsState";
import { addItemOnList, addItemsOnList, assertNotNull, deleteById, filterList } from "../shared/functions";
import { TodoList } from "src/core/model/todo/TodoList";
import { Teammate } from "src/core/model/team/Teammate";



type TeamDetailsReducer<T> = (state: TeamDetailsViewModel, event: EventWithPayload<T>) => TeamDetailsViewModel


export const setTeamDetails: TeamDetailsReducer<TeamDetailsViewModel> = (_, event) => {
    return event.payload;
}

export const deleteTodoList: TeamDetailsReducer<string> = (state, event) => {
    const details = assertNotNull(state.viewModel.details);
    const todoLists = details.todoLists;
    return {
        viewModel: {
            ...state.viewModel,
            details: {
                ...details,
                todoLists: filterList(todoLists, deleteById)(event.payload)
            } 
        }
    }
}

export const addTeammatesOnTeam: TeamDetailsReducer<Teammate[]> = (state, event) => {
    const details = assertNotNull(state.viewModel.details);
    const teammateList = details.teammates;
    const newTeammmates = event.payload;
    return {
        viewModel: {
            ...state.viewModel,
            details: {
                ...details,
                teammates: addItemsOnList(teammateList)(newTeammmates)
            }
        }
    }
}

export const addTodoList: TeamDetailsReducer<TodoList> = (state, event) => {
    const details = assertNotNull(state.viewModel.details)
    const todoLists = details.todoLists;
    return {
        viewModel: {
            ...state.viewModel,
            details: {
                ...details,
                todoLists: addItemOnList(todoLists)(event.payload)
            }
        }
    }
}

export const removeTeammateFromTeam: TeamDetailsReducer<string> = (state, event) => {
    const details = assertNotNull(state.viewModel.details);
    const teammates = details.teammates;
    return {
        viewModel: {
            ...state.viewModel,
            details: {
                ...details,
                teammates: filterList(teammates, deleteById)(event.payload)
            }
        }
    } 
}




