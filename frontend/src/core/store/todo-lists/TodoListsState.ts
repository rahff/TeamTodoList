import { createSlice } from "@reduxjs/toolkit"
import { TodoList } from "src/core/application/todo/dto/TodoList"
import { todoListsInitialState } from "./data/inMemory.store"
import { addTodoList, deleteTodoList, setTodoLists } from "./TodoListsReducer"


export interface TodoListsView {
    lists: TodoList[]
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