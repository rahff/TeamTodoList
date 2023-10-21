import { TodoList } from "src/core/application/todo/dto/TodoList";
import { EventWithPayload } from "../shared/Event";
import { TodoListsView } from "./TodoListsState";
import { addItemOnList, deleteById, filterList } from "../shared/functions";


type TodoListsListReducer<T> = (state: TodoListsView, event: EventWithPayload<T>) => TodoListsView;

export const setTodoLists: TodoListsListReducer<TodoListsView> = (state, event) => {
    return event.payload;
}

export const deleteTodoList: TodoListsListReducer<string> = (state, event) => {
    return {
        ...state,
        lists: filterList(state.lists, deleteById)(event.payload)
    };
}

export const addTodoList: TodoListsListReducer<TodoList> = (state, event) => {
    return {
        ...state,
        lists: addItemOnList(state.lists)(event.payload)
    }
}

