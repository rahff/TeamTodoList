import { createSlice } from "@reduxjs/toolkit"
import { TodoList } from "src/core/application/todo/dto/TodoList"
import { addTodoList, deleteTodoList, setTodoLists } from "./TodoListsReducer"
import { ViewModel } from "../shared/State"


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