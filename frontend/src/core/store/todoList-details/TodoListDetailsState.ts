import { createSlice } from "@reduxjs/toolkit"
import { addTodo, deleteTodo, doneTodo, setTodoListDetails } from "./TodoLisDetailsReducer"
import { ViewModel } from "../shared/State"
import { Todo } from "src/core/model/todo/Todo"
import { TodoList } from "src/core/model/todo/TodoList"




export type TodoListDetails = {
    info: TodoList | null,
    todos: Todo[],
}


export interface TodoListDetailsViewModel extends ViewModel<TodoListDetails> {}

export const todoListDetailsInitialState: TodoListDetailsViewModel = {
    viewModel: {
        info: null,
        todos: []
    }
}

export const todoListDetailsSlice = createSlice({
    name: "todoListDetails",
    initialState : {...todoListDetailsInitialState},
    reducers: {
        setTodoListDetails,
        addTodo,
        deleteTodo,
        doneTodo
    },
})
