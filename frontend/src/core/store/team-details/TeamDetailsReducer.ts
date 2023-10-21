
import { TodoList } from "src/core/application/todo/dto/TodoList";
import { EventWithPayload } from "../shared/Event";
import { TeamDetailsView } from "./TeamDetailsState";
import { addItemOnList, assertNotNull, deleteById, filterList } from "../shared/functions";


type TeamDetailsReducer<T> = (state: TeamDetailsView, event: EventWithPayload<T>) => TeamDetailsView

export const deleteTodoList: TeamDetailsReducer<string> = (state, event) => {
    const details = assertNotNull(state.details);
    const todoLists = details.todoLists;
    return {
        ...state,
        details: {
            ...details,
            todoLists: filterList(todoLists, deleteById)(event.payload)
        } 
    } as TeamDetailsView
}


export const setTeamDetails: TeamDetailsReducer<TeamDetailsView> = (_, event) => {
    return event.payload;
}

export const addTodoList: TeamDetailsReducer<TodoList> = (state, event) => {
    const details = assertNotNull(state.details)
    const todoLists = details.todoLists;
    return {
        ...state,
        details: {
            ...details,
            todoLists: addItemOnList(todoLists)(event.payload)
        }
    }
}

export const removeTeammateFromTeam: TeamDetailsReducer<string> = (state, event) => {
    const details = assertNotNull(state.details);
    const teammates = details.teammates;
    return {
        ...state,
        details: {
            ...details,
            teammates: filterList(teammates, deleteById)(event.payload)
        }
    } 
}




