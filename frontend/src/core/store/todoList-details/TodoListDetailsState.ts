import { createSlice } from "@reduxjs/toolkit"
import { Todo } from "src/core/application/todo/dto/Todo"
import { addTodo, deleteTodo, doneTodo, setTodoListDetails } from "./TodoLisDetailsReducer"
import { TodoList } from "src/core/application/todo/dto/TodoList"
import { ViewModel } from "../shared/State"

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
