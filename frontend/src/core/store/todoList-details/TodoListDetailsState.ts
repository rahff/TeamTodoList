import { createSlice } from "@reduxjs/toolkit"
import { Todo } from "src/core/application/todo/dto/Todo"
import { todoListDetailsInitialState } from "./data/inMemory.store"
import { addTodo, deleteTodo, doneTodo, setTodoListDetails } from "./TodoLisDetailsReducer"
import { TodoList } from "src/core/application/todo/dto/TodoList"

export type TodoListDetails = {
    info: TodoList | null,
    todos: Todo[],
}


export interface TodoListDetailsView {
    details: TodoListDetails
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
