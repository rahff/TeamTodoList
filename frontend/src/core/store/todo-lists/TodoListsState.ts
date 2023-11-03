import { createSlice } from "@reduxjs/toolkit"
import { addTodoList, deleteTodoList, setTodoLists } from "./TodoListsReducer"
import { ViewModel } from "../shared/State"
import { TodoList } from "src/core/model/todo/TodoList"



interface ListOfTodoList {lists: TodoList[]};
export interface TodoListsViewModel extends ViewModel<ListOfTodoList> {}

export const todoListsInitialState: TodoListsViewModel = {
    viewModel: {
        lists: []
    }
}

export const todoLitsSlice = createSlice({
    name: "todoLists",
    initialState: {...todoListsInitialState},
    reducers: {
        setTodoLists,
        addTodoList,
        deleteTodoList
    }
})