import { createSlice } from "@reduxjs/toolkit"
import { TodoList } from "src/core/todo/dto/TodoList"
import { addPersonnalTodoList, addTeamTodoList, addTodo, deleteTeamTodoList, setTeamTodoLists } from "./TodoListsReducer"
import { TodoListDetails } from "src/core/todo/dto/TodoListDetails"


export interface TodoListsState {
    personnalLists: TodoList[],
    teamLists: TodoList[],
    details: TodoListDetails | null
}

const initialState: TodoListsState = {
    personnalLists: [],
    teamLists: [],
    details: null
}

export const todoLitsSlice = createSlice({
    name: "todoLists",
    initialState,
    reducers: {
        setTeamTodoLists,
        deleteTeamTodoList,
        addTodo,
        addTeamTodoList,
        addPersonnalTodoList
    },
})