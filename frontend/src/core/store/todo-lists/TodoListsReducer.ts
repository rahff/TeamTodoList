import { EventWithPayload } from "../shared/Event";
import { TodoListsViewModel } from "./TodoListsState";
import { addItemOnList, deleteById, filterList } from "../shared/functions";
import { TodoList } from "src/core/model/todo/TodoList";



type TodoListsListReducer<T> = (state: TodoListsViewModel, event: EventWithPayload<T>) => TodoListsViewModel;

export const setTodoLists: TodoListsListReducer<TodoListsViewModel> = (_, event) => {
    return event.payload;
}

export const deleteTodoList: TodoListsListReducer<string> = (state, event) => {
    const lists = state.viewModel.lists;
    return {
        viewModel:{
            ...state.viewModel,
            lists: filterList(lists, deleteById)(event.payload)
        },
    };
}

export const addTodoList: TodoListsListReducer<TodoList> = (state, event) => {
    const lists = state.viewModel.lists
    return {
        viewModel: {
            ...state.viewModel,
            lists: addItemOnList(lists)(event.payload)
        }
    }
}

